package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO {
	private int id;
	private String name;
	private Date starttime;
	private Date endtime;
	private Date createDate;
	private Date updatetedDate;
	private int value;
	private Long promotion_id;
	
}
