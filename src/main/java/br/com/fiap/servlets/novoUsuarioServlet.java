package br.com.fiap.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.bean.UsuarioRede;
import br.com.fiap.controller.UsuarioRedeController;

/**
 * Servlet implementation class novoUsuarioServlet
 */
@WebServlet("/novoUsuario")
public class novoUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FuncoesAux funcao = new FuncoesAux();
		UsuarioRedeController usuarioDao = null;
		try {
			usuarioDao = new UsuarioRedeController();
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			Date dataCadastro = new Date();
			if(funcao.verificarEmail(email)) {
				request.setAttribute("mensagemErroEmail", "O e-email já está cadastrado!");
				 request.setAttribute("emailInvalido", true); 
				request.getRequestDispatcher("/Registrar.jsp").forward(request, response);
			} else {
                 UsuarioRede usuario = new UsuarioRede(nome, idade, email, senha, dataCadastro);
				
				usuarioDao.cadastrarUsuario(usuario);
				
				request.setAttribute("cadastroSucesso", true);
				request.getRequestDispatcher("/IniciarSessao.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		usuarioDao.encerrar();
		doGet(request, response);
	}

}
