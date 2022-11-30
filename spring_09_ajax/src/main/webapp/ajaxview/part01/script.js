let httpRequest;
/*window.onload = function(){

	let btn = document.getElementById("btn");
	btn.onclick = process;
	
}
*/

let btn = document.getElementById("btn");
	btn.onclick = process;


function process(){

	//1. ajax를 처리하기 위한 브라우저별 객체생성을 한다.
	if(window.XMLHttpRequest){
		httpRequest = new XMLHttpRequest();
	}else{
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); //IE6이하
	}
	
	//2. 서버가 응답한 데이터를 처리하기 위한 함수를 등록
	  httpRequest.onreadystatechange = viewMessage;
	
	//3. 서버에 요청준비
	  //open(method, url, 비동기화)
	  httpRequest.open('GET','ajaxview/part01/sample.txt',true);
	  
	//4.서버에 요청
	  httpRequest.send();
}//end process()


//서버로 부터 응답을 받았을때 수행한 함수 정의
function viewMessage(){
	if(httpRequest.readyState==4 && httpRequest.status==200){
		document.getElementById("wrap").innerHTML = httpRequest.responseText;
	}
}//end viewMessage()



