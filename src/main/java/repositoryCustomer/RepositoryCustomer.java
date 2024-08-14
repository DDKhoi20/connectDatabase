package repositoryCustomer;

import entityCustomer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RepositoryCustomer extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findByPhoneOrEmail(String phone, String email);

    List<Customer> findBySexAndBirthdateBetween(String sex, Date startDate, Date endDate);
}
