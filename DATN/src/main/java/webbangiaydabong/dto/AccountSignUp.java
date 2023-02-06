package webbangiaydabong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSignUp {
    private Long id;
    private String sdt;
    private String email;
    private String fullname;
    private String username;
    private String password;
    private String city;
    private String nameCity;
    private String district;
    private String nameDistrict;
    private String ward;
    private String detailAddress;
    private String address;
}
