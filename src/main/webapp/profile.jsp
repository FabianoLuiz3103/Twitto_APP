<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Set" %> 
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Profile Twittor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
     <link rel="stylesheet" href="./estilo/profile.css">
     <link rel="icon"  href="./imagens/lgT.png" type="image/x-icon">
</head>

<body class="bg-primary-subtle">
    <header class="">
        <div class="background-div ms-2 bg-light"></div>
        <div class="containerB mt-5 ms-4">
            <a href="#">
                <img src="./imagens/userSemFoto.png" alt="" class="profile-image" id="profile-image">
            </a>
             <h4 class="fw-bold text-dark mt-2 profile-text">${sessionScope.user}</h4>
        </div>
    </header>

   
    <div class="modalB" id="image-modal">
        <span class="close-button" onclick="fecharModal()">&times;</span>
        <img src="./imagens/userSemFoto.png" alt="" class="modal-image" id="modal-image">
    </div>
   
    
    
    <main class="container" style="margin-top: 10vh;">
     
        
    <div class="row ms-5">
    
        <div class="col-md-6 col-lg-4" style="margin-left:1vh; margin-right:20vh">
        
  <c:forEach var="usuario" items="${usuarios}">
    <c:if test="${usuario.idUsuario eq sessionScope.idUsuario}">
        <div class="card mt-5 card-postagem mb-5">
            <div class="card-header d-flex justify-content-between">
                <span class="mt-2">Usuário: ${sessionScope.user}</span>
                <div class="ml-auto">
                    <div class="dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Editar
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <button class="dropdown-item" onclick="abrirModalAtualizar('${sessionScope.idUsuario}')">Atualizar</button>
                            <button class="dropdown-item" onclick="abrirModalDeletar('${sessionScope.idUsuario}')">Deletar Conta</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <blockquote class="blockquote mb-0">
                    <p>${sessionScope.user}</p>
                    <p>${usuario.email}</p>
                    <div class="d-flex justify-content-between mt-5">
                        <footer class="blockquote-footer">Data de cadastro:</footer>
                        <footer style="margin-top:" class="blockquote-footer date">
    			<c:set var="dataOriginal" value="${usuario.dataCadastro}" />
    			<c:set var="ano" value="${fn:substring(dataOriginal, 0, 4)}" />
    			<c:set var="mes" value="${fn:substring(dataOriginal, 5, 7)}" />
    			<c:set var="dia" value="${fn:substring(dataOriginal, 8, 10)}" />
    
    			${dia}-${mes}-${ano}
				</footer>
                    </div>
                </blockquote>
            </div>
        </div>
        
        <div class="modal fade" id="atualizarModal${sessionScope.idUsuario}" tabindex="-1" role="dialog" aria-labelledby="atualizarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="atualizarModalLabel">Atualizar Dados</h5>
                <span class="fechar-modal" onclick="fecharModalAtualizar('${sessionScope.idUsuario}')">&times;</span>
            </div>
            <div class="modal-body">
                <form
				class="mt-3 d-flex justify-content-center  align-items-center flex-column"
				action="atualizarUsuario" method="post">
				<div class="mb-3">
					<div class="d-flex">
					    <input type="hidden" name="idUsuario" value="${sessionScope.idUsuario}">
						<input type="text" class="form-control custom-input input-celular" id="nome"
							name="nome" maxlength="50" placeholder="Nome completo" required
							style="width: 53vh;" value="${usuario.nome}">
					</div>
				</div>
				<div class="mb-3">
					<div class="d-flex">
						<input type="text" class="form-control custom-input input-celular" id="idade"
							name="idade" maxlength="2" placeholder="Idade" required
							style="width: 53vh;" value="${usuario.idade}">
					</div>
				</div>
				<div class="mb-3">
					<div class="d-flex">
						 <input type="email" class="form-control custom-input input-celular" id="email" name="email" aria-describedby="emailHelp" maxlength="50" placeholder="E-mail" required style="width: 53vh;" value="${usuario.email }">
					</div>
					 <p id="mensagemErroEmail" class="text-danger mt-1 fw-bold" style="font-size: 10px;"></p>

				</div>
				
                <div class="mb-1">
					<div class="d-flex">
						<input type="password" class="form-control custom-input input-celular" id="senha"
							name="senha" minlength="5" maxlength="12" placeholder="Senha" required
							style="width: 53vh;" value="${usuario.senha}">
					</div>
					  <button type="button" id="mostrarSenhaBtn" class="btn btn-outline-primary mt-2">
        				<i class="bi bi-eye-slash" id="icone-olho">Mostrar</i>
    				  </button>
				</div>
				
				<div class="d-flex w-100 mt-3">
						
					<button type="button" class="btn btn-secondary" onclick="fecharModalAtualizar('${sessionScope.idUsuario}')">Cancelar</button>
					<button type="submit"
						class="btn btn-success w-50 m-auto text-cente fw-bold">Atualizar</button>
				</div>
				
			</form>
                
            </div>
        </div>
    </div>
</div>
    	
   

    	<div class="modal fade" id="deletarModal${sessionScope.idUsuario}" tabindex="-1" role="dialog" aria-labelledby="deletarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deletarModalLabel">Excluir Conta</h5>
                <span class="fechar-modal" onclick="fecharModalDeletar('${sessionScope.idUsuario}')">&times;</span>
            </div>
            <div class="modal-body">
                <p class="fw-semibold">Tem certeza de que deseja excluir sua conta?</p>
                <p>#${usuario.nome}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="fecharModalDeletar('${sessionScope.idUsuario}')">Cancelar</button>
                <form id="excluirPostagem" action="excluirUsuario" method="post">
                    <input type="hidden" name="idUsuario" value="${sessionScope.idUsuario}">
                    <button form="excluirPostagem" type="submit" class="btn btn-danger">Excluir</button>
                </form>
            </div>
        </div>
    </div>
</div>
        
        
<a href="listarPosts"><button class="btn btn-dark w-100">IR PARA O FEED</button></a>
    </c:if>
    
    	
</c:forEach>
  
        
  
	</div>
        <div class="col-md-4 col-lg-6">
             <h2 class="mt-3 blinking-text text-center">Suas postagens, ${sessionScope.user}</h2>
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
                        <textarea class="form-control areaB" name="mensagemUpdate" id="mensagemUpdate" cols="30" rows="5"  maxlength="400" style="width: 63vh;" required>${post.mensagem}</textarea>
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
</div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
     <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
     <script src="./js/profileUm.js"></script>
     <script src="./js/profileDois.js"></script>
      <script src="./js/profileTres.js"></script>
      <script>
      document.addEventListener("DOMContentLoaded", function() {
    	    const urlParams = new URLSearchParams(window.location.search);
    	    if (urlParams.has('error')) {
    	        console.log('Erro detectado na URL. Tentando abrir o modal.');

    	        if (urlParams.has('errorEmail')) {
    	            var userId = '<%= session.getAttribute("idUsuario") %>';
    	            document.getElementById("mensagemErroEmail").textContent = "EMAIL INVÁLIDO, O EMAIL JÁ ESTÁ EM USO POR OUTRO USUÁRIO!";
    	            document.getElementById("email").value = "";
    	            
    	            $('#atualizarModal' + userId).modal('show'); 
    	            document.getElementById("email").classList.add("is-invalid");

    	            
    	        } else {
    	            console.log('Nenhum erro na URL.');
    	            document.getElementById("email").classList.remove("is-invalid");
    	        }
    	    }
    	});
      
      var senhaInput = document.getElementById("senha");
      var mostrarSenhaBtn = document.getElementById("mostrarSenhaBtn");
      var iconeOlho = document.getElementById("icone-olho");

      mostrarSenhaBtn.addEventListener("click", function () {
          if (senhaInput.type === "password") {
              senhaInput.type = "text";
              iconeOlho.classList.remove("bi-eye-slash");
              iconeOlho.classList.add("bi-eye");
          } else {
              senhaInput.type = "password";
              iconeOlho.classList.remove("bi-eye");
              iconeOlho.classList.add("bi-eye-slash");
          }
      });


      </script>
    

</body>

</html>
    