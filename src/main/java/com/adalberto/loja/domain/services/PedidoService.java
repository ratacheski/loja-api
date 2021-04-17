package com.adalberto.loja.domain.services;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adalberto.loja.domain.exceptions.RegraDeNegocioException;
import com.adalberto.loja.domain.model.Cliente;
import com.adalberto.loja.domain.model.Pedido;
import com.adalberto.loja.domain.model.enums.FormaPagamento;
import com.adalberto.loja.domain.model.enums.StatusPedido;
import com.adalberto.loja.domain.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido criarPedido(Cliente cliente) {

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setData(OffsetDateTime.now());
		pedido.setStatus(StatusPedido.ANDAMENTO);

		return pedidoRepository.save(pedido);

	}

	public Pedido finalizarPedido(Integer id) {

		Pedido pedido = pedidoRepository.findById(id).orElseThrow( // utilizando lambda function
				() -> new RegraDeNegocioException("Pedido não encontrado"));
		if (!pedido.getStatus().equals(StatusPedido.ANDAMENTO)) {
			throw new RegraDeNegocioException("Este pedido não pode ser finalizado");
		}
		pedido.setStatus(StatusPedido.FINALIZADO);
		return pedidoRepository.save(pedido);
	}

	public Pedido pedidoEntregue(Integer id) {

		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));
		if (!pedido.getStatus().equals(StatusPedido.FINALIZADO)) {
			throw new RegraDeNegocioException("Este pedido não pode ser entregue, pois ele não está em andamento");
		}
		pedido.setStatus(StatusPedido.ENTREGUE);
		return pedidoRepository.save(pedido);
	}

	public Pedido cancelarPedido(Integer id) {

		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));
		if (!pedido.getStatus().equals(StatusPedido.ANDAMENTO)) {
			throw new RegraDeNegocioException("Este pedido não pode ser cancelado");
		}
		pedido.setStatus(StatusPedido.CANCELADO);
		return pedidoRepository.save(pedido);
	}

	public Pedido pagar(Integer valor, Integer id) {

		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));
		if (!pedido.getStatus().equals(StatusPedido.FINALIZADO)) {
			throw new RegraDeNegocioException("Este pedido não pode ser pago");
		}
		FormaPagamento forma = FormaPagamento.getByValor(valor);
		if (forma == null) {
			throw new RegraDeNegocioException("Forma de pagamento inexistente");
		}
		pedido.setFormaPagamento(forma);
		return pedidoRepository.save(pedido);
	}
}
