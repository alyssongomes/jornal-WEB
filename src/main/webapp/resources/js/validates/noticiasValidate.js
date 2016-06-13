$(function() {
	$('select').material_select();
	$("#btnSalvar").attr("disabled", "disabled");
});
 
function verificar(){
	if($("#titulo").val() === ""){
		alert("Insera um título para notícia!");
	}else{
		$.ajax({
	        url:"/verificarTitulo",
	        type:"POST",
	        data: "titulo="+$("#titulo").val(),
	        success: function(data){
	        	if(data === false){
	        		$("#btnSalvar").removeAttr('disabled');
	        	}else{
	        		alert("Já existe um notícia com este título!");
	        		$("#btnSalvar").attr("disabled", "disabled");
	        	}
	        }
		});
	}
}
