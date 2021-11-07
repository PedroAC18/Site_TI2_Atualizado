package model;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cpf;
	private String nome;
	private String sobrenome;
	private String login;
	private String senha;
	private String celular;
	
	public Usuario() {
		this.cpf = "00000000000";
		this.nome = "";
		this.sobrenome = "";
		this.login = "login";
		this.senha = "senha";
		this.celular = "00999999999";
	}
	
	
	public Usuario(String cpf, String nome, String sobrenome, String login, String senha, String celular) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.login = login;
		this.senha = senha;
		this.celular = celular;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSobrenome() {
		return sobrenome;
	}


	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}
	
}
	
	