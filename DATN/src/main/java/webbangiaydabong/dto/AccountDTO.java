package webbangiaydabong.dto;

import java.util.Date;
import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Account;
import webbangiaydabong.entity.Authority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	private Long id;
	private String email;
	private String fullname;
	private String username;
	private String password;
	private Date birthday;
	private String address;
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
