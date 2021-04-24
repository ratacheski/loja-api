package com.adalberto.loja.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adalberto.loja.api.model.FullPedidoRepresentationModel;
import com.adalberto.loja.api.model.PedidoRepresentationModel;
import com.adalberto.loja.api.model.input.PagamentoInput;
import com.adalberto.loja.api.model.input.PedidoInsertInput;
import com.adalberto.loja.domain.exceptions.RegraDeNegocioException;
import com.adalberto.loja.domain.model.Cliente;
import com.adalberto.loja.domain.model.Pedido;
import com.adalberto.loja.domain.model.enums.FormaPagamento;
import com.adalberto.loja.domain.repository.ClienteRepository;
import com.adalberto.loja.domain.repository.PedidoRepository;
import com.adalberto.loja.domain.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoRepresentationModel cadastrar(@RequestBody PedidoInsertInput input) {

		Optional<Cliente> cliente = clienteRepository.findById(input.getIdCliente());
		if (cliente.isEmpty()) {
			throw new RegraDeNegocioException("Cliente não encontrado.");
		}
		return modelMapper.map(pedidoService.criarPedido(cliente.get()), PedidoRepresentationModel.class);

	}

	@GetMapping
	public List<PedidoRepresentationModel> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos.stream().map(pedido -> modelMapper.map(pedido, PedidoRepresentationModel.class))
				.collect(Collectors.toList());
	}

	@PutMapping("/{id}/finalizar")
	public PedidoRepresentationModel finalizar(@PathVariable Integer id) {
		Pedido pedidoFinalizado = pedidoService.finalizarPedido(id);

		return modelMapper.map(pedidoFinalizado, PedidoRepresentationModel.class);
	}

	@PutMapping("/{id}/entregue")
	public PedidoRepresentationModel pedidoEntregue(@PathVariable Integer id) {
		Pedido pedidoEntregue = pedidoService.pedidoEntregue(id);

		return modelMapper.map(pedidoEntregue, PedidoRepresentationModel.class);
	}

	@PutMapping("/{id}/cancelar")
	public PedidoRepresentationModel cancelar(@PathVariable Integer id) {
		Pedido pedidoCancelado = pedidoService.cancelarPedido(id);

		return modelMapper.map(pedidoCancelado, PedidoRepresentationModel.class);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FullPedidoRepresentationModel> obterPorId(@PathVariable Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (!pedido.isEmpty()) {
			return ResponseEntity.ok(modelMapper.map(pedido.get(), FullPedidoRepresentationModel.class));
		}
		return ResponseEntity.notFound().build();
	}

	@PatchMapping("/{id}/pagar")
	public PedidoRepresentationModel escolherFormaDePagamento(@RequestBody PagamentoInput pagamento,
			@PathVariable Integer id) {
		Pedido pagar = pedidoService.pagar(pagamento.getValor(), id);
		return modelMapper.map(pagar, PedidoRepresentationModel.class);
	}

}
