package webbangiaydabong.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String fullname;
	private String password;
	private Date birthday;
	private String address;
	private String sdt;
	private String photo;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	Set<Order> danhSachOrder;

	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	Set<Authority> authorities;
	@OneToMany(mappedBy = "account")
	private Set<Promotion> danhSachkhuyenmai;

}
