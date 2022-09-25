package webbangiaydabong.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="image")
public class Image {
@Id
private Long id;
private String photo;
@ManyToOne
@JoinColumn(name="product_id")
private Product product;
}
