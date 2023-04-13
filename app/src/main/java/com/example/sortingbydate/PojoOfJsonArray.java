package com.example.sortingbydate;

public class PojoOfJsonArray
{
    public PojoOfJsonArray(String name, String date) {
        this.name = name;
        this.date = date;
    }

    private final String name;
    private final String date;


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

}
