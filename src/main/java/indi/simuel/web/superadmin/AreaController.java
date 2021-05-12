package indi.simuel.web.superadmin;

import indi.simuel.entity.Area;
import indi.simuel.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AreaController
 * @Author txm
 * @Date 2021/1/21 21:41
 * @Version 1.0
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "listarea", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listArea() {
//        logger.info("===start===");
//        long startTime = System.currentTimeMillis();
        HashMap<String, Object> modelMap = new HashMap<>();
        try {
            List<Area> areaList = areaService.getAreaList();
            modelMap.put("rows", areaList);
            modelMap.put("total", areaList.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMeg", e.toString());
        }
//        logger.error("test error!");
//        long endTime = System.currentTimeMillis();
//        logger.debug("costTime:[{}ms]", endTime - startTime);
//        logger.info("===end===");
        return modelMap;
    }
}
