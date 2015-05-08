package net.devmobility.lolloader.utils;

public class Constants {

    //https://api.flickr.com/services/rest/?method=flickr.photos.search
    // &api_key=ee2b515275ae5273017da4a6a069f925
    // &tags=lolcat
    // &per_page=5
    // &page=1
    // &format=json
    // &nojsoncallback=1";


    /**
     * Flickr API Key  (super secret...)
     */
    // Flickr API key for this app
    public static final String MY_API_KEY = "ee2b515275ae5273017da4a6a069f925";



    /**
     * Base URLs
     */
    // base Flickr URL for making RESTful requests
    public static String FLICKR_BASE_SEARCH_URL = "https://api.flickr.com/services/rest/";

    // method for searching Flickr API for image data
    private final String METHOD_SEARCH =  "flickr.photos.search";

    // method for testing Flickr API services
    private final String METHOD_ECHO =  "flickr.test.echo";



    /**
     * Params
     */
    // total images given for a specified tag
    public static final String TOTAL = "total";
    // return response format parameter
    public static final String FORMAT = "json";
    // remove that pesky Flickr header
    public static final String NO_JSON_CALLBACK = "1";
    // Flickr API key tag
    public static final String API_KEY = "api_key";
    // search tags
    public static final String TAGS = "tags";
    // how many hits per page
    public static final String PER_PAGE = "per_page";
    // number of page to request data from
    public static final String PAGE = "page";

}