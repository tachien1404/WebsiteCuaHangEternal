package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	private Long id;
	private String email;
	private String fullname;
	private String password;
	private Date birthday;
	private String address;
	private String sdt;
	private String photo;

	public AccountDTO(Account acc) {
		this.address = acc.getAddress();
		this.birthday = acc.getBirthday();
		this.email = acc.getEmail();
		this.fullname = acc.getEmail();
		this.id = acc.getId();
		this.password = acc.getPassword();
		this.photo = acc.getPhoto();
		this.sdt = acc.getPhoto();
	}

}
