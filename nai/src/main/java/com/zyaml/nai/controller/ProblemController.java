package com.zyaml.nai.controller;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.service.neo.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-05 22:53
 */
@RequestMapping("/api/problem")
@RestController
@Slf4j
public class ProblemController {

    @Autowired
    ProblemService problemService;

    /**
     * 根据题号 pid 获取对应题信息
     * @param pid 题号
     * @return
     */
    @GetMapping("/{pid}")
    public Resp getByPid(@PathVariable String pid){
        log.info("=====> getByPid-api pid : " + pid);
        return problemService.getProblemByPid(pid);
    }

    /**
     * 获取和 题号pid 一样难度的所有题
     * @param pid
     * @return
     */
    @GetMapping("/diffs/{pid}")
    public Resp getByPidAndDiff(@PathVariable String pid){
        log.info("=====> getByPidAndDiff-api pid : " + pid);
        return problemService.getProblemsByPidAndDiff(pid);
    }

    /**
     * 获取 pid 的题名
     * @param pid
     * @return
     */
    @GetMapping("/name/{pid}")
    public Resp getTitleByPid(@PathVariable String pid){

        log.info("=====> getNameByPid-api pid : " + pid);

        return problemService.getTitle(pid);
    }
}
