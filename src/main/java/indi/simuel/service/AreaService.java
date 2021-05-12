package indi.simuel.service;

import indi.simuel.entity.Area;

import java.util.List;

public interface AreaService {
    String AREA_LIST_KEY = "arealist";

    /**
     * 获取区域列表，优先从缓存获取
     *
     * @return
     */
    List<Area> getAreaList();
}
