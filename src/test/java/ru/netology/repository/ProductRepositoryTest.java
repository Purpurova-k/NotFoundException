package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();

    private Book book1 = new Book(1, "Пиковая дама", 500, "Александр Пушкин");
    private Book book2 = new Book(2, "Герой нашего времени", 800, "Михаил Лермонтов");

    private TShirt tShirt1 = new TShirt(6, "Adidas", 500, "China");
    private TShirt tShirt2 = new TShirt(7, "Nike", 1000, "USA");

    @Test
    public void shouldSaveOneItem() {
        repository.save(coreJava);
        Product[] expected = new Product[]{coreJava};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.save(book1);
        repository.save(tShirt1);

        repository.removeById(6);

        Product expected[] = {book1};
        Product actual[] = repository.findAll();

        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldNotRemoveByInvalidId() {
        repository.save(book1);
        repository.save(book2);
        repository.save(tShirt1);
        repository.save(tShirt2);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(10);
        });

    }
}