package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Date create_date;
	private Double price;
	private String note;
	private int status;
	private Long account_id;
	private Long van_chuyen_id;
	private Long thanh_toan_id;
	private int promotion_id;
}
