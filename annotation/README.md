# MyArouter 组件化开发使用

### 1.添加依赖
```
android {
    defaultConfig {
        ...
        javaCompileOptions {
                   annotationProcessorOptions {
                       arguments = [moduleName: project.getName(), packageNameForAPT: packageNameForAPT]
                   }
               }
        }
    }
}

dependencies {
    // 依赖注解
    implementation project(':annotation')
    // 依赖注解处理器
    annotationProcessor project(':compiler')
    ...
}
```

### 2.添加注释格式：@ARouter(path ="/groupName/classname"）

```
@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {
...
}
```

### 3.跳转到其他的模块
```
int requestCode = 1001;
Bundle bundle = new Bundle();
bundle.putString("name", "xiaoming");
bundle.putInt("age", 18);
bundle.putBoolean("isSuccess", true);

//build(/<groupName>/<className>)
//requestCode >0 = startActivityForResult
//navigation(this); startActivity

RouterManager.getInstance()
    .build("/personal/Personal_MainActivity")
    .withBundle(bundle)
    .withString("username", "student")
    .navigation(this, requestCode);
    

// withResultString =调用前面模块的onActivityForResult
RouterManager.getInstance()
 .build("/app/MainActivity")
 .withResultString("call", "I'am comeback!")
 .navigation(this);

```

### 4.获取其他模块的参数
```

@Parameter
String username;

@Override
protected void onCreate(Bundle savedInstanceState) {
ParameterManager.getInstance().loadParameter(this);
    Log.e(TAG, "接收参数值：" + username);
     //或者
if(getIntent()!=null){
    Bundle bundle = getIntent().getExtras();
    if(bundle!=null){
        Log.e(TAG, "onCreate: bundle  ="+ bundle.getString("name") );
        }
    }
}

```
