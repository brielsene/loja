package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		
		Categoria celulares = new Categoria("Celulares");
		Produto celular = new Produto("Iphone","Muito Legal", new BigDecimal("800"), celulares);

		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO  = new CategoriaDAO(em);
		
		//inicia operações
		em.getTransaction().begin();
		
		
		categoriaDAO.adicionarCategoria(celulares);
//		produtoDAO.cadastrar(celular);
		
		em.persist(celulares);
		celulares.setNome("XPTO");
		
		
		em.flush();
		em.clear();
		
		celulares = em.merge(celulares);
		celulares.setNome("gg");
		em.flush();
		
		List<Produto> buscaTodosProdutos = produtoDAO.buscaTodosProdutos();
		buscaTodosProdutos.forEach(b -> System.out.println(b.getNome()));
		

	}

}
