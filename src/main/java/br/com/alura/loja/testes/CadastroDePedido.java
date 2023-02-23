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

public class CadastroDePedido {

	public static void main(String[] args) {
		
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		Produto produto = produtoDAO.buscarPorId(1l);
		em.getTransaction().begin();
		Cliente cliente = new Cliente("Gabriel", "123456");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionaItem(new ItemPedido(10, pedido, produto));
		
		ClienteDAO clienteDAO = new ClienteDAO(em);
		clienteDAO.cadastrar(cliente);
		
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		
		em.getTransaction().commit();
		
		
		
		
		

	}
	
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("Celulares");
		Produto celular = new Produto("IPHONE 14", "IPHONE 14 PLUS", new BigDecimal("4500"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.adicionarCategoria(celulares);
		produtoDAO.cadastrar(celular);
		Produto buscarPorId = produtoDAO.buscarPorId(1l);
		System.out.println(buscarPorId.getNome()+" - "+buscarPorId.getDescricao());
		
		em.getTransaction().commit();;
		em.close();
	}

}
