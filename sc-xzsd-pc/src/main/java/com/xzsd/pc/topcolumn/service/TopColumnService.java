package com.xzsd.pc.topcolumn.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.topcolumn.dao.TopColumnDao;
import com.xzsd.pc.topcolumn.entity.TopColumn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 查顶部栏
 * @author lcx
 * @date 2020/4/13
 */
@Service
public class TopColumnService {
    @Resource
    private TopColumnDao topColumnDao;

    /**
     * 查询顶部栏
     * @param loginUserId
     * @return
     * @author lcx
     * @date 2020/4/13
     */
    public AppResponse getTopColumn(String loginUserId){
        TopColumn topColumn = topColumnDao.getTopColumn(loginUserId);
        if(null == topColumn){
            return AppResponse.bizError("查询顶部栏失败");
        }
        return AppResponse.success("查询顶部栏成功", topColumn);
    }
}
