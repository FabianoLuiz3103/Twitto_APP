package br.com.fiap.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.bean.UsuarioRede;
import br.com.fiap.controller.UsuarioRedeController;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        UsuarioRedeController usuarioDao = null;
        try {
            usuarioDao = new UsuarioRedeController();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        UsuarioRede usuario = new UsuarioRede(email, senha);
        String nomeUsuario = null;
        String idUsuario = null;

        for(UsuarioRede u: usuarioDao.listarTodos()) {
            if(u.getEmail().equalsIgnoreCase(email)) {
                idUsuario = u.getIdUsuario();
                nomeUsuario = u.getNome();
                break;
            }
        }

        if (usuarioDao.validarUsuario(usuario)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", nomeUsuario);
            session.setAttribute("idUsuario", idUsuario);
            session.setAttribute("usuAutenticado", true);

            // Atualizar a lista de usuários online
            ServletContext context = request.getServletContext();
            Set<String> onlineUsers = (Set<String>) context.getAttribute("onlineUsers");

            if (onlineUsers == null) {
                onlineUsers = new HashSet<>();
                context.setAttribute("onlineUsers", onlineUsers);
            }

            onlineUsers.add(nomeUsuario);

            response.sendRedirect("listarPosts");
        } else {
            request.setAttribute("erroLogin", "Usuário ou senha inválido");
            request.setAttribute("loginInvalido", true);
            request.getRequestDispatcher("/IniciarSessao.jsp").forward(request, response);
        }
        usuarioDao.encerrar();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nomeUsuario = (String) session.getAttribute("user");
        
        if (nomeUsuario != null) {
            // Remover o usuário da lista de usuários online
            ServletContext context = request.getServletContext();
            Set<String> onlineUsers = (Set<String>) context.getAttribute("onlineUsers");

            if (onlineUsers != null) {
                onlineUsers.remove(nomeUsuario);
            }
            
            session.invalidate();
        }
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}

