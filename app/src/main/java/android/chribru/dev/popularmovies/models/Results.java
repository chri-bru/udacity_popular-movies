package android.chribru.dev.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Results implements Parcelable {

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

    public final static Parcelable.Creator<Results> CREATOR = new Creator<Results>() {
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        public Results[] newArray(int size) {
            return (new Results[size]);
        }
    };

    /**
     * No args constructor for use in serialization
     *
     */
    public Results() {
    }

    /**
     * Constructor with all parameters
     * @param movies list of all movies
     * @param totalResults number of total results
     * @param page page number
     * @param totalPages total number of pages
     */
    public Results(Integer page, List<Movie> movies, Integer totalResults, Integer totalPages) {
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
    protected Results(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.movies, (android.chribru.dev.popularmovies.models.Movie.class.getClassLoader()));
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

    public Results withPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Results withResults(List<Movie> movies) {
        this.movies = movies;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Results withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Results withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}
