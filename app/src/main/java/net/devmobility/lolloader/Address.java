package net.devmobility.lolloader;

public class Address {
    private String url;
    private int port;

    // only builder should be able to create an instance
    private Address(Builder builder) {
        this.url = builder.url;
        this.port = builder.port;
    }

    public static Url builder() {
        return new Builder();
    }

    public static class Builder implements Url, Port, Build {
        private String url;
        private int port;

        public Port url(String url) {
            this.url = url;
            return this;
        }

        public Build port(int port) {
            this.port = port;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    interface Url {
        Port url(String url);
    }

    interface Port {
        Build port(int port);
    }

    interface Build {
        Address build();
    }

}