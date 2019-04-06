package android.chribru.dev.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResults implements Parcelable {

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

    public final static Parcelable.Creator<MovieResults> CREATOR = new Creator<MovieResults>() {
        public MovieResults createFromParcel(Parcel in) {
            return new MovieResults(in);
        }

        public MovieResults[] newArray(int size) {
            return (new MovieResults[size]);
        }
    };

    /**
     * No args constructor for use in serialization
     *
     */
    public MovieResults() {
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

    /**
     * Constructor for Parcelable
     * @param in Parcel to use to initialize the object with
     */
    protected MovieResults(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.movies, (Movie.class.getClassLoader()));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(movies);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
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

    public MovieResults withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}
