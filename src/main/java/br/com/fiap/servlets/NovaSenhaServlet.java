package br.com.fiap.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.fiap.controller.UsuarioRedeController;

/**
 * Servlet implementation class NovaSenhaServlet
 */
@WebServlet("/novaSenha")
public class NovaSenhaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FuncoesAux funcao = new FuncoesAux();
		UsuarioRedeController usuarioDao = null;
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		try {
			usuarioDao = new UsuarioRedeController();
			if(action.equals("E")) {
				String email = request.getParameter("email");
				String idUsuario = funcao.verificarEmailSenha(email);
				
				if(idUsuario != null) {
					session.setAttribute("idU_novaSenha", idUsuario);
					String  validX = "";
					validX += "validEmail=true";
					String url = "IniciarSessao.jsp?valid=true&" +  validX;
					response.sendRedirect(url);
					
				} else {
					response.setContentType("application/json");
					Gson gson = new Gson();
					JsonObject jsonCliente = new JsonObject();
					String errorX = "";
					jsonCliente.addProperty("erroEmail", "EMAIL INVÁLIDO! O EMAIL JÁ ESTÁ CADASTRADO.");
					errorX += "errorEmail=true";
					response.getWriter().write(gson.toJson(jsonCliente));
					String url = "IniciarSessao.jsp?error=true&" + errorX;
					response.sendRedirect(url);
					
				}
				
			}
			
			if(action.equals("S")) {
				String idU = (String) session.getAttribute("idU_novaSenha");
				String novaSenha = request.getParameter("senha");
				if(novaSenha != null) {
					Date dataUpdate = new Date();
					usuarioDao.alterarSenha(novaSenha, dataUpdate, idU);
					String  alterarX = "";
					alterarX += "alterarSenha=true";
					String url = "IniciarSessao.jsp?alterar=true&" +  alterarX;
					response.sendRedirect(url);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		usuarioDao.encerrar();
		doGet(request, response);
	}

}
