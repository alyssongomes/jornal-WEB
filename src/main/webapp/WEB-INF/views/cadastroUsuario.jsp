<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../resources/lib/materialize/materialize.min.css"></link>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="../../resources/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../../resources/lib/materialize/materialize.min.js"></script>
<script type="text/javascript" src="../../resources/js/validates/usuarioLoginValidate.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btnSalvar").attr("disabled", "disabled");
	});
</script>
<title>Cadastrar-se</title>
</head>
<body bgcolor="#263238">
<form action="inserirNovoUsuario" method="post" enctype="multipart/form-data">
	<div style="width: 500px; height:600px; margin-right: auto; margin-top:20px; margin-left: auto; background-color: #ede7f6 ; padding: 20px;  border-radius: 6px;" >
		<center>
			<h5>Cadastre-se agora como Leitor!</h5>
			<hr>
				<div class="row" align="left">
		        	<div class="input-field col s12">
		        		<i class="material-icons prefix">account_circle</i>
			          	<input name="nome" type="text" class="validate">
			          	<label for="nome">Nome</label>
			        </div>
			    </div>
				<div class="row" align="left">
			        <div class="input-field col s12">
			        	<i class="material-icons prefix">email</i>
			          	<input name="email" type="email" class="validate">
			          	<label for="email">Email</label>
			        </div>
			    </div>
				<div class="row" align="left">
		        	<div class="input-field col s12">
		        		<i class="material-icons prefix">perm_identity</i>
			          	<input id="login" name="login" type="text" class="validate" onblur="verificar()">
			          	<label for="nome">Login para acesso</label>
			        </div>
			    </div>
				<div class="row" align="left">
			        <div class="input-field col s12">
			        	<i class="material-icons prefix">vpn_key</i>
			          	<input name="senha" type="password" class="validate">
			          	<label for="password">Senha</label>
			        </div>
			    </div>
			    <div class="file-field input-field" align="left">
			      <div class="btn">
			        <span>Imagem</span>
			        <input name="imagem" type="file">
			      </div>
			      <div class="file-path-wrapper">
			      	<i class="material-icons prefix">person_pin</i>
			        <input class="file-path validate" type="text">
			      </div>
			    </div>
				<input type="checkbox" hidden checked="checked" name="papeis" value="1" ></input><br/>
				<a class="btn" href="/">Voltar</a>
				<input id="btnSalvar" class="btn" type="submit" value="Cadastrar" />
			</center>
		</div>	
</form>
</body>
</html>