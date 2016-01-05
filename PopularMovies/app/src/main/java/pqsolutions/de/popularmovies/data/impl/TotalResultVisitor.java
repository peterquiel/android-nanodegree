package pqsolutions.de.popularmovies.data.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pqsolutions.de.popularmovies.data.json.JsonVisitor;
import pqsolutions.de.popularmovies.data.json.JsonVisitorAdapter;

/**
 * Created by pedda on 26.11.15.
 */
public class TotalResultVisitor  extends JsonVisitorAdapter {
        private static final String logTag = TotalResultVisitor.class.getSimpleName();

    public int page;
    public int totalPages;
    public int totalResults;

    @Override
    public void handle(String key, Integer value) {
        if ("page".equals(key)) {
            page = value;
        } else if ("total_pages".equals(key)) {
            totalPages = value;
        } else if ("total_results".equals(key)) {
            totalResults = value;
        }
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
