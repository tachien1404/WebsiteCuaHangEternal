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
		private String sdt;
		private String name;
		private String address;
		@ManyToOne
		@JoinColumn(name = "account_id")
		private Account account;
		@JsonIgnore
		@OneToMany(mappedBy = "diaChi")
		private Set<Order> danhSachOrder;

}
