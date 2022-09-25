package webbangiaydabong.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="size")
@Getter
@Setter
public class size {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Integer value;//38-43
@OneToMany(mappedBy = "size")
private List<SizeDetail>danhSachSize;
}
