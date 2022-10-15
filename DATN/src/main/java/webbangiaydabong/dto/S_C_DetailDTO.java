package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class S_C_DetailDTO {
    private Long id;
    private Long product_id;
    private Long size_id;
    private Long color_id;
    private Integer quantity;
    private Integer status;
}
