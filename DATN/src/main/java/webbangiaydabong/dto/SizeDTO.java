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
	private boolean isdelete;
	public SizeDTO (Size entity){
		this.id=entity.getId();
		this.value=entity.getValue();
		this.isdelete=entity.isIsdelete();
	}
}
