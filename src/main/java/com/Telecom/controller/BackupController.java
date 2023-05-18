package com.Telecom.controller;

import com.Telecom.entity.BackupInfo;
import com.Telecom.entity.Result;
import com.Telecom.service.BackupService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "备份服务接口")
@RestController
public class BackupController {
    //装备BackupService
    @Resource
    private BackupService backupService;

    //查询所有备份的接口
    @GetMapping("backup/select/all")
    @ApiOperation("查询当前数据库中所有的备份信息")
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 401,message = "请求未经授权"),
            @ApiResponse(code = 403,message = "服务器收到请求，但拒绝提供服务"),
            @ApiResponse(code = 404,message = "请求的资源不存在")

    })
    public Result selectBackupInfoList(){
        List<BackupInfo> backupInfos = backupService.selectBackupInfoList();
        if (backupInfos != null) {
            return Result.success("查询成功",backupInfos);
        }else {
            return Result.error("402","查询失败");
        }
    }


    //添加备份接口
    @PostMapping("backup/update/addBackupInfo")
    @ApiOperation("向数据库中添加备份信息")
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功"),
            @ApiResponse(code = 401,message = "请求未经授权"),
            @ApiResponse(code = 403,message = "服务器收到请求，但拒绝提供服务"),
            @ApiResponse(code = 404,message = "请求的资源不存在")

    })
    public Result addBackupInfo(@RequestBody BackupInfo backupInfo) {
        int affected = backupService.addBackupInfo(backupInfo);
        if (affected > 0) {
            return Result.success("添加备份信息成功",1);
        }else{
            return Result.error("402","添加备份信息失败");
        }
    }

    //根据id 关闭某项备份
    @GetMapping("backup/update/stopBackup")
    @ApiOperation("根据id 关闭数据库某项备份")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "备份id",required = true,paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "关闭成功"),
            @ApiResponse(code = 401,message = "请求未经授权"),
            @ApiResponse(code = 403,message = "服务器收到请求，但拒绝提供服务"),
            @ApiResponse(code = 404,message = "请求的资源不存在")

    })
    public Result stopBackupById(@PathVariable("id")Long id){
        int affected = backupService.stopBackupById(id);
        if (affected > 0) {
            return Result.success("关闭id为 "+id+" 的备份成功",1);
        }else{
            return Result.error("402","关闭失败,请稍后重试");
        }
    }

    //根据id 开启某项备份
    @GetMapping("backup/update/enableBackup")
    @ApiOperation("根据id 开启数据库某项备份")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "备份id",required = true,paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "开启成功"),
            @ApiResponse(code = 401,message = "请求未经授权"),
            @ApiResponse(code = 403,message = "服务器收到请求，但拒绝提供服务"),
            @ApiResponse(code = 404,message = "请求的资源不存在")

    })
    public Result enableBackupById(@PathVariable("id")Long id){
        int affected = backupService.enableBackupById(id);
        if (affected > 0) {
            return Result.success("开启id为 "+id+" 的备份成功",1);
        }else{
            return Result.error("402","开启失败,请稍后重试");
        }
    }

}
