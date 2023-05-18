package com.Telecom.service;

import com.Telecom.entity.BackupInfo;

import java.util.List;

public interface BackupService {
    //查询所有备份信息
    public List<BackupInfo>  selectBackupInfoList();

    //添加备份信息
    public int addBackupInfo(BackupInfo backupInfo);

    //backup
    public void backup(BackupInfo backupInfo);

    //查询当前数据库所有备份信息 并按照时间执行备份
    public void checkAndStartBackup();

    //根据id值 将某项备份关闭
    public int stopBackupById(Long id);

    //根据id值 将某项备份开启
    public int enableBackupById(Long id);

    //分页查询
    public List<BackupInfo>  selectBackupInfoByPage(Integer pageNum,Integer pageSize);


}
