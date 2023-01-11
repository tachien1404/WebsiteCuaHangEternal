package webbangiaydabong.dto.functiondto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class SearchDto {
    private Long id;
    private int pageIndex;
    private int pageSize;
    private String keyword;
    private Date end;
    private Date start;
    private Date date;
    private Integer status;
}
