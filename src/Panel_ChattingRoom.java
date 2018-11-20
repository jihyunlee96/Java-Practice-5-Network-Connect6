import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel_ChattingRoom extends JPanel implements ActionListener {

	int width = 290;
	int height = 470;
	
	JLabel chatroom;
	JTextArea output;
	JTextArea input;
	JButton send;
	
	String out = "";
	
	public Panel_ChattingRoom()
	{
		// set size / location for panel
		setSize(width, height);
		setLocation(700, 365);
		
		
		// add chatroom label
		chatroom = new JLabel("                   Chat Room");
		chatroom.setLocation(1, 1);
		chatroom.setSize(width - 2, 40);
		chatroom.setFont(new Font("Herculanum", Font.PLAIN, 18));
		chatroom.setForeground(Color.WHITE);
		
		add(chatroom);
		
		
		// add output text area
		output = new JTextArea();
		output.setLocation(4, 43);
		output.setSize(width - 4, 320);
		
		add(output);
		
		
		// add input text area
		input = new JTextArea();
		input.setLocation(4, 370);
		input.setSize(width - 4, 66);
		
		add(input);
		
		
		// add send button
		send = new JButton("Send");
		send.setLocation(4, 440);
		send.setSize(width - 4, 22);
		send.addActionListener(this);
		
		add(send);
		
		
		setBackground(new Color(83, 135, 83));
		setLayout(null);
		setVisible(true);
		
		repaint();
	}

	
	public void paintComponent (Graphics g)
	{
		// initiate Graphics2D
		super.paintComponent (g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		// draw frame of the panel
		g2.setStroke(new BasicStroke(3));
		g2.setColor(new Color(163, 215, 163));
		g2.drawLine(0, 0, width, 0);
		g2.drawLine(width, 0, width, height);
		g2.drawLine(width, height, 0, height);
		g2.drawLine(0, height, 0, 0);
	}
	
	
	public void actionPerformed (ActionEvent e)
	{
		Main.client.clientSender.sendMsg("[chat]," + Main.frame.start.selected + "," + Main.frame.nickname + "," + input.getText());
		input.setText("");
		
		repaint();
	}
	
	
	public void chat_out (String str)
	{
		out += "\n";
		out += str;
				
		output.setText(out);
	}
	
}

