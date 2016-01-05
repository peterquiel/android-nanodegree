package pqsolutions.de.sunshine.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pedda on 22.12.15.
 */
public class WeatherData implements Parcelable, Serializable {
    private Double dayTemperatur;
    private Double dayMinTemperatur;
    private Double dayMaxTemperatur;
    private Date day;
    private String description;
    private Double nightTemperatur;


    public void setDayTemperatur(Double dayTemperatur) {
        this.dayTemperatur = dayTemperatur;
    }

    public Double getDayTemperatur() {
        return dayTemperatur;
    }

    public void setDayMinTemperatur(Double dayMinTemperatur) {
        this.dayMinTemperatur = dayMinTemperatur;
    }

    public Double getDayMinTemperatur() {
        return dayMinTemperatur;
    }


    public void setDayMaxTemperatur(Double dayMaxTemperatur) {
        this.dayMaxTemperatur = dayMaxTemperatur;
    }

    public Double getDayMaxTemperatur() {
        return dayMaxTemperatur;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getDay() {
        return day;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNightTemperatur(Double nightTemperatur) {
        this.nightTemperatur = nightTemperatur;
    }

    public Double getNightTemperatur() {
        return nightTemperatur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this);
    }

    public static final Parcelable.Creator<WeatherData> CREATOR = new Parcelable.Creator<WeatherData>() {

        public WeatherData createFromParcel(Parcel in) {
            return (WeatherData) in.readSerializable();
        }

        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };
}
