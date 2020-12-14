package com.lg.annotation.user;


import com.lg.annotation.ARouter;
import com.lg.annotation.model.UserInfo;
import com.lg.common.user.BaseUser;
import com.lg.common.user.IUser;

/**
 * personal模块实现的内容
 */
@ARouter(path = "/app/getUserInfo")
public class IUserImpl implements IUser {

    UserInfo userInfo;


    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public BaseUser getUserInfo() {
        if(this.userInfo==null){
            UserInfo userInfo = new UserInfo();
            userInfo.setName("lg");
            userInfo.setAccount("lee");
            userInfo.setPassword("666666");
            userInfo.setVipLevel(9);
            this.userInfo =userInfo;
        }

        return this.userInfo;
    }
}
