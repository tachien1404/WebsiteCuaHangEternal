package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
	private Long id;
	private float price;
	private Integer quantity; 
	private Date createDate;
	private Long product_id;
	private Long order_id;
}
