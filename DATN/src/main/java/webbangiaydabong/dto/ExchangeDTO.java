package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO {
	private Long id;
	private Date createDate;
	private Long product_id;
	private Long orderdetail_id;
}
