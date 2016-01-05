package pqsolutions.de.sunshine;

import android.view.View;
import android.widget.TextView;

/**
 * Created by pedda on 28.12.15.
 */
public class ViewHolder {

    private final TextView dateTextView;
    private final TextView forecastTextView;
    private final TextView lowTextView;
    private final TextView highTextView;

    public ViewHolder(View view ) {
        dateTextView = ((TextView) view.findViewById(R.id.list_item_date_textview));
        forecastTextView = ((TextView) view.findViewById(R.id.list_item_forecast_text_view));
        lowTextView = ((TextView) view.findViewById(R.id.list_item_low_textview));
        highTextView = ((TextView) view.findViewById(R.id.list_item_high_textview));
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public TextView getForecastTextView() {
        return forecastTextView;
    }

    public TextView getLowTextView() {
        return lowTextView;
    }

    public TextView getHighTextView() {
        return highTextView;
    }
}
