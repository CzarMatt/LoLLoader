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
    // Flickr API app secret token
    public static final String MY_API_SECRET = "b6cb6abce3e927c4";


    /**
     * Base URLs
     */
    // Flickr network scheme
    public static String FLICKR_SCHEME = "https";
    // base Flickr URL for making RESTful requests
    public static String FLICKR_BASE_URL = "api.flickr.com";
    // Flickr services type path
    public static String FLICKR_SERVICES_PATH = "services";
    // Flickr api type
    public static String FLICKR_API_TYPE_PATH = "rest";



    /**
     * HTTP Params
     */
    // method for searching Flickr API for image data
    public static final String METHOD = "method";
    // remove that pesky Flickr header
    public static final String NO_JSON_CALLBACK = "nojsoncallback";
    // Flickr API key tag
    public static final String API_KEY = "api_key";
    // search tags
    public static final String TAGS = "tags";
    // how many hits per page
    public static final String PER_PAGE = "per_page";
    // number of page to request data from
    public static final String PAGE = "page";
    // type of responce data format
    public static final String FORMAT = "format";


    /**
     * Query params
     */
    // method for searching Flickr API for image data
    public static final String METHOD_SEARCH = "flickr.photos.search";
    // method for testing Flickr API services
    public static final String METHOD_ECHO = "flickr.test.echo";
    // return response format parameter
    public static final String FORMAT_TYPE = "json";
    // value that removes json api header from requests
    public static final String NO_JSON_CALLBACK_VALUE = "1";


    /**
     * JSON values
     */
    // total images given for a specified tag
    public static final String TOTAL = "total";



}