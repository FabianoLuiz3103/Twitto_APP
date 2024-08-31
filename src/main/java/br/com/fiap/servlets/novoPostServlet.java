package br.com.fiap.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.bean.Postagem;
import br.com.fiap.controller.PostagemController;

/**
 * Servlet implementation class novoPostServlet
 */
@WebServlet("/novoPost")
public class novoPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idUsuario = (String) session.getAttribute("idUsuario");
		String mensagem = request.getParameter("mensagem");
		Date dataPostagem = new Date();
		PostagemController postagemDao = null;
		try {
			postagemDao = new PostagemController();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		
		Postagem post = new Postagem(mensagem, idUsuario, dataPostagem);
		postagemDao.cadastrarPostagem(post);
		response.sendRedirect("listarPosts");
		postagemDao.encerrar();
		doGet(request, response);
	
	}

}
