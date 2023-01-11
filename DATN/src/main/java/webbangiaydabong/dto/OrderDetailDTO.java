package webbangiaydabong.dto;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import webbangiaydabong.entity.OrderDetail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private int quantity;
    private float price;

    //    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    private String productName;
    private String photo;
    private String category_name;
    private String brand_name;
    private int status;
    //id ordeteo lấy ra
    private BigInteger id;
    private BigInteger product_id;
    private BigInteger order_id;
    //id deteo tìm kiếm
    private Long detail_id;
    private Long orderId;
    private Long productId;
    private Long scId;
    private Long sizeId;
    private Long colorId;
    private int valuesize;
    private String namecolor;

    public OrderDetailDTO(OrderDetail entity) {
        if (entity.getSaimau() != null) {
            this.scId=entity.getSaimau().getId();
            if (entity.getSaimau().getSize() != null) {
                this.sizeId = entity.getSaimau().getSize().getId();
                this.valuesize = entity.getSaimau().getSize().getValue();
            }
            if (entity.getSaimau().getMau() != null) {
                this.colorId = entity.getSaimau().getMau().getId();
                this.namecolor = entity.getSaimau().getMau().getName();
            }
        }
        if(entity.getProduct()!=null){
            this.productId=entity.getProduct().getId();
            this.productName=entity.getProduct().getName();
            if(entity.getProduct().getCategory()!=null){
                this.category_name=entity.getProduct().getCategory().getName();
            }
            if(entity.getProduct().getHang()!=null){
                this.brand_name=entity.getProduct().getHang().getName();
            }
        }
        this.detail_id=entity.getId();
        this.price= entity.getPrice();
        this.quantity= entity.getQuantity();
    }

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

        if (this.status == 6) {
            return "Đơn hàng nháp";
        }
        if (this.status == 7) {
            return "Đơn off";
        }
        return null;
    }
}
