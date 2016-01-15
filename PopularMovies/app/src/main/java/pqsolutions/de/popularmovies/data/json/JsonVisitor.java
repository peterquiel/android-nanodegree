package pqsolutions.de.popularmovies.data.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Peter Quiel on 12.11.15.
 */
public interface JsonVisitor {

    void handle(String key, Object value);

    void handle(String key, String value);

    void handle(String key, Boolean value);

    void handle(String key, Integer value);

    void handle(String key, Long value);

    void handle(String key, Double value);

    void handle(String key, JSONObject value) throws JSONException;

    void handle(String key, JSONArray value) throws JSONException;

}
