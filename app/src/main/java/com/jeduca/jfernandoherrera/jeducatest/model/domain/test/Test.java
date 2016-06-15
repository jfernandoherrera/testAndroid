package com.jeduca.jfernandoherrera.jeducatest.model.domain.test;


public class Test {

    private long id;
    private String type;
    private int percentage;
    private int accomplished;
    private int notAccomplished;
    private int dontApply;

    public Test(long id, String type) {

        this.id = id;

        this.type = type;

    }

    public int getAccomplished() {

        return accomplished;

    }

    public int getDontApply() {

        return dontApply;

    }

    public int getNotAccomplished() {

        return notAccomplished;

    }

    public int getPercentage() {

        return percentage;

    }

    public long getId() {

        return id;

    }

    public String getType() {

        return type;

    }

    public void setId(long id) {

        this.id = id;

    }

    public void setAccomplished(int accomplished) {

        this.accomplished = accomplished;

    }

    public void setDontApply(int dontApply) {

        this.dontApply = dontApply;

    }

    public void setNotAccomplished(int notAccomplished) {

        this.notAccomplished = notAccomplished;

    }

    public void setPercentage(int percentage) {

        this.percentage = percentage;

    }

    public void setType(String type) {

        this.type = type;

    }

}
