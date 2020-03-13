package model;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/weball")
public class WebSocketServer {
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnMessage
	public void onMessage(String message,Session session) throws IOException
	{
		System.out.println(message);
		
		synchronized (clients) 
		{
			/* 메시지는 '이름: '추가해서 보내기. 귓속말은 메시지 입력창에 [번호] 입력하면 됨 admin 창에만 번호를 보이게 해서 admin은 귓속말을 할 수 있게 함 */
			String id = null;
			
			if(message.indexOf(":]")>0)
			{
				id = message.substring(message.indexOf(":[")+2, message.indexOf("]"));
				System.out.println("id:["+id+"]");
			}
			
			String movemessage = session.getId() + ":" + message;
			for(Session client : clients) //자기 자신에게는 보내지 않음
			{
				if(!client.equals(session))
				{
					if(id == null)
					{
						client.getBasicRemote().sendText(movemessage);
					} else if(id.equals(client.getId())) //귓속말 보내
					{
						client.getBasicRemote().sendText(movemessage);
					}
				}
			}
		}
	}
	
	@OnOpen
	public void onOpen(Session session)
	{
		//Add session to the connected sessions set
		System.out.println(session.getId());
		clients.add(session);
	}
	
	@OnClose
	public void onClose(Session session)
	{
		//Remove session from the connected sessions set
		clients.remove(session);
	}

}
