package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColorDTO {
private Long id;
@NotNull(message = "Ten mau khong duoc de trong")
private String name;
private String value;
private boolean isdelete;
}
