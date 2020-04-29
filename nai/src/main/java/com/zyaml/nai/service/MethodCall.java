package com.zyaml.nai.service;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.dto.Words;
import com.zyaml.nai.service.neo.*;
import com.zyaml.nai.util.Mould;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @Author: 994
 * @Date: 2020-03-10 22:25
 */
@Log4j2
@Component
public class MethodCall{

    @Autowired
    private TitleService titleService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private DiffService diffService;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private OriginService originService;

    /**
     * 找到模板对应的方法
     *
     * <步骤>
     * 1. 匹配每个方法上的 mould 注解的 formal信息
     * 2. 找到对应的方法后调用接口
     *
     *
     * @param words
     * @return
     */
    public Resp methodCall(Words words){
        try {
            //获取类名的包名地址
            Class<?> printClass = this.getClass();
            //java反射机制获取所有方法名
            Method[] declaredMethods = printClass.getDeclaredMethods();
            log.info("=====> 问句分析 format -> "+words.getFormat());
            //遍历循环方法并获取对应的注解名称
            for (Method declaredMethod : declaredMethods) {
                // 判断是否方法上存在注解  Mould
                if(declaredMethod.isAnnotationPresent(Mould.class)){
                    // 获取自定义注解对象
                    Mould methodAnno = declaredMethod.getAnnotation(Mould.class);
                    //遍历匹配 format[] 数组
                    for(String format : methodAnno.format()){
                        if(format.equals(words.getFormat())){
                            Resp invoke = (Resp) declaredMethod.invoke(this,words);
                            return invoke;
                        }
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> PID Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Mould(format = "PID+")
    private Resp pid(Words<String,String> words){
        return problemService.getProblemByPid(words.get("PID"));
    }

    @Mould(format = "PID+dif+")
    private Resp pidDif(Words<String,String> words){
        return problemService.getDiffName(words.get("PID"));
    }

    @Mould(format = "PID+name+")
    private Resp pidName(Words<String,String> words){
        return problemService.getTitle(words.get("PID"));
    }

    @Mould(format = "PID+source+")
    private Resp pidSource(Words<String,String> words){
        return problemService.getType(words.get("PID"));
    }

    @Mould(format = "PID+alg+")
    private Resp pidAlg(Words<String,String> words){
        return problemService.getAlg(words.get("PID"));
    }

    @Mould(format = "PID+time+")
    private Resp pidTime(Words<String,String> words){
        return problemService.getTime(words.get("PID"));
    }

    @Mould(format = "PID+region+")
    private Resp pidRegion(Words<String,String> words){
        return problemService.getRegion(words.get("PID"));
    }

    @Mould(format = "PID+ori+")
    private Resp pidOri(Words<String,String> words){
        return problemService.getOri(words.get("PID"));
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> Title Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Mould(format = "TITLE+")
    private Resp title(Words<String,String> words){
        return titleService.getProblemAndAllTags(words.get("TITLE"));
    }

    @Mould(format = "TITLE+alg+")
    private Resp titleAlg(Words<String,String> words){
        return titleService.getALg(words.get("TITLE"));
    }

    @Mould(format = "TITLE+ori+")
    private Resp titleOri(Words<String,String> words){
        return titleService.getOri(words.get("TITLE"));
    }

    @Mould(format = "TITLE+region+")
    private Resp titleReg(Words<String,String> words){
        return titleService.getReg(words.get("TITLE"));
    }

    @Mould(format = "TITLE+dif+")
    private Resp titleDiff(Words<String,String> words){
        return titleService.getDiff(words.get("TITLE"));
    }

    @Mould(format = "TITLE+source+")
    private Resp titleTK(Words<String,String> words){
        return titleService.getTK(words.get("TITLE"));
    }

    @Mould(format = "TITLE+time+")
    private Resp titleTime(Words<String,String> words){
        return titleService.getTime(words.get("TITLE"));
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> DIF Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Mould(format = {"DIF+", "DIF+dif+"})
    private Resp dif(Words<String,String> words){
        return diffService.getProblemsByDiff(words.get("DIF"));
    }

    @Mould(format = {"DIF+num+", "DIF+dif+num+"})
    private Resp difCount(Words<String,String> words){
        return diffService.getCount(words.get("DIF"));
    }

    @Mould(format = {"DIF+TK+", "DIF+dif+TK+", "TK+DIF+", "TK+DIF+dif+", "TK+tk+DIF+", "TK+tk+DIF+dif+"})
    private Resp difSource(Words<String,String> words){
        return diffService.getDifAndSource(words.get("DIF"),words.get("TK"));
    }

    @Mould(format = {"DIF+ALG+", "DIF+dif+ALG+", "ALG+DIF+", "ALG+DIF+dif+"})
    private Resp difAlg(Words<String,String> words){
        return diffService.getProByDifAndAlg(words.get("DIF"),words.get("ALG"));
    }

    @Mould(format = {"DIF+LOC+", "DIF+dif+LOC+"})
    private Resp difReg(Words<String,String> words){
        return diffService.getProByDifAndReg(words.get("DIF"),words.get("LOC"));
    }

    @Mould(format = {"DIF+YEAR+", "DIF+dif+YEAR+", "YEAR+DIF+", "YEAR+DIF+dif+"})
    private Resp difTime(Words<String,String> words){
        return diffService.getProByDifAndTime(words.get("DIF"),words.get("YEAR"));
    }

    @Mould(format = {"DIF+great+YEAR+", "DIF+dif+great+YEAR+", "great+YEAR+DIF+", "great+YEAR+DIF+dif+"})
    private Resp difGT(Words<String,String> words){
        return diffService.getProByDifAndGT(words.get("DIF"),words.get("YEAR"));
    }

    @Mould(format = {"DIF+less+YEAR+", "DIF+dif+less+YEAR+", "less+YEAR+DIF+", "less+YEAR+DIF+dif+"})
    private Resp difLT(Words<String,String> words){
        return diffService.getProByDifAndLT(words.get("DIF"),words.get("YEAR"));
    }

    @Mould(format = {"DIF+ORI+", "DIF+dif+ORI+", "ORI+DIF+", "ORI+DIF+dif+"})
    private Resp difOri(Words<String,String> words){
        return diffService.getProByDifAndOri(words.get("DIF"),words.get("ORI"));
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> ALG Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Mould(format = "ALG+")
    private Resp alg(Words<String,String> words){
        return algorithmService.getByAlg(words.get("ALG"));
    }

    @Mould(format = {"ALG+YEAR+", "YEAR+ALG+" })
    private Resp algTime(Words<String,String> words){
        return algorithmService.getProByAlgAndTime(words.get("ALG"), words.get("YEAR"));
    }

    @Mould(format = {"ALG+great+YEAR+","ALG+alg+great+YEAR+","great+YEAR+ALG+","great+YEAR+ALG+alg+"})
    private Resp algGT(Words<String,String> words){
        return algorithmService.getProByAlgAndGT(words.get("ALG"),words.get("YEAR"));
    }

    @Mould(format = {"ALG+less+YEAR+","ALG+alg+less+YEAR+","less+YEAR+ALG+","less+YEAR+ALG+alg+"})
    private Resp algLT(Words<String,String> words){
        return algorithmService.getProByAlgAngLT(words.get("ALG"),words.get("YEAR"));
    }

    @Mould(format = {"ALG+ORI+", "ORI+ALG+"})
    private Resp algOri(Words<String,String> words){
        return algorithmService.getProByAlgAndOri(words.get("ALG"),words.get("ORI"));
    }

    @Mould(format = {"ALG+LOC+", "LOC+ALG+"})
    private Resp algReg(Words<String,String> words){
        return algorithmService.getProByAlgAndReg(words.get("ALG"),words.get("LOC"));
    }

    @Mould(format = {"ALG+TK+", "TK+ALG+"})
    private Resp algSource(Words<String,String> words){
        return algorithmService.getProByAlgAndSource(words.get("ALG"),words.get("TK"));
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> Types Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Mould(format = {"TK+","TK+tk+"})
    private Resp type(Words<String,String> words){
        return typeService.getByType(words.get("TK"));
    }

    @Mould(format = {"TK+ORI+","TK+tk+ORI+","ORI+TK+tk","ORI+TK+"})
    private Resp typeOri(Words<String,String> words){
        return typeService.getByTypeAndOri(words.get("TK"),words.get("ORI"));
    }

    @Mould(format = {"TK+LOC+","TK+tk+LOC+","LOC+TK+tk+","LOC+TK+"})
    private Resp typeLoc(Words<String,String> words){
        return typeService.getByTypeAndLoc(words.get("TK"),words.get("LOC"));
    }

    @Mould(format = {"TK+YEAR+","TK+tk+YEAR+","YEAR+TK+","YEAR+TK+tk+"})
    private Resp typeTime(Words<String,String> words){
        return typeService.getByTypeAndTime(words.get("TK"),words.get("YEAR"));
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> Region Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Mould(format = "LOC+")
    private Resp reg(Words<String,String> words){
        return regionService.getByReg(words.get("LOC"));
    }

    @Mould(format = {"LOC+YEAR+","YEAR+LOC+"})
    private Resp regTime(Words<String,String> words){
        return regionService.getByRegAndTime(words.get("LOC"),words.get("YEAR"));
    }

//    @Mould(format = {"LOC+ORI+","ORI+LOC+"})
//    private Resp locOri(Words<String,String> words){
//
//    }

    //  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> YEAR Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Mould(format = "YEAR+")
    private Resp time(Words<String,String> words){
        return timeService.getByTime(words.get("YEAR"));
    }

    @Mould(format = "ORI+")
    private Resp ori(Words<String,String> words){
        return originService.getOriginByName(words.get("ORI"));
    }

}
