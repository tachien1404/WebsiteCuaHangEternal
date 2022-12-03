package webbangiaydabong.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webbangiaydabong.entity.Customer;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String sdt;
    private String name;
    private String address;

    private Set<OrderDTO> danhSachOrder;
    public  CustomerDto(Customer entity){
        this.address= entity.getAddress();
        this.sdt=entity.getSdt();
        this.name= entity.getName();
        this.id=entity.getId();

    }
}
