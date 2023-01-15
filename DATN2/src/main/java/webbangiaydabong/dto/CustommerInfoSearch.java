package webbangiaydabong.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.dto.functiondto.SortByValue;
import webbangiaydabong.entity.Account;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustommerInfoSearch {
    Long id;
    String sdt;
    String name;
    String address;
    Account account;
}
