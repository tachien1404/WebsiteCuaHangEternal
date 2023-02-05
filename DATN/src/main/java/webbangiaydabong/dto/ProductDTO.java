package webbangiaydabong.dto;

import java.math.BigInteger;
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
    private Integer status;
    private String photo;
    private Long category_id;
    private Long hang_id;
    private Long sole_id;
    private String soleName;
    private String shoelineName;
    private Long shoeLine_id;
    private Float startgia;
    private Float endgia;
  private  Long sumquantity=0L;//to



    public ProductDTO(Product entity){
        this.id=entity.getId();
        this.name= entity.getName();

        this.createDate=entity.getCreateDate();
        this.photo=entity.getPhoto();
        this.outputprice=entity.getOutputprice();
        this.status= entity.getStatus();
        this.updatedate=entity.getUpdatedate();
        if(entity.getCategory()!=null){
            this.category_id=entity.getCategory().getId();
            this.categoryName=entity.getCategory().getName();
        }
        if(entity.getHang()!=null){
            this.brandName=entity.getHang().getName();
            this.hang_id=entity.getHang().getId();
        }
        if(entity.getShoeLine()!=null){
            this.shoelineName=entity.getShoeLine().getName();
            this.shoeLine_id=entity.getShoeLine().getId();
        }
        if(entity.getSole()!=null){
            this.sole_id=entity.getSole().getId();
            this.soleName=entity.getSole().getName();
        }
    }
    public String getStatusName() {

        if (this.status == 1) {
            return "Kinh Doanh!";
        }
        if (this.status == 0) {
            return "Ngừng kinh doanh!";
        }
        return null;
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
