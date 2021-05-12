package indi.simuel.service;

import indi.simuel.entity.HeadLine;

import java.util.List;

public interface HeadLineService {

    String HEAD_LINE_KEY = "headlinekey";

    List<HeadLine> getHeadLines(HeadLine headLineCondition);
}
