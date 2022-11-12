package webbangiaydabong.entity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="authorities")
public class Authority {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne 
	@JoinColumn(name = "account_id")
	private Account account;
	@ManyToOne  
	@JoinColumn(name = "role_id")
	private Roles role;
}
