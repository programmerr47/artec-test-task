package com.github.programmerr47.artec_test_task.api.objects;

import java.util.Collections;
import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-14
 */
public class ShopsResult implements ResponseObject{
    private List<String> actions;
    private List<Location> locations;

    private ShopsResult(Builder builder) {
        this.actions = builder.actions;
        this.locations = builder.locations;
    }

    public List<String> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    public static class Builder {
        private List<String> actions;
        private List<Location> locations;

        public Builder setActions(List<String> actions) {
            this.actions = actions;
            return this;
        }

        public Builder setLocations(List<Location> locations) {
            this.locations = locations;
            return this;
        }

        public ShopsResult build() {
            return new ShopsResult(this);
        }
    }
}
