package br.com.fiap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.controller.PostagemController;

/**
 * Servlet implementation class ExcluirPostagemServlet
 */
@WebServlet("/excluirPostagem")
public class ExcluirPostagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPostagem = request.getParameter("idPostagem");
		PostagemController postagemDao = null;
		try {
			postagemDao = new PostagemController();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postagemDao.excluirPost(idPostagem);
		response.sendRedirect("listarPosts");
		postagemDao.encerrar();
		doGet(request, response);
	}

}
