package com.github.programmerr47.artec_test_task.api.objects;

import java.util.Collections;
import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-13
 */
@SuppressWarnings("unused")
public class GetShopsObject implements RequestObject {
    private int type;
    private Position position;
    private boolean onlyIds;
    private List<Integer> providerFilter;
    private int skip;
    private int top;
    private List<Integer> typeFilter;
    private Radius radius;

    private GetShopsObject(Builder builder) {
        this.type = builder.type;
        this.position = builder.position;
        this.onlyIds = builder.onlyIds;
        this.providerFilter = builder.providerFilter;
        this.skip = builder.skip;
        this.top = builder.top;
        this.typeFilter = builder.typeFilter;
        this.radius = builder.radius;
    }

    public int getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isOnlyIds() {
        return onlyIds;
    }

    public List<Integer> getProviderFilter() {
        return Collections.unmodifiableList(providerFilter);
    }

    public int getSkip() {
        return skip;
    }

    public int getTop() {
        return top;
    }

    public List<Integer> getTypeFilter() {
        return Collections.unmodifiableList(typeFilter);
    }

    public Radius getRadius() {
        return radius;
    }

    public static class Builder {
        private int type;
        private Position position;
        private boolean onlyIds;
        private List<Integer> providerFilter;
        private int skip;
        private int top;
        private List<Integer> typeFilter;
        private Radius radius;

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder setOnlyIds(boolean onlyIds) {
            this.onlyIds = onlyIds;
            return this;
        }

        public Builder setProviderFilter(List<Integer> providerFilter) {
            this.providerFilter = providerFilter;
            return this;
        }

        public Builder setSkip(int skip) {
            this.skip = skip;
            return this;
        }

        public Builder setTop(int top) {
            this.top = top;
            return this;
        }

        public Builder setTypeFilter(List<Integer> typeFilter) {
            this.typeFilter = typeFilter;
            return this;
        }

        public Builder setRadius(Radius radius) {
            this.radius = radius;
            return this;
        }

        public GetShopsObject build() {
            return new GetShopsObject(this);
        }
    }
}
