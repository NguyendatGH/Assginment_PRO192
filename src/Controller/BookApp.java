package Controller;

import Model.Book;
import Model.BookList;
import View.Utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import view.Menu;

public class BookApp extends Menu<String> {

    static String[] menu = {"Display all book", "Add new book", "Find book", "Remove book", "Sort book", "Update field", "Backup data", "Quit"};
    BookList list = new BookList();

    public BookApp() {
        super("BookManagement System", menu);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1: {
                list.printList(list.getListOfBook());
                break;
            }
            case 2: {
                AddnewBook();
                break;
            }
            case 3: {
                searchBook();
                break;
            }
            case 4: {
                DeleteBook();
                break;
            }
            case 5: {
                SortBook();
                break;
            }
            case 6: {
                UpdateField();
                break;
            }
            case 7: {
                list.backUpData("backup.txt");
                break;
            }
            case 8: {
                System.exit(0);
                break;
            }
        }
    }

    public boolean login() {
        String correctUsername = "admin";
        String correctPassword = "password";
        int attempts = 5;

        while (attempts > 0) {
            String username = Utils.getValue("Enter username: ");
            String password = Utils.getValue("Enter password: ");

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("Login successful!");
                return true;
            } else {
                attempts--;
                System.out.println("Invalid username or password. Try again, you have " + attempts + " time left");
            }
        }

        System.out.println("Too many failed attempts. Program will exit.");
        System.exit(0);
        return false;
    }

    public void AddnewBook() {
        while (true) {
            try {
                System.out.println("Fill all required field to add a new book: ");
                String id = Utils.getValue("Enter book id: ");
                if (!Utils.validID(id) || list.IdExits(id) == false) {
                    continue;
                }

                String title = Utils.getValue("[+] Title: ");
                String author = Utils.getValue("[+] Author: ");
                String category = Utils.getValue("[+] Category: ");

                String publishDate;
                while (true) {
                    publishDate = Utils.getValue("[+] Publish Date: ");
                    if (Utils.validDate(publishDate)) {
                        break;
                    }
                }
                int quantity = Utils.checkInt("[+] Quantity: ");
                double price = Utils.checkDouble("[+] Price: ");

                list.AddNewBook(id, title, author, category, publishDate, quantity, price);
                System.out.println("Add successfully!");
                break;
            } catch (Exception er) {
                System.out.println("" + er);
            }
        }
    }

    private void searchBook() {
        String[] mSearch = {"By title", "By category", "By datePubish", "By price"};
        Menu m = new Menu("Search option", mSearch) {
            @Override
            public void execute(int n) {
                switch (n) {
                    case 1: {
                        String input = Utils.getValue("Enter title: ");
                        ArrayList<Book> result = list.findBook(p -> p.getTitle().equalsIgnoreCase(input));
                        list.printList(result);
                        break;
                    }
                    case 2: {
                        System.out.println("*honor, category, comic, fairytail, fantasy, romance");
                        String input = Utils.getValue("Enter category: ");
                        ArrayList<Book> result = list.findBook(p -> p.getCategory().equalsIgnoreCase(input));
                        list.printList(result);
                        break;
                    }

                    case 3: {
                        String input = Utils.getValue("Enter year: ");

                        if (Utils.validDate(input) == true) {
                            try {
                                SimpleDateFormat date = new SimpleDateFormat();
                                Date input_format = date.parse(input);

                                int option = Utils.checkInt("Press 1 to search before & 2 to search after that year: ");
                                ArrayList<Book> result;
                                if (option == 1) {
                                    result = list.findBook(p -> p.getYearPub().before(input_format));
                                } else if (option == 2) {
                                    result = list.findBook(p -> p.getYearPub().after(input_format));
                                } else {
                                    System.out.println("Invalid option");
                                    return;
                                }
                                list.printList(result);
                            } catch (Exception e) {
                                System.out.println("" + e);
                            }
                        }
                        break;
                    }
                    case 4: {
                        Double input = Utils.checkDouble("Enter price: ");
                        ArrayList<Book> result;

                        System.out.println("Press 1 to list all book have lower price");
                        System.out.println("Press 2 to list all book have high price");
                        System.out.println("Press 3 to list all book have equal price");

                        int option = Utils.checkInt("+Enter your option: ");
                        switch (option) {
                            case 1: {
                                result = list.findBookByPrice(input, 1);
                                list.printList(result);
                                break;
                            }
                            case 2: {
                                result = list.findBookByPrice(input, 2);
                                list.printList(result);
                                break;
                            }
                            case 3: {
                                result = list.findBookByPrice(input, 3);
                                list.printList(result);
                                break;
                            }
                            default: {
                                System.out.println("invalid option");
                                return;
                            }
                        }
                        break;
                    }
                }
            }
        };
        m.run();
    }

    private void DeleteBook() {
        String[] mSearch = {"By title", "By category", "By datePubish", "Remove all"};
        Menu m = new Menu("Delete option", mSearch) {
            @Override
            public void execute(int n) {
                switch (n) {
                    case 1: {
                        String input = Utils.getValue("Enter title: ");
                        list.removeBook(p -> p.getTitle().equalsIgnoreCase(input));
                        System.out.println("List after remove");
                        list.printList(list.getListOfBook());
                        break;
                    }
                    case 2: {
                        System.out.println("*honor, category, comic, fairytail, fantasy, romance");
                        String input = Utils.getValue("Enter category: ");
                        list.removeBook(p -> p.getCategory().equalsIgnoreCase(input));
                        System.out.println("List after remove");
                        list.printList(list.getListOfBook());
                        break;
                    }

                    case 3: {
                        String input = Utils.getValue("Enter year: ");
                        if (Utils.validDate(input) == true) {
                            try {
                                SimpleDateFormat date = new SimpleDateFormat();
                                Date input_format = date.parse(input);

                                int option = Utils.checkInt("Press 1 to search before & 2 to search after that year: ");
                                ArrayList<Book> result;
                                if (option == 1) {
                                    list.removeBook(p -> p.getYearPub().before(input_format));
                                } else if (option == 2) {
                                    list.removeBook(p -> p.getYearPub().after(input_format));
                                } else {
                                    System.out.println("Invalid option");
                                    return;
                                }
                                System.out.println("List after remove");
                                list.printList(list.getListOfBook());
                            } catch (Exception e) {
                                System.out.println("" + e);
                            }
                        }
                        break;
                    }
                    case 4: {
                        BookApp AD = new BookApp();
                        System.out.println("Warning!! this action can remove all book data, need Admin permission!");
                        if (AD.login()) {
                            list.removeAllList();
                            System.out.println("Remove success!");
                        } else {
                            System.out.println("You cannot do this action!");
                        }
                        break;
                    }
                }
            }
        };
        m.run();
    }

    private void SortBook() {
        String[] mSearch = {"By title", "By price", "By quantity", "By date publish"};
        Menu m = new Menu("Sort option", mSearch) {
            @Override
            public void execute(int n) {
                switch (n) {
                    case 1: {
                        boolean o = user_option();
                        list.sortByName(o);
                        list.printList(list.getListOfBook());
                        break;
                    }
                    case 2: {
                        boolean o = user_option();
                        list.sortByPrice(o);
                        list.printList(list.getListOfBook());
                        break;
                    }
                    case 3: {
                        boolean o = user_option();
                        list.sortByQuantity(o);
                        list.printList(list.getListOfBook());
                        break;
                    }
                    case 4: {
                        boolean o = user_option();
                        list.sortByPublishDate(o);
                        list.printList(list.getListOfBook());
                        break;
                    }
                }
            }
        };
        m.run();
    }

    public boolean user_option() {
        while (true) {
            String choice = Utils.getValue("Enter 'a' to sort increasing, 'b' for sort decreasing: ");
            if ("a".equalsIgnoreCase(choice)) {
                return true;
            } else if ("b".equalsIgnoreCase(choice)) {
                return false;
            } else {
                System.out.println("Invalid choice! Please enter 'a' || 'b'.");
            }
        }
    }

    private void UpdateField() {
        String input = Utils.getValue("Enter ID of book you want to update: ");
        ArrayList<Book> arrList = list.findBook(p -> p.getBookID().equalsIgnoreCase(input));

        if (arrList.isEmpty()) {
            System.out.println("Doesnot exist!");
        } else {
            String[] mSearch = {"Quantity", "Price",};
            Menu m = new Menu("Choose the field you want to update", mSearch) {
                @Override
                public void execute(int n) {
                    switch (n) {
                        case 1: {
                            int newQ = Utils.checkInt("number of books: ");
                            arrList.get(0).updateQuantity(input, newQ);
                            System.out.println("Update success!");
                            break;
                        }
                        case 2: {
                            double newP = Utils.checkDouble("new price: ");
                            arrList.get(0).updatePrice(input, newP);
                            System.out.println("Update success!");
                            break;
                        }
                    }
                }
            };
            m.run();
        }
    }

    public static void main(String[] args) {
        BookApp lib = new BookApp();
        if (lib.login()) {
            lib.run();
        }
    }
}
