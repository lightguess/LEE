package com.lg.personal.user;


import com.lg.annotation.ARouter;
import com.lg.common.user.BaseUser;
import com.lg.common.user.IUser;
import com.lg.personal.model.UserInfo;

/**
 * personal模块实现的内容
 */
@ARouter(path = "/personal/getUserInfo")
public class PersonalUserImpl implements IUser {

    @Override
    public BaseUser getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("xiaohong");
        userInfo.setAccount("hong");
        userInfo.setPassword("123456");
        userInfo.setVipLevel(9);
        return userInfo;
    }
}
