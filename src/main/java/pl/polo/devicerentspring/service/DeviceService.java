package pl.polo.devicerentspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polo.devicerentspring.model.Device;
import pl.polo.devicerentspring.repository.CategoryRepository;
import pl.polo.devicerentspring.repository.DeviceRepository;

import javax.transaction.Transactional;
import java.util.Scanner;

@Service
public class DeviceService {
    @Autowired
    Scanner sc;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    Device device;
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public void addDevice(){
        System.out.println("Podaj nazwę urzadzenia:");
        device.setName(sc.nextLine());
        System.out.println("Podaj opis urządzenia:");
        device.setDescription(sc.nextLine());
        System.out.println("Podaj cenę urządzenia:");
        device.setPrice(sc.nextDouble());
        System.out.println("Podaj ilość(szt) urządzenia:");
        device.setAmount(sc.nextInt());
        System.out.println("Podaj kategorię(id) urządzenia:");
        device.setCategory(categoryRepository.findById(sc.nextLong()).get());
        sc.nextLine();
        deviceRepository.save(device);
        System.out.println("Dodano urzadzenie");
        System.out.println(device.toString());
    }

    public void deleteDevice(){
        System.out.println("Podaj id urządzenia:");
        Long id = (long) sc.nextInt();
        sc.nextLine();
        Device firstDevice = deviceRepository.findById(id).get();
        deviceRepository.delete(firstDevice);
        System.out.println("Usunięto urzadzenie o ID " + id);
    }
}
