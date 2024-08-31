package br.com.fiap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.controller.UsuarioRedeController;

/**
 * Servlet implementation class ExcluirUsuarioServlet
 */
@WebServlet("/excluirUsuario")
public class ExcluirUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuario = request.getParameter("idUsuario");
		UsuarioRedeController usuarioDao = null;
		try {
			usuarioDao = new UsuarioRedeController();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuarioDao.excluirUsuario(idUsuario);
		response.sendRedirect("Registrar.jsp");
		usuarioDao.encerrar();
		doGet(request, response);
	}

}
