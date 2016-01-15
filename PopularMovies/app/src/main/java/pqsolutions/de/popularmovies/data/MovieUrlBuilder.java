package pqsolutions.de.popularmovies.data;

/**
 * Used to build the url for different kind of movie db request.
 * <p/>
 * Created by pedda on 26.11.15.
 */
public interface MovieUrlBuilder {

    PageableBuilder topRated();

    CommonParameterBuilder genre();

    PageableBuilder popular();

    interface PageableBuilder {

        /**
         * sets the page to be loaded. Default is <code>0</code>.
         *
         * @param pageNumber the page, greater than 0;
         * @return <code>this</code>.
         */
        CommonParameterBuilder page(int pageNumber);

    }

    interface CommonParameterBuilder {

        /**
         * ISO 639-1 code for the language. This is the two letter language abbreviation, like US, EN, FR aso.
         *
         * @param locale the two letter language abbreviation.
         * @return <code>this</code>.
         */
        CommonParameterBuilder language(String locale);

        /**
         * Returns finally the url that has been build.
         *
         * @param apiKey to use for the request.
         * @return the url
         */
        String apiKey(String apiKey);

    }
}

