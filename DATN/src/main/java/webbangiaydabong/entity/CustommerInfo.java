package webbangiaydabong.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "custommerinfo")
public class CustommerInfo {
private String name;
private String Address;
@Id
private int id;
private String Sdt;
}
