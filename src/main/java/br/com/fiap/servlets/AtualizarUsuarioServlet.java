package br.com.fiap.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.fiap.controller.UsuarioRedeController;

/**
 * Servlet implementation class AtualizarUsuarioServlet
 */
@WebServlet("/atualizarUsuario")
public class AtualizarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FuncoesAux funcao = new FuncoesAux();
		UsuarioRedeController usuarioDao = null;
		try {
			usuarioDao = new UsuarioRedeController();
			String idUsuario = request.getParameter("idUsuario");
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			Date dataUpdade = new Date();
			if(funcao.verificarEmailUpdate(email, idUsuario)) {
				
				response.setContentType("application/json");
				Gson gson = new Gson();
				JsonObject jsonCliente = new JsonObject();
				String errorX = "";
				jsonCliente.addProperty("erroEmail", "EMAIL INVÁLIDO! O EMAIL JÁ ESTÁ CADASTRADO.");
			    errorX += "errorEmail=true";
				response.getWriter().write(gson.toJson(jsonCliente));
			    String url = "profile?error=true&" + errorX;
	            response.sendRedirect(url);
			} else {
				usuarioDao.atualizarUsuario(nome, idade, email, senha, dataUpdade, idUsuario);
				response.sendRedirect("profile");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		usuarioDao.encerrar();
		doGet(request, response);
	}

}
