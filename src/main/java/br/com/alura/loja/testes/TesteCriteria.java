package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDao = new ProdutoDAO(em);
		produtoDao.buscarPorParametroComCriteria("PS5", null, LocalDate.now());
		
		
	
		
			

	}
	
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("Celulares");
		Categoria videoGames = new Categoria("Video Games");
		Categoria informatica = new Categoria("Informática");
		
		
		Produto celular = new Produto("IPHONE 14", "IPHONE 14 PLUS", new BigDecimal("4500"), celulares);
		Produto videogame = new Produto("PS5", "PLAYSTATION5", new BigDecimal("3500"), videoGames);
		Produto macbook = new Produto("Macbook", "Macbook Pro", new BigDecimal("10000"), celulares);
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.adicionarCategoria(celulares);
		categoriaDAO.adicionarCategoria(videoGames);
		categoriaDAO.adicionarCategoria(informatica);
		
		
		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(videogame);
		produtoDAO.cadastrar(macbook);
				
		em.getTransaction().commit();;
		em.close();
	}

}
