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
public class TypeService implements BaseNeo4jService{

    @Autowired
    TypesCql typesCql;

    StringBuilder sb;

    public Resp getByType(String type){
        List<Problem> problems = typesCql.getByType(type, 0, 10);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录题目");
        }

        sb = new StringBuilder();
        sb.append(type).append("下的题:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
    }

    public Resp getByTypeAndOri(String type,String ori){
        List<Problem> problems = typesCql.getByTypeAndOri(type,ori, 0, 10);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录"+ori+"的题目");
        }

        sb = new StringBuilder();
        sb.append(type).append("题库里来源是").append(ori).append("的题有:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
    }

    public Resp getByTypeAndLoc(String type,String loc){
        List<Problem> problems = typesCql.getByTypeAndLoc(type,loc, 0, 10);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录"+loc+"的题目");
        }

        sb = new StringBuilder();
        sb.append(loc).append("里").append(type).append("的题:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);

    }

    public Resp getByTypeAndTime(String type,String time){
        List<Problem> problems = typesCql.getByTypeAndTime(type, time,0, 10);

        if(problems == null || problems.size()<1){
            return new Resp(type+"暂时没有收录"+time+"的题目");
        }

        sb = new StringBuilder();
        sb.append(time).append("年").append(type).append("的题有:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
    }


}
