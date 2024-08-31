package br.com.fiap.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String nomeUsuario = (String) session.getAttribute("user");
            ServletContext context = request.getServletContext();
            Set<String> onlineUsers = (Set<String>) context.getAttribute("onlineUsers");

            if (onlineUsers != null) {
                onlineUsers.remove(nomeUsuario); // Remova o usuário da lista
            }

            session.invalidate(); // Invalida a sessão
        }
        response.sendRedirect("IniciarSessao.jsp"); // Redireciona após o logout
    }
}
