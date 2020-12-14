package com.lg.annotation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lg.annotation.model.UserInfo;
import com.lg.annotation.user.IUserImpl;
import com.lg.arouterapi.RouterManager;
import com.lg.arouterapi.core.ARouterLoadGroup;


@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTo(View view) {
//        Intent intent = new Intent(this, HomeActivity$$ARouter.findTargetClass("/app/HomeActivity"));
//        startActivity(intent);
//        Intent intent = new Intent(this, Personal_MainActivity.class);
//        intent.putExtra("name", "simon");
//        startActivity(intent);
//        ARouterLoadGroup group = new ARouter$$Group$$personal();
        Bundle bundle = new Bundle();
        bundle.putString("name", "xiaoming");
        bundle.putInt("age", 18);
        bundle.putBoolean("isSuccess", true);

        RouterManager.getInstance()
                .build("/personal/Personal_MainActivity")
                .withBundle(bundle)
                .withString("username", "student")
                .navigation(this, 163);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: ");
        if (data != null) {
            Log.e(TAG, data.getStringExtra("call"));
        }
    }


}