	package app;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.*;

import service.ProdutoService;
import service.UsuarioService;

public class Aplicacao {
	
	
    private static UsuarioService usuarioService = new UsuarioService();
    private static ProdutoService produtoService = new ProdutoService();
	
    public static void main(String[] args) {
    	port(4568);
    	staticFiles.location("/");
    	//Usuário

        post("/usuario", (request, response) -> usuarioService.add(request, response));
        get("/usuario", (request, response) -> usuarioService.get(request, response));
        
        //Produto
        post("/produto", (request,response) ->  produtoService.add(request, response) );
		get("/all/produto/", (request, response) -> produtoService.getAll(request, response)); 
		get("/get/produto/:id", (request, response) -> produtoService.get(request, response)); 
		//put("/update/produto/:id", (request, response) -> produtoService.update(request, response));
		//delete("/delete/produto/:id", (request, response) -> produtoService.remove(request,response));
              
    }
}