package com.github.programmerr47.artec_test_task.api.objects;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
@SuppressWarnings("unused")
public class Radius implements RequestObject {
    private int minRadius;
    private int maxRaduis;

    private Radius(Builder builder) {
        this.minRadius = builder.minRadius;
        this.maxRaduis = builder.maxRaduis;
    }

    public int getMinRadius() {
        return minRadius;
    }

    public int getMaxRaduis() {
        return maxRaduis;
    }

    public static class Builder {
        private int minRadius;
        private int maxRaduis;

        public Builder setMinRadius(int minRadius) {
            this.minRadius = minRadius;
            return this;
        }

        public Builder setMaxRaduis(int maxRaduis) {
            this.maxRaduis = maxRaduis;
            return this;
        }

        public Radius build() {
            return new Radius(this);
        }
    }
}
