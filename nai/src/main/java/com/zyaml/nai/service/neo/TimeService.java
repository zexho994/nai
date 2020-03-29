package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.repository.ProblemCql;
import com.zyaml.nai.repository.TimeCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-29 15:02
 */
@Service
public class TimeService {

    @Autowired
    TimeCql timeCql;

    public Resp getByTime(String time){
        List<Problem> problems = timeCql.findByTime(time);
        StringBuilder sb = new StringBuilder();

        if(problems == null || problems.size() < 1){
            sb.append(time).append("暂时没有题目");
        }

        sb.append(time).append("的题目：\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
    }

}
