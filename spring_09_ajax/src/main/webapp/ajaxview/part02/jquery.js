$(document).ready(function(){
	$('#btn').on('click',process);
});



//data:'name=' + $('#name').val(),
/*
function process(){
	$.ajax({
		type:'POST',
		dataType:'text',
		data:{name:$('#name').val()},
		url:'name.do',
		success : viewMessage
	});
}
*/

function process(){

	$.post('name.do',{name:$('#name').val()}, viewMessage);
}

function viewMessage(res){
	$('#wrap').html(res);
}