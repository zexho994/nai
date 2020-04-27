package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.comom.TagsCommom;
import com.zyaml.nai.entry.dto.ProblemAndTags;
import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
import com.zyaml.nai.repository.RecommendCql;
import com.zyaml.nai.repository.TitleCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-24 13:17
 */
@Service
public class TitleService {

    @Autowired
    private TitleCql titleCql;

    @Autowired
    private RecommendCql recommendCql;

    public Resp getProblemAndAllTags(String title){
        ProblemAndTags problemAndTags = new ProblemAndTags();

        // 1.遍历获取题目的所有标签
        problemAndTags.setProblem(titleCql.findProblem(title));

        if(problemAndTags.getProblem() == null){
            return new Resp(ErrorCode.NULL,"题目不存在");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("标题 : ").append(problemAndTags.getProblem().getTitle()).append("\n");

        problemAndTags.setDifficulty(titleCql.findDiff(title));
        if(problemAndTags.getDifficulty()!=null){
            sb.append(TagsCommom.DIF+ " : ").append(problemAndTags.getDifficulty().getDifficultyString()).append("\n");
        }

        problemAndTags.setAlg(titleCql.findAlg(title));

        if(problemAndTags.getAlg()!=null){
            sb.append("算法 : ");
            ToMsgFormat.algListToMsg(problemAndTags.getAlg(),sb);
        }

        problemAndTags.setTk(titleCql.findTK(title));
        if(problemAndTags.getTk()!=null){
            sb.append(TagsCommom.TK + " : ").append(problemAndTags.getTk().getTagString()).append("\n");
        }

        problemAndTags.setOri(titleCql.findOri(title));
        if(problemAndTags.getOri()!=null){
            sb.append(TagsCommom.ORI + " : ").append(problemAndTags.getOri().getName()).append("\n");
        }


        problemAndTags.setTime(titleCql.findTime(title));
        if(problemAndTags.getTime()!=null){
            sb.append(TagsCommom.TIME + " : ").append(problemAndTags.getTime().getName()).append("\n");
        }

        problemAndTags.setRegion(titleCql.findReg(title));
        if(problemAndTags.getRegion()!=null){
            sb.append(TagsCommom.REGION + " : ").append(problemAndTags.getRegion().getName()).append("\n");
        }

        //推荐功能
        sb.append("\n相似例题 : ");

        sb.append("\n同难度下相同算法的题 :\n");
        List<Problem> problemList = recommendCql.sameDifSameAlg(title);
        ToMsgFormat.titleList(problemList,sb);

        sb.append("\n更高难度的相同算法的题 :\n");
        List<Problem> problemList1 = recommendCql.sameALgHighDif(title);
        ToMsgFormat.titleList(problemList1,sb);

        sb.append("\n同难度下其他算法的题 :\n");
        List<Problem> problemList2 = recommendCql.samDifNotAlg(title);
        ToMsgFormat.titleList(problemList2,sb);

        // 3.封装所有对象
        return new Resp(sb.toString(),problemAndTags);
    }

    public Resp getALg(String title){

        List<Tags> alg = titleCql.findAlg(title);

        StringBuilder sb = new StringBuilder();

        if(alg == null || alg.size() < 1){
            return new Resp(title+"没有算法标签",null);
        }
        sb.append(title).append("的算法标签有:\n");

        ToMsgFormat.algListToMsg(alg,sb);

        return new Resp(sb.toString(),alg);

    }

    public Resp getOri(String title){
        Tags ori = titleCql.findOri(title);
        StringBuilder sb = new StringBuilder();

        if(ori == null){
            return new Resp(title+"没有来源标签",null);
        }

        sb.append(title).append("的来源是").append(ori.getName());

        return new Resp(sb.toString(),ori);
    }

    public Resp getTime(String title){
        Tags res = titleCql.findTime(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp(title+"没有时间标签",null);
        }

        sb.append(title).append("是").append(res.getName()).append("的题目");

        return new Resp(sb.toString(),res);
    }

    public Resp getPid(String title){
        Problem res = titleCql.findProblem(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp("没找到"+title+"的编号",null);
        }

        sb.append(title).append("的编号是").append(res.getPid());

        return new Resp(sb.toString(),res);
    }

    public Resp getDiff(String title){
        Difficulty res = titleCql.findDiff(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp("没找到"+title+"的难度",null);
        }

        sb.append(title).append("的难度是").append(res.getDifficultyString());

        return new Resp(sb.toString(),res);
    }

    public Resp getReg(String title){
        Tags res = titleCql.findReg(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp(title+"没有地区标签",null);
        }

        sb.append(title).append("来自于").append(res.getName());

        return new Resp(sb.toString(),res);
    }

    public Resp getTK(String title){
        Types res = titleCql.findTK(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            sb.append("无法找到").append(title).append("的题库");
            return new Resp(sb.toString(),null);
        }

        sb.append(title).append("的题库是").append(res.getTagString());

        return new Resp(sb.toString(),res);
    }
}
