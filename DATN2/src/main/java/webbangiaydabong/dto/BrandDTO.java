package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Brand;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
private Long id;
private String name;
public  BrandDTO(Brand entity){
this.id=entity.getId();
this.name= entity.getName();
}
}
