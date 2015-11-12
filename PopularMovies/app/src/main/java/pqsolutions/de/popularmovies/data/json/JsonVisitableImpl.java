package pqsolutions.de.popularmovies.data.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by pedda on 12.11.15.
 */
public class JsonVisitableImpl implements JsonVisitable, JsonVisitor {

    private JSONArray jsonArray = null;
    private JSONObject jsonObject = null;
    private JsonVisitor visitor;

    @Override
    public JsonVisitable use(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        return this;
    }

    @Override
    public JsonVisitable use(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        return this;
    }

    @Override
    public void accept(JsonVisitor visitor) throws JSONException {
        this.visitor = visitor;
        this.handle(null, this.jsonArray);
        this.handle(null, this.jsonObject);
    }


    @Override
    public void handle(String key, Object value) {
        try {
            if (value instanceof JSONArray) {
                this.handle(key, ((JSONArray) value));
            } else if (value instanceof JSONObject) {
                this.handle(key, ((JSONObject) value));
            } else if (value instanceof Integer) {
                this.handle(key, ((Integer) value));
            } else if (value instanceof Double) {
                this.handle(key, ((Double) value));
            } else if (value instanceof Boolean) {
                this.handle(key, ((Boolean) value));
            } else if (value instanceof Long) {
                this.handle(key, ((Long) value));
            } else if (value instanceof String) {
                this.handle(key, ((String) value));
            } else {
                visitor.handle(key, value);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void handle(String key, String value) {
        visitor.handle(key, value);
    }

    @Override
    public void handle(String key, Boolean value) {
        visitor.handle(key, value);

    }

    @Override
    public void handle(String key, Integer value) {
        visitor.handle(key, value);
    }

    @Override
    public void handle(String key, Long value) {
        visitor.handle(key, value);
    }

    @Override
    public void handle(String key, Double value) {
        visitor.handle(key, value);
    }

    @Override
    public void handle(String key, JSONObject value) throws JSONException {
        visitor.handle(key, value);
        if (value != null) {
            Iterator<String> keys = value.keys();
            while (keys.hasNext()) {
                String nextKey = keys.next();
                Object nextValue = value.get(nextKey);
                this.handle(nextKey, nextValue);
            }
        }
    }

    @Override
    public void handle(String key, JSONArray value) throws JSONException {
        visitor.handle(key, value);
        if (value != null) {
            for (int i = 0; i < value.length(); i++) {
                Object nextValue = value.get(i);
                this.handle(i + "", nextValue);
            }
        }
    }
}
