package pqsolutions.de.popularmovies.data.impl;

import android.os.Parcel;
import android.os.Parcelable;
import pqsolutions.de.popularmovies.data.Genre;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public class GenreImpl implements Genre {

    private int id;
    private String name;

    public static final Parcelable.Creator<Genre> CREATOR = new Parcelable.Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel source) {
            GenreImpl genre = new GenreImpl();
            genre.setName(source.readString());
            genre.setId(source.readInt());
            return genre;
        }

        @Override
        public Genre[] newArray(int size) {
            return new GenreImpl[size];
        }
    };

    public GenreImpl setId(int id) {
        this.id = id;
        return this;
    }

    public GenreImpl setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0x42;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }


}
