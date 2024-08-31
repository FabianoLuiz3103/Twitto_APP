package br.com.fiap.servlets;

import br.com.fiap.bean.UsuarioRede;
import br.com.fiap.controller.UsuarioRedeController;

public class FuncoesAux {
	
	public boolean verificarEmail(String email) throws ClassNotFoundException {
		UsuarioRedeController usuarioDao = new UsuarioRedeController();
		boolean valid = false;
		for(UsuarioRede u: usuarioDao.listarTodos() ) {
			if(u.getEmail().equalsIgnoreCase(email)) {
				System.out.println(u.getEmail());
				valid = true;
				break;
				
			}
		}
		usuarioDao.encerrar();
		return valid;
	}
	
	public String verificarEmailSenha(String email) throws ClassNotFoundException {
		UsuarioRedeController usuarioDao = new UsuarioRedeController();
		String idUsuario = null;
		for(UsuarioRede u: usuarioDao.listarTodos() ) {
			if(u.getEmail().equalsIgnoreCase(email)) {
				System.out.println(u.getEmail());
				idUsuario = u.getIdUsuario();
				break;
				
			}
		}
		usuarioDao.encerrar();
		if(idUsuario != null) {
			return idUsuario;
		}else {
			return null;
		}
		
	}
	
	public boolean verificarEmailUpdate(String email, String idUsuario) throws ClassNotFoundException {
		String emailRegistrado = null;
		UsuarioRedeController usuarioDao = new UsuarioRedeController();
		boolean valid = false;
		for(UsuarioRede u: usuarioDao.listarTodos() ) {
			if(u.getIdUsuario().equalsIgnoreCase(idUsuario)) {
				emailRegistrado = u.getEmail();
			}
		}
		if(emailRegistrado.equalsIgnoreCase(email)) {
			valid = false;
		}else {
			for(UsuarioRede u: usuarioDao.listarTodos() ) {
				if(u.getEmail().equalsIgnoreCase(email)) {
					System.out.println(u.getEmail());
					valid = true;
					break;
					
				}
			}
		}
		usuarioDao.encerrar();
		return valid;
	}


}
