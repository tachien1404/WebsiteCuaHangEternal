package webbangiaydabong.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Orderdetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;//giá sản phẩm
    private Integer quantity;//số lượng bán đc
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;//ngày đổi trả
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private Order order;
    @JsonIgnore
    @OneToMany(mappedBy = "orderDetail")
    private List<Exchange> danhSachExchange;
    @ManyToOne
    @JoinColumn(name = "S_C_Details_id")
    private S_C_Details saimau;
}
