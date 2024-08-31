package br.com.fiap.bean;

import java.util.Date;

public class Postagem {
	
	private String idPostagem;
	private String mensagem;
	private String idUsuario;
	private int qtdCurtidas;
	private Date dataPostagem;
	private Date dataUpdate;
	
	public Postagem() {
		
	}
	
	public Postagem(String mensagem, String idUsuario, Date dataPostagem) {
		this.mensagem = mensagem;
		this.idUsuario = idUsuario;
		this.dataPostagem = dataPostagem;
	}
	
	public Postagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(String idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getQtdCurtidas() {
		return qtdCurtidas;
	}

	public void setQtdCurtidas(int qtdCurtidas) {
		this.qtdCurtidas = qtdCurtidas;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}
	
	

}
