package webbangiaydabong.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webbangiaydabong.entity.Customer;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    @NotNull(message = "So dien thoai khong duoc de trong")
    private String sdt;
    @NotNull(message = "Ho ten khong duoc de trong")
    private String name;
    @NotNull(message = "Dia chi khong duoc de trong")
    private String address;
    private String email;
    private Set<OrderDTO> danhSachOrder;
    private String nameCity;
    private String nameDistrict;
    public CustomerDto(Customer entity) {
        this.address = entity.getAddress();
        this.sdt = entity.getSdt();
        this.name = entity.getName();
        this.id = entity.getId();
        this.email = entity.getEmail();

    }
}
