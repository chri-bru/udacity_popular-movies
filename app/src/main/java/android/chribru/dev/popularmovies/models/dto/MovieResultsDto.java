package android.chribru.dev.popularmovies.models.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResultsDto implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<MovieDto> movieDtos = new ArrayList<>();
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public final static Parcelable.Creator<MovieResultsDto> CREATOR = new Creator<MovieResultsDto>() {
        public MovieResultsDto createFromParcel(Parcel in) {
            return new MovieResultsDto(in);
        }

        public MovieResultsDto[] newArray(int size) {
            return (new MovieResultsDto[size]);
        }
    };

    /**
     * No args constructor for use in serialization
     *
     */
    public MovieResultsDto() {
    }

    /**
     * Constructor with all parameters
     * @param movieDtos list of all movieDtos
     * @param totalResults number of total results
     * @param page page number
     * @param totalPages total number of pages
     */
    public MovieResultsDto(Integer page, List<MovieDto> movieDtos, Integer totalResults, Integer totalPages) {
        super();
        this.page = page;
        this.movieDtos = movieDtos;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    /**
     * Constructor for Parcelable
     * @param in Parcel to use to initialize the object with
     */
    protected MovieResultsDto(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.movieDtos, (MovieDto.class.getClassLoader()));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(movieDtos);
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

    public MovieResultsDto withPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<MovieDto> getMovieDtos() {
        return movieDtos;
    }

    public void setMovieDtos(List<MovieDto> movieDtos) {
        this.movieDtos = movieDtos;
    }

    public MovieResultsDto withResults(List<MovieDto> movieDtos) {
        this.movieDtos = movieDtos;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public MovieResultsDto withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public MovieResultsDto withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}
