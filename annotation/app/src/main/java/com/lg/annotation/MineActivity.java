package com.lg.annotation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lg.arouterapi.core.ARouterLoadGroup;
import com.lg.common.RecordPathManager;


//@ARouter(path = "/app/MineActivity")
public class MineActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

    }

    public void onClickTo(View view) {
//        Intent intent = new Intent(this, MainActivity$$ARouter.findTargetClass("/app/MainActivity"));
//        startActivity(intent);

    }


}
