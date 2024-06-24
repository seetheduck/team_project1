package user;

import lombok.Data;

@Data
public class UserDto {
	private int no, gender, user_stat, signout_is, sign_up_date;
	private String id, pw, uname, email;
}
