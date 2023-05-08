package com.Telecom.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackupInfo {
    private Integer id;
    private String userNameA;
    private String userNameB;
    private String ipA;
    private String ipB;
    private String backupPathA;
    private String backupPathB;
    private String time;
    private String state;
}