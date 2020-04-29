package com.zyaml.nai.entry.dto;

import com.zyaml.nai.comom.TagsCommom;
import com.zyaml.nai.entry.node.*;
import com.zyaml.nai.util.Ptags;
import lombok.Data;

import java.util.List;


/**
 * 该对象负责封装Problem以及和该题目相关的Tags
 *
 * @Author: 994
 * @Date: 2020-04-26 14:29
 */
@Data
public class ProblemAndTags {

    @Ptags(tagName = TagsCommom.PROBLEM)
    private Problem problem;

    @Ptags(tagName = TagsCommom.DIF)
    private Difficulty difficulty;

    @Ptags(tagName = TagsCommom.ANSWER)
    private Answer answer;

    @Ptags(tagName = TagsCommom.ALG)
    private List<Tags> alg;

    @Ptags(tagName = TagsCommom.REGION)
    private Tags region;

    @Ptags(tagName = TagsCommom.TIME)
    private Tags time;

    @Ptags(tagName = TagsCommom.ORI)
    private List<Tags> ori;

    @Ptags(tagName = TagsCommom.TK)
    private Types tk;

    private List<Problem> sameDifSameAlg;

    private List<Problem> sameDifNotAlg;

    private List<Problem> sameAlgHighDif;

}
