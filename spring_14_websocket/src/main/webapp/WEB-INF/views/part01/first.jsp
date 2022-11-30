<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript">
	//웹 소켓을 저장할 수 있는 변수를 선언
	let websocket;
	$(document).ready(function() {
		//입장 버튼을 클릭했을 때 이벤트 처리
		$('#enterBtn').on('click', function() {
			//웹 소켓 연결
			websocket = new WebSocket("ws://localhost:8090/myapp/chatws.do");
			//소켓으로 할려면 ws로 해야된다									//dispatcher-context.xml->/chatws.do

			//웹 소켓 이벤트 처리
			websocket.onopen = onOpen; //소켓서버에 연결
			websocket.onmessage = onMessage; //소켓버서에서 메세지 보냈을 때
			websocket.onclose = onClose;//소켓서버에 연결이 종료 되었을 때
		});

		//전송 버튼을 누르 때 이벤트 처리
		$('#sendBtn').on('click', function() {
			messageProcess();

		});

		//message 창에서 Enter를 눌렀을 때도 메시지를 전송
		//키보드를 누를 때 이벤트 처리
		 $('#message').keypress(function(event) {
			 if(event.keyCode == 13){
			    messageProcess();
			 }
		}); 
		
		//퇴장 버튼을 누를 때 이벤트 처리
       $('#exitBtn').on('click', function(){
    	   //웹 소켓 연결 해제
    	   websocket.close();
       });
		
		//브라우저창을 종료할때
		$(window).on('close', function(){
			websocket.close();
		});
		
	});//end ready()/////////////////////////////
	//////////////////////

	function messageProcess() {
		//nickname과 message에 입력된 내용을 서버에 전송
		let nick = $('#nickname').val();//nickname과message를 받고 websocket에 보낸다
		let msg = $('#message').val();
		//메세지 전송
		websocket.send(nick + ":" + msg);
		//메세지 입력창 초기화
		$('#message').val('');
	}//end messageProcess()////////////////////////////

	//WebSocket이 연결된 경우 호출되는 함수
	function onOpen(evt) {
		console.log("웹 소켓에 연결 성공");
	}//end onOpen()//////////////////	

	//서버에서 메시지가 왔을때 호출되는 함수
	function onMessage(evt) {
		//서버가 전송한 메세지 가져오기
		let data = evt.data;
		console.log("data => " + data);
		//메세지 출력
		$('#chatMessageArea').append("<p>" + data + "</p>");
	}//end onMessage()////////////////////////////////

	//WebSocket이 연결 해제된 경우 호출되는 함수
	function onClose(evt) {
		console.log("웹 소켓에 연결 해제");
	}//end onClose()//////////////////////////////
</script>
</head>
<body>
	<span>이름:</span>
	<input type="text" id="nickname" />
	<input type="button" id="enterBtn" value="입장" />
	<input type="button" id="exitBtn" value="나가기" />

	<h1>채팅 영역</h1>
	<div id="charArea">
		<div id="chatMessageArea"></div>
	</div>

	<span>전송할 메시지:</span>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송" />
</body>
</html>










