package webbangiaydabong.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Long id;
	private Date create_date;
	private Double price;
	private String note;
	private int status;
	private Long account_id;
	private Long van_chuyen_id;
	private Long thanh_toan_id;
	private int promotion_id;
	private String name_acount;
	private String address;
	private String sdt;
	
	public OrderDTO(Order entity, boolean onlyVanBang) {
		this.id = entity.getId();
		this.create_date=entity.getCreate_date();
		this.price=entity.getPrice();
		this.status=entity.getStatus();
		this.note=entity.getNote();
		if (onlyVanBang && entity.getAccount() != null) {
			this.name_acount = entity.getAccount().getFullname();
		}
		this.address=entity.getAccount().getAddress();
this.sdt=entity.getAccount().getSdt();
	}
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
