package cn.com.dayet.mapper;

import cn.com.dayet.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Dayet
 * @version 1.0
 * @date 2019/9/11 0:39
 */
@Mapper
public class UserMapper {
    @Insert("inster into user(name,account_id,gmt_create,gmt_modified) values(#{name},#{accountId},#{tokne},#{gmtCreate},#{gmtModified})")
    public void insert(User user) {
    }
}
