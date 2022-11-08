package webbangiaydabong.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="sizecolordetals")
@Data
@Getter
@Setter
public class S_C_Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="size_id")
    private size size;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color mau;

    private Integer quantity;
    private Integer status;
}
