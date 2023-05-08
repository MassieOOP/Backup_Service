package com.Telecom;

import com.Telecom.dao.BackupInfoDao;
import com.Telecom.entity.BackupInfo;
import com.Telecom.service.BackupService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class BackupApplicationTest {
    @Resource
    private BackupInfoDao backupInfoDao;

    @Resource
    private BackupService backupService;

    @Test
    public void testSelectBackupInfoList() {
        List<BackupInfo> backupInfos = backupService.selectBackupInfoList();
        for (BackupInfo backupInfo : backupInfos) {
            System.out.println(backupInfo);
        }
    }

    @Test
    public void testAddBackupInfo() {
        BackupInfo backupInfo = new BackupInfo(null, "root", "root", "192.168.160.134", "47.95.208.87", "/backup_test2/*", "backup_test2", "16:05", "1");
        int affected = backupService.addBackupInfo(backupInfo);
        if(affected == 1){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    @Test
    public void testBackup(){
        BackupInfo backupInfo = new BackupInfo(null, "root", "root", "192.168.160.134", "47.95.208.87", "/backup_test/*", "backup_test", "16:05", "1");
        backupService.backup(backupInfo);
    }

    @Test
    public void testStopBackupById(){
        backupInfoDao.stopBackupById(1L);
    }
}
