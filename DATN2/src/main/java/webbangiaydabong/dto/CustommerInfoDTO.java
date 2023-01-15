package webbangiaydabong.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustommerInfoDTO {
	private Long id;
	private String name;
	private String address;
	private String sdt;
	private Long account_id;
	
	
}
