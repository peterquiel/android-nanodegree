package pqsolutions.de.sunshine;

import junit.framework.TestCase;

import pqsolutions.de.sunshine.data.WeatherData;
import pqsolutions.de.sunshine.data.WeatherDataParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Peter Quiel on 04.11.15.
 */
public class WeatherDataParserTest extends TestCase {


    private final static String WEATHER_DATA_FREMONT_JUN_4 = "{\"cod\":\"200\",\"message\":2.5405,\"city\":{\"id\":\"5350734\",\"name\":\"Fremont\",\"coord\":{\"lon\":-121.982,\"lat\":37.5509},\"country\":\"United States of America\",\"population\":0},\"cnt\":7,\"list\":[{\"dt\":1401912000,\"temp\":{\"day\":28.12,\"min\":12.74,\"max\":28.12,\"night\":12.74,\"eve\":23.73,\"morn\":28.12},\"pressure\":1004.41,\"humidity\":52,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.41,\"deg\":263,\"clouds\":0},{\"dt\":1401998400,\"temp\":{\"day\":28.58,\"min\":10.3,\"max\":28.58,\"night\":10.3,\"eve\":22.76,\"morn\":16.08},\"pressure\":1002.75,\"humidity\":47,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":3.13,\"deg\":261,\"clouds\":0},{\"dt\":1402084800,\"temp\":{\"day\":26.73,\"min\":11.2,\"max\":27.55,\"night\":11.2,\"eve\":22.94,\"morn\":12.53},\"pressure\":1001.79,\"humidity\":50,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.01,\"deg\":267,\"clouds\":0},{\"dt\":1402171200,\"temp\":{\"day\":30.67,\"min\":11.81,\"max\":31.25,\"night\":11.81,\"eve\":25.92,\"morn\":15.5},\"pressure\":1001.95,\"humidity\":48,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.91,\"deg\":271,\"clouds\":0},{\"dt\":1402257600,\"temp\":{\"day\":16.6,\"min\":10.32,\"max\":17.52,\"night\":12.62,\"eve\":17.52,\"morn\":10.32},\"pressure\":1003.29,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.84,\"deg\":292,\"clouds\":0},{\"dt\":1402344000,\"temp\":{\"day\":14.82,\"min\":10.66,\"max\":16.14,\"night\":11.97,\"eve\":16.14,\"morn\":10.66},\"pressure\":1009.02,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":3.72,\"deg\":305,\"clouds\":12},{\"dt\":1402430400,\"temp\":{\"day\":15.26,\"min\":9.84,\"max\":16.75,\"night\":12.76,\"eve\":16.75,\"morn\":9.84},\"pressure\":1009.9,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":5.87,\"deg\":325,\"clouds\":0}]}";

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private List<WeatherData> weatherData;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        weatherData = new WeatherDataParser().parseWeatherData(WEATHER_DATA_FREMONT_JUN_4, simpleDateFormat.parse("01.01.2010"));

    }

    public void test_if_list_size_equals_count() {
        assertEquals(7, weatherData.size());
    }

    public void test_if_date_are_set_correctly() throws ParseException {
        assertEquals(simpleDateFormat.parse("01.01.2010"), weatherData.get(0).getDay());
        assertEquals(simpleDateFormat.parse("02.01.2010"), weatherData.get(1).getDay());
        assertEquals(simpleDateFormat.parse("03.01.2010"), weatherData.get(2).getDay());
        assertEquals(simpleDateFormat.parse("04.01.2010"), weatherData.get(3).getDay());
        assertEquals(simpleDateFormat.parse("05.01.2010"), weatherData.get(4).getDay());
        assertEquals(simpleDateFormat.parse("06.01.2010"), weatherData.get(5).getDay());
        assertEquals(simpleDateFormat.parse("07.01.2010"), weatherData.get(6).getDay());
    }

    public void test_parse_description_weather_data() throws ParseException {
        for (int i = 0; i < this.weatherData.size(); i++) {
            if (i == 5) {
                assertEquals("light rain", this.weatherData.get(i).getDescription());
            } else {
                assertEquals("sky is clear", this.weatherData.get(i).getDescription());
            }
        }
    }

    public void test_if_min_temp_is_parsed_correctly() {
        assertEquals(12.74, this.weatherData.get(0).getDayMinTemperatur(), 0.001);
        assertEquals(10.3, this.weatherData.get(1).getDayMinTemperatur(), 0.001);
        assertEquals(11.2, this.weatherData.get(2).getDayMinTemperatur(), 0.001);
    }

    public void test_if_max_temp_is_parsed_correctly() {
        assertEquals(28.12, this.weatherData.get(0).getDayMaxTemperatur(), 0.001);
        assertEquals(28.58, this.weatherData.get(1).getDayMaxTemperatur(), 0.001);
        assertEquals(27.55, this.weatherData.get(2).getDayMaxTemperatur(), 0.001);
    }

    public void test_if_day_temperature_is_parsed_correctly() {
        assertEquals(28.12, this.weatherData.get(0).getDayTemperatur(), 0.001);
        assertEquals(28.58, this.weatherData.get(1).getDayTemperatur(), 0.001);
        assertEquals(26.73, this.weatherData.get(2).getDayTemperatur(), 0.001);
    }
}