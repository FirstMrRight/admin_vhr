package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Position {
    private Integer id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createDate;

    private Boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim ();
    }

    public Date getCreatedate() {
        return createDate;
    }

    public void setCreatedate(Date createdate) {
        this.createDate = createdate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
