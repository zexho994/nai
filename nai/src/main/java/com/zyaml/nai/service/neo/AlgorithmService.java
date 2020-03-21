package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.repository.AlgorithmCql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-10 11:09
 */
@Service
public class AlgorithmService implements BaseNeo4jService{

    @Autowired
    AlgorithmCql algorithmCql;

    /**
     * 根据算法获取题目列表
     * @param alg 算法名称
     * @return
     */
    public Resp getByAlg(String alg){
        List<Problem> byAlg = algorithmCql.getByAlg(alg);

        if(byAlg == null || byAlg.size() < 1){
            return new Resp(alg+"下暂时没有题目",null);
        }

        return new Resp(byAlg);
    }

    /**
     * 根据算法和时间获取题目
     * @param alg 算法名称: "字符串"
     * @param time 时间年份: "2010"
     * @return
     */
    public Resp getProByAlgAndTime(String alg,String time){

        List<Problem> problems = algorithmCql.getProByAlgAndTime(alg, time, 0, 10);

        if(problems == null || problems.size() < 1){
            return new Resp(time+"年暂时没有"+alg+"类型的题");
        }
        return new Resp(problems);
    }

    /**
     * 根据算法和来源获取题目
     * @param alg 算法名称: "字符串"
     * @param ori 题库来源: "IOI"
     * @return
     */
    public Resp getProByAlgAndOri(String alg,String ori){

        List<Problem> problems = algorithmCql.getProByAlgAndOri(alg, ori, 0, 10);

        if(problems == null || problems.size() < 1){
            return new Resp(ori+"时没有"+alg+"类型的题");
        }

        return new Resp(problems);
    }



}
