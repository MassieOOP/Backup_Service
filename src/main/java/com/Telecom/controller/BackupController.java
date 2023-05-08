package com.Telecom.controller;

import com.Telecom.entity.BackupInfo;
import com.Telecom.entity.Result;
import com.Telecom.service.BackupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BackupController {
    //装备BackupService
    @Resource
    private BackupService backupService;

    //查询所有备份的接口
    @GetMapping("backup/selectAll")
    public Result selectBackupInfoList(){
        List<BackupInfo> backupInfos = backupService.selectBackupInfoList();
        if (backupInfos != null) {
            return Result.success("查询成功",backupInfos);
        }else {
            return Result.error("402","查询失败");
        }
    }


    //添加备份接口
    @PostMapping("backup/add")
    public Result addBackupInfo(@RequestBody BackupInfo backupInfo) {
        int affected = backupService.addBackupInfo(backupInfo);
        if (affected > 0) {
            return Result.success("添加备份信息成功",1);
        }else{
            return Result.error("402","添加备份信息失败");
        }
    }

    //根据id 关闭某项备份
    @GetMapping("backup/stop/{id}")
    public Result stopBackupById(@PathVariable("id")Long id){
        int affected = backupService.stopBackupById(id);
        if (affected > 0) {
            return Result.success("关闭id为 "+id+" 的备份成功",1);
        }else{
            return Result.error("402","关闭失败,请稍后重实");
        }
    }

    //根据id 开启某项备份
    @GetMapping("backup/enable/{id}")
    public Result enableBackupById(@PathVariable("id")Long id){
        int affected = backupService.enableBackupById(id);
        if (affected > 0) {
            return Result.success("开启id为 "+id+" 的备份成功",1);
        }else{
            return Result.error("402","开启失败,请稍后重实");
        }
    }

}
