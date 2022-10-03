package webbangiaydabong.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Long id;
	private String name;
	
	private Integer quantity;
	private Date createDate;
	
	private Double inportprice;
	private Double outputprice;
	private Date updatedate;
	private int status;
	
	private Long category_id;
	private Long hang_id;
	public ProductDTO(List<Product> lstProducts) {
for (Product product : lstProducts) {
	this.id=product.getId();
	this.category_id=product.getCategory().getId();
	this.createDate=product.getCreateDate();
	this.hang_id=product.getHang().getId();
	
	this.inportprice=product.getInportprice();
	this.name=product.getName();
	this.outputprice=product.getOutputprice();
	this.quantity=product.getQuantity();
	this.status=product.getStatus();
	this.updatedate=product.getUpdatedate();
}
	}
}
