package br.com.fiap.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fiap.controller.PostagemController;

/**
 * Servlet implementation class curtirServlet
 */
@WebServlet("/curtir")
public class curtirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String idPostagem = request.getParameter("idPostagem");
	        System.out.println(idPostagem);
	        PostagemController postagemDao = null;
			try {
				postagemDao = new PostagemController();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        boolean sucesso = postagemDao.atualizarNumeroCurtidas(idPostagem);

	       
	        int novoNumeroCurtidas = postagemDao.obterNumeroCurtidas(idPostagem);
	      

	        Map<String, Object> jsonResponse = new HashMap<>();
	        jsonResponse.put("success", sucesso);
	        jsonResponse.put("novoNumeroCurtidas", novoNumeroCurtidas);
	        response.setContentType("application/json");
	        response.getWriter().write(new Gson().toJson(jsonResponse));
	        
	        postagemDao.encerrar();
	    }

}
