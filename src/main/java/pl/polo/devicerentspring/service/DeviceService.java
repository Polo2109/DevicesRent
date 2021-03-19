package pl.polo.devicerentspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polo.devicerentspring.exceptions.CategoryNotFoundException;
import pl.polo.devicerentspring.model.Category;
import pl.polo.devicerentspring.model.Device;
import pl.polo.devicerentspring.repository.CategoryRepository;
import pl.polo.devicerentspring.repository.DeviceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class DeviceService {

    private Scanner sc;
    private DeviceRepository deviceRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public DeviceService(Scanner sc, DeviceRepository deviceRepository, CategoryRepository categoryRepository) {
        this.sc = sc;
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addDevice(){
        Device device = new Device();
        System.out.println("Podaj nazwę urzadzenia:");
        device.setName(sc.nextLine());
        System.out.println("Podaj opis urządzenia:");
        device.setDescription(sc.nextLine());
        System.out.println("Podaj cenę urządzenia:");
        device.setPrice(sc.nextDouble());
        System.out.println("Podaj ilość(szt) urządzenia:");
        device.setAmount(sc.nextInt());
        sc.nextLine();
        System.out.println("Podaj nazwę kategorii:");
        String categoryName = sc.nextLine();
        try {
            readAndSetCategory(device, categoryName);
            deviceRepository.save(device);
            System.out.println("Dodano urzadzenie");
            System.out.println(device.toString());
        }catch (CategoryNotFoundException e){
            System.out.println("Urządzenia nie dodano \n" + e.getMessage());
        }
    }

    private void readAndSetCategory(Device device, String categoryName) {
        Optional<Category> category = categoryRepository.findFirstByNameIgnoreCase(categoryName);
        category.ifPresentOrElse(device::setCategory,
                () -> {
            throw new CategoryNotFoundException("Kategoria o podanej nazwie nie istnieje"); }
        );
    }

    public void deleteDevice(){
        System.out.println("Podaj id urządzenia:");
        Long id = sc.nextLong();
        sc.nextLine();
        Optional<Device> deviceToDelete = deviceRepository.findById(id);
        deviceToDelete.ifPresentOrElse(p ->deviceRepository.delete(p),
                () -> System.out.println("Brak urządzenia o wskazanym ID"));
    }
    public void findDevice(){
        System.out.println("Podaj nazwę urządzenia");
        String name = sc.nextLine();
        List<Device> foundedDevices = deviceRepository.findFirstByNameContainingIgnoreCase(name);
        if(foundedDevices != null){
            foundedDevices.forEach(p -> System.out.println(p.toString()));
        }else
            System.out.println("Brak urządzenia o wskazanej nazwie");
    }
}
