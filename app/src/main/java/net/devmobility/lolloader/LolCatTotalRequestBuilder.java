package net.devmobility.lolloader;

public class LolCatTotalRequestBuilder {

    private final String searchTag;
    private final int perPage;
    private final int page;

    public static SearchTag builder() {
        return new Builder();
    }

    // mandatory creation
    private LolCatTotalRequestBuilder(Builder builder) {
        this.searchTag = builder.searchTag;
        this.perPage = builder.perPage;
        this.page = builder.page;
    }

    public static class Builder implements SearchTag, PerPage, Page, Build {
        private String searchTag;
        private int perPage;
        private int page;

        public PerPage searchTag(String searchTag) {
            this.searchTag = searchTag;
            return this;
        }

        public Page perPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public Build page(int page) {
            this.page = page;
            return this;
        }

        public LolCatTotalRequestBuilder build() {
            return new LolCatTotalRequestBuilder(this);
        }
    }


    @Override
    public String toString() {
        //            private String buildPhotoUri (String farmId, String serverId, String id, String secret){
//                Uri.Builder builder = new Uri.Builder();
//                builder.scheme("https")
//                        .authority(String.format("farm%s.staticflickr.com", farmId))
//                        .appendPath(serverId)
//                        .appendPath(String.format("%s_%s", id, secret));
//                return builder.build().toString();
//            }
        return "URL";
    }

    interface SearchTag {
        PerPage searchTag(String url);
    }

    interface PerPage {
        Page perPage(int perPage);
    }

    interface Page {
        Build page(int page);
    }

    interface Build {
        LolCatTotalRequestBuilder build();
    }


}
