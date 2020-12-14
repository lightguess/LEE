package com.lg.annotation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


//@ARouter(path = "/app/HomeActivity")
public  class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onClickTo(View view) {
//        Intent intent = new Intent(this, MineActivity$$ARouter.findTargetClass("/app/MineActivity"));
//        startActivity(intent);
    }

}
