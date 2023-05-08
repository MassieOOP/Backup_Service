package com.Telecom.dao;

import com.Telecom.entity.BackupInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackupInfoDao {
    public int addBackupInfo(BackupInfo backupInfo);

    public List<BackupInfo>  selectBackupInfoList();

    public int stopBackupById(Long id);

    public int enableBackupById(Long id);
}
