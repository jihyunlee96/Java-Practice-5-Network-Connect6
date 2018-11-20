import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel_User extends JPanel {

	int width = 310;
	int height = 70;
	
	JLabel image;
	JLabel nickname;
	JLabel level;
	JLabel winlose;
	JLabel stone_image;
	
	User user;
	boolean isBlack;
	
	public Panel_User (User in_user, int x, int y, int in_width, int in_height, boolean black)
	{
		String[] images = {"/Users/jihyunlee/eclipse-workspace/Round5_Connect6(2)/icon_boy.png",
				"/Users/jihyunlee/eclipse-workspace/Round5_Connect6(2)/icon_girl.png"};
		
		width = in_width;
		height = in_height;
		
		user = in_user;
		isBlack = black;
		
		// set size / location for panel
		setSize(width, height);
		setLocation(x, y);
		
		setBackground(new Color(249, 243, 217));
		setLayout(null);
		
		
		// add image label		
		image = new JLabel();
		image.setLocation(3, 3);
		image.setSize(68, 68);
		
		ImageIcon icon = new ImageIcon( images[user.image] );
		image.setIcon(icon);
		
		add(image);

		
		// add nickname label
		nickname = new JLabel(user.nickname);
		nickname.setLocation(88, 3);
		nickname.setSize(200, 30);
		nickname.setFont(new Font("Candara", Font.PLAIN, 12));
		
		add(nickname);
		
		
		// add level label
		int lv = user.win / 5 + 1;
		
		level = new JLabel("Lv. " + lv);
		level.setLocation(173, 3);
		level.setSize(80, 30);
		level.setFont(new Font("Candara", Font.PLAIN, 12));
		
		add(level);
		
		
		// add win/lose label
		float winning_rate;
		if ((user.lose + user.win) != 0)
			winning_rate = (float)user.win / (user.lose + user.win) * 100;
		else
			winning_rate = 0.f;
		
		String str = String.format("Winning rate: %.1f", winning_rate);
		str += " %";
		
		winlose = new JLabel(str);
		winlose.setLocation(88, 40);
		winlose.setSize(200, 30);
		winlose.setFont(new Font("Candara", Font.PLAIN, 12));
		
		add(winlose);
		
		
		// add stone image label
		// add image label		
		stone_image = new JLabel();
		stone_image.setLocation(240, 3);
		stone_image.setSize(68, 68);
		
		ImageIcon icon2;
		Image im;
		
		if (black)
			icon2 = new ImageIcon( "/Users/jihyunlee/eclipse-workspace/Round5_Connect6(2)/blackStone.png" );
		else
			icon2 = new ImageIcon( "/Users/jihyunlee/eclipse-workspace/Round5_Connect6(2)/whiteStone.png" );
		
		im = icon2.getImage();
		im = im.getScaledInstance(44, 44, java.awt.Image.SCALE_SMOOTH);
		icon2 = new ImageIcon(im);
		
		stone_image.setIcon(icon2);
		
		add(stone_image);
		
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
		g2.setColor(Color.WHITE);
		g2.drawLine(0, 0, width, 0);
		g2.drawLine(width, 0, width, height);
		g2.drawLine(width, height, 0, height);
		g2.drawLine(0, height, 0, 0);
	}
	
	public void update()
	{
		// add win/lose label
		float winning_rate;
		if ((user.lose + user.win) != 0)
			winning_rate = (float)user.win / (user.lose + user.win) * 100;
		else
			winning_rate = 0.f;
				
		String str = String.format("Winning rate: %.1f", winning_rate);
		str += " %";
		
		winlose.setText(str);
		
		repaint();
	}
}
