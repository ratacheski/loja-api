package com.adalberto.loja.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.adalberto.loja.api.model.ClienteRepresentationModel;
import com.adalberto.loja.api.model.input.ClienteInput;
import com.adalberto.loja.domain.exceptions.RegraDeNegocioException;
import com.adalberto.loja.domain.model.Cliente;
import com.adalberto.loja.domain.repository.ClienteRepository;
import com.adalberto.loja.domain.services.ValidationService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<ClienteRepresentationModel> listar(@RequestParam(name = "nome", required = false) String nome) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		if (nome == null) {
			clientes = repository.findAll();
		} else {
			clientes = repository.findAllByNomeContaining(nome);
		}

		System.out.println(nome);
		return clientes.stream().map(cliente -> mapper.map(cliente, ClienteRepresentationModel.class))
				.collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteRepresentationModel cadastrar(@Valid @RequestBody ClienteInput clienteInput) {

		if(!validationService.isCPF(clienteInput.getCpf())) {
			throw new RegraDeNegocioException("Cpf inválido");
		}
		Cliente cliente = mapper.map(clienteInput, Cliente.class);
		Cliente clienteSalvo = repository.save(cliente);
		return mapper.map(clienteSalvo, ClienteRepresentationModel.class);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {

		boolean clienteExistente = repository.existsById(id);
		if (clienteExistente) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {

		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		cliente = repository.save(cliente);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteRepresentationModel> buscar(@PathVariable Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (!cliente.isEmpty()) {
			return ResponseEntity.ok(mapper.map(cliente.get(), ClienteRepresentationModel.class));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ClienteRepresentationModel> buscarPorCpf(@PathVariable String cpf) {
	
		if(!validationService.isCPF(cpf)) {
			throw new RegraDeNegocioException("Cpf inválido");
		}
		Optional<Cliente> cliente = repository.findByCpf(cpf);
		if (!cliente.isEmpty()) {
			return ResponseEntity.ok(mapper.map(cliente.get(), ClienteRepresentationModel.class));
		}
		return ResponseEntity.notFound().build();
	}

}
