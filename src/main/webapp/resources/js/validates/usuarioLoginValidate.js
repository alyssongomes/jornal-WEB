function verificar(){
	if($("#login").val() === ""){
		alert("Insera um login para usuário!");
	}else{
		$.ajax({
	        url:"/verificarLogin",
	        type:"POST",
	        data: "login="+$("#login").val(),
	        success: function(data){
	        	if(data === false){
	        		$("#btnSalvar").removeAttr('disabled');
	        	}else{
	        		alert("Já existe um usuário com este login!");
	        		$("#btnSalvar").attr("disabled", "disabled");
	        	}
	        }
		});
	}
}