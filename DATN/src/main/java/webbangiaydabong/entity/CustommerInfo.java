package webbangiaydabong.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "custommerinfo")
public class CustommerInfo {
private String name;
private String Address;
@Id
private int id;
private String Sdt;
@ManyToOne
@JoinColumn(name="account_id")
private Account account;
@OneToMany(mappedBy = "diaChi")
private Set<Order>danhSachOrder;

}
