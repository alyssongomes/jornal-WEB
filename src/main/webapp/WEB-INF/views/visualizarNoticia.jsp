<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../resources/lib/materialize/materialize.min.css"></link>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="../../resources/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/lib/materialize/materialize.min.js"></script>
<script type="text/javascript" src="../../resources/js/controllers/comentarioController.js"></script>
<script type="text/javascript" src="../../resources/js/controllers/mudarPapelController.js"></script>

<title>${noticia.titulo}</title>
</head>
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
					<li style="margin-left: 10px; margin-top: 5px;"><a href="/login"><h5>Home</h5></a></li>
				</ul>
				<ul class="right hide-on-med-and-down">
				 	<c:choose>
						<c:when test="${not empty usuario_logado}" >
							<c:if test="${papel == 2 }">
								<li><a class="dropdown-button" href="#!" data-activates="editor">Editor<i class="material-icons right">arrow_drop_down</i></a></li>
							</c:if>
							<c:if test="${papel == 3 }">
								<li><a class="dropdown-button" href="#!" data-activates="jornalista">Jornalista<i class="material-icons right">arrow_drop_down</i></a></li>
							</c:if>
				 			<li><a class="dropdown-button" href="#!" data-activates="papel">Papeis<i class="material-icons right">arrow_drop_down</i></a></li>
							<li><a href="/logout"><b>Log out</b></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/telaLogin"><b>Log in</b></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
	</div>
<center>
	<div style="background-image:url('resources/img/textura.jpg'); width: 950px; height: auto; " >
		<div style="padding: 30px; width: 900px; height: auto;">
			<center>
				<h3>${noticia.titulo}</h3>
				<h5>${noticia.subtitulo}</h5>
				<hr>
				<h6>Por <b>${noticia.usuario.nome }</b>, em <fmt:formatDate value="${noticia.data_noticia.time}" type="date" dateStyle="short" /> -- Seção: ${noticia.secao.titulo}</h6>
				<img align="left" style="float: left; padding-right: 10px; padding-top: 10px;" src="resources/img/noticias/${noticia.path}">
				<p align="justify">${noticia.texto}</p>
			</center>
			<hr>
			<h5>Comentários</h5>
			<div id="comentarios" align="left" style="height: 250px; overflow: scroll;">
				<c:forEach var="comentario" items="${noticia.comentarios}">
					<blockquote style=" margin: 20px 0; padding-left: 1.5rem; border-left: 5px solid #4a148c;">
						<h5>${comentario.texto}</h5>
						<p>- ${comentario.usuario.nome}, <fmt:formatDate value="${comentario.data.time}" type="date" dateStyle="short" /></p>
					</blockquote>
				</c:forEach>
				<a href="#" id="end"></a>
			</div>
			<c:if test="${not empty usuario_logado }">
				<div id="registrarComentario">
					<input type="hidden" id="noticia" value="${noticia.id}" />
					<input type="hidden" id="autor" value="${usuario_logado.id}" />
					<div class="input-field col m8">
			          <i class="material-icons prefix">comment</i>
			          <input type="text" id="texto"/>
			          <label for="icon_telephone">Seu comentário</label>
			        </div>
					<button class="btn" id="comentar">Enviar</button>
				</div>
			</c:if>
		</div>
	</div>
</center>
</body>
</html>