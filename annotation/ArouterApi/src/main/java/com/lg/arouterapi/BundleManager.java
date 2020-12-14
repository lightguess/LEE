package com.lg.arouterapi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lg.arouterapi.core.Call;

public final  class BundleManager {

    private Bundle bundle = new Bundle();
    private Call call;
    private boolean isResult;

    public Bundle getBundle() {
        return bundle;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    boolean isResult (){
        return isResult;
    }

    public BundleManager withString (@NonNull String key , @Nullable String value){
        bundle.putString(key, value);
        return this;
    }


    public  BundleManager withResultString (@NonNull String key , @Nullable String value){
        bundle.putString(key,value);
        isResult = true;
        return this;
    }



        public BundleManager withBoolean(@NonNull String key, boolean value) {
            bundle.putBoolean(key, value);
            return this;
        }
    //
        public BundleManager withInt(@NonNull String key, int value) {
            bundle.putInt(key, value);
            return this;
        }
    //
        public BundleManager withBundle(@NonNull Bundle bundle) {
            this.bundle = bundle;
            return this;
        }
    //
        public Object navigation(Context context) {
            return RouterManager.getInstance().navigation(context, this, -1);
        }
    //    // 这里的code，可能是requestCode，也可能是resultCode。取决于isResult
        public Object navigation(Context context, int code) {
            return RouterManager.getInstance().navigation(context, this, code);
        }

}
