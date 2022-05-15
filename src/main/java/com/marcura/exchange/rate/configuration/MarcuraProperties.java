package com.marcura.exchange.rate.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ruhancoetzee on May-2022.
 */

/**
 * TODO Spring auto configuration to load required external properties for the exchange rate api
 */
@ConfigurationProperties(prefix = "marcura")
public class MarcuraProperties {

    private String api_Key;
    private Exchange exchange;

    public String getApi_Key() {
        return api_Key;
    }

    public void setApi_Key(String api_Key) {
        this.api_Key = api_Key;
    }

    public Exchange getExchange() { return exchange; }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public static class Exchange {

        private String url;
        private String base;
        private Double default_spread;

        public String getUrl() { return url; }

        public void setUrl(String url) { this.url = url; }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public Double getDefault_spread() {
            return default_spread;
        }

        public void setDefault_spread(Double default_spread) {
            this.default_spread = default_spread;
        }
    }
}
