package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
private Long id;
private String name;
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
