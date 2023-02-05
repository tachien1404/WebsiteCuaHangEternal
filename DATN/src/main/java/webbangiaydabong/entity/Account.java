package webbangiaydabong.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Ten dang nhap khong duoc de trong")
    private String username;
    @NotNull(message = "Email khong duoc de trong")
    private String email;
    @NotNull(message = "Ho ten khong duoc de trong")
    private String fullname;
    @NotNull(message = "Mat khau khong duoc de trong")
    private String password;
    private Date birthday;
    @NotNull(message = "Dia chi khong duoc de trong")
    private String address;
    @NotNull(message = "So dien thoai khong duoc de trong")
    private String sdt;
    private String photo;
    private boolean isActive;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    Set<Order> danhSachOrder;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    Set<Authority> authorities;

//    @JsonIgnore
//    @OneToMany(mappedBy = "account")
//    private Set<Promotion> danhSachkhuyenmai;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    Set<CustommerInfo> danhSachDiaChiGiaoHang;
}
