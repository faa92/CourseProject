package org.example.utils;

public class PropertiesReader {
    public String getProperty(AppProperties property) {
        try {
            return System.getenv().get(property.getProp());
        } catch (NullPointerException nullPointerException) {
            return null;
        }
    }
    public enum AppProperties {
        LOCAL_CURRENCY("localCurrency");
        private String prop;
        AppProperties(String prop) {
            this.prop = prop;
        }
        public String getProp() {
            return prop;
        }
    }
}
