package webbangiaydabong.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import webbangiaydabong.dto.functiondto.SortByValue;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionSearch {
    Long id;
    String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date starttime;//bắt đầu
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endtime;//kt
    private int value;//dis count
    List<SortByValue> sortByValues;
}
