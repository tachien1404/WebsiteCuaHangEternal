package webbangiaydabong.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.entity.Color;
import webbangiaydabong.entity.Product;
import webbangiaydabong.entity.Sole;
import webbangiaydabong.entity.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FindQuantity {
    Long id;
    Product product;
    Size size;
    Color mau;
    Sole sole;
}
