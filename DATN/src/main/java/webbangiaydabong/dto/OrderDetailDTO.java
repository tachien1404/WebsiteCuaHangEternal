package webbangiaydabong.dto;

import java.math.BigInteger;
import java.util.Date;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private float price;
    private Integer quantity;
    //    @CreationTimestamp
    private Date createDate;
    private BigInteger product_id;
    private BigInteger order_id;
    private String productName;
    private String photo;
    private String category_name;
    private String brand_name;
    private int status;
    private BigInteger id;

    public String getStatusName() {



        if (this.status == 0) {
            return "Chờ xác nhận !";
        }
        if (this.status == 1) {
            return "Đang chuẩn bị hàng!";
        }
        if (this.status == 2) {
            return "Đang giao hàng!";
        }

        if (this.status == 3) {
            return "Hoàn thành !";
        }

        if (this.status == 4) {
            return "Đã hủy ";
        }
        if (this.status == 5) {
            return "Trả hàng  ";
        }

        return null;
    }
}
