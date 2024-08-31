<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Set" %> 
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <title>Home Twittor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="./estilo/home.css">
    <link rel="icon"  href="./imagens/lgT.png" type="image/x-icon">
</head>

<body class="bg-primary-subtle">
    <nav class="navbar bg-body-tertiary fixed-top">
        <div class="container-fluid">
            <div class="d-flex  align-items-center">
                <a class="navbar-brand" href="profile">
                    <img src="./imagens/userSemFoto.png" alt="" class="rounded-circle border border-black"
                        style="width: 50px; height: 50px;">
                </a>
                 <a class="navbar-brand" href="profile">
                <h6 class="text-danger m-auto ">${sessionScope.user}</h6>
                  </a>
            </div>
            <div class="position-absolute top-0 end-0 ">
                    <a href="logout">
                        <button class="navbar-toggler mt-3 fw-bold" style="width:100px; height:50px; margin-right:3vh;" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                            SAIR
                        </button>
                    </a>
                </div>
           
        </div>
    </nav>

    <main class="container" style="margin-top: 20vh;">
        <div class="row mt-5">
            <div class="col-md-6 col-sm-12 card shadow-sm card-celular" style="width: 37rem; margin-left: 10vh">
                <div class="card-body">
                    <h5 class="card-title text-center">Adicionar postagem</h5>
                    <div class="campo" id="meuCampo">
                        <div class="linha text-center ms-3 linha-celular" style="font-size: 15px;">No que você está pensando?</div> 
                    </div>
                </div>
            </div>
        </div>

        <div id="meuModal" class="modal ">
            <div class="modal-conteudo modPost">
                <span class="fechar-modal" onclick="fecharModal()">&times;</span>
        
                <form
				class="mt-3 d-flex justify-content-center  align-items-center flex-column"
				action="novoPost" method="post">
                <div class="mb-3">
					<div class="d-flex">
						<textarea class="form-control area" name="mensagem" id="mensagem" cols="30" rows="5"  maxlength="400" style="width: 80vh;" placeholder="No que você está pensando?" required></textarea>
					</div>
				</div>
				<div class="d-flex w-100 mt-3">
					<button type="submit"
						class="btn btn-success w-50 m-auto text-cente fw-bold">Postar</button>
				</div>
			</form>
            </div>
        </div>
        
    <div class="row ms-5">
        <div class="col-md-6 col-sm-12">
            <c:forEach var="post" items="${posts}">
    <div class="card mt-5 card-postagem mb-5">
        <div class="card-header d-flex justify-content-between">
            <span class="mt-2">Postagem: #${post.idPostagem}</span>
            <div class="ml-auto">
               
                <c:if test="${post.idUsuario eq sessionScope.idUsuario}">
                   
                    <div class="dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Editar
                        </a>
                       <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    					 <button class="dropdown-item" onclick="abrirModalAtualizar('${post.idPostagem}')">Atualizar</button>
    
    						
    					<button class="dropdown-item" onclick="abrirModalDeletar('${post.idPostagem}')">Deletar</button>
						</div>
                       
                    </div>
                </c:if>
            </div>
        </div>
        <div class="card-body">
            <blockquote class="blockquote mb-0">
                <p>${post.mensagem}</p>
                 <div class="btn-group mt-2">
        	
        	  <button class="btn btn-curtir btn-like" data-id-postagem="${post.idPostagem}"><i id="coracao" class="far fa-heart" style="color: #ff0000; font-size:22px;"></i>
        	  </button>

    		<span class="mt-2 text-danger badge badge-secondary numero-curtidas-${post.idPostagem}">${post.qtdCurtidas}</span>
        	
    	</div>
                
                <div class="d-flex justify-content-between mt-5">
                <c:set var="user" value="${usuarios.stream().filter(u -> u.idUsuario == post.idUsuario).findFirst().orElse(null)}" />
                <c:if test="${user ne null}">
                <c:if test="${user.nome eq sessionScope.user}">
                    <footer class="blockquote-footer">${user.nome}, você</footer>
                 </c:if>
                 <c:if test="${user.nome ne sessionScope.user}">
                    <footer class="blockquote-footer">${user.nome}</footer>
                  </c:if>
                </c:if>
                <footer style="margin-top:" class="blockquote-footer date">
    			<c:set var="dataOriginal" value="${post.dataPostagem}" />
    			<c:set var="ano" value="${fn:substring(dataOriginal, 0, 4)}" />
    			<c:set var="mes" value="${fn:substring(dataOriginal, 5, 7)}" />
    			<c:set var="dia" value="${fn:substring(dataOriginal, 8, 10)}" />
    
    			${dia}-${mes}-${ano}
				</footer>
				</div>

            </blockquote>
        </div>
    </div>

    	<div class="modal fade" id="atualizarModal${post.idPostagem}" tabindex="-1" role="dialog" aria-labelledby="atualizarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="atualizarModalLabel">Atualizar Postagem</h5>
                <span class="fechar-modal" onclick="fecharModalAtualizar('${post.idPostagem}')">&times;</span>
            </div>
            <div class="modal-body">
                <form id="atualizarPostagem" action="atualizarPostagem" method="post">
                <input type="hidden" name="idPostagem" value="${post.idPostagem}">
                    <div class="form-group">
                        <label class="fw-semibold mb-1" for="mensagemAtualizada">Nova postagem:</label>
                        <textarea class="form-control areaB" name="mensagemUpdate" id="mensagemUpdate" cols="30" rows="5"  maxlength="400" style="width: 53vh;" required>${post.mensagem}</textarea>
                    </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="fecharModalAtualizar('${post.idPostagem}')">Fechar</button>
                <button type="submit" form="atualizarPostagem" class="btn btn-primary">Salvar Alterações</button>
            </div>
                </form>
            </div>
        </div>
    </div>
</div>
    	
   

    	<div class="modal fade" id="deletarModal${post.idPostagem}" tabindex="-1" role="dialog" aria-labelledby="deletarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deletarModalLabel">Excluir Postagem</h5>
                <span class="fechar-modal" onclick="fecharModalDeletar('${post.idPostagem}')">&times;</span>
            </div>
            <div class="modal-body">
                <p class="fw-semibold">Tem certeza de que deseja excluir esta postagem?</p>
                <p>#${post.idPostagem}: ${post.mensagem}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="fecharModalDeletar('${post.idPostagem}')">Cancelar</button>
                <form id="excluirPostagem" action="excluirPostagem" method="post">
                    <input type="hidden" name="idPostagem" value="${post.idPostagem}">
                    <button form="excluirPostagem" type="submit" class="btn btn-danger">Excluir</button>
                </form>
            </div>
        </div>
    </div>
</div>
    	
</c:forEach>
            
        </div>
        
        
       <div class="col-md-4 col-sm-12 col-lg-4 userOn" style="margin-left: 20vh">
    <div class="card" id="onlineUsersList">
        <div class="card-header">
            Usuários Online
        </div>
        <div class="card-body">
            <ul id="onlineUsers" class="list-group">
                
                <%
                    ServletContext context = request.getServletContext();
                    Set<String> onlineUsers = (Set<String>) context.getAttribute("onlineUsers");
                    if (onlineUsers != null) {
                        for (String user : onlineUsers) {
                %>
                            <li class="list-group-item"><%= user %></li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
    </div>
</div>
</div>
    </main>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous">
    </script>
    <script src="./js/homeUm.js"></script>
    <script src="./js/homeDois.js"></script>
    <script src="./js/homeTres.js"></script>
  
</body>

</html>
    