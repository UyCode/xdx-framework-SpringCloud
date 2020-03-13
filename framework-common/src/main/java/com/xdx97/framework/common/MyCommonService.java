package com.xdx97.framework.common;

import com.github.pagehelper.PageHelper;
import com.xdx97.framework.entitys.pojo.user.User;
import com.xdx97.framework.utils.primarykeysnowflake.PrimaryKeySnowflakeGenerator;
import com.xdx97.framework.utils.redis.RedisUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这里面封装一些Service公用的方法
 */
@Component
public class MyCommonService {

    /**
     * 使用pageHelper 默认当前页 10
     * @param pageNum
     */
    public void startPage(int pageNum){
        startPage( pageNum,  10);
    }
    public void startPage(int pageNum, int pageSize){
        PageHelper.startPage( pageNum,  pageSize);
    }

    /**
     * 获取当前用户的雪花算法key
     * @param o
     * @return
     */
    public String getSnowflakeKey(Object o){

        PrimaryKeySnowflakeGenerator primaryKeySnowflake = new PrimaryKeySnowflakeGenerator();
        return primaryKeySnowflake.generate(o);
    }

    /**
     * 获取当前用户
     *
     * @return user 当前用户
     * @author 小道仙
     * @date 2020年3月13日
     */
    public User getCurUser(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
//        HttpServletResponse response = servletRequestAttributes.getResponse();
        return (User)RedisUtils.get(request.getHeader("X-Token"));
    }

}
