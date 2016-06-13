<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../resources/css/app.css"></link>
<link rel="stylesheet" type="text/css" href="../../resources/lib/materialize/materialize.min.css"></link>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="../../resources/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/lib/materialize/materialize.min.js"></script>
<script type="text/javascript" src="../../resources/js/controllers/melhorOfertaController.js"></script>
<script type="text/javascript" src="../../resources/js/controllers/mudarPapelController.js"></script>
<title>Home</title>
</head>
<!-- background="/resources/img/textura.jpg" -->
<body bgcolor="#263238" >
	<ul class="dropdown-content" id="jornalista">
		<li><a href="/gerenciarNoticias"><center>Gerenciar Noticias</center></a></li>
	</ul>
	<ul class="dropdown-content" id="editor">
		<li><a href="/cadastrosEditor"><center>Gerenciar Cadastros</center></a></li>
	</ul>
	<ul id="papel" class="dropdown-content">
		<c:forEach var="papel" items="${usuario_logado.papeis}" >
			<li value="${papel.papel }" id="${papel.id }" ><a href="#" onclick="mudarPapel(${papel.id })"><center>${papel.papel }</center></a></li>
		</c:forEach>
	</ul>
	<div class="navbar-fixed">
		<nav>
			<div class="nav-wrapper black darken-1">
				<ul>
					<li style="margin-left: 10px; margin-bottom: 10px;"><h5><img src="resources/img/usuarios/${usuario_logado.path}" width="40" height="40" style="border-radius: 6px;"><b>&nbsp;Bem-vindo(a) </b>${usuario_logado.nome}</h5></li>
				</ul>
				<ul class="right hide-on-med-and-down">
					<c:if test="${papel == 2 }">
						<li><a class="dropdown-button" href="#!" data-activates="editor">Editor<i class="material-icons right">arrow_drop_down</i></a></li>
					</c:if>
					<c:if test="${papel == 3 }">
						<li><a class="dropdown-button" href="#!" data-activates="jornalista">Jornalista<i class="material-icons right">arrow_drop_down</i></a></li>
					</c:if>
				 	<li><a class="dropdown-button" href="#!" data-activates="papel">Papeis<i class="material-icons right">arrow_drop_down</i></a></li>
					<li><a href="logout"><b>Log out</b></a></li>
				</ul>
			</div>
		</nav>
	</div>
	<br/>
	<div id = "noticias" class="noticias-main" style="background-image: url('/resources/img/textura.jpg');">
		<br />
		<center>
			<h3 style="color: black;">Notícias</h3>
		</center>
	  	<div id="modal-noticias">
		  	<form action="listarPorSecao" method="post">
				<div class="input-field col s5 black-text" >
					<select id="secoes" name="id_secao">
				      	<c:forEach var="secao" items="${secoes}">
							<option value="${secao.id}"  name="${secao.titulo}">${secao.titulo}</option>
						</c:forEach>
						<option value="-1" >Todas</option>
				    </select>
			    	<script type="text/javascript">
						$(document).ready(function() {
							$('#secoes').material_select();
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
								<span class="card-title indigo "><a
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
	<div id = "classificados" style="width: 35%; margin-left: 59%;">
		<center>  <!--  <h2><b>Classificados</b></h2>-->
		<c:forEach var="classificado" items="${classificados}">
			<div class="col s12 m8" id="${classificado.id}">
					<div class="card large cyan darken-4" style="height: 600px;">
						<div class="card-image">
							<img src="resources/img/classificados/${classificado.path}">
							<span class="card-title light-blue-text"><h4><b>${classificado.titulo}</b></h4></span>
						</div>
						<div class="card-content">
							<p>
								${classificado.texto}, <b>R$ ${classificado.preco} - ${classificado.telefone}</b>
								<div id="melhorOferta">
							      	<c:if test="${not empty classificado.usuario}">
								      	<p><b>
									      	${classificado.melhor_oferta}, 
									      	<fmt:formatDate value="${classificado.data_oferta.time}" type="date" dateStyle="short" />, 
											${classificado.usuario.nome}
										</b></p>
									</c:if>
								</div>
							</p>
						</div>
						<hr />
						<div id="adicionarMelhorOferta" style="padding: 30px; margin-top: -30px;">
							<input type="hidden" id="autor" value="${usuario_logado.id}" />
			      			<input type="text" id="valor" placeholder="Faça sua oferta"/>
			      			<button class="btn" onclick="adicionarOferta('${classificado.id}')" >Confirmar Oferta</button>
		      			</div>
					</div>
				</div>
		</c:forEach>
		</center>
	</div>
</div>
</body>
</html>