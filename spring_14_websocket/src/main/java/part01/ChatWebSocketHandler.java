package part01;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler{
	
	//접속한 클라이언트의 정보를 저장할 컬렉션 객체
	public static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	
	public ChatWebSocketHandler() {
	
	}
	
	//클라이언트가 연결 되었을때 호출되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId() + "연결 됨");
		//리스트에 추가
		list.add(session);
	}

	//클라이언트가 메시지를 보냈을 때 호출되는 메소드
	 @Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		//전송된 메시지를 가져옴
		 String msg = (String)message.getPayload();
		 System.out.println("msg => " + msg);
		 //모든 클라이언트에게 메세지 전송
		 for(WebSocketSession socket : list) {
			 //메세지 생성
			 WebSocketMessage<String> sentMsg = new TextMessage(msg);
			 //각 세션에게 메세지를 전송
			 socket.sendMessage(sentMsg);
			 
		 }
	}
	 
	 //클라이언트와의 연결이  끊겼을때 호출되는 메소드
	 @Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	     System.out.println("현재 접속 클라이언트 갯수: " + list.size());
		 // 리스트에서 제거
		 System.out.println(session.getId() + "연결 종료됨");
		 list.remove(session);
		 
		 System.out.println("현재 접속 클라이언트 갯수: " + list.size());
	}
}




