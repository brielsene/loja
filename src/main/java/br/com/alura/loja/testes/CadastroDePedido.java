package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		Produto produto = produtoDAO.buscarPorId(1l);
		Produto produto2 = produtoDAO.buscarPorId(2l);
		Produto produto3 = produtoDAO.buscarPorId(3l);
//		System.out.println(produto.getPreco()+" - Tá funfando");
		em.getTransaction().begin();
		Cliente cliente = new Cliente("Gabriel", "123456");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionaItem(new ItemPedido(10, pedido, produto));
		pedido.adicionaItem(new ItemPedido(40, pedido, produto2));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido.adicionaItem(new ItemPedido(5, pedido, produto3));
		
		ClienteDAO clienteDAO = new ClienteDAO(em);
		clienteDAO.cadastrar(cliente);
		
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		
		
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
		System.out.println("valor total: "+totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDAO.relatorioDeVendasVo();
		relatorio.forEach(System.out::println);

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
		
		Produto buscarPorId = produtoDAO.buscarPorId(1l);
//		System.out.println(buscarPorId.getNome()+" - "+buscarPorId.getDescricao()+" - "+buscarPorId.getPreco());
		
		em.getTransaction().commit();;
		em.close();
	}

}
