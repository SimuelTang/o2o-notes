package indi.simuel.service;

import indi.simuel.BaseTest;
import indi.simuel.dao.HeadLineDao;
import indi.simuel.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author simuel_tang
 * @Date 2021/3/10
 * @Time 11:00
 */
public class HeadLineServiceTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testGetHeadlines() {
        List<HeadLine> headLines = headLineDao.queryHeadlines(new HeadLine());
        assertEquals(1, headLines.size());
    }
}
