package webbangiaydabong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name ="cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
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
    @ManyToOne
    @JoinColumn(name = "sole_id")
    private Sole sole;

    @NotNull
    private Integer quantity;
    private String userName;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", product=" + product +
                ", size=" + size +
                ", mau=" + mau +
                ", quantity=" + quantity +
                ", userName='" + userName + '\'' +
                '}';
    }
}
