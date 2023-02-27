package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

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
	
	//relatório de vendas
	public List<Object[]> relatorioDeVendas(){
		String jpql = "SELECT produto.nome,"
				+ " SUM(item.quantidade),"
				+ "MAX(pedido.data) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, Object[].class)
				.getResultList();
	}
	
	//criar uma instância relatorioDeVendasVo sem entidade
	public List<RelatorioDeVendasVo> relatorioDeVendasVo(){
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo("
				+ "produto.nome,"
				+ " SUM(item.quantidade),"
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return em.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
	
	//carregar um relacionamento que é lazy fazendo join pelo fetch
	public Pedido buscarPedidoComCliente(Long id) {
		return em.createQuery("SELECT p from Pedido p JOIN FETCH p.cliente where p.id = :id",Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
	

	


}
