package org.javaboy.vhr.model;


import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Integer id;

    private String name;

    private String gender;

    private Date birthday;

    private String idCard;

    private String wedlock;

    private Integer nationId;

    private String nativePlace;

    private Integer politicId;

    private String email;

    private String phone;

    private String address;

    private Integer departmentId;

    private Integer jobLevelId;

    private Integer posId;

    private String engageForm;

    private String tiptopDegree;

    private String specialty;

    private String school;

    private Date beginDate;

    private String workState;

    private String workID;

    private Double contractTerm;

    private Date conversionTime;

    private Date notworkDate;

    private Date beginContract;

    private Date endContract;

    private Integer workAge;


    /**
     * 以下是一对一的情况
     */
    private Nation nation;

    private Politicsstatus politicsstatus;

    private Department department;

    private JobLevel jobLevel;

    private Position position;

}
