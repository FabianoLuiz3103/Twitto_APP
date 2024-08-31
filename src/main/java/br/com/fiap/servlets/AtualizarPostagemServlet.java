package br.com.fiap.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.controller.PostagemController;

/**
 * Servlet implementation class AtualizarPostagemServlet
 */
@WebServlet("/atualizarPostagem")
public class AtualizarPostagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPostagem = request.getParameter("idPostagem");
		String mensagem = request.getParameter("mensagemUpdate");
		Date dataUpdate = new Date();
		PostagemController postagemDao = null;
		try {
			postagemDao = new PostagemController();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		postagemDao.atualizarPost(mensagem, dataUpdate, idPostagem);
		response.sendRedirect("listarPosts");
		postagemDao.encerrar();
	}

}
