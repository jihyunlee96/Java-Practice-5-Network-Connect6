import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Network_Client {
	Thread sender = null;
	ClientSender clientSender = null;
	Thread receiver = null;
	
	static int room_no = -1;
		
	public Network_Client (String nickname) {
		try {
			String serverIp = "127.0.0.1";
			Socket socket = new Socket(serverIp, 9909);
			System.out.println("서버에 연결되었습니다.");
			
			clientSender = new ClientSender(socket, nickname);
			sender = new Thread(clientSender);
			receiver = new Thread(new ClientReceiver(socket));
			
			sender.start();
			receiver.start();
		} catch(ConnectException ce) {
			ce.printStackTrace();
		} catch(Exception e) {}
	}
	
	
	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;
		String name;
		
		ClientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;
			try {
				out = new DataOutputStream(socket.getOutputStream());
			} catch(Exception e) {}
		}
		
		public void run() {
			Scanner scanner = new Scanner(System.in);
			try {
				if(out!=null) {
					out.writeUTF(name);
				}
				
				while(out!=null)
					out.writeUTF("["+name+"]"+scanner.nextLine());
			} catch(IOException e) {}
		}
		
		
		public void sendMsg(String msg) {
			try { out.writeUTF(msg); }
			catch (Exception e) {}
		}
	}
	
	static class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		
		int play_again_ready = 0;
		
		ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch(IOException e) {}
		}
		
		public void run() {
			while(in!=null) {
				try {
					String message = in.readUTF();
					System.out.println(message);
					
					if(message.contains("[Server] Start Game")) {
						String str[] = message.split(",");
						Main.frame.start_game(str[1], str[2]);
					}
					
					else if (message.contains("[stone]")) {
						String str[] = message.split(",");	
						
						if (Integer.parseInt(str[1]) - 1 == room_no)
							Main.frame.board.draw_other_stone(Integer.parseInt(str[2]), Integer.parseInt(str[3]));
					}
					
					else if (message.contains("[Login] Successful")) {
						String str[] = message.split(",");
						
						if (Main.frame.login != null) {
							if (Main.frame.login.id_field.getText().equalsIgnoreCase(str[1])) {						
								Main.frame.nickname = str[1];
								Main.frame.login.close_login();
								
								Main.client.clientSender.name = str[1];
								Main.client.clientSender.out.writeUTF(Main.client.clientSender.name);
							}
						}
					}
					
					else if (message.contains("[chat]")) {
						String str[] = message.split(",");
						
						if (Integer.parseInt(str[1]) - 1 == room_no) {
							message = "[" + str[2] + "] " + str[3]; 
							
							Main.frame.chatroom.chat_out(message);
						}
					}
					
					
					else if (message.contains("[Update Room]")) {
						String str[] = message.split(",");
						Main.frame.start.update_room(Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3]);
					}
					
					else if (message.contains("[Update All Room]")) {
						String str[] = message.split(",");
						
							Main.frame.start.room[0].update_room(str[1], str[2]);
							Main.frame.start.room[1].update_room(str[3], str[4]);
							Main.frame.start.room[2].update_room(str[5], str[6]);
							Main.frame.start.room[3].update_room(str[7], str[8]);
					}
					
										
					if (message.contains("[play again ready]")) {
						String str[] = message.split(",");
						
						if (Integer.parseInt(str[1]) - 1 == room_no)
							play_again_ready ++;
												
						if (play_again_ready >= 2)
							Main.frame.board.panel_go.play_again();
					}
					
				} catch(IOException e) {}
			}
		}
	}
}