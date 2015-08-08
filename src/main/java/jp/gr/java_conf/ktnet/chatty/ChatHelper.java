package jp.gr.java_conf.ktnet.chatty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatHelper {

//	private static final Logger logger = Logger.getLogger(ChatHelper.class);
	
	private static final String JDBC_CLASS = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite::resource:data/chatty.sqlite";
	
	private static String buildDatabaseUrl(String realPath) {
		return "jdbc:sqlite:" + realPath;
	}
	
	/**
	 * 最新のメッセージを取得します.
	 * @return 最新のメッセージ.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static List<Message> fetchMessage(String datetime)
			throws ClassNotFoundException, SQLException {
		
		Class.forName(JDBC_CLASS);
		
		try(
			Connection connection
				= DriverManager.getConnection(DB_URL);
			Statement statement = connection.createStatement();
		) {
			
			String sql = "SELECT message, update_datetime FROM message";
			if(datetime != null && !datetime.equals("")) {
				sql += " Where update_datetime > '" + datetime + "'";
			}
			sql += " ORDER BY update_datetime asc";
			System.out.println("[sql]" + sql);
	        ResultSet rs = statement.executeQuery(sql);
	
	        // レコード
	        ArrayList<Message> messages = new ArrayList<Message>();
	        
	        while(rs.next()) {
	        	messages.add(
	        			new Message(
	        					rs.getString("message"),
	        					rs.getString("update_datetime")
	        			)
	        	);
	        }
	        
	        return messages;
		}
		
	}
	
	
	/**
	 * メッセージを追加します.
	 * @param msg メッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	public static void saveMessage(Message msg)
			throws ClassNotFoundException, SQLException {
		
		Class.forName(JDBC_CLASS);
		
		if(msg == null) {
			return;
		}
		
		try(
			Connection connection
				= DriverManager.getConnection(DB_URL);
			Statement statement = connection.createStatement();
		) {
			String sql = "INSERT INTO message ("
					   + "id, room_name, user_name, message) "
					   + "VALUES ("
					   + "(select max(id) from message) + 1,"
					   + "'room1',"
					   + "'user1',"
					   + "'" + msg.getMessage() + "')";
			System.out.println("[sql]" + sql);			
			statement.executeUpdate(sql);
		}
	}
	
}