import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesiFlix {
    private Map<String, List<User>> genreSubscribers = new HashMap<>(); 
    private Map<String, List<String>> moviesByGenre = new HashMap<>(); 
    private List<User> allUsers = new ArrayList<>();
    public DesiFlix() {
        addGenre("horror");
        addGenre("comedy");
        addGenre("thriller");
        
    }

    public void addGenre(String genre) {
        String genre2 = genre.toLowerCase(); 
        if (!genreSubscribers.containsKey(genre2)) {
            genreSubscribers.put(genre2, new ArrayList<>());
            moviesByGenre.put(genre2, new ArrayList<>());
            System.out.println("Genre: " + genre + " added to DesiFlix.");
        } else {
            System.out.println("Genre: " + genre + " already exists.");
        }
    }

    public void addUser(User user) {
        if (!allUsers.contains(user)) {
            allUsers.add(user);
            System.out.println(user.getName() + " added to DesiFlix.");
        } else {
            System.out.println(user.getName() + " is already a DesiFlix user.");
        }
    }

    public void addGenreForUser(User user, String genre) {
        String genre2 = genre.toLowerCase(); 
        if (!genreSubscribers.containsKey(genre2)) {
            System.out.println("Invalid genre: " + genre + ". Please add the genre first.");
            return;
        }

        if (!allUsers.contains(user)) {
            System.out.println(user.getName() + " is not registered with DesiFlix. Add the user first.");
            return;
        }

        List<User> subscribers = genreSubscribers.get(genre2);
        if (!subscribers.contains(user)) {
            subscribers.add(user);
            System.out.println(user.getName() + " subscribed to " + genre);
        } else {
            System.out.println(user.getName() + " is already subscribed to " + genre);
        }
    }

    public void removeGenreForUser(User user, String genre) {
        String genre2 = genre.toLowerCase();
        if (!genreSubscribers.containsKey(genre2)) {
            System.out.println("Invalid genre: " + genre);
            return;
        }

        if (!allUsers.contains(user)) {
            System.out.println(user.getName() + " is not registered with DesiFlix.");
            return;
        }

        List<User> subscribers = genreSubscribers.get(genre2);
        if (subscribers.contains(user)) {
            subscribers.remove(user);
            System.out.println(user.getName() + " unsubscribed from " + genre);
        } else {
            System.out.println(user.getName() + " is not subscribed to " + genre);
        }
    }

    // Add a new movie
    public void uploadMovie(String genre, String movieName) {
        String genre2 = genre.toLowerCase(); 
        if (!moviesByGenre.containsKey(genre2)) {
            System.out.println("Invalid genre: " + genre + ". Please add the genre first.");
            return;
        }

        List<String> movies = moviesByGenre.get(genre2);
        if (!movies.contains(movieName.toLowerCase())) {
            movies.add(movieName.toLowerCase());
            System.out.println("Movie: " + movieName + " added to genre: " + genre);
            notifySubscribers(genre2, movieName); // Notify subscribers
        } else {
            System.out.println("Movie: " + movieName + " already exists in genre: " + genre);
        }
    }

    private void notifySubscribers(String genre, String movieName) {
        List<User> subscribers = genreSubscribers.get(genre);
        for (User user : subscribers) {
            Thread notificationThread = new Thread(() -> user.notify(movieName, genre));
            notificationThread.start();
        }
    }

    public List<User> getAllusers (){
        return allUsers;
    }
}
