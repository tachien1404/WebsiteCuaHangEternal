package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbangiaydabong.entity.Brand;
import webbangiaydabong.entity.Category;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
private Long id;
@NotNull(message = "Ten danh muc khong duoc de trong")
private String name;
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
