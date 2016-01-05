package pqsolutions.de.sunshine.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pedda on 12.11.15.
 */
public interface JsonVisitable {

    JsonVisitable use(JSONArray json);

    JsonVisitable use(JSONObject json);

    void accept(JsonVisitor visitor) throws JSONException;
}
