<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../resources/lib/materialize/materialize.min.css"></link>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="../../resources/lib/jquery/jquery-ui.min.css"></link>
<script type="text/javascript" src="../../resources/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/lib/materialize/materialize.min.js"></script>
<script type="text/javascript" src="../../resources/lib/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="../../resources/js/validates/usuarioLoginValidate.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		$('select').material_select();
		$("#btnSalvar").attr("disabled", "disabled");
	});
</script>
<title>Visão do Editor</title>
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
					<li style="margin-left: 10px; margin-bottom: 10px;"><h5><a href="/login"><img src="resources/img/usuarios/${usuario_logado.path}" width="40" height="40" style="border-radius: 6px;"></a><b>Bem-vindo(a) </b>${usuario_logado.nome}</h5></li>
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
	
<div id="tabs" style="width: 1000px;height:525px; margin-left: auto; margin-right: auto; margin-top: 10px;">
	<ul>
		<li><a href="#secoes">Seções</a></li>
		<li><a href="#classificados">Classificados</a></li>
		<li><a href="#jornalistas">Jornalistas</a></li>
		<li><a href="#noticias">Noticías do Jornalistas</a></li>
	</ul>
	<div id="secoes">
		<form action="inserirSecao" method="post">
			<h3>Seções</h3>
			<div class="input-field col s8" style="width: 500px;">
				<input type="text" name="titulo" placeholder="Titulo"/>
				<input type="text" name="descricao" placeholder="Descrição da Seção"/>
				<input type="submit" class="btn" value="SALVAR"/>
			</div>
		</form>
	</div>
	<div id="classificados">
		<div style="width: 700px;">
			<form action="inserirClassificado" method="post" enctype="multipart/form-data">
				<h3>Classificados</h3>
				<input type="text" name="titulo" placeholder="Titulo do Classificado" /><br/>
				<input type="text" name="texto" placeholder="Descrição do Classificado" /><br/>
				<input type="text" name="preco" placeholder="Preço" /><br/>
				<input type="text" name="telefone" placeholder="Telefone para Contato" /><br/>
				<div class="file-field input-field" align="left">
			      <div class="btn">
			        <span>Imagem</span>
			        <input name="imagem" type="file">
			      </div>
			      <div class="file-path-wrapper">
			        <input class="file-path validate" type="text" placeholder="Imagem para perfil">
			      </div>
			    </div>
				<input type="submit" class="btn" value="SALVAR"/>
			</form>
		</div>
	</div>
	<div id="jornalistas" style="height: 474px; overflow: scroll;">
		<form action="inserirJornalista" method="post" enctype="multipart/form-data">
			<h3>Jornalista</h3>
			<input type="text" name="nome" placeholder="Nome do Jornalista" />
			<input type="text" name="email" placeholder="Email do Jornalista" />
			<input type="text" id="login" name="login" placeholder="Login do Jornalista" onblur="verificar()" />
			<input type="password" name="senha" placeholder="Senha do Jornalista" />
			<div class="file-field input-field" align="left">
		      <div class="btn">
		        <span>Imagem</span>
		        <input name="imagem" type="file">
		      </div>
		      <div class="file-path-wrapper">
		        <input class="file-path validate" type="text" placeholder="Imagem para perfil">
		      </div>
		    </div>
		    <p>
		      <input name="papeis" value="1" type="checkbox" class="filled-in" id="filled-in-box"/>
		      <label for="filled-in-box">Leitor</label>
		    </p>
		    <p>
		      <input name="papeis" value="2" type="checkbox" class="filled-in" id="filled-in-box-2"/>
		      <label for="filled-in-box-2">Editor</label>
		    </p>
		    <p>
		      <input name="papeis" value="3" type="checkbox" class="filled-in" id="filled-in-box-3"/>
		      <label for="filled-in-box-3">Jornalista</label>
		    </p>
			<input id="btnSalvar" type="submit" class="btn" value="CADASTRAR JORNALISTA"/>
			<table class="striped">
				<thead>
					<tr>
						<th></th>
						<th>NOME</th>
						<th>EMAIL</th>
						<th>EXCLUIR</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="jornalista" items="${jornalistas}">
						<tr>
							<td> <img src="resources/img/usuarios/${jornalista.path}" width="50" height="50"></td>
							<td>${jornalista.nome}</td>
							<td>${jornalista.email}</td>
							<td><a style="color: red;" href="removerJornalista?id=${jornalista.id}">Inativar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<div id="noticias" style="height: 474px; overflow: scroll;">
		<form action="noticiasJornalista" method="post">
			<h3>Notícias por Jornalista</h3>
			<select id="jornalistas" name="id_jornalista">
		      	<c:forEach var="jornalista" items="${jornalistas}">
					<option value="${jornalista.id}"  name="${jornalista.id}">${jornalista.nome}</option>
				</c:forEach>
		    </select>
		    <input class="btn" type="submit" value="Filtrar"/>
		    <div style="height: 50px; overflow: sroll;">
		    	<table class="striped">
			    	<thead>
						<tr>
							<th>TITULO</th>
							<th>SUBTITULO</th>
							<th>DATA DE PUBLICAÇÃO</th>
							<th>ATIVA?</th>
							<th>DESABILITAR</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="noticia" items="${noticiasJornalista}">
							<tr>
								<td>${noticia.titulo}</td>
								<td>${noticia.subtitulo}</td>
								<td><fmt:formatDate value="${noticia.data_noticia.time}" type="date" dateStyle="short" /></td>
								<td>${noticia.habilitado}</td>
								<td><a style="color: red;" href="desabilitarNoticiaEditor?id=${noticia.id}">Desabilitar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		    </div>
		</form>
	</div>
</div>
</body>
</html>