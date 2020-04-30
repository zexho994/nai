package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.repository.TypesCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-21 20:33
 */
@Service
public class TypeService implements IServiceCommon{

    @Autowired
    TypesCql typesCql;
    @Autowired
    TitleService titleService;

    public Resp getByType(String type){
        List<Problem> problems = typesCql.getByType(type, DEFAULT_PAGE, DEFAULT_SIZE);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录题目");
        }

        Resp resp = titleService.getProblemAndTags(problems);
        return resp;
    }

    public Resp getByTypeAndOri(String type,String ori){
        List<Problem> problems = typesCql.getByTypeAndOri(type,ori, DEFAULT_PAGE, DEFAULT_SIZE);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录"+ori+"的题目");
        }

        Resp resp = titleService.getProblemAndTags(problems);
        return resp;
    }

    public Resp getByTypeAndLoc(String type,String loc){
        List<Problem> problems = typesCql.getByTypeAndLoc(type,loc, DEFAULT_PAGE, DEFAULT_SIZE);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录"+loc+"的题目");
        }

        Resp resp = titleService.getProblemAndTags(problems);
        return resp;

    }

    public Resp getByTypeAndTime(String type,String time){
        List<Problem> problems = typesCql.getByTypeAndTime(type, time,DEFAULT_PAGE, DEFAULT_SIZE);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录"+time+"的题目");
        }

        Resp resp = titleService.getProblemAndTags(problems);
        return resp;
    }


}
