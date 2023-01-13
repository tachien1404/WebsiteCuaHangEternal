package webbangiaydabong.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.Size;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class S_C_DetailSearch {
    Long id;
    Product product;
    Size size;
    Color mau;
    private Integer quantity;
    private Integer status;
    List<SortByValue> sortByValues;
}
