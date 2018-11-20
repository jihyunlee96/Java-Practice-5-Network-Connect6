import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Panel_ThisUser extends Panel_User implements ActionListener {

	JButton giveUp;
	
	public Panel_ThisUser(User user, boolean isBlack)
	{
		super(user, 347, 768, 340, 70, isBlack);
		super.stone_image.setLocation(230, 3);
		super.repaint();
		
		giveUp = new JButton("기\n권");
		giveUp.setSize(40, 64);
		giveUp.setLocation(295, 5);
		giveUp.setForeground(Color.RED);
		
		giveUp.addActionListener(this);
		
		add(giveUp);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		gikwon();
	}
	
	public void gikwon()
	{
		if (isBlack = true)
			Main.frame.board.result = 0;
		else
			Main.frame.board.result = 1;
			
		Main.frame.board.isGameOver = true;
		
		Main.frame.board.add(Main.frame.board.panel_go);
		Main.frame.board.panel_go.setVisible(true);
		Main.frame.board.panel_go.identify_winner();
		
		Main.frame.board.repaint();
	}
}
