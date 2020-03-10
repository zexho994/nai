package com.zyaml.nai.service.neo;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.vo.ProblemVO;
import com.zyaml.nai.repository.DiffCql;
import com.zyaml.nai.util.DtoUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-07 21:40
 */
@Service
@Log4j2
public class DiffService {
    @Autowired
    DiffCql diffCql;

    /**
     * 根据难度名称获取所有题目
     * @param diff 难度名
     * @return
     */
    public List<Problem> getProblemsByDiff(String diff){
        List<Problem> problemsByDiff = diffCql.getProblemsByDiff(diff);
        return problemsByDiff;
    }

    /**
     * 获取diff难度的题目数量
     * @param diff 难度名
     * @return
     */
    public int getCount(String diff){
        log.debug("[getCount] diff -> " + diff);
        int count = diffCql.getCount(diff);
        return count;
    }

    /**
     * 根据难度和题库获取题库列表
     * @param dif 难度名称
     * @param source 题库名称
     * @return 题目列表
     */
    public List<ProblemVO> getDifAndSource(String dif,String source){
        log.debug("=====> [getDifAndSource] run");
        List<Problem> problems = diffCql.getByDifAndSource(dif, source);
        List<ProblemVO> problemVOS = DtoUtil.mapList(problems, ProblemVO.class);
        return problemVOS;
    }

}
