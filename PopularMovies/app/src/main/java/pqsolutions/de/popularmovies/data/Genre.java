package pqsolutions.de.popularmovies.data;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Peter Quiel on 11.11.15.
 */
public interface Genre extends Parcelable {

    int getId();

    String getName();
}
