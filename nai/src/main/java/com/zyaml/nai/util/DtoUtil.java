package com.zyaml.nai.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 数据对象转换，从一个对象转成另外一个对象。
 * @author liuyihao
 */
public final class DtoUtil {

    private DtoUtil() {
        throw new InstantiationError( "Must not instantiate this class" );
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        try {
            T o = targetClass.newInstance();
            BeanUtils.copyProperties(source, o);
            return o;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <S, T> void mapTo(S source, T dist) {
        BeanUtils.copyProperties(source, dist);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            T target = map(source.get(i), targetClass);
            list.add(target);
        }
        return list;
    }
}