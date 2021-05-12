package indi.simuel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import indi.simuel.cache.JedisUtil;
import indi.simuel.dao.AreaDao;
import indi.simuel.entity.Area;
import indi.simuel.exceptions.AreaOperationException;
import indi.simuel.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AreaServiceImpl
 * @Author txm
 * @Date 2021/1/21 21:06
 * @Version 1.0
 */

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static final Logger LOGGER = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Transactional
    @Override
    public List<Area> getAreaList() {
        // 在redis中存储时使用的key
        String key = AREA_LIST_KEY;
        // 接收数据的对象
        List<Area> areaList = new ArrayList<>();
        // 数据和json进行转化操作的类
        ObjectMapper objectMapper = new ObjectMapper();
        if (!jedisKeys.exists(key)) {
            // key在redis中不存在，则先从数据库中获取再存入redis
            areaList = areaDao.queryArea();
            String jsonOfAreaList;
            try {
                // 转化成json存入redis
                jsonOfAreaList = objectMapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonOfAreaList);
        } else {
            // 若存在，直接从redis中读取数据
            String jsonOfAreaList = jedisStrings.get(key);
            // 指定将要转化的类型（集合类型、区域作为存储单元）
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType
                    (ArrayList.class, Area.class);
            try {
                areaList = objectMapper.readValue(jsonOfAreaList, javaType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return areaList;
    }
}
