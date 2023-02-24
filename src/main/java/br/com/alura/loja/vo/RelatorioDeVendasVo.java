package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {
	
	
	private String nomeProduto;
	private Long quantidadedVendida;
	private LocalDate dataUltimaVenda;
	
	
	public RelatorioDeVendasVo(String nomeProduto, Long quantidadedVendida, LocalDate dataUltimaVenda) {
		this.nomeProduto = nomeProduto;
		this.quantidadedVendida = quantidadedVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}
	
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public Long getQuantidadedVendida() {
		return quantidadedVendida;
	}
	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}


	@Override
	public String toString() {
		return "RelatorioDeVendasVo [nomeProduto=" + nomeProduto + ", quantidadedVendida=" + quantidadedVendida
				+ ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}
	
	
	
	
	
	

}
