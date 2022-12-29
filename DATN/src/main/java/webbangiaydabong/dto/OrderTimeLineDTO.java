package webbangiaydabong.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import webbangiaydabong.entity.OrderTimeLine;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTimeLineDTO {
    private Long id;
    private String type;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm:ss:aa", timezone="GMT+0700")
    private Date create_date;
    private Long order_id;
    private Long account_id;

    public OrderTimeLineDTO(OrderTimeLine entity){
        this.id=entity.getId();
        this.description=entity.getDescription();
        this.type= entity.getType();
        this.create_date=entity.getCreate_date();

    }
}
