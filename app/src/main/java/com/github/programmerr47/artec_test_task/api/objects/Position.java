package com.github.programmerr47.artec_test_task.api.objects;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
@SuppressWarnings("unused")
public class Position implements RequestObject {
    private double latitude;
    private double longitude;

    private Position(Builder builder) {
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static class Builder {
        private Double longitude;
        private Double latitude;

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Position build() {
            return new Position(this);
        }
    }
}
