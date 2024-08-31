<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Iniciar sessão no Twittor</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="./estilo/IniciarSessao.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap"
	rel="stylesheet">
<link rel="icon" href="./imagens/lgT.png" type="image/x-icon">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="bg-primary-subtle">
	<header class="container text-center mt-5 mb-1">
		<h1 style="font-family: 'Caveat', cursive; font-size: 60px"
			class="text-danger">Twittor</h1>
	</header>
	<main class="d-flex justify-content-center">
		<div class="card shadow border border-0" style="width: 70vh;">

			<h4 class="text-center mt-4 fw-normal" style="font-size: 20px;">Início
				de sessão no FPX</h4>

			<form
				class="mt-3 d-flex justify-content-center  align-items-center flex-column"
				action="login" method="post">
				<div class="mb-3">
					<div class="d-flex">
						<input type="email"
							class="form-control form-control-lg custom-input <%=request.getAttribute("loginInvalido") != null ? "is-invalid" : ""%>"
							id="email" name="email" aria-describedby="emailHelp"
							maxlength="50" placeholder="E-mail" required style="width: 40vh;">
					</div>

				</div>
				<div class="mb-3">
					<div class="d-flex">
						<input type="password"
							class="form-control form-control-lg custom-input <%=request.getAttribute("loginInvalido") != null ? "is-invalid" : ""%>"
							id="senha" name="senha" aria-describedby="emailHelp"
							minlength="5" maxlength="12" placeholder="Senha" required
							style="width: 40vh;">
					</div>
				</div>
				<%
				String erroLogin = (String) request.getAttribute("erroLogin");
				if (erroLogin != null) {
				%>
				<p class="text-danger mt-1 fw-bold"
					style="font-size: 12px; margin-bottom: -5px;"><%=erroLogin%></p>
				<%
				}
				%>


				<div class="d-flex flex-column text-center w-100 mt-3">
					<button type="submit"
						class="btn btn-primary btn-lg m-auto text-cente fw-bold fs-5"
						style="width: 40vh;">Iniciar sessão</button>
					<a href="#" class="mt-2 text-decoration-none" onclick="modalSenha()">Esqueceu a senha?</a>
				</div>
				<div class="card-body d-flex align-items-center w-100">
					<span class="border border-1 w-100 rounded-5 mx-2"
						style="height: 1px; margin-bottom: 10px;"></span>
					<p>ou</p>
					<span class="border border-1 w-100 rounded-5 mx-2"
						style="height: 1px; margin-bottom: 10px;"></span>
				</div>

				<div class="d-flex justify-content-center w-100 mb-3">
					<a href="Registrar.jsp"><button type="button"
							class="btn btn-success w-100 m-auto text-cente fw-bold fs-6">Criar
							nova conta</button></a>
				</div>
			</form>

		</div>

		<div class="modal" id="myModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Cadastro feito com sucesso!</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Ir para login</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal" id="modalSenha" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="formEmailSenha" action="novaSenha?action=E"
							method="post">

							<div class="d-flex justify-content-center flex-column">
								<div class="mb-3">
									<div class="d-flex">
										<input type="email"
											class="form-control custom-input <%=request.getAttribute("emailInvalido") != null ? "is-invalid" : ""%>"
											id="emailSenha" name="email" aria-describedby="emailHelp"
											maxlength="50" placeholder="E-mail" required
											style="max-width: 500px;">
									</div>
									<p id="mensagemErroEmail" class="text-danger mt-1 fw-bold"
										style="font-size: 10px;"></p>
								</div>

								<div class="w-100 d-flex align-items-center">
									<button type="submit"
										class="btn btn-success btn-registrar fw-bold m-auto">Enviar</button>
								</div>
							</div>


						</form>

						<form action="novaSenha?action=S" method="post" id="formSenha"
							style="display: none">

							<div class="d-flex justify-content-center flex-column">
								<div class="mb-3">
									<div class="d-flex">
										<input type="password" class="form-control custom-input"
											id="senhaAlterada" name="senha" minlength="5" maxlength="12"
											placeholder="Senha" required style="max-width: 500px;">
									</div>
								</div>

								<div class="w-100 d-flex align-items-center">
									<button type="submit"
										class="btn btn-success btn-registrar fw-bold m-auto">Enviar</button>
								</div>
							</div>
						</form>
						<div style="display: none" id="sucesso">
							<h5 class="text-success">Sua senha foi alterada com sucesso!</h5>
						</div>
					</div>
					<div class="modal-footer">
						<div class="w-100 d-flex align-items-center">
							<button id="login" style="display: none"
								class="btn btn-success btn-registrar fw-bold m-auto" onclick="fecharModalSenha()">Ir para login</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>


	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var cadastroSucesso =
	<%=request.getAttribute("cadastroSucesso")%>
		;

			var modal = document.getElementById('myModal');

			if (modal && cadastroSucesso === true) {

				var myModal = new bootstrap.Modal(modal);
				myModal.show();
			}
		});

		function modalSenha() {
			var modal = document.getElementById('modalSenha');
			var modalS = new bootstrap.Modal(modal);
			modalS.show();

		}
		function fecharModalSenha() {
			$('#modalSenha').modal('hide');

		}
	</script>

	<script>
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							const urlParams = new URLSearchParams(
									window.location.search);
							if (urlParams.has('error')) {
								console
										.log('Erro detectado na URL. Tentando abrir o modal.');

								if (urlParams.has('errorEmail')) {
									document
											.getElementById("mensagemErroEmail").textContent = "EMAIL INVÁLIDO, NÃO FOI ENCONTRADO USUÁRIO COM ESTE E-MAIL!";
									document.getElementById("emailSenha").value = "";

									modalSenha();
									document.getElementById("emailSenha").classList
											.add("is-invalid");

								} else {
									console.log('Nenhum erro na URL.');
									document.getElementById("emailSenha").classList
											.remove("is-invalid");
								}
							}
						});
	</script>

	<script>
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							const urlParams = new URLSearchParams(
									window.location.search);
							var formSenha = document
									.getElementById('formSenha');
							var formEmailSenha = document
									.getElementById('formEmailSenha');
							
							if (urlParams.has('valid')) {
								console
										.log('Validação detectada na URL. Tentando abrir o modal.');

								if (urlParams.has('validEmail')) {

									modalSenha();
									formSenha.style.display = 'block';
									formEmailSenha.style.display = 'none';
				

								} else {
									console.log('Nenhuma validação na URL.');
									formSenha.style.display = 'none';
									formEmailSenha.style.display = 'block';
								
								}
							}
						});
	</script>

	<script>
		document.addEventListener("DOMContentLoaded",
						function() {
							const urlParams = new URLSearchParams(
									window.location.search);
							var formSenha = document
									.getElementById('formSenha');
							var login = document.getElementById('login');
							var sucesso = document
									.getElementById('sucesso');
							
							var formEmailSenha = document
							.getElementById('formEmailSenha');
							if (urlParams.has('alterar')) {
								console
										.log('Validação detectada na URL. Tentando abrir o modal.');

								if (urlParams.has('alterarSenha')) {

									modalSenha();
									formSenha.style.display = 'none';
									sucesso.style.display = 'block';
									login.style.display = 'block';
									formEmailSenha.style.display = 'none';

								} else {
									console.log('Nenhuma validação na URL.');
									formSenha.style.display = 'block';
									login.style.display = 'none';
									sucesso.style.display = 'none';

								}
							}
						});
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>