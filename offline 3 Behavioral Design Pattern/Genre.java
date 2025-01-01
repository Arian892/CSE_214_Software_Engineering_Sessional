import java.util.ArrayList;
import java.util.List;

public abstract class Genre {
    List<String> movieList = new ArrayList<>();
    List<User> users = new ArrayList<>();
    String genre ;
    // public abstract String getGenre();
    
    public void addMovie(String movie) {
        boolean exists = false;
        for (String existingMovie : movieList) {
            if (existingMovie.equalsIgnoreCase(movie)) {
                exists = true;
                break;
            }
        }
    
        if (exists) {
            System.out.println("The movie '" + movie + "' already exists in the '" + genre + "' genre.");
        } else {
            movieList.add(movie);
            System.out.println("The movie '" + movie + "' has been added to the '" + genre + "' genre.");
            Notify(movie);
        }
    }
    

    public void Notify(String movie) {
        for (User user : users) {
            Thread notificationThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    user.Notify(movie, genre);
                }
            });
            notificationThread.start();
        }
    }
    
    public void addUser(User user){
        users.add(user);
    }
    public void removeUser(User user){
        users.remove(user);
    }
    public String getGenre(){
        return genre;
    }
    
}

class ThrillerGenre extends Genre{
    ThrillerGenre(){
        genre = "Thriller";
    }

  

};


class HorrorGenre extends Genre{
    HorrorGenre(){
        genre = "Horror";
    }


};


class ComedyGenre extends Genre{
    ComedyGenre(){
        genre = "Comedy";
    }

};