function mudarPapel(idPapel){
	$.ajax({
        url:"/mudarPapel",
        type:"POST",
        data: "papel="+idPapel,
        success: function(data){
        	alert(data);
            location.reload();
        }
	});
}