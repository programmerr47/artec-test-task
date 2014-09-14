package com.github.programmerr47.artec_test_task.api.objects;

import java.util.Collections;
import java.util.List;

/**
 * @author Michael Spitsin
 * @since 2014-09-14
 */
@SuppressWarnings("unused")
public class Location implements ResponseObject {
    private List<String> actions;
    private String address;
    private Position coordinates;
    private String id;
    private String name;
    private String phone;
    private List<String> services;
    private int type;
    private String workingTime;

    private Location(Builder builder) {
        this.actions = builder.actions;
        this.address = builder.address;
        this.coordinates = builder.coordinates;
        this.id = builder.id;
        this.name = builder.name;
        this.phone = builder.phone;
        this.services = builder.services;
        this.type = builder.type;
        this.workingTime = builder.workingTime;
    }

    public List<String> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public String getAddress() {
        return address;
    }

    public Position getCoordinates() {
        return coordinates;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getServices() {
        return Collections.unmodifiableList(services);
    }

    public int getType() {
        return type;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public static class Builder {
        private List<String> actions;
        private String address;
        private Position coordinates;
        private String id;
        private String name;
        private String phone;
        private List<String> services;
        private int type;
        private String workingTime;

        public Builder setActions(List<String> actions) {
            this.actions = actions;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCoordinates(Position coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setServices(List<String> services) {
            this.services = services;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setWorkingTime(String workingTime) {
            this.workingTime = workingTime;
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
