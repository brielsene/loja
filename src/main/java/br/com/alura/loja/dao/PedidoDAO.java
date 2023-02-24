package br.com.alura.loja.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;

public class PedidoDAO {
	
	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public void atualizar (Pedido pedido) {
		this.em.merge(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jqpl = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jqpl, BigDecimal.class).getSingleResult();
	}
	
	
	

	


}
