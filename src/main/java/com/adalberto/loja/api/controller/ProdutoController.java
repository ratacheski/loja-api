package com.adalberto.loja.api.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.adalberto.loja.api.model.ProdutoRepresentationModel;
import com.adalberto.loja.api.model.input.ProdutoInput;
import com.adalberto.loja.domain.exceptions.RegraDeNegocioException;
import com.adalberto.loja.domain.model.Produto;
import com.adalberto.loja.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<ProdutoRepresentationModel> listar(@RequestParam(name = "nome", required = false) String nome) {

		List<Produto> produtos = new ArrayList<Produto>();
		if (nome == null) {
			produtos = repository.findAll();
		} else {
			produtos = repository.findAllByNomeContaining(nome);
		}

		System.out.println(nome);
		return produtos.stream().map(produto -> mapper.map(produto, ProdutoRepresentationModel.class))
				.collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoRepresentationModel cadastrar(@Valid @RequestBody ProdutoInput produtoInput) {

		Produto produto = mapper.map(produtoInput, Produto.class);
		Produto produtoSalvo = repository.save(produto);
		return mapper.map(produtoSalvo, ProdutoRepresentationModel.class);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {

		boolean produtoExistente = repository.existsById(id);
		if (produtoExistente) {
			try {
				repository.deleteById(id);
				return ResponseEntity.noContent().build();
			} catch (Exception ex) {
				if (ex.getClass().equals(DataIntegrityViolationException.class))
					throw new RegraDeNegocioException(
							"Este produto não pode ser excluido pois está vinculado a um pedido");

			}
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Integer id, @RequestBody Produto produto) {

		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		produto.setId(id);
		produto = repository.save(produto);
		return ResponseEntity.ok(produto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoRepresentationModel> buscar(@PathVariable Integer id) {
		Optional<Produto> Produto = repository.findById(id);
		if (!Produto.isEmpty()) {
			return ResponseEntity.ok(mapper.map(Produto.get(), ProdutoRepresentationModel.class));
		}
		return ResponseEntity.notFound().build();
	}
}
