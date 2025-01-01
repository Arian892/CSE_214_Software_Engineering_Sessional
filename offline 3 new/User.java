import java.util.ArrayList;
import java.util.List;

public class User{
    private String name ;
    private List<String> favouriteGenres = new ArrayList<>();
    private DesiFlix desiFlix;



    public User (String name, DesiFlix desiFlix){
        this.name = name;
        this.desiFlix = desiFlix;
        desiFlix.addUser(this); // registration part

    }

    public String getName (){
        return name ;
    }

    public void addFavoriteGenre(String genre) {
        String genre2 = genre.toLowerCase(); // as i always store in lower letter
        if (!favouriteGenres.contains(genre2)) {
            favouriteGenres.add(genre2);
            desiFlix.addGenreForUser(this, genre); 
        } else {
            System.out.println(name + " already has " + genre + " as a favorite genre.");
        }
    }

    public void removeFavoriteGenre(String genre) {
        String genre2 = genre.toLowerCase(); 
        if (favouriteGenres.contains(genre2)) {
            favouriteGenres.remove(genre2);
            desiFlix.removeGenreForUser(this, genre2);
        } else {
            System.out.println(name + " does not have " + genre + " as a favorite genre.");
        }
    }

    public void notify(String movieName, String genre) {
        System.out.println("Notification for " + name + ": A new movie '" + movieName + "' was uploaded in genre '" + genre + "'.");
    }

    public List<String> getfavouriteGenres() {
        return favouriteGenres;
    }
}