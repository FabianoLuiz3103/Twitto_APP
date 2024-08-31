<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Registrar-me no Twittor</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
     <link rel="stylesheet" href="./estilo/Registrar.css">
     
 <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">

<link rel="icon"  href="./imagens/lgT.png" type="image/x-icon">

</head>
<body class="bg-primary-subtle">

<header class="container text-center mt-5 mb-1">
    <h1 style="font-family: 'Caveat', cursive; font-size: 60px" class="text-danger">Twittor</h1>
</header>
<main class="d-flex justify-content-center">
    <div class="card shadow border border-0">
        <div class="card-header bg-light">
            <h4 class="text-center">Criar uma nova conta</h4>
            <h6 class="text-center fw-lighter">É rápido e fácil</h6>
        </div>

        <form
            class="mt-3 d-flex justify-content-center align-items-center flex-column"
            action="novoUsuario" method="post">
            <div class="mb-3">
                <div class="d-flex">
                    <input type="text" class="form-control custom-input" id="nome"
                        name="nome" maxlength="50" placeholder="Nome completo" required style="width: 40vh">
                </div>
            </div>
            <div class="mb-3">
                <div class="d-flex">
                    <input type="text" class="form-control custom-input" id="idade"
                        name="idade" maxlength="2" placeholder="Idade" required style="width: 40vh">
                </div>
            </div>
            <div class="mb-3">
                <div class="d-flex">
                     <input type="email" class="form-control custom-input <%= request.getAttribute("emailInvalido") != null ? "is-invalid" : "" %>" id="email" name="email" aria-describedby="emailHelp" maxlength="50" placeholder="E-mail" required style="width: 40vh">
                </div>
                <%
                String mensagemErroEmail = (String) request.getAttribute("mensagemErroEmail");
                if (mensagemErroEmail != null) {
                %>
                <p class="text-danger mt-1 fw-bold" style="font-size: 10px; margin-bottom: -5px;"><%=mensagemErroEmail%></p>
                <%
                }
                %>
            </div>
            <div class="mb-3">
                <div class="d-flex">
                    <input type="password" class="form-control custom-input"
                        id="senha" name="senha" minlength="5" maxlength="12" placeholder="Senha"
                        required style="width: 40vh">
                </div>
            </div>

            <div class="mx-5">
                <p class="fw-lighter m-auto" style="font-size: 12px;">Ao clicar
                    em Registar-se, estará de acordo com os nossos Termos, a nossa
                    Política de Privacidade e a nossa Política de Cookies.</p>
            </div>
            <div class="d-flex w-100 mt-3">
                <button type="submit"
                    class="btn btn-success btn-registrar fw-bold m-auto">Registrar-se</button>
            </div>
            <div class="mt-3 mb-3">
                <a href="IniciarSessao.jsp"
                    class=" text-center text-decoration-none fs-5 text-primary"
                    style="font-size: 10px;">Já tem uma conta?</a>
            </div>
        </form>
    </div>
</main>

<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
</body>
</html>
