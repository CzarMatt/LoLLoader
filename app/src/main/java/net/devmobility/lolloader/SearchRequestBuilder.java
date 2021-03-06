package net.devmobility.lolloader;

import android.net.Uri;

import net.devmobility.lolloader.utils.Constants;

public class SearchRequestBuilder {

    private final String searchTag;
    private final int page;

    public static SearchTag builder() {
        return new Builder();
    }

    private SearchRequestBuilder(Builder builder) {
        this.searchTag = builder.searchTag;
        this.page = builder.page;
    }

    public static class Builder implements SearchTag, Page, Build {
        private String searchTag;
        private int page;

        public Page searchTag(String searchTag) {
            this.searchTag = searchTag;
            return this;
        }

        public Build page(int page) {
            this.page = page;
            return this;
        }

        public SearchRequestBuilder build() {
            return new SearchRequestBuilder(this);
        }
    }

    /**
     * Construct the Flickr search URL
     */
    @Override
    public String toString() {
        return buildPhotoUri();
    }

    private String buildPhotoUri() {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(Constants.FLICKR_SCHEME)
                .authority(Constants.FLICKR_BASE_URL)
                .appendPath(Constants.FLICKR_SERVICES_PATH)
                .appendPath(Constants.FLICKR_API_TYPE_PATH)
                .appendQueryParameter(Constants.METHOD, Constants.METHOD_SEARCH)
                .appendQueryParameter(Constants.API_KEY, Constants.MY_API_KEY)
                .appendQueryParameter(Constants.TAGS, searchTag)
                .appendQueryParameter(Constants.PAGE, String.valueOf(page))
                .appendQueryParameter(Constants.FORMAT, Constants.FORMAT_TYPE)
                .appendQueryParameter(Constants.NO_JSON_CALLBACK, Constants.NO_JSON_CALLBACK_VALUE);
        return uriBuilder.build().toString();
    }

    protected interface SearchTag {
        Page searchTag(String url);
    }

    protected interface Page {
        Build page(int page);
    }

    protected interface Build {
        SearchRequestBuilder build();
    }

}