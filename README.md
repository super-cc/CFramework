# CFramework的介绍
基于DataBinding的一个基本框架，网络层使用okHttp。
第一次写框架，定有不妥之处，望大家留言指出。
框架介绍也写的一般，比较乱。会慢慢更正。
outlook：guoshichaocc@live.com
gmail：guoshichao.cn@gmail.com

# CFramework的依赖

### 添加依赖
在Project的build.gradle中添加：
```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
在Module的build.gradle中添加：
```
dependencies {
        compile 'com.github.super-cc:CFramework:v1.0.3'
}
```
在Module的build.gradle中android中添加：
```
dataBinding {
        enabled = true
}
```

### 记得添加网络权限
```
<!--网络权限-->
<uses-permission android:name="android.permission.INTERNET"/>
```

# CFramework的使用

### 基础框架
先看文件夹的图片：

![文件夹](http://upload-images.jianshu.io/upload_images/7203723-ef356da6e9680ae5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

先简单的了解一下：
- base：基础的Activity，Adapter，Http等。
- constant：一些常量。
- db：数据库操作。
- lock：给xml设置的类
- model：DataBinding数据模型
- network：网络的封装。
- response：网络请求返回的Json解析类
- service：服务类。
- ui：Activity，Fragment，Adapter界面类。
- utils：工具类。
- view：自定义控件。

### 基本使用

- 在model下新建Demo类：
``` java
/**
 * 创建日期：2017/10/31 on 14:46
 * 描述：数据模型Demo，如果需要数据刷新就继承BaseObservable
 * 作者：郭士超
 * QQ：1169380200
 */

public class Demo extends BaseObservable{

    private String name;
    private String age;

    public Demo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
```
- 在model下新建DemoItem类：
``` java
/**
 * 创建日期：2017/10/31 on 16:47
 * 描述：在ListView或RecyclerView中使用的数据模型
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoItem {

    private String name;
    private String age;

    public DemoItem(String name, String age) {
        this.name = name;
        this.age = age + "岁";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
```

- 在lock下新建DemoLock类：
``` java
/**
 * 创建日期：2017/10/31 on 13:54
 * 描述：DemoLock，处理一些网络数据，和一些逻辑
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoLock extends CBaseLock<ActivityDemoBinding> {

    private Demo mDemo;
    private List<DemoItem> mListDemo;
    private DemoAdapter mDemoAdapter;
    private CBaseRecyclerViewAdapter mAdapter;

    public DemoLock(Context context, ActivityDemoBinding binding) {
        super(context, binding);
    }

    @Override
    protected void init() {
        // 初始化数据
        mDemo = new Demo("郭士超", "22");

        mListDemo = new ArrayList<DemoItem>();
        mListDemo.add(new DemoItem(mDemo.getName(), mDemo.getAge()));
        mDemoAdapter = new DemoAdapter(mContext, mListDemo, R.layout.item_demo, BR.demoItem);
        mBinding.lvDemo.setAdapter(mDemoAdapter); // 这里或者在xml里设置adapter
        mDemoAdapter.setOnItemClickListener(new CBaseListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewDataBinding binding, int position) {
                showToast(((ItemDemoBinding) binding).getDemoItem().getName()
                        + mListDemo.get(position).getAge());
            }
        });

        LinearLayoutManager ms = new LinearLayoutManager(mContext);
        mBinding.rvDemo.setLayoutManager(ms);
        mAdapter = new CBaseRecyclerViewAdapter(mContext, mListDemo, R.layout.item_demo, BR.demoItem);
//        mBinding.rvDemo.setAdapter(mAdapter); // 这里或者在xml里设置adapter
        mAdapter.setOnItemClickListener(new CBaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewDataBinding binding, int position) {
                showToast(((ItemDemoBinding) binding).getDemoItem().getName()
                        + mListDemo.get(position).getAge());
            }
        });

        // 访问网络的方法
        HashMap hashMap = new HashMap();
        hashMap.put("userId", "1");
        Connection.getInstance().post(DemoResponse.class, UrlConfig.XXX, hashMap, new Connection.ResponseListener() {
            @Override
            public void tryReturn(int id, Object response) {
                switch (id) {
                    case 200:
                        DemoResponse data = (DemoResponse) response;
                        new Demo(data.getData().getName(), String.valueOf(data.getData().getAge()));
                        break;
                    case 100:
                        showToast("用户不存在");
                        break;
                    default:
                        showToast(((HttpResponse)response).getMsg());
                        break;
                }
            }
        });
    }

    public void update(View view) {
        mDemo.notifyChange(); // 刷新数据
        mListDemo.add(new DemoItem(mDemo.getName(), mDemo.getAge()));
        mDemoAdapter.notifyDataSetChanged(); // 刷新数据
        mAdapter.notifyDataSetChanged(); // 刷新数据
    }

    public Demo getDemo() {
        return mDemo; // 让xml中可以调用到Demo
    }

    public DemoAdapter getDemoAdapter() {
        return mDemoAdapter; // 让xml中可以调用到mAdapter
    }

    public CBaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

}
```

- 新建xml文件activity_demo布局：
``` xml
<?xml version="1.0" encoding="utf-8"?>
<layout
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

        <variable
            name="demoLock"
            type="com.superc.framework.lock.DemoLock" />
    </data>

<android.support.constraint.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{demoLock.demo.name}"
        android:textSize="20sp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintHorizontal_bias="0.36"
        android:layout_marginTop="32dp"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@{demoLock.demo.age}"
        android:textSize="20sp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintHorizontal_bias="0.64"
        android:layout_marginTop="32dp"/>

    <EditText
        android:id="@+id/editText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:text="@={demoLock.demo.name}"
        android:inputType="textPersonName"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_bias="0.503"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="72dp"/>

    <EditText
        android:id="@+id/editText2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ems="10"
        android:text="@={demoLock.demo.age}"
        android:inputType="number"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_bias="0.503"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@+id/editText"
        />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="更新"
        android:onClick="@{(view)->demoLock.update(view)}"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@+id/editText2"/>

    <ListView
        android:id="@+id/lv_demo"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="16dp"
        android:visibility="visible"
        app:adapter="@{demoLock.demoAdapter}"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button"
        app:layout_constraintVertical_bias="0.0"
        app:layout_constraintRight_toLeftOf="@+id/guideline"
        android:layout_marginRight="8dp"/>

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_demo"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginTop="16dp"
        app:adapter="@{demoLock.adapter}"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="@+id/guideline"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button"/>

    <android.support.constraint.Guideline
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/guideline"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.5"/>

</android.support.constraint.ConstraintLayout>

</layout>
```

- 在ui下新建DemoActivity类：
``` java
/**
 * 创建日期：2017/10/30 on 13:55
 * 描述：一个Demo
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoActivity extends BaseActivity {

    @Override
    protected void initBinding() {

        // 数据绑定操作，可以套用代码
        ActivityDemoBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        DemoLock demoLock = new DemoLock(this, mBinding);
        mBinding.setDemoLock(demoLock);

    }

}
```

现在已经可以运行代码。

- 如果是在Fragment中使用：
``` java
/**
 * 创建日期：2017/10/31 on 14:07
 * 描述：
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoFragment extends CBaseFragment {

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {

        FragmentDemoBinding mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_demo, container, false);
        DemLock demLock = new DemLock(mActivity, mBinding);
        mBinding.setDemLock(demLock);

        return mBinding.getRoot();
    }

}
```
- 如果需要自定义Adapter：
``` java
/**
 * 创建日期：2017/10/31 on 15:44
 * 描述：继承BaseListViewAdapter，只需要复写subTask就可以
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoAdapter extends CBaseListViewAdapter{

    public DemoAdapter(Context context, List list, int layoutId, int variableId) {
        super(context, list, layoutId, variableId);
    }

    @Override
    protected void subTask(ViewDataBinding binding, int position) {
        super.subTask(binding, position);
        // 这里可以写自己一些自己的逻辑，如果不需要，一般直接用BaseListViewAdapter就可以，不需要再构建
        // 写法大概如下
        ((ItemDemoBinding)binding).tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
```

### 依赖使用
如果依赖使用的话，最好是自己再写一个BaseActivity和BaseLock分别继承与CBaseActivity和CBaseLock，在里面写一些自己项目需要的东西。

### 其它用法

##### Connection类
这个类是单例模式，调用方法就是：
``` java
Connection.getInstance().post();
```
这个类封装了get，post的方法，主要使用这两种形式就可以。
还有一些其它的post方法（重点的两个）：
- postLogin：登入时自动保存userId和token。
- postToken：访问直接带userId和token。

里面还有一个toLogin()方法没有写，这个方法用于如果token不对，跳回到LoginActivit。
这个也可以自己仿照自己封装一个。

##### RunTime类
这个类是一些运行时的临时数据。
是使用容器实现单例模式。

##### UrlConfig类
这个类存放项目的接口url。

### 工具类
主要工具类有：
- AppUtil：App相关辅助类。
- AtyManager：Activity管理类。
- DensityUtil：屏幕常用单位转换的辅助类。
- KeyBoardUtil：软键盘相关辅助类。
- LogUtil：Log统一管理类，上线时把isDebug设置为false。
- NetUtil：跟网络相关的工具类。
- ScreenUtil：获得屏幕相关的辅助类。
- SPUtil：SharePreference存储工具类。
- ToastUtil：Toast统一管理类。
- UnicodeUtil：utf-8和unicode汉字码转换器。

工具类的注释很详细，如果需要查看，在代码中import，然后ctrl+鼠标左击，进入代码中查看。

#### 写在后面
自己第一次写框架，写自己的教程，写的很乱，我会慢慢更正。
