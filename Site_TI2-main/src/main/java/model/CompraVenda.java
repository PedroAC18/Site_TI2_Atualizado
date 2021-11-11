package model;

import java.io.Serializable;

public class CompraVenda implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean realizada;
	private Usuario comprador;	//CPF
	private Usuario vendededor;
	private Produto produto;
	private String metodoPagamento;
	
	public CompraVenda() {
		
		this.realizada = false;
		this.comprador = new Usuario();
		this.vendededor = new Usuario();
		this.produto = new Produto();
		this.metodoPagamento = "";
	}
	
	public boolean isRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public Usuario getVendededor() {
		return vendededor;
	}

	public void setVendededor(Usuario vendededor) {
		this.vendededor = vendededor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public CompraVenda(boolean realizada, Usuario comprador, Usuario vendededor, Produto produto,
			String metodoPagamento) {
		
		this.realizada = realizada;
		this.comprador = comprador;
		this.vendededor = vendededor;
		this.produto = produto;
		this.metodoPagamento = metodoPagamento;
	}

}
