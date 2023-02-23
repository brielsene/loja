package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDAO {
	
	private EntityManager em;
	
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public List<Produto>buscaTodosProdutos(){
		String jpql = "SELECT p FROM Produto p ";
		return em.createQuery(jpql, Produto.class)
				.getResultList();
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

}
