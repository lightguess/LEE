package com.lg.personal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lg.annotation.ARouter;
import com.lg.annotation.Parameter;
import com.lg.arouterapi.ParameterManager;
import com.lg.arouterapi.RouterManager;
import com.lg.common.base.BaseActivity;
import com.lg.common.user.BaseUser;
import com.lg.common.user.IUser;

@ARouter(path = "/personal/Personal_MainActivity")
public class Personal_MainActivity extends BaseActivity {


    private static final String TAG = Personal_MainActivity.class.getSimpleName();
//    @Parameter
//    String name;

    @Parameter(name = "/app/getUserInfo")
    IUser iUser;

    @Parameter
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);

        Log.e("TAG", "personal/Personal_MainActivity");

        // 懒加载方式，跳到哪加载哪个类
        ParameterManager.getInstance().loadParameter(this);

        Log.e(TAG, "接收参数值：" + username);
        if(getIntent()!=null){
            Bundle bundle = getIntent().getExtras();
            if(bundle!=null){
                Log.e(TAG, "onCreate: bundle  ="+ bundle.getString("name") );

            }
        }
        BaseUser userInfo = iUser.getUserInfo();
        if (userInfo != null) {
            Log.e(TAG, userInfo.getName() + " / "
                    + userInfo.getAccount() + " / "
                    + userInfo.getPassword());
        }
    }

    public void jumpApp(View view) {
        RouterManager.getInstance()
                .build("/app/MainActivity")
                .withResultString("call", "I'am comeback!")
                .navigation(this);
    }

    public void jumpOrder(View view) {

    }
}
