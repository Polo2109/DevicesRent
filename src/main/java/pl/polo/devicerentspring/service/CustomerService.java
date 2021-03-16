package pl.polo.devicerentspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polo.devicerentspring.model.Category;
import pl.polo.devicerentspring.model.Customer;
import pl.polo.devicerentspring.repository.CategoryRepository;
import pl.polo.devicerentspring.repository.CustomerRepository;

import java.util.Scanner;

@Service
public class CustomerService {
    @Autowired
    Scanner sc;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    Customer customer;

    public void addCustomer(){
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
        Customer firstCustomer = customerRepository.findById(id).get();
        customerRepository.delete(firstCustomer);
        System.out.println("Usunięto klienta o ID " + id);
    }
}
