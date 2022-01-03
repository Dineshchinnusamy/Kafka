package com.model;

public class Athletics {
    String name;
    String event;
    String age;

    public Athletics(String name, String event, String age) {
        this.name = name;
        this.event = event;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Athletics{" +
                "name='" + name + '\'' +
                ", event='" + event + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

