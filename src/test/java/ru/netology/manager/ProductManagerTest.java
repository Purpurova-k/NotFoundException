package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class ProductManagerTest {
    private ProductRepository repository = Mockito.mock(ProductRepository.class);

    private ProductManager manager = new ProductManager(repository);

    private Book book1 = new Book(1, "Пиковая дама", 500, "Александр Пушкин");
    private Book book2 = new Book(2, "Герой нашего времени", 800, "Михаил Лермонтов");
    private Book book3 = new Book(3, "Оно", 1000, "Стивен Кинг");
    private Book book4 = new Book(4, "Вино из одуванчиков", 400, "Рэй Брэдбери");
    private Book book5 = new Book(5, "Дубровский", 450, "Александр Пушкин");


    private TShirt tShirt1 = new TShirt(6, "Adidas", 500, "China");
    private TShirt tShirt2 = new TShirt(7, "Nike", 1000, "USA");
    private TShirt tShirt3 = new TShirt(8, "Zara", 1500, "China");
    private TShirt tShirt4 = new TShirt(9, "Adidas", 1000, "China");
    private TShirt tShirt5 = new TShirt(10, "Bershka", 800, "Japan");


    @Test
    public void shouldAddBookToRepository() {
        Product[] returned = {book1};
        doReturn(returned).when(repository).findAll();

        manager.add(book1);

        Product[] expected = {book1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


    @Test
    public void shouldAddTShirtToRepository() {
        Product[] returned = {tShirt1};
        doReturn(returned).when(repository).findAll();

        manager.add(tShirt1);

        Product[] expected = {tShirt1};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


    @Test
    public void shouldFindBookByName() {
        Product[] returned = {book3};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {book3};
        Product[] actual = manager.searchBy("Оно");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


    @Test
    public void shouldFindBookByAuthor() {
        Product[] returned = {book1, book5};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {book1, book5};
        Product[] actual = manager.searchBy("Александр Пушкин");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


    @Test
    public void shouldFindBookInvalid() {
        Product[] returned = {book1, book2, book3, book4, book5};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {};
        Product[] actual = manager.searchBy("Лев Толстой");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


    @Test
    public void shouldFindEmptyRepositoryBook() {
        Product[] returned = {};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {};
        Product[] actual = manager.searchBy("книги");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


    @Test
    public void shouldFindTShirtByName() {
        Product[] returned = {tShirt3};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {tShirt3};
        Product[] actual = manager.searchBy("Zara");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


    @Test
    public void shouldFindTShirtByProducer() {
        Product[] returned = {tShirt1, tShirt3, tShirt4};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {tShirt1, tShirt3, tShirt4};
        Product[] actual = manager.searchBy("China");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    public void shouldFindTShirtInvalid() {
        Product[] returned = {tShirt1, tShirt2, tShirt3, tShirt4, tShirt5};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {};
        Product[] actual = manager.searchBy("Майка");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }


}