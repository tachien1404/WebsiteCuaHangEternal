package webbangiaydabong.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class salesReportDto {
    private Integer nam;//năm//là tháng luôn
    private Double revenue;//doanh thu

    private Date start;//ngày bắt đầu chọn

    private Date end;//ngày kt chọn
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+0700")
    private Date ngay;//để hứng dữ liệu trả ra
    private Double doanhthuday;//doanh thu ngày hôm nay
    private BigInteger sld; //số lượng đơn bán đc hôm nay
}
