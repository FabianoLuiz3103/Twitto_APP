package br.com.fiap.bean;

import java.util.Date;
import java.util.List;

public class UsuarioRede {
	
	private String idUsuario;
	private String nome;
	private int idade;
	private String email;
	private String senha;
    private Date dataCadastro;
    private Date dataUpdate;
    private List<Postagem> posts;

    
    public UsuarioRede() {
    		
    }
    
	public UsuarioRede(String nome, int idade, String email, String senha, Date dataCadastro) {
		this.nome = nome;
		this.idade = idade;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}
	public UsuarioRede(String nome, int idade, String email) {
		this.nome = nome;
		this.idade = idade;
		this.email = email;
	}
	
	public UsuarioRede(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
    
	public String getNome() {
		return nome;
	}
	

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public List<Postagem> getPosts() {
		return posts;
	}

	public void setPosts(Postagem posts) {
		this.posts.add(posts);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
