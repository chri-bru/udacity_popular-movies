package android.chribru.dev.popularmovies.models.dto;

import java.io.Serializable;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResultsDto implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoDto> results = null;
    public final static Parcelable.Creator<VideoResultsDto> CREATOR = new Creator<VideoResultsDto>() {


    @SuppressWarnings({
            "unchecked"
    })
    public VideoResultsDto createFromParcel(Parcel in) {
        return new VideoResultsDto(in);
    }

    public VideoResultsDto[] newArray(int size) {
            return (new VideoResultsDto[size]);
        }
    };

    private final static long serialVersionUID = 7972801271982786517L;

    protected VideoResultsDto(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (VideoResultsDto.class.getClassLoader()));
    }

    public VideoResultsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoDto> getResults() {
        return results;
    }

    public void setResults(List<VideoDto> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
