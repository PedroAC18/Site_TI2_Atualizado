package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import usuarioDao.DAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class usuarioService {

	private UsuarioDAO usuarioDAO;

	public ProdutoService() {
		try {
			usuarioDAO = new ProdutoDAO("usuario.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String cpf = request.queryParams("cpf");
		String nome = request.queryParams("nome");
		String sobrenome = request.queryParams("sobrenome");
		String login = request.queryParams("login");
		String senha = request.queryParams("senha");
		String celular = request.queryParams("celular");

		int id = usuarioDAO.getMaxId() + 1;

		Usuario usuario = new Usuario(cpf, nome, sobrenome, login, senha, celular);

		usuarioDAO.add(usuario);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		
		String email = request.params(":login");
		Usuario usuario = (Usuario) usuarioDAO.get(email);
		
		if (usuario != null) {

    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<usuario>\n" +
            		"\t<cpf>" + usuario.getCpf() + "</cpf>\n" +
            		"\t<nome>" + usuario.getNome() + "</nome>\n" +
            		"\t<sobrenome>" + usuario.getSobrenome() + "</sobrenome>\n" +
            		"\t<email>" + usuario.getLogin() + "</email>\n" +
            		"\t<senha>" + usuario.getSenha() + "</senha>\n" +
            		"</usuario>\n";
        } else {
            response.status(404); // 404 Not found
            return "usuario " + email + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Usuario usuario = (Usuario) usuarioDAO.get(id);

        if (usuario != null) {
        	usuario.setDescricao(request.queryParams("descricao"));
        	usuario.setPreco(Float.parseFloat(request.queryParams("preco")));
        	usuario.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	usuario.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	usuario.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));

        	usuarioDAO.update(usuario);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "usuario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int cpf = Integer.parseInt(request.params(":cpf"));

        Usuario usuario = (Usuario) usuarioDAO.get(cpf);

        if (usuario != null) {

            usuarioDAO.remove(usuario);

            response.status(200); // success
        	return cpf;
        } else {
            response.status(404); // 404 Not found
            return "usuario não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<usuario type=\"array\">");
		for (Usuario usuario : usuarioDAO.getAll()) {
			returnValue.append("\n<usuario>\n" + 
            		"\t<id>" + usuario.getId() + "</id>\n" +
            		"\t<descricao>" + usuario.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + usuario.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + usuario.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + usuario.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade>" + usuario.getDataValidade() + "</validade>\n" +
            		"</usuario>\n");
		}
		returnValue.append("</usuario>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}