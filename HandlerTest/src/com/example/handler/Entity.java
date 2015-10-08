package com.example.handler;

/**
 * @desc 请用一句话描述此文件
 * @creator caozhiqing
 * @data 2015/9/29
 */
public class Entity {

    private String name;

    private String id;

    public Entity(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
