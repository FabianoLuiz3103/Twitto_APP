package br.com.fiap.controller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import br.com.fiap.DAO.PostagemDAO;
import br.com.fiap.bean.Postagem;
import br.com.fiap.connection.ConnectionFactory;

public class PostagemController {

	private PostagemDAO postagemDao;
	
	public PostagemController() throws ClassNotFoundException {
		Connection conexao = new ConnectionFactory().conexao();
		this.postagemDao = new PostagemDAO(conexao);
	}
	
	public void cadastrarPostagem(Postagem post) {
		this.postagemDao.cadastrarPost(post);
	}

	public List<Postagem> listarPosts() {
		return this.postagemDao.listarPosts();
	}
	
	public List<Postagem> listarPorUsuario(String idUsuario){
		return this.postagemDao.listarPorUsuario(idUsuario);
	}
	
	public void atualizarPost(String mensagem, Date dataUpdate, String idPostagem) {
		this.postagemDao.atualizarPost(mensagem, dataUpdate, idPostagem);
	}
	
	public void excluirPost(String idPostagem) {
		this.postagemDao.excluirPost(idPostagem);
	}
	
	public void encerrar() {
		this.postagemDao.fecharConexao();
	}

	public Postagem buscar(String idPostagem) {
		return this.postagemDao.buscar(idPostagem);
	}
	
	public boolean atualizarNumeroCurtidas(String idPostagem) {
		return this.postagemDao.atualizarNumeroCurtidas(idPostagem);
	}
	
	public int obterNumeroCurtidas(String idPostagem) {
		return this.postagemDao.obterNumeroCurtidas(idPostagem);
	}
}
