package com.Telecom.controller;

import com.Telecom.entity.BackupInfo;
import com.Telecom.service.BackupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "展示分页效果的接口")
@Controller
public class BackupInfoController {
    @Resource
    private BackupService backupService;

    @GetMapping("/info")
    @ApiOperation("分页查询当前数据库中所有的备份信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页查询的页码", required = false, defaultValue = "1", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数", required = false, defaultValue = "6", paramType = "path")
    })
    public String queryAllInfo(@RequestParam(defaultValue = "1")Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize,
                               Model model){
        try{
            //调用分页查询方法
            List<BackupInfo> backupInfos = backupService.selectBackupInfoByPage(pageNum,pageSize);
            //查询出的数据传输到前端
            model.addAttribute("backupInfos",backupInfos);
            //设置页面大小
            PageInfo pageInfo = new PageInfo(backupInfos, pageSize);
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            //清理 ThreadLocal 存储分页参数 保证线程安全
            PageHelper.clearPage();
        }
        return "allInfo";
    }
}
