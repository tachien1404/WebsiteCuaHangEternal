package webbangiaydabong.dto;

import java.util.Date;
import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Authority;

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
	private boolean active;
	private boolean role;

	public AccountDTO(Account acc) {
		this.address = acc.getAddress();
		this.birthday = acc.getBirthday();
		this.email = acc.getEmail();
		this.fullname = acc.getFullname();
		this.id = acc.getId();
		this.password = acc.getPassword();
		this.photo = acc.getPhoto();
		this.username=acc.getUsername();
		this.sdt = acc.getSdt();
		this.active = acc.isActive();
		this.role = true;
		Iterator<Authority> iterator = acc.getAuthorities().iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getRole().getName().equals("ROLE_USER")) {
       		 this.role = false;	
       		 break;
            }
        }
	}

}
