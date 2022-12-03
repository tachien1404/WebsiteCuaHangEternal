package webbangiaydabong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webbangiaydabong.dto.CustomerDto;
import webbangiaydabong.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select new webbangiaydabong.dto.CustomerDto(c) from Customer c where c.name like :name")
    CustomerDto searchName(String name);
}
