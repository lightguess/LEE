package com.lg.arouterapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.lg.annotation.ARouter;
import com.lg.annotation.model.RouterBean;
import com.lg.arouterapi.core.ARouterLoadGroup;
import com.lg.arouterapi.core.ARouterLoadPath;
import com.lg.arouterapi.core.Call;


public final  class RouterManager {


    // 路由组名
    private String group;
    // 路由详细路径
    private String path;

    private static RouterManager instance;

    private LruCache<String , ARouterLoadGroup> groupLruCache;

    private LruCache<String , ARouterLoadPath> pathLruCache;

    // APT生成的路由组Group源文件前缀名
    private static final String GROUP_FILE_PREFIX_NAME = ".ARouter$$Group$$";

    public static  RouterManager getInstance(){
        if(instance == null) {
            synchronized (RouterManager.class) {
                if (instance == null) {
                    instance = new RouterManager();
                }
            }
        }
        return instance;
    }


    private  RouterManager(){
        groupLruCache = new LruCache<>(163);
        pathLruCache = new LruCache<>(163);
    }


    public BundleManager build(String path){
        if (TextUtils.isEmpty(path) || !path.startsWith("/")) {
           throw new IllegalArgumentException("未按规范配置，如：/app/MainActivity");
        }
        group = subFromPath2Group(path);
        this.path = path;
        //
       return new BundleManager();
    }

    private String subFromPath2Group(String path) {
        if (path.lastIndexOf("/") == 0) {
            //规范
            throw new IllegalArgumentException ("@ARouter注解未按规范配置，如：/app/MainActivity");
        }

        String finalGroup = path.substring(1, path.indexOf("/", 1));
                if (TextUtils.isEmpty(finalGroup)) {
                    throw new IllegalArgumentException("@ARouter注解未按规范配置，如：/app/MainActivity");
                }
        return finalGroup;



    }

    Object navigation(@NonNull Context context, BundleManager bundleManager, int code) {
        String groupClassName = context.getPackageName() + ".apt" + GROUP_FILE_PREFIX_NAME + group;
        Log.e("arouter >>> ", "groupClassName -> " + groupClassName);
        //1.getGroup
        try {
            ARouterLoadGroup groupLoad = groupLruCache.get(group);
            if(groupLoad == null){
                Class<?> clazz = Class.forName(groupClassName);
                groupLoad = (ARouterLoadGroup) clazz.newInstance();
                groupLruCache.put(group,groupLoad);
            }
            if (groupLoad.loadGroup().isEmpty()) {
                throw new RuntimeException("路由加载失败");
            }

            //2.getpath
            ARouterLoadPath pathLoad = pathLruCache.get(path);
            if(pathLoad ==null){
                Class<? extends  ARouterLoadPath> clazz = groupLoad.loadGroup().get(group);
                if(clazz !=null){
                    pathLoad = clazz.newInstance();
                }
                if(pathLoad!=null) {
                    pathLruCache.put(path,pathLoad);
                }
            }

            //3.nativgate

            if(pathLoad!=null){
                pathLoad.loadPath();
            }

            if(pathLoad.loadPath().isEmpty()){
                throw new RuntimeException("路由路径加载失败");
            }

            RouterBean routerBean = pathLoad.loadPath().get(path);
            if(routerBean!=null){
                switch (routerBean.getType()){
                    case ACTIVITY:
                        Intent intent = new Intent(context, routerBean.getClazz());
                       intent.putExtras(bundleManager.getBundle());
                       if(bundleManager.isResult()){
                           ( (Activity)context).setResult(code,intent);
                           ((Activity)context).finish();

                       }else if(code>0){
                           ( (Activity)context).startActivityForResult(intent,code,bundleManager.getBundle());

                       }else {
                           context.startActivity(intent,bundleManager.getBundle());
                       }

                        break;
                    case CALL:
                        Class<?> clazz = routerBean.getClazz();
                        Call call = (Call) clazz.newInstance();
                        bundleManager.setCall(call);
                        return  bundleManager.getCall();

                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }



        return null;
    }



}
