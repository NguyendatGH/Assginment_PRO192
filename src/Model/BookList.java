/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 *
 * @author DELL
 */
public class BookList {

    ArrayList<Book> listOfBook;

    public BookList() {
        listOfBook = new ArrayList<>();
        try {
            loadData("bookList.txt");
        } catch (Exception e) {
            System.out.println("Cannot load data");
        }
    }

    public void loadData(String fileName) throws FileNotFoundException {
        try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String id = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    String category = parts[3];
                    String datePub = parts[4];
                    double price = Double.parseDouble(parts[5]);
                    int quantity = Integer.parseInt(parts[6]);

                    if (id.matches("B\\d{3}")) {
                        listOfBook.add(new Book(id, title, author, category, datePub, quantity, price));
                    }

                } else {
                    System.out.println("Error to add");
                }
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public void backUpData(String filename) {
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(filename));
            for (Book obj : listOfBook) {
                file.write(obj.toString());
                file.newLine();
            }
            System.out.println("Back up success!");

            file.close();
        } catch (IOException e) {
            System.out.println("Error to write file");
        }
    }

    public ArrayList<Book> getListOfBook() {
        return listOfBook;
    }

    public void setListOfBook(ArrayList<Book> listOfBook) {
        this.listOfBook = listOfBook;
    }

    public void printList(ArrayList<Book> listOfBook) {
        if (listOfBook.isEmpty()) {
            System.out.println("Nothing to print out");
        } else {
            System.out.println("List of books:");
            System.out.printf("%-5s | %-24s | %-24s | %-24s | %-24s | %-14s | %-14s | %-14s |\n", "ID", "Title", "Author", "Category", "Published", "Quantity", "Price ($)", "Promotional(%)");
            for (Book e : listOfBook) {
                System.out.println(e);
            }
        }
    }

    public boolean IdExits(String id) {
        for (Book b : listOfBook) {
            if (b.getBookID().equalsIgnoreCase(id)) {
                System.out.println("ID already exists");
                return false;
            }
        }
        return true;
    }

    public void AddNewBook(String id, String title, String author, String category, String datePub, int quantity, double price) throws ParseException {
        listOfBook.add(new Book(id, title, author, category, datePub, quantity, price));
    }

    public ArrayList<Book> findBook(Predicate<Book> p) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book e : listOfBook) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean removeBook(Predicate<Book> b) {
        return listOfBook.removeIf(b);
    }

    public void removeAllList() {
        listOfBook.removeAll(listOfBook);
    }

    public void sortByName(boolean option) {
        if (option == true) {
            Collections.sort(listOfBook, Comparator.comparing(Book::getTitle));
        } else {
            Collections.sort(listOfBook, Comparator.comparing(Book::getTitle).reversed());
        }
    }

    public void sortByPrice(boolean option) {
        if (option == true) {
            Collections.sort(listOfBook, Comparator.comparing(Book::getPrice));
        } else {
            Collections.sort(listOfBook, Comparator.comparing(Book::getPrice).reversed());
        }
    }

    public void sortByQuantity(boolean option) {
        if (option == true) {
            Collections.sort(listOfBook, Comparator.comparing(Book::getQuantity));
        } else {
            Collections.sort(listOfBook, Comparator.comparing(Book::getQuantity).reversed());
        }
    }

    public void sortByPublishDate(boolean option) {
        if (option == true) {
            Collections.sort(listOfBook, Comparator.comparing(Book::getYearPub));
        } else {
            Collections.sort(listOfBook, Comparator.comparing(Book::getYearPub).reversed());
        }
    }
}
