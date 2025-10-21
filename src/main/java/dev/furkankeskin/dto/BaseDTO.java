package dev.furkankeskin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {

    private Long id;

    private Date createTime;
}
