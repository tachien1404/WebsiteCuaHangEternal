package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	private String name;
	private float price;
	private Integer quantity;
	private Date createDate;
	private String photo;
	private Double inportprice;
	private Double outputprice;
	private Date updatedate;
	private String status;
	private Long image;
	private Long category_id;
	private Long hang_id;
}
