package pl.polo.devicerentspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polo.devicerentspring.exceptions.RentException;
import pl.polo.devicerentspring.model.Customer;
import pl.polo.devicerentspring.model.Device;
import pl.polo.devicerentspring.repository.CustomerRepository;
import pl.polo.devicerentspring.repository.DeviceRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class RentService {

    private Scanner sc;
    private CustomerRepository customerRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    public RentService(Scanner sc, CustomerRepository customerRepository, DeviceRepository deviceRepository) {
        this.sc = sc;
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public void rentDeviceToCustomer() {
        try {
            rent();
        } catch(RentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void rent() {
        System.out.println("Podaj ID klienta:");
        long customerId = sc.nextLong();
        System.out.println("Podaj ID urządzenia:");
        long deviceId = sc.nextLong();
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Device> device = deviceRepository.findById(deviceId);
        if(customer.isPresent())
            device.ifPresentOrElse(dev -> {
                if(dev.getAmount() > dev.getCustomers().size())
                    dev.addCustomer(customer.get());
                else
                    throw new RentException("Brak wolnych urządzeń o wskazanym ID");
            }, () -> {
                throw new RentException("Brak urządzenia o wskazanym ID");
            });
        else
            throw new RentException("Brak klienta o wskazanym ID");
    }
}
