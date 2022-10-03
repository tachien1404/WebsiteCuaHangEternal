package webbangiaydabong.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="image")
public class Image {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String photo;
@ManyToOne
@JoinColumn(name="product_id")
private Product product;
}
