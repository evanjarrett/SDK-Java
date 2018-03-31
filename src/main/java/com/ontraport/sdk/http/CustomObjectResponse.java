package com.ontraport.sdk.http;

import com.ontraport.sdk.http.AbstractResponse;

public class CustomObjectResponse extends AbstractResponse {
    private Data[] data;

    public Data[] getData() {
        return data;
    }

    public class Data implements com.ontraport.sdk.http.AbstractResponse.Data {
        String id;
        String name;
        String date_created;
        String primary_nav;
        String singular;
        String plural;
        String possessive;
        String plural_possessive;
        String icon;
        String theme;
        String deletable;
        String object_label;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDateCreated() {
            return date_created;
        }

        public boolean onPrimaryNav() {
            return primary_nav.equals("1");
        }

        public String getSingular() {
            return singular;
        }

        public String getPlural() {
            return plural;
        }

        public String getPossessive() {
            return possessive;
        }

        public String getPluralPossessive() {
            return plural_possessive;
        }

        public String getIcon() {
            return icon;
        }

        public String getTheme() {
            return theme;
        }

        public String getDeletable() {
            return deletable;
        }

        public String getLabel() {
            return object_label;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Data) {
                Data d = (Data) o;
                return getId().equals(d.getId());
            }
            if (o instanceof String) {
                String s = (String) o;
                return getId().equals(s);
            }
            if (o instanceof Integer) {
                String s = Integer.toString((Integer) o);
                return getId().equals(s);
            }
            return false;
        }


    }
}
