package webbangiaydabong.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name="promotion")
@Entity
@Getter
@Setter
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private Date starttime;//bắt đầu 
private Date endtime;//kt
private Date createDate;//ngày tạo
private Date updatetedDate;//ngày sửa đôiỉ 
private int value;//dis count
@ManyToOne
@JoinColumn(name = "account_id")
private Account account;

@OneToMany(mappedBy = "giamgia")
private List<Order>danhSachOrders;

}
