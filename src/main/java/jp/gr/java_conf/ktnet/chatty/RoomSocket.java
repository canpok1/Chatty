package jp.gr.java_conf.ktnet.chatty;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class RoomSocket {

//	@OnMessage
//	public void broadcast(String message, Session session) {
//		for(Session s : session.getOpenSessions()) {
//			s.getAsyncRemote().sendText(message);
//		}
//	}
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("opened");
	}
	
	@OnMessage
	public String echo(String message) {
		System.out.println(message);
		return message;
	}
	
	@OnClose
	public void onOpen(CloseReason reason) {
		System.out.println("closed");
	}
	
}
