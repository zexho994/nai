package com.zyaml.nai.service.neo;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.node.Problem;
import com.zyaml.nai.entry.node.Tags;
import com.zyaml.nai.repository.TimeCql;
import com.zyaml.nai.util.ToMsgFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-03-29 15:02
 */
@Service
public class TimeService implements IServiceCommon{

    @Autowired
    TimeCql timeCql;
    @Autowired
    TitleService titleService;

    /**
     * 获取时间的标签数据
     * @return
     */
    public Resp getTimeTag(){
        List<Tags> timeTag = timeCql.getTimeTag();
        StringBuilder sb = new StringBuilder();
        sb.append("年份 :");
        ToMsgFormat.tagListToMsg(timeTag,sb);
        return new Resp(sb.toString(),timeTag);
    }

    /**
     * 获取 time 年的题目
     * @param time 年份
     * @return
     */
    public Resp getByTime(String time){
        List<Problem> problems = timeCql.findByTime(time,DEFAULT_PAGE,DEFAULT_SIZE);
        StringBuilder sb = new StringBuilder();

        if(problems == null || problems.size() < 1){
            sb.append(time).append("暂时没有题目");
        }

        Resp resp = titleService.getProblemAndTags(problems);
        return resp;

    }



}
