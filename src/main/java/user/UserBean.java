package user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserBean {
	private int no, gender, user_stat, signout_is, sign_up_date;
	private String id, pw, uname, email;
	
}
