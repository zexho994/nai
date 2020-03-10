package com.zyaml.nai.util;

import com.zyaml.nai.Exception.ErrorCode;
import com.zyaml.nai.Exception.RestException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 操作节点与词典
 *
 * 将节点的数据写入到自定义词典中
 * @Author: 994
 * @Date: 2020-03-10 19:43
 */
public class toDictionaryIO<V> {
    //1.题目名称    2.算法名字

    private List<V> list = new ArrayList<>();


    /**
     * 算法名文件目录
     */
    String algorithm_Path = "/algorithm.txt";

    /**
     * 题目名文件目录
     */
    String problemName_Path = "/problem.txt";


    /**
     * 读取文件数据
     * @param path 文件的路径
     */
    public void in(String path){
    }

    /**
     * 文件流
     *
     * <作用> 写入到文件中 </作用>
     *
     * 如果该流在打开文件进行输出前，目标文件不存在，那么该流会创建该文件。
     */
    public void outFile(Collection<String> f,String path) throws IOException {
        Iterator<String> iterator = f.iterator();
        File file = new File(path);

        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);

        while (iterator.hasNext()){
            bufferWritter.write(iterator.next());
            bufferWritter.newLine();
        }
        bufferWritter.close();
    }



}
