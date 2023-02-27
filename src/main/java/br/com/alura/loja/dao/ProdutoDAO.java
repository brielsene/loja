package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	public List<Produto> buscarPorParametro(String nome, BigDecimal preco, LocalDate dataCadastro){
		//o 1=1 é apenas pra forçar na query que sempre vai dar verdadeiro
		String jpql = "SELECT p from Produto p WHERE 1=1";
		if(nome != null & !nome.trim().isEmpty()) {
			jpql+=" AND p.nome = :nome";
		}if(preco != null) {
			jpql+=" AND p.preco = :preco";
		}if(dataCadastro != null) {
			jpql+=" AND p.dataCadastro = :dataCadastro";
		}
		
		
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		if(nome != null & !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}if(preco != null) {
			query.setParameter("preco", preco);
		}if(dataCadastro != null) {
			query.setParameter("dataCadastro", dataCadastro);
		}
		
		
		return query.getResultList() ;
	}
	
	
	public List<Produto> buscarPorParametroComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		
		if(nome != null & !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome) );
		}if(preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco) );
		}if(dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro) );
		}
		
		query.where(filtros);
		return em.createQuery(query).getResultList();
		
		
		
	}

}
