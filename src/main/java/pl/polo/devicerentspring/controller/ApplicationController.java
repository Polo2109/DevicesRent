package pl.polo.devicerentspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.polo.devicerentspring.service.CategoryService;
import pl.polo.devicerentspring.service.CustomerService;
import pl.polo.devicerentspring.service.DeviceService;
import pl.polo.devicerentspring.service.RentService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class ApplicationController {
    @Autowired
    private Scanner sc;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RentService rentService;

    public void loop(){
        Options option;
        do {
            printOptions();
            option = getOption();
            sc.nextLine();
            switch (option){
                case ADD_DEVICE:
                    deviceService.addDevice();
                    break;
                case ADD_CATEGORY:
                    categoryService.addCategory();
                    break;
                case ADD_CLIENT:
                    customerService.addCustomer();
                    break;
                case RENT_DEVICE:
                    rentService.rentDevice();
                    break;
                case DELETE_DEVICE:
                    deviceService.deleteDevice();
                    break;
                case DELETE_CATEGORY:
                    categoryService.deleteCategory();
                    break;
                case DELETE_CLIENT:
                    customerService.deleteCustomer();
                    break;
            }

        }while (option != Options.END);
    }

    private void printOptions(){
        System.out.println("Opcje:");
        Options[] options = Options.values();
        for (Options option : options) {
            System.out.println(option.getOption() + " - " + option.getDescription());
        }
    }
    private Options getOption() {
        boolean optionOK = false;
        Options option = null;
        while (!optionOK){
            try{
                option = Options.createFromInt(sc.nextInt());
                if(option == null)
                    System.out.println("Nie ma opcji z takim ID");
                else
                    optionOK = true;
            }catch (InputMismatchException ignored){
                System.out.println("Wprowadzono wartość, która nie jest liczbą, podaj ponownie");
            }
        }
        return option;
    }




}
