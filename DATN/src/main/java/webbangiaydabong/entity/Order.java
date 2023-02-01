package webbangiaydabong.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date create_date;// ngày tạo hóa đơn
	private Double price;// tổng tiền
	private Double shippingFee; // phí ship
	private String note;
	private int status;// 0 chưa xác nhận ,1 đã xác nhận
	private int kenh;//đơn bán tại kênh nào
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "thanh_toan_id")
	private Payment thanhToan;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
	private Set<OrderDetail> danhSachOrder;
//	@ManyToOne
//	@JoinColumn(name = "promotion_id")
//	private Promotion giamgia;
	@ManyToOne
	@JoinColumn(name = "custommerinfo_id")
	private CustommerInfo diaChi;
	@ManyToOne
	@JoinColumn(name = "custommer_id")
	private Customer customer ;
	private Double giamgia;

}
