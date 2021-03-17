package pl.polo.devicerentspring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polo.devicerentspring.model.Customer;
import pl.polo.devicerentspring.model.Device;
import pl.polo.devicerentspring.repository.CustomerRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CustomerService {

    Scanner sc;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(Scanner sc, CustomerRepository customerRepository) {
        this.sc = sc;
        this.customerRepository = customerRepository;
    }

    public void addCustomer(){
        Customer customer = new Customer();
        System.out.println("Podaj imię klienta:");
        customer.setName(sc.nextLine());
        System.out.println("Podaj nazwisko klienta:");
        customer.setSurname(sc.nextLine());
        System.out.println("Podaj pesel klienta:");
        customer.setPesel(sc.nextLine());
        System.out.println("Podaj nr dowodu klienta:");
        customer.setIdCard(sc.nextLine());
        customerRepository.save(customer);
        System.out.println("Dodano klienta");
        System.out.println(customer.toString());
    }

    public void deleteCustomer(){
        System.out.println("Podaj id klienta:");
        Long id = sc.nextLong();
        sc.nextLine();
        Optional<Customer> customerToDelete = customerRepository.findById(id);
        customerToDelete.ifPresentOrElse(p ->customerRepository.delete(p),
                () -> System.out.println("Brak klienta o wskazanym ID"));
    }
}
