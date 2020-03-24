package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Difficulty;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.entry.node.Types;
import com.zyaml.nai.repository.TitleCql;
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

    public Resp getALg(String title){

        List<Tags> alg = titleCql.findAlg(title);

        StringBuilder sb = new StringBuilder();

        if(alg == null || alg.size() < 1){
            return new Resp(title+"没有算法标签",null);
        }
        sb.append(title).append("的算法标签有");
        sb = algListToMsg(alg,sb);
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


    public StringBuilder algListToMsg(List<Tags> list,StringBuilder sb){
        int i = 0;
        for(Tags t : list){
            sb.append(++i).append(t.getName());
        }
        return sb;
    }

}
