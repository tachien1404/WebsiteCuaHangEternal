package webbangiaydabong.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustommerInfoDTO {
	private Long id;
	@NotNull(message = "Ho ten khong duoc de trong")
	private String name;
	@NotNull(message = "Dia chi khong duoc de trong")
	private String address;
	@NotNull(message = "So dien thoai khong duoc de trong")
	private String sdt;
	private Long account_id;
	
	
}
