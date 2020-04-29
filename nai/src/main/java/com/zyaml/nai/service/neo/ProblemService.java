package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.Exception.RestException;
import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
import com.zyaml.nai.repository.ProblemCql;
import com.zyaml.nai.util.ToMsgFormat;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: 994
 * @Date: 2020-03-06 00:03
 */
@Service
@Log4j2
public class ProblemService implements BaseNeo4jService{
    @Autowired
    private ProblemCql problemCql;

    @Autowired
    private TitleService titleService;

    StringBuilder sb;

    /**
     * 根据pid获取<Problem>问题信息</Problem>
     * @param pid
     * @return
     */
    public Resp getProblemByPid(String pid){
        String npid = matchPid(pid);

        Problem problems = problemCql.getProblemByPid(npid);

        if(problems == null){
            return new Resp(ErrorCode.NOT_FOUND,null);
        }

        Resp res = titleService.getProblemAndAllTags(problems.getTitle());

        return res;
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
    @Deprecated
    public Resp getProblemsByPidAndDiff(String diff){
        List<Problem> problems = problemCql.getProblemsByPidAndDiff(diff);


        return new Resp(problems);
    }

    /**
     * 根据pid获取难度名
     * @param pid
     * @return
     */
    public Resp getDiffName(String pid){
        log.debug("=====>[知识问答 getDiffName]根据pid:"+pid+",获取题目难度");
        String s = matchPid(pid);
        Difficulty dif = problemCql.getDifByPid(s);
        sb = new StringBuilder();
        sb.append(pid).append("的难度是").append(dif.getDifficultyString());
        return new Resp(sb.toString(),dif);
    }

    /**
     * 获取题目的名称
     * @param pid
     * @return
     */
    public Resp getTitle(String pid){
        log.debug("=====>[知识问答 getTitle]根据pid:"+pid+",获取题目名称");

        String s = matchPid(pid);

        Problem title = problemCql.getTitle(s);

        sb = new StringBuilder();
        sb.append(pid).append("的题目是").append(title.getTitle());

        return new Resp(sb.toString(),title);
    }


    /**
     * 获取题库
     * @param pid
     * @return
     */
    public Resp getType(String pid){
        log.debug("=====>[知识问答 getTitle]根据pid:"+pid+",获取题库信息");

        String npid = matchPid(pid);

        Types type = problemCql.getType(npid);

        if(type==null){
            return new Resp(ErrorCode.NOT_FOUND,null);
        }

        sb = new StringBuilder();
        sb.append(pid).append("的题库是").append(type.getTagString());

        return new Resp(sb.toString(),type);

    }

    /**
     * 根据pid获取对应题目算法标签
     * @param pid
     * @return
     */
    public Resp getAlg(String pid){
        String p = matchPid(pid);

        List<Tags> alg = problemCql.getAlgByPid(p);

        if(alg==null || alg.size() <1){
            return new Resp(200,pid+"没有算法标签");
        }

        List<String> list = new ArrayList<>();

        for(Tags a: alg){
            list.add(a.getName());
        }

        sb = new StringBuilder();
        sb.append(pid).append("的算法标签有:\n");

        ToMsgFormat.algListToMsg(alg,sb);

        return new Resp(sb.toString(),alg);

    }

    /**
     * 根据pid获取对应的出题地区
     * <Regin>地区名</Regin>
     * @param pid
     * @return
     */
    public Resp getRegion(String pid){
        String p = matchPid(pid);

        Tags regin = problemCql.getRegin(p);

        if(regin == null){
            return new Resp(200,pid+"没有地区标签");
        }

        sb = new StringBuilder();
        sb.append(pid).append("来自").append(regin.getName());

        return new Resp(sb.toString(),regin);

    }

    /**
     * 获取对应pid题目的出题时间
     * @param pid
     * @return
     */
    public Resp getTime(String pid){
        String s = matchPid(pid);

        Tags time = problemCql.getTime(pid);

        if(time == null){
            return new Resp(200,pid+"没有时间标签");
        }

        sb = new StringBuilder();
        sb.append(pid).append("是").append(time.getName()).append("的题");

        return new Resp(sb.toString(),time);

    }

    /**
     * 根据pid,找到题目的来源
     * @param pid
     * @return
     */
    public Resp getOri(String pid){
        String p = matchPid(pid);
        List<Tags> oriList = problemCql.getOri(p);
        if (oriList == null || oriList.size() < 1){
            return new Resp("未找到数据",null);
        }
        sb = new StringBuilder();
        sb.append(pid).append("的来源: ");
        for(Tags ori : oriList){
            sb.append(ori.getName()+" ");
        }
        return new Resp(sb.toString(),oriList);
    }
}
