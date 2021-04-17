package com.adalberto.loja.api.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adalberto.loja.api.model.FullPedidoRepresentationModel;
import com.adalberto.loja.api.model.input.ItemPedidoInput;
import com.adalberto.loja.domain.exceptions.RegraDeNegocioException;
import com.adalberto.loja.domain.model.ItemPedido;
import com.adalberto.loja.domain.model.Pedido;
import com.adalberto.loja.domain.model.Produto;
import com.adalberto.loja.domain.model.keys.ItemPedidoPk;
import com.adalberto.loja.domain.repository.ItemPedidoRepository;
import com.adalberto.loja.domain.repository.PedidoRepository;
import com.adalberto.loja.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("/pedidos/{pedidoId}/itens")
public class ItemPedidoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<FullPedidoRepresentationModel> adicionarItem(@PathVariable Integer pedidoId,
			@RequestBody ItemPedidoInput input) {

		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		if (!pedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Produto> produto = produtoRepository.findById(input.getIdProduto());
		if (!produto.isPresent()) {
			throw new RegraDeNegocioException("Produto não existente");
		}
		// fazer uma 3º verificação se já existe este produto adicionado no pedido, se
		// existir somar a quantidade a qutdade existente, se não é só criar.
		// usar itemPedidorepository
		ItemPedidoPk pk = new ItemPedidoPk(pedidoId, input.getIdProduto());
		Optional<ItemPedido> itemPedidoExistente = itemPedidoRepository.findById(pk);
		ItemPedido itemPedidoSalvo = new ItemPedido();
		if (itemPedidoExistente.isPresent()) {
			int quantidadeExistente = itemPedidoExistente.get().getQuantidade();
			int novaQuantidade = quantidadeExistente + input.getQuantidade();
			itemPedidoExistente.get().setQuantidade(novaQuantidade);
			itemPedidoSalvo = itemPedidoRepository.save(itemPedidoExistente.get());
		} else {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setId(pk);
			itemPedido.setPedido(pedido.get()); 
			itemPedido.setProduto(produto.get());
			itemPedido.setQuantidade(input.getQuantidade());
			itemPedido.setValorUnitario(produto.get().getPreco());
			itemPedidoSalvo = itemPedidoRepository.save(itemPedido);
		}

		FullPedidoRepresentationModel fullPedidoRepresentationModel = mapper.map(itemPedidoSalvo.getPedido(),
				FullPedidoRepresentationModel.class);
		return ResponseEntity.ok(fullPedidoRepresentationModel);

	}
	
	@DeleteMapping("/{produtoId}")
	public ResponseEntity<Void>excluirItem(@PathVariable Integer pedidoId, @PathVariable Integer produtoId){
		
		
		Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
		if (!pedido.isPresent()) {
			throw new RegraDeNegocioException("Esse pedido não existe");
		}
		Optional<Produto> produto = produtoRepository.findById(produtoId);
		if (!produto.isPresent()) {
			throw new RegraDeNegocioException("Produto não existente");
		}
		ItemPedidoPk pk = new ItemPedidoPk(pedidoId, produtoId);
		Optional<ItemPedido> produtoExistente = itemPedidoRepository.findById(pk);
		if(produtoExistente.isPresent()) {
			itemPedidoRepository.deleteById(pk);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
