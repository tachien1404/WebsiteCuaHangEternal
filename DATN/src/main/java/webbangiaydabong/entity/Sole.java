package webbangiaydabong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="sole")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isdelete;
    @JsonIgnore
    @OneToMany(mappedBy = "sole")
    private Set<Product> danhSachProduct;
}
