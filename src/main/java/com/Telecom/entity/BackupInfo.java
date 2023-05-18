package com.Telecom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
@ApiModel("备份信息POJO类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackupInfo {

    @ApiModelProperty("备份信息id")
    private Integer id;

    @ApiModelProperty("源服务器的用户名")
    private String userNameA;

    @ApiModelProperty("目标服务器的用户名")
    private String userNameB;

    @ApiModelProperty("源服务器的ip地址")
    private String ipA;

    @ApiModelProperty("目标服务器的ip地址")
    private String ipB;

    @ApiModelProperty("源路径")
    private String backupPathA;

    @ApiModelProperty("目标路径")
    private String backupPathB;

    @ApiModelProperty("每天进行备份的时间")
    private String time;

    @ApiModelProperty("此项备份的状态(1:开启 0:关闭)")
    private String state;
}