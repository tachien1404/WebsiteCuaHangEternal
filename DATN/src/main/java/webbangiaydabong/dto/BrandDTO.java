package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Brand;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
private Long id;
@NotNull(message = "Ten hang khong duoc de trong")
private String name;
public  BrandDTO(Brand entity){
this.id=entity.getId();
this.name= entity.getName();
}
}
