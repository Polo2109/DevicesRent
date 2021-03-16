package pl.polo.devicerentspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polo.devicerentspring.model.Category;
import pl.polo.devicerentspring.repository.CategoryRepository;

import java.util.Scanner;

@Service
public class CategoryService {

    @Autowired
    private Scanner sc;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Category category;

    public void addCategory(){
        System.out.println("Podaj nazwę kategorii:");
        category.setName(sc.nextLine());
        System.out.println("Podaj opis kategorii:");
        category.setDescription(sc.nextLine());
        categoryRepository.save(category);
        System.out.println("Dodano kategorię");
        System.out.println(category.toString());
    }

    public void deleteCategory(){
        System.out.println("Podaj id urządzenia:");
        Long id = sc.nextLong();
        sc.nextLine();
        Category firstCategory = categoryRepository.findById(id).get();
        categoryRepository.delete(firstCategory);
        System.out.println("Usunięto urządzenie o ID " + id);
    }

}
