package webbangiaydabong.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    private String code; //mã khuyến mại
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date starttime;//bắt đầu
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endtime;//kt
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;//ngày tạo
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatetedDate;//ngày sửa đôiỉ
    private int value;//dis count
    private boolean delete;
    private int status;

}
