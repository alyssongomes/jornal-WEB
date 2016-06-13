function adicionarOferta(idClassificado){
	var adicionarOfertaForm = $("div[id=classificados]").find("div[id="+idClassificado+"]").find("#adicionarMelhorOferta");
	var valor = null;
	if(adicionarOfertaForm.find("#valor").val() === ""){
		alert("Insira um valor!");
	}else{
		try{
			valor = parseFloat(adicionarOfertaForm.find("#valor").val());
		}catch (e) {
			alert("Insira um valor válido");
		}
		adicionarOfertaForm.find("#valor").val("");
		ofertar(idClassificado, parseInt(adicionarOfertaForm.find("#autor").val()), valor);
	}
}

function mostrarMelhorOferta(oferta,idClassificado){
	var melhorOferta = $("div[id=classificados]").find("div[id="+idClassificado+"]").find("#melhorOferta");
	melhorOferta.empty();
	var d = new Date();
	melhorOferta.append("" +
			"<p><b>"+oferta.valor+
			", "+d.getDate()+"/"+d.getMonth()+"/"+d.getFullYear()+
			", "+oferta.usuario+"</b></p>");
	alert(oferta.mensagem);
}

function ofertar(classificado,usuario,valor){
	$.ajax({
        url:"/adicionarMelhorOferta",
        type:"POST",
        data: "classificado="+classificado+"&usuario="+usuario+"&valor="+valor,
        success: function(data){
        	data  = JSON.parse(data);
        	if(data.resultado === true){
        		mostrarMelhorOferta(data,classificado);
        	}else{
        		alert(data.mensagem);
        	}
        }
	});
}