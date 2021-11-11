package service;

import java.net.URISyntaxException;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService {
	private UsuarioDAO usuarioDAO;
	
	public UsuarioService() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public Object add(Request request, Response response) throws URISyntaxException {
		usuarioDAO.connect();
		String cpf      = request.queryParams("cpf");
		String nome		= request.queryParams("nome"); 
		String sobrenome        = request.queryParams("sobrenome");
		String login 	  = request.queryParams("login");
		String senha      = request.queryParams("senha");
		String celular      = request.queryParams("celular");
		
		Usuario user = new Usuario(cpf, nome, sobrenome, login, senha, celular);
		
		usuarioDAO.add(user);
		
		response.status(201); // created
		response.redirect("../index.html");
		
		usuarioDAO.close();
		
		return null;
	}
	
	// Efetuar login pelo login
	public Object get(Request request, Response response) throws URISyntaxException {
		usuarioDAO.connect();
		
		String login = request.queryParams("login");
		System.out.println(login);
		
		Usuario usuario = (Usuario) usuarioDAO.get(login);
		
		
		usuarioDAO.close();
		
		response.header("Content-Type", "application/json");
		response.header("Content-Encoding", "UTF-8");
			
		if (usuario != null) {
			return usuario.toJson();
		} else {
			response.status(404); // NOT FOUND
			response.redirect("404.html");
			return null;
		}
	}

	
	
}