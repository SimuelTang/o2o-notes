package indi.simuel.service;

import indi.simuel.BaseTest;
import indi.simuel.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * @ClassName AreaServiceTest
 * @Author txm
 * @Date 2021/1/21 21:12
 * @Version 1.0
 */

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    
    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList);
    }
    
}
