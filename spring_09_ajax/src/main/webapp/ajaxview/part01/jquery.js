$(document).ready(function(){
	$('#btn').on('click',process);
});

function process(){
  $.ajax({
  		type:'GET',
  		dataType: 'text',
  		url:'ajaxview/part01/sample.txt',
  		success:viewMessage
  });
}

function viewMessage(res){
	$('#wrap').html(res);
}