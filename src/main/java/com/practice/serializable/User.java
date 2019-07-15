package com.practice.serializable;

import java.io.Serializable;

/**
 * @author Higmin
 * @date 2019/7/15 13:51
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 8818836580366910470L;

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
