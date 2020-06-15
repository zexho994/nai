package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.comom.TagsCommom;
import com.zyaml.nai.entry.dto.ProblemAndTags;
import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
import com.zyaml.nai.repository.TitleCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 以 title 为查询条件
 * @Author: 994
 * @Date: 2020-03-24 13:17
 */
@Service
public class TitleService implements IServiceCommon{

    @Autowired
    private TitleCql titleCql;

    @Autowired
    private RecommendService recommendService;

    public Resp getProblemAndAllTags(String title){
        StringBuilder sb = new StringBuilder();
        ProblemAndTags problemAndTags = this.getProblemAndTags(title,sb);
        setRecommendMsg(problemAndTags,sb);
        // 3.封装所有对象
        return new Resp(sb.toString(),problemAndTags);
    }

    /**
     * 获取拥有所有关联数据的problem对象
     * @param title
     * @param sb
     * @return
     */
    private ProblemAndTags getProblemAndTags(String title,StringBuilder sb){
        ProblemAndTags problemAndTags = new ProblemAndTags();

        // 1.遍历获取题目的所有标签
        problemAndTags.setProblem(titleCql.findProblem(title));

        if(problemAndTags.getProblem() == null){
            return null;
        }

        sb.append("标题 : ").append(problemAndTags.getProblem().getTitle()).append("\n");

        problemAndTags.setDifficulty(titleCql.findDiff(title));
        if(problemAndTags.getDifficulty()!=null){
            sb.append(TagsCommom.DIF+ " : ").append(problemAndTags.getDifficulty().getDifficultyString()).append("\n");
        }

        problemAndTags.setAlg(titleCql.findAlg(title));

        if(problemAndTags.getAlg()!=null){
            sb.append("算法 : \n");
            ToMsgFormat.tagListToMsg(problemAndTags.getAlg(),sb);
        }

        problemAndTags.setTk(titleCql.findTK(title));
        if(problemAndTags.getTk()!=null){
            sb.append(TagsCommom.TK + " : ").append(problemAndTags.getTk().getTagString()).append("\n");
        }

        problemAndTags.setTime(titleCql.findTime(title));
        if(problemAndTags.getTime()!=null){
            sb.append(TagsCommom.TIME + " : ").append(problemAndTags.getTime().getName()).append("\n");
        }

        problemAndTags.setRegion(titleCql.findReg(title));
        if(problemAndTags.getRegion()!=null){
            sb.append(TagsCommom.REGION + " : ").append(problemAndTags.getRegion().getName()).append("\n");
        }

        List<Tags> oriList = titleCql.findOri(title);
        if(oriList != null && oriList.size() > 0){
            sb.append(TagsCommom.ORI + " : ");
            for(Tags ori : oriList){
                sb.append(ori.getName()+" ");
            }
            problemAndTags.setOri(oriList);
        }

        return problemAndTags;
    }

    /**
     * 在字符串sb后面添加推荐功能的数据
     * @param problemAndTags
     * @param sb
     */
    private void setRecommendMsg(ProblemAndTags problemAndTags,StringBuilder sb){
        //推荐功能
        String title = problemAndTags.getProblem().getTitle();

        sb.append("\n相似例题 :");
        problemAndTags.setSameDifSameAlg(recommendService.sameDifSameAlg(title,sb));
        problemAndTags.setSameAlgHighDif(recommendService.sameALgHighDif(title,problemAndTags.getDifficulty(),sb));
        problemAndTags.setSameDifNotAlg(recommendService.sameDifNotAlg(problemAndTags.getAlg(),problemAndTags.getDifficulty().getDifficulty(),sb));
    }

    public Resp getProblemAndAllTags(List<Problem> titles){
        StringBuilder sb = new StringBuilder();
        int n = 1;
        List<ProblemAndTags> list = new LinkedList<>();

        for(Problem p : titles){
            Resp res = getProblemAndAllTags(p.getTitle());
            sb.append("题目"+n++ +". \n").append(res.getMessage()+"\n");
            list.add((ProblemAndTags)res.getData());
        }
        return new Resp(sb.toString(),list);
    }

    public Resp getProblemAndTags(List<Problem> titles){
        StringBuilder sb = new StringBuilder();
        List<ProblemAndTags> list = new LinkedList<>();

        for(Problem p : titles){
            list.add(getProblemAndTags(p.getTitle(), sb));
            sb.append("\n\n");
        }

        return new Resp(sb.toString(),list);
    }

    /**
     * title 对应的题目的算法
     * @param title
     * @return
     */
    public Resp getALg(String title){

        List<Tags> alg = titleCql.findAlg(title);

        StringBuilder sb = new StringBuilder();

        if(alg == null || alg.size() < 1){
            return new Resp(title+"没有算法标签",null);
        }
        sb.append(title).append("的算法标签有:\n");

        ToMsgFormat.tagListToMsg(alg,sb);

        return new Resp(sb.toString(),alg);

    }

    /**
     * 题目的来源
     * @param title
     * @return
     */
    public Resp getOri(String title){
        List<Tags> oriList = titleCql.findOri(title);
        StringBuilder sb = new StringBuilder();

        if(oriList == null || oriList.size() < 1){
            return new Resp(title+"没有来源标签",null);
        }
        sb.append(title).append("的来源是: ");
        int n = 1;
        for(Tags ori : oriList){
            sb.append(n++ +"." + ori.getName()+" ");
        }
        return new Resp(sb.toString(),oriList);
    }

    /**
     * 题目的时间
     * @param title 题目的名称
     * @return
     */
    public Resp getTime(String title){
        Tags res = titleCql.findTime(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp(title+"没有时间标签",null);
        }

        sb.append(title).append("是").append(res.getName()).append("的题目");

        return new Resp(sb.toString(),res);
    }

    /**
     * 题目的pid
     * @param title
     * @return
     */
    public Resp getPid(String title){
        Problem res = titleCql.findProblem(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp("没找到"+title+"的编号",null);
        }

        sb.append(title).append("的编号是").append(res.getPid());

        return new Resp(sb.toString(),res);
    }

    /**
     * 题目的难度
     * @param title
     * @return
     */
    public Resp getDiff(String title){
        Difficulty res = titleCql.findDiff(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp("没找到"+title+"的难度",null);
        }

        sb.append(title).append("的难度是").append(res.getDifficultyString());

        return new Resp(sb.toString(),res);
    }

    /**
     * 题目的省份
     * @param title
     * @return
     */
    public Resp getReg(String title){
        Tags res = titleCql.findReg(title);
        StringBuilder sb = new StringBuilder();

        if(res == null){
            return new Resp(title+"没有地区标签",null);
        }

        sb.append(title).append("来自于").append(res.getName());

        return new Resp(sb.toString(),res);
    }

    /**
     * 题目的题库
     * @param title
     * @return
     */
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
