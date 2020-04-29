package com.zyaml.nai.service.neo;

import com.zyaml.nai.entry.dto.ProblemAndTags;
import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.repository.RecommendCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关联数据查询
 * @Author: 994
 * @Date: 2020-04-29 16:08
 */
@Service
public class RecommendService {

    @Autowired
    RecommendCql recommendCql;

    public List<Problem> sameDifSameAlg(String title,StringBuilder sb){
        List<Problem> problemList = recommendCql.sameDifSameAlg(title);
        if(problemList == null){
            return null;
        }
        sb.append("\n同难度下相同算法的题 :\n");
        ToMsgFormat.titleList(problemList,sb);
        return problemList;
    }

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

    public List<Problem> samDifNotAlg(String title,StringBuilder sb){
        List<Problem> problemList = recommendCql.samDifNotAlg(title);
        if(problemList == null){
            return null;
        }
        sb.append("\n同难度下其他算法的题 :\n");
        ToMsgFormat.titleList(problemList,sb);
        return problemList;
    }
}
