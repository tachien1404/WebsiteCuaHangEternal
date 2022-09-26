package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VanchuyenDTO {
	private Long id;
	private String name;
	private float price;
}
