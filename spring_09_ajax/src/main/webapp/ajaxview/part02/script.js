let httpRequest;

let btn = document.querySelector("#btn")//아이디엔#이붙는다
btn.onclick= process;

function process(){
	//1.ajax를 처리하기 위한 브라우별 객체생성을 한다.
	if(window.XMLHttpRequest){
		httpRequest = new XMLHttpRequest();
	}else{
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP");//IE6이하
	}
	
	//2. 서버가 응답한 데이터를 처리하기 위한 함수를 정의 한 후 등록을 한다.
	httpRequest.onreadystatechange = viewMessage;
	
	//3. 서버에 요청준비를 한다.
	httpRequest.open('POST','name.do',true);
	
	//post방식이면서 넘기는 파라미터값이 있으면 해야되는 코드
	httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	//4.서버에 요청을 한다.post일때 여기서 넘겨준다
	httpRequest.send('name=' + document.querySelector("#name").value);
	
	
}//end process()


//서버로 부터 정상적으로 응답을 받았을 때 수행할 함수 정의
function viewMessage(){
	if(httpRequest.readyState==4 && httpRequest.status==200){
		document.querySelector("#wrap").innerHTML = httpRequest.responseText;
	}


}//end viewMessage()











