import java.util.ArrayList;
import java.util.List;

public class User {
    String name ;
    List<Genre> genres = new ArrayList<>();

    User (String name){ 
        this.name = name;
    
    }
    public void addGenre(Genre genre) {
        boolean exists = false;
        for (Genre g : genres) {
            if (g.getGenre().equalsIgnoreCase(genre.getGenre())) { // Compare genre names
                exists = true;
                break;
            }
        }
    
        if (exists) {
            System.out.println("Genre '" + genre.getGenre() + "' is already in " + name + "'s favorites.");
        } else {
            genres.add(genre);
            genre.addUser(this);
            System.out.println("Genre '" + genre.getGenre() + "' added to " + name + "'s favorites.");
        }
    }
    
    public void removeGenre(Genre genre) {
        boolean exists = false;
        Genre toRemove = null;
    
        for (Genre g : genres) {
            if (g.getGenre().equalsIgnoreCase(genre.getGenre())) { // Compare genre names
                exists = true;
                toRemove = g;
                break;
            }
        }
    
        if (exists) {
            genres.remove(toRemove);
            genre.removeUser(this);
            System.out.println("Genre '" + genre.getGenre() + "' removed from " + name + "'s favorites.");
        } else {
            System.out.println("Genre '" + genre.getGenre() + "' is not in " + name + "'s favorites.");
        }
    }
    
    public void Notify(String movie,String genre){
        System.out.println("Notification for " + name + " : " +  movie + " ' movie uploaded in genre : " + genre );

    }

    
};
                                                                                   