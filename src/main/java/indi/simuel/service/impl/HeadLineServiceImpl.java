package indi.simuel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import indi.simuel.cache.JedisUtil;
import indi.simuel.dao.HeadLineDao;
import indi.simuel.entity.HeadLine;
import indi.simuel.exceptions.HeadLineOperationException;
import indi.simuel.service.HeadLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/3/10
 * @Time 9:25
 */

@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static final Logger LOGGER = LoggerFactory.getLogger(HeadLineServiceImpl.class);


    @Transactional
    @Override
    public List<HeadLine> getHeadLines(HeadLine headLineCondition) {
        String key = HEAD_LINE_KEY;
        List<HeadLine> headLineList;
        ObjectMapper objectMapper = new ObjectMapper();
        // 拼接出redis的key
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        if (!jedisKeys.exists(key)) {
            // 从数据库中获取数据
            headLineList = headLineDao.queryHeadlines(headLineCondition);
            String jsonOfHeadlines;
            try {
                // 转化为json
                jsonOfHeadlines = objectMapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonOfHeadlines);
        } else {
            // 从redis中获取数据
            String jsonOfHeadlines = jedisStrings.get(key);
            // 设置对象类型并转化
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try {
                headLineList = objectMapper.readValue(jsonOfHeadlines, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
        }
        return headLineList;
    }
}
