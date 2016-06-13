<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../resources/lib/jquery/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../resources/lib/materialize/materialize.min.css"></link>
<script type="text/javascript" src="../../resources/lib/materialize/materialize.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Login</title>
</head>
<body bgcolor="#263238">
	<div style="border-radius:10px; width: 400px; height:300px; margin-right: auto; margin-left: auto; margin-top: 11%; background-color: #e3f2fd; padding: 20px;">
		<center><h4>Tela de Acesso</h4>
			<form action="login" method="post"><!-- contatoForm criado no scope -->
				<div class="input-field col s6">
		          <i class="material-icons prefix">account_circle</i>
		          <input type="text" name="login" style="margin-bottom: 10px; margin-right: 10px"/>
		          <label for="icon_prefix">Login</label>
		        </div>
		        <div class="input-field col m6">
		          <i class="material-icons prefix">lock_outline</i>
		          <input type="password" name="senha" style="margin-bottom: 10px; margin-right: 10px"/>
		          <label for="icon_telephone">Senha</label>
		        </div>
		        <a class="btn" href="/">Voltar</a>
		        <button class="btn" >Entrar</button>
		    </form>
	    </center>
	</div>
</body>
</html>