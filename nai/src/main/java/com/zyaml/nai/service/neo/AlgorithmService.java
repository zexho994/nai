package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.repository.AlgorithmCql;
import com.zyaml.nai.util.ToMsgFormat;
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
    private AlgorithmCql algorithmCql;

    @Autowired
    private TitleService titleService;

    StringBuilder sb;

    /**
     * 根据算法获取题目列表
     * @param alg 算法名称
     * @return
     */
    public Resp getByAlg(String alg){
        List<Problem> byAlg = algorithmCql.getByAlg(alg);

        sb = new StringBuilder();

        if(byAlg == null || byAlg.size() < 1){
            return new Resp(alg+"下暂时没有题目",null);
        }

        sb.append(alg).append("下的题目有:\n");
        int i = 0;
        for(Problem p : byAlg){
            sb.append(++i+" ").append(p.getTitle()).append("\n");
        }

        return new Resp(sb.toString(),byAlg);
    }

    /**
     * 根据算法和时间获取题目
     * TODO: 时间的判断条件为等于,应该再添加大于和小于的判断
     * @param alg 算法名称: "字符串"
     * @param time 时间年份: "2010"
     * @return
     */
    public Resp getProByAlgAndTime(String alg,String time){

        List<Problem> problems = algorithmCql.getProByAlgAndTime(alg, time, 0, 10);

        Resp resp = titleService.getProblemAndTags(problems);

        if(resp.getMessage() == null || resp.getMessage().equals("")){
            resp.setMessage(time + "年没有" + alg +"的题");
        }

        return resp;
    }

    public Resp getProByAlgAndGT(String alg,String time){
        List<Problem> problems = algorithmCql.getProByAlgAndGT(alg, time, 0, 10);

        if(problems == null || problems.size() < 1){
            return new Resp("晚于"+time+"暂时没有"+alg+"类型的题");
        }

        sb = new StringBuilder();
        sb.append("大于").append(time).append("年的").append(alg).append("的题有:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
    }

    public Resp getProByAlgAngLT(String alg,String time){
        List<Problem> problems = algorithmCql.getProByAlgAndLT(alg,time,0,10);
        if(problems == null || problems.size() < 1){
            return new Resp("早于"+time+"暂时没有"+alg+"类型的题");
        }
        sb = new StringBuilder();
        sb.append("小于").append(time).append("年的").append(alg).append("的题有:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
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
            return new Resp(ori+"暂时没有"+alg+"类型的题");
        }

        sb = new StringBuilder();
        sb.append(ori).append("里").append(alg).append("算法类型的题:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
    }

    /**
     * 根据算法和地区获取题目
     * @param alg 算法名称: "字符串"
     * @param reg 题库来源: "IOI"
     * @return
     */
    public Resp getProByAlgAndReg(String alg,String reg){

        List<Problem> problems = algorithmCql.getProByAlgAndReg(alg, reg, 0, 10);

        if(problems == null || problems.size() < 1){
            return new Resp(reg+"暂时没有"+alg+"类型的题");
        }

        sb = new StringBuilder();
        sb.append(reg).append("省的").append(alg).append("算法类型的题:\n");
        ToMsgFormat.titleList(problems,sb);


        return new Resp(sb.toString(),problems);
    }

    /**
     * 根据算法和题库获取题目
     * @return
     */
    public Resp getProByAlgAndSource(String alg,String source){

        List<Problem> problems = algorithmCql.getProByAlgAndSource(alg, source, 0, 10);

        if(problems == null || problems.size() < 1){
            return new Resp(source+"暂时没有"+alg+"类型的题");
        }

        sb = new StringBuilder();
        sb.append(source).append("里").append(alg).append("算法类型的题:\n");
        ToMsgFormat.titleList(problems,sb);

        return new Resp(sb.toString(),problems);
    }

}
