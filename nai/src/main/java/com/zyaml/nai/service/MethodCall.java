package com.zyaml.nai.service;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.dto.Words;
import com.zyaml.nai.service.neo.AlgorithmService;
import com.zyaml.nai.service.neo.DiffService;
import com.zyaml.nai.service.neo.ProblemService;
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
    private ProblemService problemService;

    @Autowired
    private DiffService diffService;

    @Autowired
    private AlgorithmService algorithmService;

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
            //遍历循环方法并获取对应的注解名称
            for (Method declaredMethod : declaredMethods) {
                // 判断是否方法上存在注解  Mould
                if(declaredMethod.isAnnotationPresent(Mould.class)){
                    // 获取自定义注解对象
                    Mould methodAnno = declaredMethod.getAnnotation(Mould.class);
                    //遍历匹配 format[] 数组
                    for(String format : methodAnno.format()){
                        if(format.equals(words.getFormat())){
                            log.info("=====> methodCall.format:"+methodAnno.format());
                            Resp invoke = (Resp) declaredMethod.invoke(this,words);
                            return invoke;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> PID Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<//

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

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> DIF Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< //

    @Mould(format = {
            "DIF+",
            "DIF+dif+"})
    private Resp dif(Words<String,String> words){
        return diffService.getProblemsByDiff(words.get("DIF"));
    }

    @Mould(format = {
            "DIF+num+",
            "DIF+dif+num+"})
    private Resp difCount(Words<String,String> words){
        return diffService.getCount(words.get("DIF"));
    }

    @Mould(format = {
            "DIF+ORI+",
            "DIF+dif+ORI+"})
    private Resp difSource(Words<String,String> words){
        return diffService.getDifAndSource(words.get("DIF"),words.get("source"));
    }

    @Mould(format = {
            "DIF+ALG+",
            "DIF+dif+ALG+",
            "ALG+DIF+",
            "ALG+DIF+dif+"})
    private Resp difAlg(Words<String,String> words){
        return diffService.getProByDifAndAlg(words.get("DIF"),words.get("ALG"));
    }

    @Mould(format = {
            "DIF+LOC+",
            "DIF+dif+LOC+"})
    private Resp difReg(Words<String,String> words){
        return diffService.getProByDifAndReg(words.get("DIF"),words.get("LOC"));
    }

    @Mould(format = {
            "DIF+TIME+",
            "DIF+dif+TIME+"})
    private Resp difTime(Words<String,String> words){
        return diffService.getProByDifAndTime(words.get("DIF"),words.get("TIME"));
    }

    @Mould(format = {
            "DIF+ORI+",
            "DIF+dif+ORI+"})
    private Resp difOri(Words<String,String> words){
        return diffService.getProByDifAndOri(words.get("DIF"),words.get("ORI"));
    }

//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>> ALG Start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< //

    @Mould(format = "ALG+")
    private Resp alg(Words<String,String> words){
        return algorithmService.getByAlg(words.get("ALG"));
    }

    @Mould(format = {
            "ALG+TIME+",
            "TIME+ALG+"})
    private Resp algTime(Words<String,String> words){
        return algorithmService.getProByAlgAndTime(words.get("ALG"), words.get("TIME"));
    }

    @Mould(format = {
            "ALG+ORI+",
            "ORI+ALG+"})
    private Resp algOri(Words<String,String> words){
        return algorithmService.getProByAlgAndOri(words.get("ALG"),words.get("ORI"));
    }




}
