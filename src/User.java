
// 추후 DB와 연동할 예정
public class User {
	
	String id = "";
	String password = "";
	String nickname = "";
	int win = 0;
	int lose = 0;
	int image = 0;
	
	public User (String in_nickname)
	{
		nickname = in_nickname;
	}
	
	public void win() {  win ++;  }
	
	public void lose() {  lose ++;  }

}
