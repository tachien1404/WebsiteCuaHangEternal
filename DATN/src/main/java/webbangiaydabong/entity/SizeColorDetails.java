package webbangiaydabong.entity;

import javax.persistence.*;

@Entity
@Table(name ="sizecolordetals")
public class SizeColorDetails {
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
