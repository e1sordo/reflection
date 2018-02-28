package com.vlavik.reflection;

/**
 * Created on 28.02.2018 .
 * @author vlavik
 */
public class Event {

    @ImportantForUser
    private String name;

    @ImportantForUser
    private String startTime;

    @ImportantForUser
    private String endTime;

    private String address;

    private String telephone;

    public Event(String name, String startTime, String endTime, String address, String telephone) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.telephone = telephone;
    }
}
