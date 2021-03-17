package pl.polo.devicerentspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polo.devicerentspring.exceptions.CategoryNotFoundException;
import pl.polo.devicerentspring.model.Category;
import pl.polo.devicerentspring.model.Device;
import pl.polo.devicerentspring.repository.CategoryRepository;
import pl.polo.devicerentspring.repository.DeviceRepository;

import javax.transaction.Transactional;
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
        System.out.println("Podaj kategorię(id) urządzenia:");
        Long categoryId = sc.nextLong();
        sc.nextLine();
        try {
            readAndSetCategory(device, categoryId);
            deviceRepository.save(device);
            System.out.println("Dodano urzadzenie");
            System.out.println(device.toString());
        }catch (CategoryNotFoundException e){
            System.out.println("Urządzenia nie dodano \n" + e.getMessage());
        }
    }

    private void readAndSetCategory(Device device, Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresentOrElse(device::setCategory,
                () -> {
            throw new CategoryNotFoundException("Kategoria o podanym ID nie istnieje"); }
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
}
