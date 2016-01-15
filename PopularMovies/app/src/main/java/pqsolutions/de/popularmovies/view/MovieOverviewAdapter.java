package pqsolutions.de.popularmovies.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import pqsolutions.de.popularmovies.R;
import pqsolutions.de.popularmovies.data.Movie;
import pqsolutions.de.popularmovies.util.MovieImageHandler;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by pedda on 13.01.16.
 */
public class MovieOverviewAdapter extends BaseAdapter {

    @Inject
    private MovieImageHandler movieImageHandler;

    @Inject
    private LayoutInflater layoutInflater;

    @Inject
    private Context context;

    private ArrayList<Movie> movieList;

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (this.movieList != null) {
            return this.movieList.size();
        }
        return 0;
    }

    @Override
    public Movie getItem(int position) {
        if (this.movieList != null && -1 < position && position < movieList.size()) {
            return this.movieList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View overviewItem = convertView;
        if (overviewItem == null) {
            overviewItem = this.layoutInflater.inflate(R.layout.movie_overview_item, parent, false);
            new ViewHolder().use(overviewItem);
        }
        bind(position, ((ViewHolder) overviewItem.getTag()));
        return overviewItem;
    }

    private void bind(int position, ViewHolder tag) {
        Movie movie = this.movieList.get(position);
        tag.title.setText(movie.getTitle());
        this.movieImageHandler.putPosterInOverviewIntoTarget(movie, tag.image);
    }

    static class ViewHolder {

        TextView title;

        ImageView image;

        public void use(View overviewItem) {
            overviewItem.setTag(this);
            this.title = ((TextView) overviewItem.findViewById(R.id.movie_orignial_title));
            this.image = ((ImageView) overviewItem.findViewById(R.id.movie_poster_image));
        }
    }
}
