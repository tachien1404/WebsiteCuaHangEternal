package webbangiaydabong.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.dto.functiondto.SortByValue;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategorySearch {
    Long id;
    String name;
    List<SortByValue> sortByValues;
}
