import java.util.Scanner;

public class Library {
    private class Book {
        String title;
        String author;
        int quantity;

        Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }
    }

    public static int option() {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option > 4 || option < 0) {
            System.out.println("You entered a wrong value. Enter a number between 1 and 4 inclusive");
            option = scanner.nextInt();
        }
        return option;
    }

    public static void main(String[] args) {
        Book[] books = new Book[20];
        System.out.print("Welcome to the Library. Please choose a number " +
                "corresponding to what you want to do\n1. Add books\n2. Borrow books\n3. Return books\n4. Exit\n>>> ");
        int option = Library.option();
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        while (option != 0) {
            switch (option) {
                case 1: {
                    try {
                        System.out.print("Please enter the book title >>> ");
                        String title = scanner.next();
                        System.out.print("Please enter the book author >>> ");
                        String author = scanner.next();
                        System.out.print("Please enter the quantity >>> ");
                        int quantity = scanner.nextInt();
                        int i = 0;
                        for (; i < books.length; i++) {
                            if (books[i] != null && books[i].title.equals(title) && books[i].author.equals(author)) {
                                books[i].quantity = books[i].quantity + quantity;
                                System.out.println("book added to the Library successfully");
                                break;
                            } else if (i == books.length - 1) {
                                Library lib = new Library();
                                Library.Book bookItem = lib.new Book(title, author, quantity);
                                books[count] = bookItem;
                                count += 1;
                                System.out.println("book added to the Library successfully");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.print("Please enter the book title you want to borrow >>> ");
                        String title = scanner.next();
                        System.out.print("Please enter number of this book you want to borrow >>> ");
                        int quantity = scanner.nextInt();
                        boolean found = false;
                        for (int i = 0; i < books.length && books[i] != null; i++) {
                            if (books[i].title.equals(title) && books[i].quantity >= quantity) {
                                books[i].quantity = books[i].quantity - quantity;
                                System.out.println("You have borrowed " + quantity + " books of the title \"" + title + "\" from the library");
                                found = true;
                                break;
                            } else if (books[i].title.equals(title)) {
                                System.out.println("There is less number of this book than the quantity you requested");
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            System.out.println("There is no book of the title \"" + title + "\" in the library");
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.print("Please enter the book title you want to return >>> ");
                        String title = scanner.next();
                        System.out.print("Please enter number of this book you want to return >>> ");
                        int quantity = scanner.nextInt();
                        boolean found = false;
                        for (int i = 0; i < books.length && books[i] != null; i++) {
                            if (books[i].title.equals(title)) {
                                books[i].quantity = books[i].quantity + quantity;
                                System.out.println("You have returned " + quantity + " books of the title \"" + title + "\" to the library");
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            System.out.println("There is no book of the title \"" + title + "\" in the library");
                    } catch (Exception e) {
                        System.out.println("invalid input");
                        throw e;
                    }
                    break;
                }
            }
            System.out.print("Welcome to the Library. Please choose a number " +
                    "corresponding to what you want to do\n1. Add books\n2. Borrow books\n3. Return books\n4. Exit\n>>> ");
            option = option();
        }
    }
}
