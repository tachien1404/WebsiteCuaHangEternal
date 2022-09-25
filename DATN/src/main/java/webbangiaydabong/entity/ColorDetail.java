package webbangiaydabong.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "colordetails")
public class ColorDetail {
	@Id
private Long id;
@ManyToOne
@JoinColumn(name = "color_id")
private Color mau;
@ManyToOne
@JoinColumn(name="product_id")
private Product product;

}
