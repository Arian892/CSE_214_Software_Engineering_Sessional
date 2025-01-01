// public class Main {
//     public static void main(String[] args) {
//         User user1 = new User("john");
//         User user2 = new User("Michael");
//         User user3 = new User("Alice");

//         Genre horror = new HorrorGenre();
//         Genre comedy = new ComedyGenre();
//         Genre thriller = new ThrillerGenre();

//         user1.addGenre(horror);
//         user1.addGenre(comedy);
//         user2.addGenre(comedy);
//         user3.addGenre(comedy);

//         horror.addMovie("The Nun");
//         comedy.addMovie("The Hangover");

//     }

// }

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create genres
        Genre horror = new HorrorGenre();
        Genre comedy = new ComedyGenre();
        Genre thriller = new ThrillerGenre();

        // Create lists to hold users
        List<User> users = new ArrayList<>();

        while (true) {
            System.out.println("Choose Role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Admin Menu
                    adminMenu(scanner, horror, comedy, thriller);
                    break;

                case 2: // User Menu
                    userMenu(scanner, users, horror, comedy, thriller);
                    break;

                case 3: // Exit Program
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu(Scanner scanner, Genre horror, Genre comedy, Genre thriller) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Movie");
            System.out.println("2. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 2)
                return; // Exit Admin menu

            switch (choice) {
                case 1:
                    System.out.println("Enter Genre (Horror, Comedy, Thriller):");
                    String genreInput = scanner.nextLine();
                    System.out.println("Enter Movie Name:");
                    String movieName = scanner.nextLine();

                    switch (genreInput.toLowerCase()) {
                        case "horror":
                            horror.addMovie(movieName);
                            break;
                        case "comedy":
                            comedy.addMovie(movieName);
                            break;
                        case "thriller":
                            thriller.addMovie(movieName);
                            break;
                        default:
                            System.out.println("Invalid genre. Please try again.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userMenu(Scanner scanner, List<User> users, Genre horror, Genre comedy, Genre thriller) {
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3)
                return; // Exit User menu

            switch (choice) {
                case 1: // Signup
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    User newUser = new User(name);
                    users.add(newUser);

                    System.out.println("Choose your favorite genres (comma-separated, e.g., Horror,Comedy,Thriller):");
                    String genreInput = scanner.nextLine();
                    addGenresToUser(newUser, genreInput, horror, comedy, thriller);

                    System.out.println("Signup successful!");
                    break;

                case 2: // Login
                    System.out.println("Enter your name:");
                    String userName = scanner.nextLine();

                    // Find the user by name
                    User existingUser = null;
                    for (User user : users) {
                        if (user.name.equalsIgnoreCase(userName)) {
                            existingUser = user;
                            break;
                        }
                    }

                    if (existingUser == null) {
                        System.out.println("User not found. Please signup first.");
                    } else {
                        userActionMenu(scanner, existingUser, horror, comedy, thriller);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }

    private static void userActionMenu(Scanner scanner, User user, Genre horror, Genre comedy, Genre thriller) {
        while (true) {
            System.out.println("User Actions:");
            System.out.println("1. Add Genre");
            System.out.println("2. Remove Genre");
            System.out.println("3. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3)
                return; // Exit User actions menu

            switch (choice) {
                case 1: // Add Genre
                    System.out.println("Enter Genre to Add (Horror, Comedy, Thriller):");
                    String genreToAdd = scanner.nextLine();
                    addGenresToUser(user, genreToAdd, horror, comedy, thriller);
                    break;

                case 2: // Remove Genre
                    System.out.println("Enter Genre to Remove (Horror, Comedy, Thriller):");
                    String genreToRemove = scanner.nextLine();
                    removeGenreFromUser(user, genreToRemove, horror, comedy, thriller);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addGenresToUser(User user, String genreInput, Genre horror, Genre comedy, Genre thriller) {
        String[] genres = genreInput.split(",");
        for (String genre : genres) {
            switch (genre.trim().toLowerCase()) {
                case "horror":
                    user.addGenre(horror);
                    break;
                case "comedy":
                    user.addGenre(comedy);
                    break;
                case "thriller":
                    user.addGenre(thriller);
                    break;
                default:
                    System.out.println("Invalid genre: " + genre);
            }
        }
    }

    private static void removeGenreFromUser(User user, String genreInput, Genre horror, Genre comedy, Genre thriller) {
        switch (genreInput.trim().toLowerCase()) {
            case "horror":
                user.removeGenre(horror);
                break;
            case "comedy":
                user.removeGenre(comedy);
                break;
            case "thriller":
                user.removeGenre(thriller);
                break;
            default:
                System.out.println("Invalid genre: " + genreInput);
        }
    }
}
