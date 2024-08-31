package br.com.fiap.controller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import br.com.fiap.DAO.UsuarioRedeDAO;
import br.com.fiap.bean.UsuarioRede;
import br.com.fiap.connection.ConnectionFactory;

public class UsuarioRedeController {
	
	private UsuarioRedeDAO usuarioDao;
	
	public UsuarioRedeController() throws ClassNotFoundException {
		Connection conexao = new ConnectionFactory().conexao();
		this.usuarioDao = new UsuarioRedeDAO(conexao);
	}
	
	public void cadastrarUsuario(UsuarioRede usuario) {
		this.usuarioDao.cadastrarUsuario(usuario);
	}
	
	public List<UsuarioRede> listarTodos(){
		return this.usuarioDao.listarTodos();
	}
	
	public List<UsuarioRede> listarComPostagem(){
		return this.usuarioDao.listarComPostagem();
	}
	
	public void atualizarUsuario(String nome, int idade, String email, String senha, Date dataUpdate, String idUsuario) {
		this.usuarioDao.atualizarUsuario(nome, idade, email, senha, dataUpdate, idUsuario);
	}
	
	public void excluirUsuario(String idUsuario) {
		this.usuarioDao.excluirUsuario(idUsuario);
	}
	
	public UsuarioRede buscar(String idUsuario) {
		return this.usuarioDao.buscar(idUsuario);
	}
	
	public boolean validarUsuario(UsuarioRede usuario) {
		return this.usuarioDao.validarUsuario(usuario);
	}
	
	public void alterarSenha(String senha, Date dataUpdate, String idUsuario) {
		this.usuarioDao.alterarSenha(senha, dataUpdate, idUsuario);
	}
	public void encerrar() {
		this.usuarioDao.fecharConexao();
	}
}
