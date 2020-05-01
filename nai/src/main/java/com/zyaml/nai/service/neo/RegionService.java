package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.repository.RegionCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-21 21:15
 */
@Service
public class RegionService implements IServiceCommon {

    @Autowired
    RegionCql regionCql;

    @Autowired
    TitleService titleService;

    public Resp getRegTag(){
        List<Tags> regTag = regionCql.getRegTag();
        StringBuilder sb = new StringBuilder();
        ToMsgFormat.tagListToMsg(regTag,sb);
        return new Resp(sb.toString(),regTag);
    }

    /**
     * 获取 reg 下的题目
     * @param reg 省份
     * @return
     */
    public Resp getByReg(String reg){
        List<Problem> problems = regionCql.getByReg(reg,DEFAULT_PAGE,DEFAULT_SIZE);

        if(problems == null || problems.size()<1){
            return new Resp(reg+"暂时没有收录题目");
        }

        Resp resp = titleService.getProblemAndTags(problems);
        return resp;

    }

    /**
     * @param reg 省份
     * @param time 时间
     * @return
     */
    public Resp getByRegAndTime(String reg,String time){
        List<Problem> problems = regionCql.getByRegAndTime(reg, time, DEFAULT_PAGE, DEFAULT_SIZE);

        if(problems == null || problems.size()<1){
            return new Resp(reg+"暂时没有收录"+time+"的题目");
        }

        Resp resp = titleService.getProblemAndTags(problems);
        return resp;
    }

}
