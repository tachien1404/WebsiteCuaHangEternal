package webbangiaydabong.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.NumberFormat;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.*;

import java.util.Date;
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
    Float priceStart;
    Float priceEnd;
    Category category;
    Brand hang;
    Sole sole;
    ShoeLine shoeLine;
    List<SortByValue> sortByValues;

}
