package service;

import java.net.URISyntaxException;

import org.json.JSONArray;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;

public class ProdutoService implements Service {
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoService() {
		produtoDAO = new ProdutoDAO();
	}

	@Override
	public Object add(Request request, Response response) throws URISyntaxException {
		produtoDAO.connect();
		
		int id = produtoDAO.getIdMax()+1;//Integer.parseInt(request.queryParams("id"));
		String nome = request.queryParams("nome").trim();
		float preco = Float.parseFloat(request.queryParams("preco"));
		String fichaTecnica = request.queryParams("fichaTecnica").trim();
		int quantidade = 1;//Integer.parseInt(request.queryParams("quantidade"));
		String categoria = request.queryParams("categoria").trim();
		String imagemUrl = request.queryParams("imagemUrl").trim();
		

		
		
		Produto produto = new Produto();
		produto = new Produto(id, nome, preco, fichaTecnica, quantidade, categoria, imagemUrl);
		
		produtoDAO.add(produto);

		response.status(201); // created
		response.redirect("../index.html");
		
		produtoDAO.close();
		
		return produtoDAO.getIdMax();
	}

	@Override
	public Object get(Request request, Response response) throws URISyntaxException {
		produtoDAO.connect();
		
		int id = Integer.parseInt(request.params(":id"));
		
		Produto produto = (Produto) produtoDAO.get(id);

		response.header("Content-Type", "application/json");
		response.header("Content-Encoding", "UTF-8");
		
		produtoDAO.close();
		
		if (produto != null) {
			return produto.toJson();
		} else {
			response.status(404); // NOT FOUND
			response.redirect("404.html");
			return null;
		}
	}

	@Override
	public Object update(Request request, Response response) throws URISyntaxException {
		produtoDAO.connect();

		int id = Integer.parseInt(request.params(":id"));

		Produto produto = (Produto) produtoDAO.get(id);

		if (produto != null) {
			produto.setNome(request.queryParams("nome").trim());
			produto.setPreco(Float.parseFloat(request.queryParams("preco")));
			produto.setFichaTecnica(request.queryParams("fichaTecnica").trim());
			produto.setQuantidade(Integer.parseInt(request.queryParams("bebidaQuantidade")));
			produto.setCategoria(request.queryParams("categoria").trim());
			produto.setImagemUrl(request.queryParams("imagemUrl").trim());

			produtoDAO.update(produto);

			return produto.getId();
		} else {
			response.status(404); // 404 Not found
			response.redirect("404.html");
			return null;
		}

	}

	@Override
	public Object remove(Request request, Response response) throws URISyntaxException {
		produtoDAO.connect();

		int id = Integer.parseInt(request.params(":id"));

		Produto produto = (Produto) produtoDAO.get(id);

		if (produto != null) {

			produtoDAO.delete(produto);

			response.status(200); // success
			return produto.getId();
		} else {
			response.status(404); // 404 Not found
			response.redirect("/notfound.html");
			return null;
		}
	}

	@Override
	public Object getAll(Request request, Response response) throws URISyntaxException {				
		response.header("Content-Type", "application/json");
		response.header("Content-Encoding", "UTF-8");
		
		produtoDAO.connect();
		
		JSONArray allProds = new JSONArray();
		
		for (Produto p : produtoDAO.getAll()) {
			Produto produto = (Produto) p;
			allProds.put(produto.toJson());
		}

		produtoDAO.close();
				
		return allProds;

	}

}
