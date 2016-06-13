<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/css/app.css"></link>
<script type="text/javascript" src="../resources/lib/jquery/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/lib/materialize/materialize.min.css"></link>
<script type="text/javascript" src="../resources/lib/materialize/materialize.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<title>Home</title>
</head>
<body bgcolor="#263238">
	<div class="navbar-fixed">
		<nav>
		<div class="nav-wrapper black darken-1">
			<ul>
				<li><a href="/"><h5><b>HOME</b></h5></a></li>
			</ul>
			<ul class="right">
				<li><a href="cadastrarUsuario">Cadastrar-se</a></li>
				<li><a href="telaLogin"><b>Log in</b></a></li>
			</ul>
		</div>
		</nav>
	</div>

	<div class="hero-init">
		<h1>Bem-vindo ao meu Jornal!</h1>
		<p>Esta é a página inicial do meu sistema de gerenciamento de
			nóticias. Saibam que essa porra deu um trabalho danado, então eu
			espero que o professor pelo menos me dê um 10, Alysson agradece! :3</p>
		<p>
		</p>
	</div>

	<br/>
	<div id="noticias" style="background-image: url('/resources/img/textura.jpg');">
		<br />
		<center>
			<h3 style="color: black;">Notícias</h3>
		</center>
		<div id="modal-noticias">
		<form action="listarPorSecaoIndex" method="post">
			<div class="input-field col s5 black-text" >
				<select name="id_secao">
			      	<c:forEach var="secao" items="${secoes}">
						<option value="${secao.id}"  name="${secao.titulo}">${secao.titulo}</option>
					</c:forEach>
					<option value="-1" >Todas</option>
			    </select>
		    	<script type="text/javascript">
					$(document).ready(function() {
						$('select').material_select();
					});
		    	</script>
			</div>
			<input class="btn" type="submit" value="Filtrar"/>
		</form>
		<br/>
			<c:forEach var="noticia" items="${noticias}">
				<div class="row">
					<div class="col m12">
						<div class="card blue-grey darken-1">
							<div class="card-content white-text">
								<span class="card-title"><a
									href="visualizarNoticia?id=${noticia.id}"><h3>${noticia.titulo}</h3></a></span>
								<p>
								<h5>${noticia.subtitulo}</h5>
								</p>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="classificados">
		<center>
			<!--  <h3 style="color: white;">Classificados</h3>-->
			<c:forEach var="classificado" items="${classificados}">
					<div class="col m12">
						<div class="card medium cyan darken-4">
							<div class="card-image">
								<img src="resources/img/classificados/${classificado.path}">
								<span class="card-title light-blue-text"><h4><b>${classificado.titulo}</b></h4></span>
							</div>
							<div class="card-content">
								<p>
									${classificado.texto}, <b>R$ ${classificado.preco}</b>
									<div id="melhorOferta">
								      	<c:if test="${not empty classificado.usuario}">
									      	<p>Melhor oferta: <b>
										      	${classificado.melhor_oferta}, 
										      	<fmt:formatDate value="${classificado.data_oferta.time}" type="date" dateStyle="short" />, 
												${classificado.usuario.nome}
											</b></p>
										</c:if>
									</div>
									<h5><b>${classificado.telefone}</b></h5>
								</p>
							</div>
						</div>
					</div>
			</c:forEach>
		</center>
	</div>


</body>
</html>