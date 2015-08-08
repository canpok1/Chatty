package jp.gr.java_conf.ktnet.chatty;

public class Message {
	
	private String message;
	private String datetime;

	public Message() {
		this(null, null);
	}
	
	public Message(String message) {
		this(message, null);
	}
	
	public Message(String message, String datetime) {
		this.message = message;
		this.datetime = datetime;
	}
	
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}