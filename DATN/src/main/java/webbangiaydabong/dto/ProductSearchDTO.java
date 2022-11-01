package webbangiaydabong.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchDTO {
    Long id;
    String name;
    Double inportprice;
    Double outputprice;
    Category category;
    Brand hang;
    List<SortByValue> sortByValues;

}
