package android.chribru.dev.popularmovies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class MovieResults {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("results")
    @Expose
    private List<Movie> movies = new ArrayList<>();

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    /**
     * Default constructor
     */
    public MovieResults() {

    }

    /**
     * Constructor for setting just the movie list (e.g. favorites)
     * @param movies list of all movies
     */
    public MovieResults(@NonNull List<Movie> movies) {
        this.movies = movies;
        this.totalResults = movies.size();
        this.totalPages = 1;
    }

    /**
     * Constructor with all parameters
     * @param movies list of all movies
     * @param totalResults number of total results
     * @param page page number
     * @param totalPages total number of pages
     */
    public MovieResults(Integer page, List<Movie> movies, Integer totalResults, Integer totalPages) {
        super();
        this.page = page;
        this.movies = movies;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public MovieResults withPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieResults withResults(List<Movie> movies) {
        this.movies = movies;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public MovieResults withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
