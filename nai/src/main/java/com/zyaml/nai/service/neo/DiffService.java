package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.from.DifFrom;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.vo.ProblemVO;
import com.zyaml.nai.repository.DiffCql;
import com.zyaml.nai.util.DtoUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-07 21:40
 */
@Service
@Log4j2
public class DiffService implements BaseNeo4jService{
    @Autowired
    DiffCql diffCql;

    /**
     * 根据难度名称获取所有题目
     * @param diff 难度名
     * @return
     */
    public Resp getProblemsByDiff(String diff){
        List<Problem> problemsByDiff = diffCql.getProByDiff(diff);

        if(problemsByDiff == null || problemsByDiff.size() < 1){
            return new Resp(diff+"下暂时没有题目",null);
        }

        return new Resp(problemsByDiff);
    }

    /**
     * 获取diff难度的题目数量
     * @param diff 难度名
     * @return
     */
    public Resp getCount(String diff){
        log.debug("[getCount] diff -> " + diff);

        int count = diffCql.getCount(diff);

        return new Resp(count);
    }

    /**
     * 根据难度和题库获取题库列表
     * @param dif 难度名称
     * @param source 题库名称
     * @return 题目列表
     */
    public Resp getDifAndSource(String dif,String source){
        log.debug("=====> [getDifAndSource] run");

        List<Problem> problems = diffCql.getProByDifAndSource(dif, source);

        List<ProblemVO> problemVOS = DtoUtil.mapList(problems, ProblemVO.class);

        return new Resp(problemVOS);
    }

    /**
     * 获取满足<难度>dif</难度>和<算法>alg</算法>的题目
     * @param from
     * @return
     */
    public Resp getProByDifAndAlg(DifFrom from){
        String dif = from.getDif();
        String alg = from.getAlg();
        int limit;
        int page;

        if(from.getSize() == null){
            limit = 10;
        }else{
            limit = from.getSize();
        }

        if(from.getPage() == null){
            page = 0;
        }else{
            page = from.getPage();
        }

        List<Problem> proByDifAndAlg = diffCql.getProByDifAndAlg(dif, alg,page,limit);

        if(proByDifAndAlg == null || proByDifAndAlg.size() < 1){
            return new Resp("不存在同时满足"+from.getDif()+"和"+from.getAlg()+"的题目",null);
        }

        return new Resp(proByDifAndAlg);
    }

    /**
     * 获取满足<难度>dif</难度>和<算法>alg</算法>的题目
     * @param dif 难度名
     * @param alg 算法名
     * @return
     */
    public Resp getProByDifAndAlg(String dif,String alg){
        List<Problem> proByDifAndAlg = diffCql.getProByDifAndAlg(dif, alg,0,15);

        if(proByDifAndAlg == null || proByDifAndAlg.size() < 1){
            return new Resp("没有同时满足"+dif+"和"+alg+"的题目",null);
        }

        return new Resp(proByDifAndAlg);
    }

    /**
     * 根据难度和省份获取题目
     *
     * @param dif 难度名称
     * @param reg 省份名称
     * @return
     *
     * 默认返回前10个结果
     */
    public Resp getProByDifAndReg(String dif,String reg){

        List<Problem> problems = diffCql.getProByDifAndReg(dif, reg, 0, 10);

        if(problems == null || problems.size()<1 ){
            return new Resp(reg+"目前没有"+dif+"的题",null);
        }

        return new Resp(problems);
    }

    /**
     * 根据难度和出题时间获取题目
     * @param dif
     * @param time
     * @return
     */
    public Resp getProByDifAndTime(String dif,String time){
        List<Problem> problems = diffCql.getProByDifAndTime(dif, time, 0, 10);

        if(problems == null || problems.size() < 1){
            return new Resp(time+"目前没有"+dif+"的题",null);
        }

        return new Resp(problems);
    }


    public Resp getProByDifAndOri(String dif,String ori){
        List<Problem> problems = diffCql.getProByDifAndOri(dif,ori,0,10);

        if(problems == null || problems.size() < 1){
            return new Resp(ori+"目前没有"+dif+"的题",null);
        }

        return new Resp(problems);
    }

}
