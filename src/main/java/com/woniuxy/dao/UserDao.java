package com.woniuxy.dao;

import com.woniuxy.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User selectByAccount(@Param("account") String account);
}
