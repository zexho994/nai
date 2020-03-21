package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.repository.RegionCql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-21 21:15
 */
@Service
public class RegionService implements BaseNeo4jService {

    @Autowired
    RegionCql regionCql;

    public Resp getByOri(String reg){
        List<Problem> problems = regionCql.getByReg(reg,0,10);

        if(problems == null || problems.size()<1){
            return new Resp(reg+"暂时没有收录题目");
        }

        return new Resp(problems);

    }

    public Resp getByOriAndTime(String reg,String time){
        List<Problem> problems = regionCql.getByRegAndTime(reg, time, 0, 10);

        if(problems == null || problems.size()<1){
            return new Resp(reg+"暂时没有收录"+time+"的题目");
        }

        return new Resp(problems);

    }

}
