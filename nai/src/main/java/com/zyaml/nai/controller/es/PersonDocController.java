package com.zyaml.nai.controller.es;

import com.zyaml.nai.entry.doc.PersonDoc;
import com.zyaml.nai.service.es.PersonDocService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 994
 * @Date: 2020-02-28 18:37
 */
@RestController
@RequestMapping("/api/es/person")
@Log4j2
public class PersonDocController {
    @Autowired
    PersonDocService personDocService;

    @GetMapping("/{name}/one")
    public PersonDoc findByName(@PathVariable String name){
        log.info("====> Elasticsearch search one person by name : " + name);
        return personDocService.getByName(name);
    }

    @GetMapping("/{name}")
    public List<PersonDoc> findAllByName(@PathVariable String name){
        log.info("====> Elasticsearch search all person by name : " + name);
        return personDocService.getAllByName(name);
    }
}
