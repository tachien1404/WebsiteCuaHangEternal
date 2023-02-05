package webbangiaydabong.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webbangiaydabong.entity.Product;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String categoryName;
    private String brandName;
    private Long id;
    @NotNull(message = "Ten san pham khong duoc de trong")
    private String name;
    private Date createDate;
    @NotNull(message = "Gia ban khong duoc de trong")
    private float outputprice;
    private Date updatedate;
    private int status;
    private String photo;
    private Long category_id;
    private Long hang_id;
    private Long sole_id;
    private Long shoeLine_id;
    private Float startgia;
    private Float endgia;
    public ProductDTO(Product entity){
        this.id=entity.getId();
        this.name= entity.getName();

        this.createDate=entity.getCreateDate();
        this.photo=entity.getPhoto();
        this.outputprice=entity.getOutputprice();
        this.status= entity.getStatus();
        this.updatedate=entity.getUpdatedate();
    }
    public ProductDTO(List<Product> lstProducts) {
        for (Product product : lstProducts) {
            this.id = product.getId();
            this.category_id = product.getCategory().getId();
            this.createDate = product.getCreateDate();
            this.hang_id = product.getHang().getId();


            this.name = product.getName();
            this.outputprice = product.getOutputprice();

            this.status = product.getStatus();
            this.updatedate = product.getUpdatedate();
        }
    }
}
