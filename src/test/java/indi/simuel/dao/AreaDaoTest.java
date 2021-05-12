package indi.simuel.dao;

import indi.simuel.BaseTest;
import indi.simuel.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName AreaDaoTest
 * @Author txm
 * @Date 2021/1/21 20:00
 * @Version 1.0
 */
public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        System.out.println(areaList);
    }
}
