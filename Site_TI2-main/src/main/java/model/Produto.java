package model;

import java.io.Serializable;

import org.json.JSONObject;

public class Produto implements Serializable, JsonFormatter {
	private static final long serialVersionUID = 1L;
	public static final int MAX_ESTOQUE = 1000;
	private int id;
	private String nome;
	private float preco;
	private String fichaTecnica;
	private int quantidade;
	private String categoria;
	private String imagemUrl;
	
	public Produto() {
		id = -1;
		nome = "";
		preco = 0.01F;
		fichaTecnica = "";
		quantidade = 0;
		categoria = "";
		imagemUrl = "";
	}

	public Produto(int id, String nome, float preco, String fichaTecnica, int quantidade, String categoria, String imagemUrl) {
		setId(id);
		setNome(nome);
		setPreco(preco);
		setFichaTecnica(fichaTecnica);
		setQuantidade(quantidade);
		setCategoria(categoria);
		setImagemUrl(imagemUrl);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		if (preco > 0)
			this.preco = preco;
	}
	
	public String getFichaTecnica(){
		return fichaTecnica;
	}
	
	public void setFichaTecnica(String fichaTecnica){
		if(fichaTecnica.length() > 3)
			this.fichaTecnica = fichaTecnica;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		if (quantidade >= 0 && quantidade <= MAX_ESTOQUE)
			this.quantidade = quantidade;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getImagemUrl() {
		return this.imagemUrl;
	}
	
	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	
	public void addQuantidadeBy(int quantidade) {
		if(this.quantidade + quantidade >= 0 ) {
			this.quantidade += quantidade;
		}
		else {
			throw new RuntimeException("A quantidade nao pode ser menor que 1");
		}
	}
	

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return id + " - " + "Produto: " + nome + "   Preço: R$" + preco + "   Quant.: " + quantidade + "   ";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Produto) obj).getId());
	}

	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("nome", this.getNome());
		obj.put("preco", this.getPreco());
		obj.put("fichaTecnica", this.getFichaTecnica());
		obj.put("quantidade", this.getQuantidade());
		obj.put("categoria", this.getCategoria());
		obj.put("imagemUrl", this.getImagemUrl());
		
		return obj;
	}	
}