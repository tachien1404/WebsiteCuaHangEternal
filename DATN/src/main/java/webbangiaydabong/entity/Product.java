package webbangiaydabong.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private Integer quantity;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createDate;// ngày tạo

	private Double inportprice;// giá nhập
	private Double outputprice;// giá xuất
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updatedate;// ngày sửa sẳn phẩm
	private Integer status;
	private boolean delete;
//	@JsonIgnore
//	@OneToMany(mappedBy = "product")
//	private Set<Image>listanh;// ảnh
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;// danhmuc
	@ManyToOne
	@JoinColumn(name = "hang_id")
	private Brand hang;


	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<S_C_Details> danhSachSizeMau;

//	@JsonIgnore
//	@OneToMany(mappedBy = "product")
//	private List<ColorDetail> danhSachMau;
////	@JsonIgnore
////	@OneToMany(mappedBy = "size")
////	private List<SizeDetail> danhSachSize;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Image> danhSachAnh;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Exchange> danhSachExchange;
}
