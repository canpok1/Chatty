package jp.gr.java_conf.ktnet.chatty;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/room")
public class RoomResource {

	@GET
	@Path("/message")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage(@QueryParam("datetime") String datetime)
			throws ClassNotFoundException, SQLException {
		List<Message> messages = ChatHelper.fetchMessage(datetime);
		
		String response = "";
		for(Message message : messages) {
			if(!response.equals("")) {
				response += ",";
			}
			response += "{"
					 + "\"msg\":\"" + message.getMessage() + "\","
					 + "\"datetime\":\"" + message.getDatetime() + "\"}";
		}
		return "{\"msgs\":[" + response + "]}";
	}
	
	@POST
	@Path("/message")
	public void postMessage(@QueryParam("msg") String newMessage)
			throws ClassNotFoundException, SQLException {
		Message message = new Message(newMessage);
		ChatHelper.saveMessage(message);
	}
}
