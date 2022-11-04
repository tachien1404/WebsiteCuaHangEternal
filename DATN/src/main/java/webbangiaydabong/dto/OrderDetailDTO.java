package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
	private Long id;
	private float price;
	private Integer quantity;
	@CreationTimestamp
	private Date createDate;
	private Long product_id;
	private Long order_id;
	private String productName;
	private String photo;
	private String category_name;
	private String brand_name;
	private int status;
	public String getStatusName() {


		if(this.status == 0) {
			return "Chờ xác nhận !";
		}
		if(this.status == 1) {
			return  "Đang giao hàng!";
		}

		if(this.status == 2) {
			return  "Hoàn thành !";
		}

		if(this.status == 3) {
			return  "Đã hủy ";
		}

		return null;
	}
}
