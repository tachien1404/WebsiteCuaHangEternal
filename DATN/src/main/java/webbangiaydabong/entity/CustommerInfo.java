package webbangiaydabong.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "custommerinfo")
public class CustommerInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@NotNull(message = "So dien thoai khong duoc bo trong")
		private String sdt;
		@NotNull(message = "Ho ten khong duoc bo trong")
		private String name;
		private String city;
		private String nameCity;
		private String district;
		private String nameDistrict;
		private String ward;
		private String detailAddress;
		private String address;
		private boolean deafault;
		@ManyToOne
		@JoinColumn(name = "account_id")
		private Account account;
		private boolean active;
		@JsonIgnore
		@OneToMany(mappedBy = "diaChi")
		private Set<Order> danhSachOrder;

}
