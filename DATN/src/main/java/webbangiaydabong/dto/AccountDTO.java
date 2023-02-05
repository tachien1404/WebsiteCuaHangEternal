package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Account;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	private Long id;
	private String email;
	@NotNull(message = "Ho ten khong duoc de trong")
	private String fullname;
	@NotNull(message = "Ten dang nhap khong duoc de trong")
	private String username;
	@NotNull(message = "Mat khau khong duoc de trong")
	private String password;
	private Date birthday;
	@NotNull(message = "Dia chi khong duoc de trong")
	private String address;
	@NotNull(message = "So dien thoai khong duoc de trong")
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
		this.username=acc.getUsername();
		this.sdt = acc.getPhoto();
	}

}
