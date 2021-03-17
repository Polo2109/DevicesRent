package pl.polo.devicerentspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polo.devicerentspring.model.Category;
import pl.polo.devicerentspring.model.Device;
import pl.polo.devicerentspring.repository.CategoryRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryService {

    private Scanner sc;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(Scanner sc, CategoryRepository categoryRepository) {
        this.sc = sc;
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(){
        Category category = new Category();
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
        Optional<Category> categoryToDelete = categoryRepository.findById(id);
        categoryToDelete.ifPresentOrElse(p ->categoryRepository.delete(p),
                () -> System.out.println("Brak kategorii o wskazanym ID"));
    }

}
