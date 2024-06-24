package user;

import lombok.Data;

@Data
public class UserDto {
	private int no, gender, user_stat, signout_is;
	private String id, pw, uname, email, sign_up_date;
}
