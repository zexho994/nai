package com.zyaml.nai.controller;

import com.zyaml.nai.entry.from.ProblemFrom;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.service.neo.DiffService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-07 21:52
 */
@Log4j2
@RestController
@RequestMapping("api/diff")
public class DiffController {

    @Autowired
    DiffService diffService;

    /**
     * 根据难度名称获取所有题目
     * @param from
     * @return
     */
    @PostMapping("/name")
    public List<Problem> getByDiff(@RequestBody ProblemFrom from){
        log.info("=====> getByDiff-api");
        return diffService.getProblemsByDiff(from.getDiff());
    }

}
