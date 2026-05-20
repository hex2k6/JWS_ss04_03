package re.ss04_03.Model;

public class Movie {
    private String movieId;
    private String title;
    private String genre;

    public Movie(String movieId, String title, String genre) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
    }

    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
}