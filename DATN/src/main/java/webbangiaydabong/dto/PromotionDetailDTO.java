package webbangiaydabong.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.entity.Product;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionDetailDTO {
    Long id;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date starttime;//bắt đầu
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endtime;//kt
    Date createDate;//ngày tạo
    Date updatetedDate;//ngày sửa đôiỉ
    Integer value;//dis count
    boolean delete;
    boolean active;
    List<Product> listProduct;
}
