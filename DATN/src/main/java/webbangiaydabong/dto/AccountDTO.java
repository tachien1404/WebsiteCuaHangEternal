package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
