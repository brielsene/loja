package br.com.alura.loja.testes;

import java.math.BigDecimal;

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

public class PerfomanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		Pedido find = em.find(Pedido.class, 1l);
		PedidoDAO pedidoDao = new PedidoDAO(em);
		Pedido buscarPedidoComCliente = pedidoDao.buscarPedidoComCliente(1l);
		em.close();
//		System.out.println(find.getCliente().getNome());
		
		
		System.out.println(buscarPedidoComCliente.getCliente().getNome());
		
			

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
		
		Cliente cliente = new Cliente("Gabriel", "121241");
		ClienteDAO clienteDao = new ClienteDAO(em);
		clienteDao.cadastrar(cliente);
		
		PedidoDAO pedidoDao = new PedidoDAO(em);
		Pedido pedido = new Pedido(cliente);
		pedido.adicionaItem(new ItemPedido(5, pedido, macbook));
		pedidoDao.cadastrar(pedido);
		
		Produto buscarPorId = produtoDAO.buscarPorId(1l);
//		System.out.println(buscarPorId.getNome()+" - "+buscarPorId.getDescricao()+" - "+buscarPorId.getPreco());
		
		em.getTransaction().commit();;
		em.close();
	}

}
