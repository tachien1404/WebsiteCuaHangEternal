package webbangiaydabong.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import webbangiaydabong.entity.Order;
import webbangiaydabong.entity.OrderDetail;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
private  Long customer_id;
    @JsonFormat(pattern="yyyy-MM-dd")
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
    private String email;
    private String sdtinfo;
    private String nameinfo;
    private String addressinfo;
    private int kenh;
    private String nameCustomer;
    private long idCustomer;
    Set<OrderDetailDTO> lstOrderDetailDTOS;
    public OrderDTO(Order entity, boolean onlyVanBang) {
        this.id = entity.getId();
        this.create_date = entity.getCreate_date();
        this.price = entity.getPrice();
        this.status = entity.getStatus();
        this.note = entity.getNote();
        if (onlyVanBang && entity.getAccount() != null) {
            this.account_id = entity.getAccount().getId();
            this.name_acount = entity.getAccount().getFullname();
            this.sdt = entity.getAccount().getSdt();
            this.email = entity.getAccount().getEmail();
            this.address=entity.getAccount().getAddress();
        }
        if (entity.getDiaChi() != null) {
            this.addressinfo = entity.getDiaChi().getAddress();
            this.sdtinfo = entity.getDiaChi().getSdt();
            this.nameinfo = entity.getDiaChi().getName();
        }
        if (entity.getDanhSachOrder() != null && entity.getDanhSachOrder().size() > 0) {
            this.lstOrderDetailDTOS = new HashSet<OrderDetailDTO>();
            for (OrderDetail deteo  : entity.getDanhSachOrder()) {
                OrderDetailDTO dto = new OrderDetailDTO();

                dto.setProductName(deteo.getProduct().getName());
                dto.setQuantity(deteo.getQuantity());
                dto.setPrice(deteo.getPrice());
               dto.setBrand_name(deteo.getProduct().getHang().getName());
               dto.setCategory_name(deteo.getProduct().getCategory().getName());
                this.lstOrderDetailDTOS.add(dto);
            }
        }
        if(entity.getCustomer()!=null){
            this.nameCustomer=entity.getCustomer().getName();
            this.idCustomer=entity.getCustomer().getId();
        }
    }
    public String getKenhName() {

        if (this.status == 0) {
            return "web !";
        }
        if (this.status == 1) {
            return "tại quầy!";
        }
        if (this.status == 2) {
            return "page!";
        }
        return null;
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
