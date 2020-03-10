package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Types;
import com.zyaml.nai.repository.ProblemCql;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: 994
 * @Date: 2020-03-06 00:03
 */
@Service
@Log4j2
public class ProblemService {
    @Autowired
    private ProblemCql problemCql;

    /**
     * 根据pid获取<Problem>问题信息</Problem>
     * @param pid
     * @return
     */
    public Problem getProblemByPid(String pid){
        log.debug("=====>[知识问答 getPrombleByPid]根据pid:"+pid+",获取题目信息");
        String npid = matchPid(pid);
        Problem problems = problemCql.getProblemByPid(npid);
        if(problems == null){
            throw new RestException(ErrorCode.NULL,"节点不存在");
        }
        return problems;
    }

    /**
     * 验证<PID>题号</PID>格式
     * @param pid
     * @return
     */
    private String matchPid(String pid){
        String pattern = "(p|P)\\d{4}";
        if(Pattern.matches(pattern, pid)){
            return pid.replace("p","P");
        }
        throw new RestException(ErrorCode.PARAM_INVALID,"pid 格式有误");
    }

    /**
     * 根据难度获取搜索题
     * @param diff
     * @return
     */
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
        log.debug("=====>[知识问答 getDiffName]根据pid:"+pid+",获取题目难度");
        String s = matchPid(pid);
        Difficulty dif = problemCql.getDifByPid(s);
        if(dif==null){
            throw new RestException(ErrorCode.NULL,"节点不存在");
        }
        return dif.getDifficultyString();
    }

    /**
     * 获取题目的名称
     * @param pid
     * @return
     */
    public String getTitle(String pid){
        log.debug("=====>[知识问答 getTitle]根据pid:"+pid+",获取题目名称");
        String s = matchPid(pid);
        Problem title = problemCql.getTitle(s);
        if(title==null){
            throw new RestException(ErrorCode.NULL,"节点不存在");
        }
        return title.getTitle();
    }

    /**
     * 获取题库
     * @param pid
     * @return
     */
    public String getType(String pid){
        log.debug("=====>[知识问答 getTitle]根据pid:"+pid+",获取题库信息");
        String npid = matchPid(pid);
        Types type = problemCql.getType(npid);
        if(type==null){
            throw new RestException(ErrorCode.NULL,"节点不存在");
        }
        return type.getTagString();
    }
}
