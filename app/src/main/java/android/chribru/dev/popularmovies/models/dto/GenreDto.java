package android.chribru.dev.popularmovies.models.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreDto implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public final static Parcelable.Creator<GenreDto> CREATOR = new Creator<GenreDto>() {
        @SuppressWarnings({
                "unchecked"
        })
        public GenreDto createFromParcel(Parcel in) {
            return new GenreDto(in);
        }

        public GenreDto[] newArray(int size) {
            return (new GenreDto[size]);
        }

    };

    protected GenreDto(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public GenreDto() {
    }

    /**
     * Constructor with all parameters
     * @param id genre ide
     * @param name genre name
     */
    public GenreDto(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}