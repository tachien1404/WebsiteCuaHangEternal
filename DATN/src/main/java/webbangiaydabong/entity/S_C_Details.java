package webbangiaydabong.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="sizecolordetals")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class S_C_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="size_id")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color mau;
    @NotNull
    private Integer quantity;
    private Integer status;
}
