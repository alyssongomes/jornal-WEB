$(function(){
	$("#comentar").click(function(){
		if($("#texto").val() === ""){
			alert("Insera algum texto no comentário!");
		}else{
			comentar($("#autor").val(),$("#noticia").val(),$("#texto").val());
		}
	});
});

function mostrarComentario(comentario){
	$("#texto").val("");
	var d = new Date(comentario.data)
	$("div[id=comentarios]").append("<blockquote style=\" margin: 20px 0; padding-left: 1.5rem; border-left: 5px solid #4a148c;\">" +
			"<h5>"+comentario.texto+"</h5>"+
			"<p>"+comentario.autor+", "+d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()-100)+"</p>" +
			"</blockquote>");
	window.location.href='#end';
}

function comentar(autor,noticia,texto){
	$.ajax({
        url:"/adicionarComentario",
        type:"POST",
        data: "autor="+autor+"&noticia="+noticia+"&texto="+texto,
        success: function(data){
        	data  = JSON.parse(data);
            mostrarComentario(data);
        }
	});
}