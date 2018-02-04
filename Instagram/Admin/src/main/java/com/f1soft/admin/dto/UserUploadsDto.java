package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class UserUploadsDto {
    private String username;
    private String image_path;
    private Date created_date;
    private String caption;
    private List<String> comments;
    private List<Long> id;
    private String profile_pic;
}
