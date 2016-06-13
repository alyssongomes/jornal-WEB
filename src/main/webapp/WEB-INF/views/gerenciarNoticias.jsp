<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<script type="text/javascript" src="../../resources/js/validates/noticiasValidate.js"></script>
<title>Gerenciamento de Notícias</title>
</head>
<body bgcolor="#263238">
<ul class="dropdown-content" id="jornalista">
		<li><a href="/gerenciarNoticias"><center>Gerenciar Noticias</center></a></li>
	</ul>
	<ul class="dropdown-content" id="editor">
		<li><a href="/cadastrosEditor"><center>Gerenciar Cadastros</center></a></li>
	</ul>
	<div class="navbar-fixed">
		<nav>
			<div class="nav-wrapper black darken-1">
				<ul>
					<li style="margin-left: 10px; margin-bottom: 10px;"><h5><a href="/login"><img src="resources/img/usuarios/${usuario_logado.path}" width="40" height="40" style="border-radius: 6px;"></a><b>&nbsp;Bem-vindo(a) </b>${usuario_logado.nome}</h5></li>
				</ul>
				<ul class="right hide-on-med-and-down">
					<c:if test="${papel == 2 }">
						<li><a class="dropdown-button" href="#!" data-activates="editor">Editor<i class="material-icons right">arrow_drop_down</i></a></li>
					</c:if>
					<c:if test="${papel == 3 }">
						<li><a class="dropdown-button" href="#!" data-activates="jornalista">Jornalista<i class="material-icons right">arrow_drop_down</i></a></li>
					</c:if>
					<li><a href="logout"><b>Log out</b></a></li>
				</ul>
			</div>
		</nav>
	</div>
<form action="inserirNovaNoticia" method="post" enctype="multipart/form-data">
	<div style="background-color: #ede7f6; padding: 20px;  border-radius: 6px; width: 1100px; height: 500px; overflow: scroll; margin-left: auto; margin-right: auto; margin-top: 30px;">
		<h4>Cadastrar nova Notícia</h4>
		
		<div class="row" align="left">
        	<div class="input-field col s12">
	          	<input type="text" id="titulo" name="titulo" class="validate" onblur="verificar()"/>
	          	<label for="titulo">Titulo da Notícia</label>
	        </div>
	    </div>
	    <div class="row" align="left">
        	<div class="input-field col s12">
	          	<input type="text" name="subtitulo" class="validate"/>
	          	<label for="nome">Subtitulo da Notícia</label>
	        </div>
	    </div>
	    <div class="row">
	        <div class="input-field col s12">
	        	<i class="material-icons prefix">mode_edit</i>
	          	<textarea id="textarea1" name="texto" class="materialize-textarea"></textarea>
	         	<label for="textarea1">Texto da Notícia</label>
	        </div>
      	</div>
		<div class="file-field input-field" align="left">
	    	<div class="btn">
	        	<span>Imagem</span>
	        	<input name="imagem" type="file">
	      	</div>
	      	<div class="file-path-wrapper">
	        	<input class="file-path validate" type="text">
	    	</div>
	    </div>
		<select name="id_secao" style="margin-bottom: 10px;">
			<c:forEach var="secao" items="${secoes}">
				<option value="${secao.id}" >${secao.titulo}</option>
			</c:forEach>
		</select>
		<input type="submit" class="btn" id="btnSalvar" value="SALVAR NOTÍCIA" />
		<br/>
		<table class="striped">
			<thead>
				<tr>
					<td>ID</td>
					<td>DATA</td>
					<td>TITULO</td>
					<td>SUBTITULO</td>
					<td>SECAO</td>
					<td>ATIVA?</td>
					<td>DESABILITAR</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="noticia" items="${noticias}">
					<tr>
						<td>${noticia.id}</td>
						<td><fmt:formatDate value="${noticia.data_noticia.time}" type="date" dateStyle="short" /></td>
						<td>${noticia.titulo}</td>
						<td>${noticia.subtitulo}</td>
						<td>${noticia.secao.titulo}</td>
						<td>${noticia.habilitado}</td>
						<td><a style="color: red;" href="desabilitarNoticia?id=${noticia.id}">Desabilitar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</form>
</body>
</html>