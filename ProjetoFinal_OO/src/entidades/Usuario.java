package entidades;

public abstract class Usuario {
	private String nome, cpf, senha, email;
	
	public Usuario(String nome, String cpf, String senha, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;
	}
}
