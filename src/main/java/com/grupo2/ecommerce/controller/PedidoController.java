package com.grupo2.ecommerce.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.ecommerce.dto.PedidoRequestDTO;
import com.grupo2.ecommerce.dto.PedidoResponseDTO;
import com.grupo2.ecommerce.model.Pedido;
import com.grupo2.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService servico;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> obterTodos(){
		List<Pedido> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> obterPorId(@PathVariable Long id){
		Optional<Pedido> optPedido = servico.obterPorId(id);
		return ResponseEntity.ok(optPedido.get());
	}
	
	@PostMapping()
	public ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody PedidoRequestDTO pedidoDTO) {
		PedidoResponseDTO pedidoResponseDTO = servico.cadastrar(pedidoDTO);
				// emailService.enviar(
		// new MensagemEmail("Cadastro de "+ cliente.getNomeCompleto(),
		//                   "Bem vindo ao Ecommerce grupo 2, "+cliente.getNomeUsuario()+"!",
		// 				   cliente.getEmail()));
		return new ResponseEntity<>(pedidoResponseDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido){
		return ResponseEntity.ok(servico.atualizar(id, pedido));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
