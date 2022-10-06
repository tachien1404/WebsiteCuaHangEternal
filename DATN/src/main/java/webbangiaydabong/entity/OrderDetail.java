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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Orderdetail")
@Getter
@Setter
public class OrderDetail {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private float price;//giá sản phẩm 
private Integer quantity;//số lượng bán đc 
private Date createDate;//ngày đổi trả 
@ManyToOne
@JoinColumn(name="product_id")
private Product product;
@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name="order_id")
private Order order;
@JsonIgnore
@OneToMany(mappedBy = "orderDetail")
private List<Exchange>danhSachExchange;

}
