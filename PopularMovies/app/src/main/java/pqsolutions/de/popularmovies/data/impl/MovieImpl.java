package pqsolutions.de.popularmovies.data.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import pqsolutions.de.popularmovies.data.Genre;
import pqsolutions.de.popularmovies.data.Movie;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class MovieImpl implements Movie {

    private Integer id;
    private String title;
    private String overview;
    private Date releaseDate;
    private String backdropPath;
    private String posterPath;
    private Double popularity;
    private Double voteAverage;
    private Integer voteCount;
    private List<Genre> genres = new ArrayList<>();

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            MovieImpl movie = new MovieImpl();
            movie.setId(source.readInt() );
            movie.setTitle(source.readString() );
            movie.setOverview(source.readString() );
            movie.setReleaseDate(((Date) source.readSerializable()));
            movie.setBackdropPath(source.readString() );
            movie.setPosterPath(source.readString() );
            movie.setPopularity(source.readDouble() );
            movie.setVoteAverage(source.readDouble() );
            movie.setVoteCount(source.readInt() );
            Parcelable[] parcelables = source.readParcelableArray(Thread.currentThread().getContextClassLoader());
            for (Parcelable parcelable : parcelables) {
                if (parcelable instanceof Genre) {
                    movie.addGenre(((Genre) parcelable));
                }
            }
            return movie;
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public Double getPopularity() {
        return popularity;
    }

    @Override
    public Double getRating() {
        return voteAverage;
    }

    @Override
    public Integer getVoteCount() {
        return voteCount;
    }

    @Override
    public Movie addGenre(Genre genre) {
        this.genres.add(genre);
        return this;
    }


    @Override
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int describeContents() {
        return 0x43;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeSerializable(releaseDate);
        dest.writeString(backdropPath);
        dest.writeString(posterPath);
        dest.writeDouble(popularity);
        dest.writeDouble(voteAverage);
        dest.writeInt(voteCount);
        dest.writeParcelableArray(this.genres.toArray(new Genre[this.genres.size()]), flags);
    }
}
