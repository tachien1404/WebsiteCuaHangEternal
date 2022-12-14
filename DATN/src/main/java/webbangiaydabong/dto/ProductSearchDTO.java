package webbangiaydabong.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;
import webbangiaydabong.entity.ShoeLine;
import webbangiaydabong.entity.Sole;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSearchDTO {
    Long id;
    String name;
    Double outputprice;
    Category category;
    Brand hang;
    Sole sole;
    ShoeLine shoeLine;
    List<SortByValue> sortByValues;

}
