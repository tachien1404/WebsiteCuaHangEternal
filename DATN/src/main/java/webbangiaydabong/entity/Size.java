package webbangiaydabong.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="size")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Size {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Integer value;//38-43
private boolean isdelete;


    @JsonIgnore
    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private List<S_C_Details> danhSachSizeMau;
}

