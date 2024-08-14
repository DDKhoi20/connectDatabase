import config.SpringConfig;
import entityCustomer.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositoryCustomer.RepositoryCustomer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MainCustomer {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static RepositoryCustomer repositoryCustomer = context.getBean(RepositoryCustomer.class);

    public static void main(String[] args) {

        createCustomer("John Doe", Date.valueOf(LocalDate.of(1994, 5, 15)), "M", "john.doe@example.com", "123-456-7890", "123 Elm St");
        createCustomer("Jane Smith", Date.valueOf(LocalDate.of(1988, 10, 25)), "F", "jane.smith@example.com", "987-654-3210", "456 Oak St");

        listAllCustomers();

        findCustomerById(1);

        findCustomersByName("Jane Smith");

        findCustomersByPhoneOrEmail("987-654-3210", "jane.smith@example.com");

        updateCustomerAddress(1, "Da Nang");

    }

    private static void createCustomer(String name, Date birthdate, String sex, String email, String phone, String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setBirthdate(birthdate);
        customer.setSex(sex);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);

        Customer savedCustomer = repositoryCustomer.save(customer);
        System.out.println("Customer saved: " + savedCustomer);
    }

    private static void listAllCustomers() {
        List<Customer> customers = repositoryCustomer.findAll();
        System.out.println("All customers:");
        customers.forEach(System.out::println);
    }

    private static void findCustomerById(long id) {
        Optional<Customer> customerOptional = repositoryCustomer.findById(id);
        if (customerOptional.isPresent()) {
            System.out.println("Found customer with ID " + id + ": " + customerOptional.get());
        } else {
            System.out.println("No customer found with ID " + id);
        }
    }

    private static void findCustomersByName(String name) {
        List<Customer> customers = repositoryCustomer.findByName(name);
        System.out.println("Customers with name " + name + ":");
        customers.forEach(System.out::println);
    }

    private static void findCustomersByPhoneOrEmail(String phone, String email) {
        List<Customer> customers = repositoryCustomer.findByPhoneOrEmail(phone, email);
        System.out.println("Customers with phone " + phone + " or email " + email + ":");
        customers.forEach(System.out::println);
    }

    private static void updateCustomerAddress(long id, String newAddress) {
        Optional<Customer> customerOptional = repositoryCustomer.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setAddress(newAddress);
            repositoryCustomer.save(customer); // Save changes
            System.out.println("Updated customer with ID " + id + ": New address is " + newAddress);
        } else {
            System.out.println("No customer found with ID " + id);
        }
    }

    private static void deleteCustomer(long id) {
        repositoryCustomer.deleteById(id);
        System.out.println("Customer with ID " + id + " has been deleted.");
    }
}
