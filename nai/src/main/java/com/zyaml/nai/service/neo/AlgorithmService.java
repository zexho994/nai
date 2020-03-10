package com.zyaml.nai.service.neo;

import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.vo.ProblemVO;
import com.zyaml.nai.repository.AlgorithmCql;
import com.zyaml.nai.util.DtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-10 11:09
 */
@Service
public class AlgorithmService {

    @Autowired
    AlgorithmCql algorithmCql;

    /**
     * 根据算法获取题目列表
     * @param alg
     * @return
     */
    public List<ProblemVO> getByAlg(String alg){
        List<Problem> byAlg = algorithmCql.getByAlg(alg);
        List<ProblemVO> problemVOS = DtoUtil.mapList(byAlg, ProblemVO.class);
        return problemVOS;
    }



}
