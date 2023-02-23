package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class Teste {

	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(em);
		CategoriaDAO dao2  = new CategoriaDAO(em);
//		em.getTransaction().begin();
//		Categoria categoria = new Categoria();
//		categoria.setNome("Celulares luxo");
//		dao2.adicionarCategoria(categoria);
//		Produto produto = new Produto("Iphone 14", "Gold",
//				new BigDecimal("2500"), categoria );
//		dao.cadastrar(produto);
//		em.getTransaction().commit();
		
		List<Produto> buscaTodosProdutos = dao.buscaTodosProdutos();
		buscaTodosProdutos.forEach(b -> System.out.println(b.getNome()));
		System.out.println(buscaTodosProdutos);
		em.getTransaction().begin();
		List<Categoria> selecionaTodasCategorias = dao2.selecionaTodasCategorias();
		
		System.out.println(selecionaTodasCategorias);
		em.close();
		
				
		

	}

}
