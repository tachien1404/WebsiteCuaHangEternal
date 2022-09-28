package webbangiaydabong.dto.functiondto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webbangiaydabong.dto.OrderDetailDTO;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DatHangDto {
//oderdetai
	private Double price; //giá =soluong * giasp
	private Integer quantity; //số lượng sp mua
	private Date createDate;//ngày tạo--ngày mua
	private Long product_id;
	private Long order_id;
	//oder
	
	private Double tongtien;//tổng tiền của đơn đặt
	private String note;
	private int status;
	private Long account_id;
	private Long van_chuyen_id;
	private Long thanh_toan_id;
	private int promotion_id;//khuyenmai
	Set<OrderDetailDTO>chitiethoadon;
}
