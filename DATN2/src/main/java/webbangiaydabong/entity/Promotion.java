package webbangiaydabong.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Table(name = "promotion")
@Entity
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date starttime;//bắt đầu
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endtime;//kt
    private Date createDate;//ngày tạo
    private Date updatetedDate;//ngày sửa đôiỉ
    private int value;//dis count
    private boolean delete;
    private boolean active;
}
