package webbangiaydabong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="shoeLine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoeLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isdelete;
    @JsonIgnore
    @OneToMany(mappedBy = "shoeLine")
    private Set<Product> danhSachProduct;
}
