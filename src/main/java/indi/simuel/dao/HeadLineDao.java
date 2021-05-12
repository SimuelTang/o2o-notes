package indi.simuel.dao;

import indi.simuel.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {
    /**
     * 根据条件查询标题
     * @param headLineCondition 查询时规定的条件
     * @return 所有符合条件的标题
     */
    List<HeadLine> queryHeadlines(@Param("headLineCondition") HeadLine headLineCondition);
}
