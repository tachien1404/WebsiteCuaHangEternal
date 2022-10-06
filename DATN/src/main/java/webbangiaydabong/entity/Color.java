package webbangiaydabong.entity;
import java.util.List;

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
@Table(name="color")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer value;//màu 1 đỏ , 2 vàng ....
	@JsonIgnore
	@OneToMany(mappedBy = "mau")
	List<ColorDetail>danhSachMau;
}
