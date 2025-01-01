import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    
        DesiFlix desiFlix = new DesiFlix();

        while (true) {
            System.out.println("Choose Role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Admin Menu
                    adminMenu(scanner, desiFlix);
                    break;

                case 2: // User Menu
                    userMenu(scanner, desiFlix);
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

    private static void adminMenu(Scanner scanner, DesiFlix desiFlix) {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Genre");
            System.out.println("2. Upload Movie");
            System.out.println("3. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3) {
                return; // Exit Admin menu
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter Genre Name:");
                    String genre = scanner.nextLine();
                    desiFlix.addGenre(genre);
                    break;

                case 2:
                    System.out.println("Enter Genre Name:");
                    String movieGenre = scanner.nextLine();
                    System.out.println("Enter Movie Name:");
                    String movieName = scanner.nextLine();
                    desiFlix.uploadMovie(movieGenre, movieName);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userMenu(Scanner scanner, DesiFlix desiFlix) {
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 3) {
                return; // Exit User menu
            }

            switch (choice) {
                case 1: // Signup
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    User newUser = new User(name, desiFlix);
                    System.out.println("Signup successful!");

                    userActionMenu(scanner, newUser, desiFlix);
                    break;

                case 2: // Login
                    System.out.println("Enter your name:");
                    String userName = scanner.nextLine();

                    // Find the user by name
                    User existingUser = null;
                    for (User user : desiFlix.getAllusers()) {
                        if (user.getName().equalsIgnoreCase(userName)) {
                            existingUser = user;
                            break;
                        }
                    }

                    if (existingUser == null) {
                        System.out.println("User not found. Please signup first.");
                    } else {
                        userActionMenu(scanner, existingUser, desiFlix);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userActionMenu(Scanner scanner, User user, DesiFlix desiFlix) {
        while (true) {
            System.out.println("User Actions:");
            System.out.println("1. Add Favorite Genre");
            System.out.println("2. Remove Favorite Genre");
            System.out.println("3. View Favorite Genres");
            System.out.println("4. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 4) {
                return; 
            }

            switch (choice) {
                case 1: // Add Favorite Genre
                    System.out.println("Enter Genre to Add:");
                    String genreToAdd = scanner.nextLine();
                    user.addFavoriteGenre(genreToAdd);
                    break;

                case 2: // Remove Favorite Genre
                    System.out.println("Enter Genre to Remove:");
                    String genreToRemove = scanner.nextLine();
                    user.removeFavoriteGenre(genreToRemove);
                    break;

                case 3: // View Favorite Genres
                    System.out.println(user.getName() + "'s Favorite Genres:");
                    for (String genre : user.getfavouriteGenres()) {
                        System.out.println(genre);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}





// public class Main {
//     public static void main(String[] args) {
//         DesiFlix desiFlix = new DesiFlix();

//         User user1 = new User("Alice", desiFlix);
//         User user2 = new User("Bob", desiFlix);
//         User user3 = new User("Charlie", desiFlix);

//         user1.addFavoriteGenre("horror");
//         user1.addFavoriteGenre("comedy");

//         user2.addFavoriteGenre("thriller");
//         user2.addFavoriteGenre("comedy");

//         user3.addFavoriteGenre("horror");

//         desiFlix.uploadMovie("horror", "Scary Movie 5");
//         desiFlix.uploadMovie("comedy", "Funny Business");
//         desiFlix.uploadMovie("thriller", "Thrill Seekers");

//         user1.removeFavoriteGenre("comedy");
//         user2.removeFavoriteGenre("thriller");

//         desiFlix.uploadMovie("horror", "Nightmare Alley");
//         desiFlix.uploadMovie("comedy", "Comedy Central");
//     }
// }
