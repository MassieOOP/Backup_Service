package com.Telecom.service.impl;

import com.Telecom.dao.BackupInfoDao;
import com.Telecom.entity.BackupInfo;
import com.Telecom.service.BackupService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BackupServiceImpl implements BackupService {
    //装配BackupInfoDao
    @Resource
    private BackupInfoDao backupInfoDao;

    @Override
    public List<BackupInfo> selectBackupInfoList() {
        return backupInfoDao.selectBackupInfoList();
    }

    @Override
    public int addBackupInfo(BackupInfo backupInfo) {
        return backupInfoDao.addBackupInfo(backupInfo);
    }

    @Override
    public int stopBackupById(Long id) {
        return backupInfoDao.stopBackupById(id);
    }

    @Override
    public int enableBackupById(Long id) {
        return backupInfoDao.enableBackupById(id);
    }


    public void backup(BackupInfo backupInfo){
        //登录服务器A的用户名
        String userNameA = backupInfo.getUserNameA();
        //登录服务器B的用户名
        String userNameB = backupInfo.getUserNameB();
        //服务器A的ip
        String ipA = backupInfo.getIpA();
        //服务器B的ip
        String ipB = backupInfo.getIpB();
        //服务器A上要备份的文件路径
        String backupPathA = backupInfo.getBackupPathA();
        //备份到服务器B上的文件路径
        String backupPathB = backupInfo.getBackupPathB();

        String infoA = userNameA + "@" + ipA + ":" + backupPathA;
        String infoB = userNameB + "@" + ipB + ":" + backupPathB;

        String[] command = {"scp", infoA, infoB};
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO();
        Process process = null;
        int exitCode = 0;
        try {
            process = pb.start();
            exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("执行时发生错误,备份失败");
        }
        if (exitCode == 0) {
            System.out.println("File copied successfully.");
        } else {
            System.out.println("File copy failed with exit code: " + exitCode);
        }
    }

    @Override
    @Scheduled(cron = "0 */1 * * * ?")
    public void checkAndStartBackup() {

        System.out.println("checkAndStartBackup()方法正在工作...");

        //获取当前系统时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm");
        String currTime = dateFormat.format(date);

        System.out.println("当前系统时间为：" + currTime);

        //获取当前数据库所有备份信息
        List<BackupInfo> backupInfos = backupInfoDao.selectBackupInfoList();

        System.out.println("从当前数据库获取的备份信息表共有 " + backupInfos.size() + "条备份信息");
        //遍历查询是否需要执行：
        for (BackupInfo backupInfo : backupInfos) {
            //触发执行备份的条件：
            // 1. 当前时间为用户设定的”每天要执行备份的时间“
            // 2. 备份信息的”状态“ 为 ”1“ (表示是一个需要执行的备份)

            System.out.println("此备份的备份时间为：" + backupInfo.getTime());
            System.out.println("此备份的备份状态为：" + backupInfo.getState());
            System.out.println("是否满足条件：" + (currTime.equals(backupInfo.getTime()) && "1".equals(backupInfo.getState())));
            if(currTime.equals(backupInfo.getTime()) && "1".equals(backupInfo.getState())){
                System.out.println("开始执行备份...");
                backup(backupInfo);
            }
        }
    }


}
