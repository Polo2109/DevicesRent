package pl.polo.devicerentspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.polo.devicerentspring.exceptions.InvalidOptionException;
import pl.polo.devicerentspring.service.CategoryService;
import pl.polo.devicerentspring.service.CustomerService;
import pl.polo.devicerentspring.service.DeviceService;
import pl.polo.devicerentspring.service.RentService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class ApplicationController {

    private Scanner sc;
    private DeviceService deviceService;
    private CategoryService categoryService;
    private CustomerService customerService;
    private RentService rentService;

    public ApplicationController(Scanner sc, DeviceService deviceService, CategoryService categoryService, CustomerService customerService, RentService rentService) {
        this.sc = sc;
        this.deviceService = deviceService;
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.rentService = rentService;
    }

    public void loop(){
        Options option;
        do {
            printOptions();
            option = getOption();
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
                    rentService.rentDeviceToCustomer();
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
                case END:
                    endProgram();
                    break;
            }

        }while (option != Options.END);
    }

    private void endProgram() {
        sc.close();
        System.out.println("Bye bye!");
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
                option = Options.createFromInt(readNumber());
                optionOK = true;
            }catch (InputMismatchException e){
                System.out.println("Wprowadzono wartość, która nie jest liczbą, podaj ponownie");
            }catch (InvalidOptionException e){
                System.out.println(e.getMessage());
            }
        }
        return option;
    }

    private int readNumber() {
        try{
            return sc.nextInt();
        }finally {
            sc.nextLine();
        }
    }


}
