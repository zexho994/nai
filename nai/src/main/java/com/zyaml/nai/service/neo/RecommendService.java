package com.zyaml.nai.service.neo;

import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.repository.RecommendCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 关联数据查询
 * @Author: 994
 * @Date: 2020-04-29 16:08
 */
@Service
public class RecommendService {

    @Autowired
    private RecommendCql recommendCql;



    /**
     * 相同的难度相同的算法
     * @param title
     * @param sb
     * @return
     */
    public List<Problem> sameDifSameAlg(String title,StringBuilder sb){
        List<Problem> problemList = recommendCql.sameDifSameAlg(title);
        if(problemList == null){
            return null;
        }
        sb.append("\n同难度下相同算法的题 :\n");
        ToMsgFormat.titleList(problemList,sb);
        return problemList;
    }

    /**
     * 相同的算法,更高的难度
     * @param title
     * @param difficulty 当前难度,获取的结果的难度 = difficulty + 1
     * @param sb
     * @return
     */
    public List<Problem> sameALgHighDif(String title, Difficulty difficulty, StringBuilder sb){
        if(difficulty.getDifficulty() == 7){
            return null;
        }
        List<Problem> problemList = recommendCql.sameALgHighDif(title,difficulty.getDifficulty()+1);
        if(problemList == null){
            return null;
        }
        sb.append("\n更高难度的相同算法的题 :\n");
        ToMsgFormat.titleList(problemList,sb);
        return problemList;
    }

    /**
     * 相同的难度,不同的算法
     * @param alg
     * @param dif
     * @param sb
     * @return
     */
    public List<Problem> sameDifNotAlg(List<Tags> alg, int dif , StringBuilder sb){
        ArrayList<String> a = new ArrayList<>(alg.size());
        for(Tags t : alg){
            a.add(t.getName());
        }
        List<Problem> problemList = recommendCql.samDifNotAlg(a,dif);
        if(problemList == null){
            return null;
        }
        sb.append("\n同难度下其他算法的题 :\n");
        ToMsgFormat.titleList(problemList,sb);
        return problemList;
    }
}
