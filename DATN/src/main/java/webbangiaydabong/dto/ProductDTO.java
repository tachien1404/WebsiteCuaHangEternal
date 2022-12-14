package webbangiaydabong.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webbangiaydabong.entity.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String categoryName;
    private String brandName;
    private Long id;
    private String name;


    private Date createDate;


    private float outputprice;
    private Date updatedate;
    private int status;
    private String photo;
    private Long category_id;
    private Long hang_id;

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
