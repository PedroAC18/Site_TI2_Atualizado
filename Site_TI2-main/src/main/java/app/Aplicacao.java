package app;

import static spark.Spark.*;

import service.ProdutoService;

public class Aplicacao {
	
	//private static ProdutoService produtoService = new ProdutoService();
    private static UsuarioService usuarioService = new usuarioService();
	
    public static void main(String[] args) {
        post("/usuario", (request, response) -> usuarioService.add(request, response));

        get("/usuario/:cpf", (request, response) -> usuarioService.get(request, response));

        get("/usuario/update/:cpf", (request, response) -> usuarioService.update(request, response));

        get("/usuario/delete/:cpf", (request, response) -> usuarioService.remove(request, response));

        get("/usuario", (request, response) -> usuarioService.getAll(request, response));
               
    }
}