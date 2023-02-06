package webbangiaydabong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "So dien thoai khong duoc bo trong")
    private String sdt;
    @NotNull(message = "Ho ten khong duoc bo trong")
    private String name;
    private String username;
    private String city;
    private String nameCity;
    private String district;
    private String nameDistrict;
    private String ward;
    private String detailAddress;
    @NotNull(message = "Dia chi khong duoc bo trong")
    private String address;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Order> danhSachOrder;
}
