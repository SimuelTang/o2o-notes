package indi.simuel.dao;

import indi.simuel.BaseTest;
import indi.simuel.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author simuel_tang
 * @Date 2021/3/10
 * @Time 9:22
 */
public class HeadLineDaoTest extends BaseTest {

    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLineList() {
        HeadLine headLine = new HeadLine();
        List<HeadLine> headLines = headLineDao.queryHeadlines(headLine);
        assertEquals(1, headLines.size());
    }
}
