package com.dean.reptile.bean;

public class Purchase {
    private int id;
    private String accessoriesId;
    private double price;
    private String people;
    private long create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(String accessoriesId) {
        this.accessoriesId = accessoriesId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
