package webbangiaydabong.dto;

import lombok.*;
import webbangiaydabong.entity.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {
	private Long id;
	private Integer value;
	public SizeDTO (Size entity){
		this.id=entity.getId();
		this.value=entity.getValue();
	}
}
