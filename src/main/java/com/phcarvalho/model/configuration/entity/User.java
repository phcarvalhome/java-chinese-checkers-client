package com.phcarvalho.model.configuration.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    public static final String SERVER = "SERVER";

    private String name;
    private String host;
    private Integer port;

    private User(String name, String host, Integer port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public static User of(String host, Integer port){
        return new User(null, host, port);
    }

    public static User of(String name, String host, Integer port){
        return new User(name, host, port);
    }

    public static User ofServer(String host, Integer port){
        return new User(SERVER, host, port);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(host, user.host) &&
                Objects.equals(port, user.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, host, port);
    }

    @Override
    public String toString() {
        return "[" + host + "] " + name;
    }
}
