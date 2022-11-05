package webbangiaydabong.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name="salecode")
@Entity
@Getter
@Setter
public class SaleCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date starttime;//bắt đầu
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endtime;//kt
    private Date createDate;//ngày tạo
    private Date updatetedDate;//ngày sửa đôiỉ
    private int value;//dis count
    private boolean delete;
}
