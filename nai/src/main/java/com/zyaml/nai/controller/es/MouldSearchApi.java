package com.zyaml.nai.controller.es;

import com.zyaml.nai.Exception.Resp;
import com.zyaml.nai.entry.from.MouldFrom;
import com.zyaml.nai.service.es.MouldDocService;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: 994
 * @Date: 2020-03-08 16:24
 */
@RequestMapping("/api/es/mould")
@Log4j2
@RestController
public class MouldSearchApi {
    @Autowired
    MouldDocService mouldDocService;

    /**
     * 添加mould
     * @param from
     * @return
     * @throws IOException
     */
    @PostMapping
    public Resp add(@RequestBody MouldFrom from) throws IOException {
        RestStatus status = mouldDocService.add(from);
        return new Resp(status);
    }

    /**
     * 获取mould
     * @param page 起始页
     * @param size 页大小
     * @return
     */
    @GetMapping("/{page}/{size}")
    public Resp search(@PathVariable int page,@PathVariable int size) throws IOException {
        Map search = mouldDocService.search(page, size);

        return new Resp(search.toString());
    }
}
