package com.hss.bean;

import java.io.Serializable;

public class Role implements Serializable {

    private Long id;
    /** 角色名 */
    private String name;
    /** 中文名 */
    private String nameZh;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}
