package com.zyaml.nai.service;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.repository.neo.ProblemCql;
import com.zyaml.nai.util.Mould;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @Author: 994
 * @Date: 2020-03-06 00:03
 */
@Service
public class ProblemService {
    @Autowired
    private ProblemCql problemCql;

    /**
     * 根据pid获取<Problem>问题信息</Problem>
     * @param pid
     * @return
     */
    public Problem getPrombleByPid(String pid){
        //验证pid格式
        if(!matchPid(pid)){
            throw new RestException(ErrorCode.PARAM_INVALID,"pid格式错误");
        }
        //neo大小写严格，转换大小写
        String npid = pid.replace("p", "P");

        Problem problems = problemCql.getProblemByPid(npid);

        if(npid == null){
            throw new RestException(ErrorCode.NULL,"节点不存在");
        }

        return problems;
    }

    /**
     * 验证<PID>题号</PID>格式
     * @param pid
     * @return
     */
    private boolean matchPid(String pid){
        String pattern = "(p|P)\\d{4}";
        if(Pattern.matches(pattern, pid)){
            return true;
        }
        return false;


    }

    public List<Problem> getProblemsByPidAndDiff(String diff){

        List<Problem> problems = problemCql.getProblemsByPidAndDiff(diff);

        return problems;
    }

    /**
     * 根据pid获取难度名
     * @param pid
     * @return
     */
    public String getDiffName(String pid){
        if(matchPid(pid)){
            return problemCql.getDifNameByPid(pid);
        }else{
            throw new RestException(ErrorCode.PARAM_INVALID,"pid格式错误");
        }
    }

}
