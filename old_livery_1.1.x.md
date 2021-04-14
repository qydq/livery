<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN sunsta TO UPDATE -->
**Table of Contents**  *generated with [sunsta](https://zhihu.com/people/qydq)*

- [android快速集成基础框架 - Livery```1.1.111```](#android%E5%BF%AB%E9%80%9F%E9%9B%86%E6%88%90%E5%9F%BA%E7%A1%80%E6%A1%86%E6%9E%B6---livery1124)
  - [情景能力# Ability](#%E6%83%85%E6%99%AF%E8%83%BD%E5%8A%9B-ability)
    - [1：主要能力](#1%E4%B8%BB%E8%A6%81%E8%83%BD%E5%8A%9B)
    - [2：最新版本能力](#2%E6%9C%80%E6%96%B0%E7%89%88%E6%9C%AC%E8%83%BD%E5%8A%9B)
  - [集成方式# Binaries](#%E9%9B%86%E6%88%90%E6%96%B9%E5%BC%8F-binaries)
  - [模块介绍# Details Module](#%E6%A8%A1%E5%9D%97%E4%BB%8B%E7%BB%8D-details-module)
    - [部分情景能力演示](#%E9%83%A8%E5%88%86%E6%83%85%E6%99%AF%E8%83%BD%E5%8A%9B%E6%BC%94%E7%A4%BA)
    - [an情景系列（material-ux）](#an%E6%83%85%E6%99%AF%E7%B3%BB%E5%88%97material-ux)
    - [an情景系列（scene-mode）](#an%E6%83%85%E6%99%AF%E7%B3%BB%E5%88%97scene-mode)
      - [弹幕控制](#%E5%BC%B9%E5%B9%95%E6%8E%A7%E5%88%B6)
  - [混淆配置# proguard-rules](#%E6%B7%B7%E6%B7%86%E9%85%8D%E7%BD%AE-proguard-rules)
  - [常见错误# Easy Mistake](#%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF-easy-mistake)
    - [非常重要1：1.1.x版本用androidx，替换掉所有的support-v4,v7包](#%E9%9D%9E%E5%B8%B8%E9%87%8D%E8%A6%81111x%E7%89%88%E6%9C%AC%E7%94%A8androidx%E6%9B%BF%E6%8D%A2%E6%8E%89%E6%89%80%E6%9C%89%E7%9A%84support-v4v7%E5%8C%85)
    - [非常重要2： Manifest merger failed : Attribute meta-data#android.support.FILE_PROVIDER_PATHS](#%E9%9D%9E%E5%B8%B8%E9%87%8D%E8%A6%812-manifest-merger-failed--attribute-meta-dataandroidsupportfile_provider_paths)
    - [非常重要3：This project uses AndroidX dependencies, but the 'android.useAndroidX' property is not enabled. Set this property to true in the gradle.properties file and retry.](#%E9%9D%9E%E5%B8%B8%E9%87%8D%E8%A6%813this-project-uses-androidx-dependencies-but-the-androiduseandroidx-property-is-not-enabled-set-this-property-to-true-in-the-gradleproperties-file-and-retry)
    - [注意事项2： More than one file was found with OS independent path 'META-INF/rxjava.properties'](#%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B92-more-than-one-file-was-found-with-os-independent-path-meta-infrxjavaproperties)
    - [更多：其它android中常见错误解决方法点击这里查看。](#%E6%9B%B4%E5%A4%9A%E5%85%B6%E5%AE%83android%E4%B8%AD%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF%E8%A7%A3%E5%86%B3%E6%96%B9%E6%B3%95%E7%82%B9%E5%87%BB%E8%BF%99%E9%87%8C%E6%9F%A5%E7%9C%8B)
  - [版本日志# Version LOG](#%E7%89%88%E6%9C%AC%E6%97%A5%E5%BF%97-version-log)
    - [apk下载及其说明](#apk%E4%B8%8B%E8%BD%BD%E5%8F%8A%E5%85%B6%E8%AF%B4%E6%98%8E)
    - [1.0.x版本总述](#10x%E7%89%88%E6%9C%AC%E6%80%BB%E8%BF%B0)
    - [1.1.x](#11x)
  - [其它说明](#%E5%85%B6%E5%AE%83%E8%AF%B4%E6%98%8E)
    - [关于自定义apk名说明](#%E5%85%B3%E4%BA%8E%E8%87%AA%E5%AE%9A%E4%B9%89apk%E5%90%8D%E8%AF%B4%E6%98%8E)
    - [关于应用内apk自动安装说明](#%E5%85%B3%E4%BA%8E%E5%BA%94%E7%94%A8%E5%86%85apk%E8%87%AA%E5%8A%A8%E5%AE%89%E8%A3%85%E8%AF%B4%E6%98%8E)
  - [致谢](#%E8%87%B4%E8%B0%A2)
  - [LICENSE](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# android快速集成基础框架 - Livery```1.1.111```

[![Apache-2.0](http://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=flat)](https://github.com/qydq/alidd-sample/blob/master/LICENSE)
[![Download](https://api.bintray.com/packages/qydq/maven/livery/images/download.svg)](https://bintray.com/qydq/maven/livery/_latestVersion)
[![JCenter](https://img.shields.io/badge/%20JCenter%20-1.1.111-5bc0de.svg)](https://bintray.com/qydq/maven/livery/_latestVersion)
![@sunst](https://avatars0.githubusercontent.com/u/20716264?s=60&u=ec068ee954f943483fbf1516803dcd5b77520ad3&v=4)

[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2021%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=21)
[![Release Version](https://img.shields.io/badge/release-1.1.111-red.svg)](https://github.com/qydq/small-video-record/releases)
[![](https://img.shields.io/badge/Author-sunst-blue.svg)](https://www.zhihu.com/people/qydq)

[***中文API帮助文档1.1下载`密码:xeq0`)***](https://pan.baidu.com/s/1yczO3lh4p8Njc_rdb9Fe5g) 一款针对Android平台下快速集成**便捷开发**框架，```an情景```系列```livery框架```部分基于基础的an-base项目[#原an框架](https://github.com/qydq/an-aw-base)仓库优化而来，其目的1是为小团子fang升级一款音乐聊天软件```[hong]3.0版本```，现在开放出来，当前优化后最新体积仅有603KB.
![](https://github.com/qydq/alidd-samples/blob/master/screen/livery_size.png?raw=true)
>20190609再次确定命名image*Internet.
>livery一直维护，周1-5工作，有bug提[issues]([https://github.com/qydq/alidd-samples/issues](https://github.com/qydq/livery-sample/issues))（或在知乎上给我留言，**问题描述清楚**就行]，一般修复好周7当晚更新.

专注于物联网领域，世界的通信标准从今开始改变，手机也可以是路由器，成功于视频直播，标准并不一定是Http/s，也可以是Bluetooth.

[**我的唯一知乎地址.**](https://www.zhihu.com/people/qydq/columns)&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;（感谢关注🙏）
## 情景能力# Ability
```livery```一路走来经历了很多版本，现在是一个非常成熟的稳定版本；它包含一些非常实用的能力和一些技巧， 用简洁友好的方式，助力便捷开发；以下列举当前支持的**部分**功能<br/>
**⚠️注意**
>`1.1.10`之前的版本差异较大，建议使用最新版本. [(点击这里查看老版本日志记录)](https://github.com/qydq/alidd-samples/blob/master/alidd_old_1.0.x.md)
>`1.2.0`Livery已经启动了1.2.0国际化版本的任务，敬请期待!

### 1：主要能力
- [x] 符合Google Material Design的基类，如：AliActivity、AliFragment、BasePresenter、BaseView==.
- [x] 两种夜间模式.
- [x] 网络1请求基于xutils3模块的封装，http实现XHttps.** （⚠️因控制大小移除xutils，替代为Retrofit+RxJava）
- [x] INA系列控件相关，如标题栏，弹幕，tab.
- [x] 拉```La```情景实用能力集，如MD5加密，数据校验，尺寸，图片处理，网络，模糊算法，更新软件==.
- [x] StrictMode API 禁权限便捷申请.
### 2：最新版本能力
- [x] 网络2请求InternetClient基于Retrofit封装；RxJava，RxAndroid实现InternetAsyncManager.
- [x] GIF图片更友好便捷使用，提供GifImageView可以更快的加载Gif，效率可对比之前INAPowerImageView.
- [x] Glide加载图片会出现抖动的修复，请使用LaImageLoader.
- [x] 拍照相册选择能力，集成[PictureSelector](https://github.com/LuckSiege/_PictureSelector_)；整合原TakePhoto模块，视频预览图MediaHelper.
- [x] 快速集成实现你的导航栏工具
- [ ] 正在开发ing...智能语音唤醒监听能力（世界上最美的就是声音Voice ）.

&#8194;&#8194;&#8194;&#8194;&#8194;[**最新体验扫描二维码下载hong1.1.111.apk**](https://github.com/qydq/alidd-samples/raw/master/apk/demo_livery1.1.111.apk)&#8194;&#8194;&#8194;&#8194;
## 集成方式# Binaries
集成方式有以下两种：

### 1.(建议)通过JCenter集成
第1步骤：  在你项目（app module）的build.gradle中添加（致谢JitPack和Jcenter）.
```Groovy
dependencies {
  implementation'com.sunsta.livery:livery:1.1.111'
}
```
第2步骤（可选）：如果使用`网络2请求`，最好在你的`XxxApplication`中继承`AnApplication`，然后在`onCreate()`方法中调用如下代码
```java
public class AliddApplication extends AnApplication {
    @Override
  public void onCreate() {
        super.onCreate();
        Livery.instance().initialze("BASE_URL");
        Livery.instance().enableLog(DEBUG,"LOGFILTER);//当配置该内容时候，会打印Livery关键建议信息
    }
```
说明：`BASE_URL`是符合Retrofit的网络请求地址，如：`https://github.com/qydq/`，需要以`/`结尾，最后把`XxxApplication`添加到`AndroidManifest.xml`中.

### 2.(可选)手动集成：

第1步骤： 在链接:https://pan.baidu.com/s/1_NtHc-AlTaw3ka2aoeqQ_A  密码:16f2；下载Livery最新版本文件livery1.1.111.aar

然后将文件拷贝到libs目录中添加引用关系：

```Groovy
dependencies {
  implementation(name:'livery1.1.111', ext:'aar')
}
```


## 模块介绍# Details Module
```hong1.x.x.apk```为提供的安装包(可以扫描前面的二维码下载)，```kaiyan.apk```为livery结合MVVM开发的安装包，已共享到```玩安卓```网站，这里也可以下载；```hong1.x.xx_picture.apk```为PictureSelector二次编译以后的图片选择框架。
尽量少的依赖其它库来完成这个demo，使用原生的系统的组件和本livery框架提供的部分

包含4个tab：```1.Livery```提供，```2.通用业务```框架的使用，```3.视频```和```4.关于```
  >```1和2模块```中控件符合material design但是具体需要注意只有标注为：☀️ 表示这个模块的功能为发行版本，其它的示例代码仅供参考.
>```3和4模块```类似该功能不做参考，个人当前测试的一些逻辑性，功能性代码，如视频编解码，这部分代码仅供参考.

关于```livery情景```更多的api可以查看帮助文档.
![](https://github.com/qydq/alidd-samples/blob/master/screen/livery_api.png?raw=true)

下面是部份```Details Module```的介绍，更多内容可以从本人知乎an情景专栏中获取.
### 部分情景能力演示
说明：效果图包含了部分livery情景能力，关注我的知乎获取更多实用的技巧.
| 主页Home | 动画Gif |
|:-----------:|:-----------:|
|
![](https://github.com/qydq/alidd-samples/blob/master/screen/IMG_HOME.JPG?raw=true)|![](https://github.com/qydq/alidd-samples/blob/master/screen/IMG_GIF.JPG?raw=true)|

| 导航栏 | 导航栏1 | 导航栏2 |
|:-----------:|:--------:|:---------:|
|
![](https://github.com/qydq/alidd-samples/blob/master/screen/IMG_NAVICATION.JPG?raw=true) |![](https://github.com/qydq/alidd-samples/blob/master/screen/IMG_NAVICATION1.JPG?raw=true)|![](https://github.com/qydq/alidd-samples/blob/master/screen/IMG_NAVICATION2.JPG?raw=true)|
### an情景系列（material-ux）
1.Gif图片加载方法
```XML
<com.sunsta.bear.engine.gif.GifImageView
 android:id="@+id/gif_iamge_view"
 android:layout_width="match_parent"
 android:layout_height="match_parent"
 android:src="@mipmap/gif"/>
```
循环播放
GifImageView默认播放一次就停止了，我们可以通过GifImageView获取GifDrawable，然后再通过GifDrawable设置循环播放的次数，或者设置无限循环播放
```JAVA
GifImageView gifImageView = findViewById(R.id.gif_iamge_view);
GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
gifDrawable.setLoopCount(5); //设置具体播放次数
gifDrawable.setLoopCount(0); //设置无限循环播放
```
### an情景系列（scene-mode）
#### 弹幕控制


barrage_rowNum //设置弹幕有多少行 ， 默认1行弹幕
barrage_rowHeight //设置弹幕的高度 ，默认10dp
注意：当覆盖布局文件中的高度时，会使用xml中的布局高度
barrage_speed //设置弹幕的滚动的速度 ，默认8000秒
barrage_itemGap //设置弹幕与弹幕之间的间距，默认10dp
barrage_RowGap //设置弹幕与弹幕之间的行距，默认2dp
barrage_mode //设置弹幕的布局方式，默认正常(default)平均
barrage_isFly //设置弹幕是否可以漫天飞羽，默认true
barrage_keepSequence //当漫天飞羽时，设置是否保持先后顺序，默认false表示弹幕在布局INABarrageView内随机没有顺序的，不分先后出现，true表示弹幕每次显示一行，当第一行显示完再显示第二行
注意：当设置弹幕行数大于1（超过默认行数），则漫天飞羽关闭
barrage_interreptor //设置差值器，（保留字段）
barrage_gravity //设置弹幕内容，相对于布局INABarrageView的方向， 默认default，则有可能从上到下显示
barrage_duration // 保留字段，不可用，无效，
动画持续的时间，不同宽度的物体，划过同一个窗口，规定了总时间，以此获取对应的速度
barrage_repeatCount //设置动画重复的次数，默认0，设置为：ValueAnimator.INFINITE 则该弹幕会无限循环播放


## 混淆配置# proguard-rules
混淆规则一定要看：[**Android App代码混淆解决方案click**](https://zhuanlan.zhihu.com/p/34559807)
```BASH
#---------------------------4.(反射实体)个人指令区-qy晴雨（请关注知乎Bgwan）---------------------
# livery 1.1.xx
-keep class com.sunsta.bear.view.activity.**{*;}
-keep class androidx.support.widget.helper.**{*;}
-keep class com.sunsta.bear.**{*;}
-keep class com.sunsta.livery.**{*;}
-keep class com.sunsta.bear.view.**{*;}
-keep class com.sunsta.bear.view.recyclerview.**{*;}
-keep class com.sunsta.bear.view.activity.**{*;}
-dontwarn com.sunsta.bear.**

-keep class com.sunsta.bear.layout.** { *; }
-dontwarn com.sunsta.bear.layout.ucrop**
-keep class com.sunsta.bear.layout.ucrop** { *; }
-keep interface com.sunsta.bear.layout.ucrop** { *; }

#内部内
-keepclasseswithmembers class com.sunsta.bear.AnConstants$URL {
 <methods>;}
-keepclasseswithmembers class com.sunsta.bear.AnConstants$KEY {
 <methods>;}

-keep public class com.sunsta.bear.engine.gif.GifIOException{<init>(int);}
-keep class com.sunsta.bear.engine.gif.GifInfoHandle{<init>(long,int,int,int);}
```
## 常见错误# Easy Mistake

./gradlew processDebugManifest --stacktrace

### 非常重要1：1.1.x版本用androidx，替换掉所有的support-v4,v7包

由于```livery```基于```an-aw-base```，在版本```1.1.x```以后用```androidx```替换了所有的```support-v4，v7```等；如果你的项目已经包含了```v4，v7，```建议删除跟```v4，v7```的依赖，如不能删除，如下参考配置.
项目的根目录的build.gradle中添加，这样就可以忽略support相关的包引用问题
```Groovy
 configurations {
  all*.exclude group :'com.android.support',module:'support-compat'
  all*.exclude group :'com.android.support',module:'support-v4'
  all*.exclude group :'com.android.support',module:'support-annotations'
  all*.exclude group :'com.android.support',module:'support-fragment'
  all*.exclude group :'com.android.support',module:'support-core-utils'
  all*.exclude group :'com.android.support',module:'support-core-ui'
 }
```
或参考：
```Groovy
implementation('me.imid.swipebacklayout.lib:library:1.1.0') {
 exclude group: 'com.android.support'
}
```
### 非常重要2： Manifest merger failed : Attribute meta-data#android.support.FILE_PROVIDER_PATHS
这是File_provider冲突，修改AndroidManifest.xml文件.[参考](https://blog.csdn.net/lbqcsdn/article/details/84795775)
```XML
<provider
 android:name="android.support.v4.content.FileProvider"
 android:authorities="${applicationId}.fileprovider"
 android:exported="false"
 android:grantUriPermissions="true">
 <meta-data
  android:name="android.support.FILE_PROVIDER_PATHS"
  android:resource="@xml/gdt_file_path" />
</provider>

<provider
 android:name=".utils.BuglyFileProvider"
 android:authorities="${applicationId}.fileProvider"
 android:exported="false"
 android:grantUriPermissions="true"
 tools:replace="name,authorities,exported,grantUriPermissions">
 <meta-data
  android:name="android.support.FILE_PROVIDER_PATHS"
  android:resource="@xml/bugly_file_paths"
 tools:replace="name,resource" />
</provider>
```
### 非常重要3：This project uses AndroidX dependencies, but the 'android.useAndroidX' property is not enabled. Set this property to true in the gradle.properties file and retry.
在你的gradle.properties中加入：
>android.useAndroidX=true
如下参考配置：
```Groovy
org.gradle.jvmargs=-Xmx1536m
# org.gradle.parallel=true
android.useAndroidX=true
# Automatically convert third-party libraries to use AndroidX
android.enableJetifier=true
android.useDeprecatedNdk=true
```
### 注意事项1：Attribute application@theme value=(@style/AppTheme) from AndroidManifest.xml:11:9-40 is also present at [com.sunsta.livery:livery:1.1.13] AndroidManifest.xml

这是livery资源冲突，在你的AndroidManifest.xml application标签中添加（根据需要添加）：

>tools:replace="android:icon,android:theme,android:label,android:allowBackup,android:name"

也可以把application标签中的重复的删除
### 注意事项2： More than one file was found with OS independent path 'META-INF/rxjava.properties'
这是rxJava冲突，在app目录的build.gradle下添加
```Groovy
 packagingOptions {
exclude 'META-INF/rxjava.properties'
 }
```
### [更多：其它android中常见错误解决方法点击这里查看。](https://github.com/qydq/alidd-samples/blob/master/error.md)
## 版本日志# Version LOG

[**an-aw-base0.x.x版本log.**](https://github.com/qydq/an-aw-base/releases)
**⚠️注意**
>代码提交严格跟随日志内容，方便日后查阅相关记录，为控制字数；这里只记录1.0.x版本日志总述（[了解详细1.0.x版本日志点击这里](https://github.com/qydq/alidd-samples/blob/master/alidd_old_1.0.x.md)）和1.1.8之后的重要版本记录

### apk下载及其说明
&#8194;&#8194;[最新版本(demo_livery1.1.13.apk)](https://github.com/qydq/alidd-samples/raw/master/apk/demo_livery1.1.13.apk)

alidd-samples项目(project)概况：

| **livery版本** *`(日期)`*   |项目大小|apk大小  |livery发布release大小 |备注（版本大小变化说明）  |
| --------                   | ----:|-----:   |-----: |                :---- |
|**v1.0.10** *`(2019/12/31)`*| 180M |   7.5M  | 6.4M  |INATabLayout为例，推广引入`livery`首发-使用案例     |
|**v1.0.19** *`(2020/01/02)`*| 255M |   13.4M |12.2M  |1.包含了视频2个情景系列 和Gif加载动画<br/>2.引入了几张大       的资源gif图， 这是apk Size变大的原因    |
|**v1.1.10** *`(2020/03/26)`*| 259M |  16M    | 2M |1.包含了视频滤镜的module videobeauty<br/>2.包含导航栏，新增md设计   |
|**v1.1.13** *`(2020/05/20)`*| 240M |  16.9M   | 1.2M |1.针对资源文件进一步优化，移除大量的布局资源，进行统一分类，视频模块测试代码放开   |
|**v1.1.51** *`(2020/06/03)`*| 240M |  17.1M   | 603M | 1.1.51再次移除了一些非必须的资源文件，增加优化了已知问题，继承的体积再此压缩到566KB|
|**v1.1.111** *`(2020/06/03)`*| 230M |  17.1M   | 603M | 1.1.111优化了一些已知问题|

说明：alidd-samples项目，是为了方便开发者快速集成livery提供的源代码；demo_livery1.1.11.apk，后面的版本是属于livery情景框架的版本号，非demo版本号（当前demo_apk版本暂时只为1.0).
另：1.1.100以后的版本，交替执行，偶数表示优化当前1.1.x版本， 1.2.x版本为基数如101版本（beta=1.2.x）

### 1.0.x版本总述
livery的初始版本，从an-aw-base重构而来，livery框架1.0.x（及以下的版本）支持的android最低版本为，minSdkVersion=19，总共发布了20个实际版本，具体依赖方法如下：
```Groovy
implementation 'com.sunsta:livery:1.0.x'
```

1.0.x包含包含了support系列的库
```Groovy
appcompat : 'com.android.support:appcompat-v7:27.0.2',
constraint: 'com.android.support.constraint:constraint-layout:1.0.2',
design : 'com.android.support:design:27.0.2',
recyclerview : 'com.android.support:recyclerview-v7:27.0.2',

retrofit  : 'com.squareup.retrofit2:retrofit:2.3.0',
gson: 'com.squareup.retrofit2:converter-gson:2.1.0',
rxjava2: 'com.squareup.retrofit2:adapter-rxjava2:2.3.0',
okhttp : 'com.squareup.okhttp3:okhttp:3.8.1',
glide  : 'com.github.bumptech.glide:glide:4.3.1',

xutils : 'org.xutils:xutils:3.5.0',
glidecompiler: 'com.github.bumptech.glide:compiler:4.3.1',
multipleimageselect: 'com.darsh.multipleimageselect:multipleimageselect:1.0.4',
crop: 'com.soundcloud.android.crop:lib_crop:1.0.0',
advancedluban: 'me.shaohui.advancedluban:library:1.3.2',
nineoldandroids : 'com.nineoldandroids:library:2.4.0',
```
### 1.1.x
**⚠️特别注意**
**：`1.1.x`相比`1.0.x`版本，不再支持`support包`，并且最低版本升级到api=21，也就是说，为了控制性能，`livery`不再支持`android5.0`以下的系统**

**记录内容：**

* 1.1.x版本主要是添加androidx，移除升级修复减小体积，相关第三方库bug，完善稳定网络2请求，修改最低支持版本为21，也就是说livery在以后不再支持android5.0以下的手机.
* 所有的控件使用均不支持support.v4,v7这样的包(包含如RecyclerView等等)，代替的是androidx最新的库.
* 优化和移除takePhoto模块依赖的编译库，因为一些第三方库长久不更新，会导致一些问题，严重的可能出现崩溃，（如multipleimageselect 该库的作者未更新，导致更新glide后，在android8.0 ,9.0上存在兼容性问题）.
* 引入androidx.camera的测试版本，（此版本存在兼容性问题，如需引用androidx.camera特性，需要依赖1.1.1版本:并同时修改minSdkVersion=21）优化并且统一框架中的资源命名规范问题，涉及到字符串，颜色资源，属性定义，布局文件，类文件标准命名等等.
```
api "androidx.concurrent:concurrent-futures:1.0.0-rc01"
api "androidx.camera:camera-lifecycle:1.0.0-alpha01"
api "androidx.camera:camera-core:1.0.0-alpha08"
api "androidx.camera:camera-camera2:1.0.0-alpha05"
```
**具体依赖方法如下类似：**
```Groovy
implementation 'com.sunsta:livery:1.1.x'
```
## 其它说明
### 关于自定义apk名说明
```Groovy
#---------------------------3.(自定义apk)个人其它说明区-sunst（请关注知乎Bgwan）---------------------
// 便利所有的Variants，all是迭代遍历操作符，相当于for
applicationVariants.all { variant ->// 遍历得出所有的variant
 variant.outputs.all {// 遍历所有的输出类型，一般是debug和replease
  // 定义apk的名字，拼接variant的版本号
  def apkName = "app_${variant.versionName}"
  // 判断是否为空
  if (!variant.flavorName.isEmpty()) {
apkName += "_${variant.flavorName}"
  }
  // 赋值属性
  String time = new Date().format("_YYYYMMddHH")
  if (variant.buildType.name.equals("release")){
outputFileName = apkName + "_Replease" + time + ".apk"
  }else {
outputFileName = apkName + "_Debug" +time + ".apk"
  }

 }
}
```
### 关于应用内apk自动安装说明
```Groovy
#---------------------------4.(应用内apk安装)个人其它说明区-sunst（请关注知乎Bgwan）---------------------
private Intent getInstallIntent() {
String fileName = savePath + appName + ".apk";
Uri uri = null;
Intent intent = new Intent(Intent.ACTION_VIEW);
try {
if (Build.VERSION.SDK_INT >= 24) {//7.0 Android N
//com.xxx.xxx.fileprovider为上述manifest中provider所配置相同
uri = FileProvider.getUriForFile(mContext, "你自己的包名.fileprovider", new File(fileName));

intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//7.0以后，系统要求授予临时uri读取权限，安装完毕以后，系统会自动收回权限，该过程没有用户交互
} else {//7.0以下
uri = Uri.fromFile(new File(fileName));
intent.setAction(Intent.ACTION_VIEW);
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
}
intent.setDataAndType(uri, "application/vnd.android.package-archive");
startActivity(intent);
return intent;
} catch (IllegalArgumentException e) {
e.printStackTrace();
} catch (ActivityNotFoundException e) {
e.printStackTrace();
} catch (Exception e) {
e.printStackTrace();
}
return intent;
}
```
## 致谢
非常感谢以下前辈（or开源组织机构org）的开源精神，当代互联网的发展离不开前辈们的分享，Livery的成功发布也是.
再次感谢🙏。<br/>   最后感谢[github](https://github.com)优秀的代码管理平台（排名不分先后）
- [x] [致敬与缅怀-雷霄骅前辈](https://blog.csdn.net/leixiaohua1020 "雷霄骅")
- [x] [yalantis](https://www.runoob.com/w3cnote/android-ui-framework.html)
- [ ] [darsh2](https://github.com/darsh2/MultipleImageSelect)
- [x] [LuckSiege](https://github.com/LuckSiege/PictureSelector)
- [ ] [crazycodeboy](https://github.com/crazycodeboy/TakePhoto)
- [x] [ReactiveX RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [x] [ReactiveX RxJava](https://github.com/ReactiveX/RxJava)
- [x] [squareup retrofit](//20191128)
- [x] [squareup gson](//20191128)
- [x] [squareup retrofit2 adapter-rxjava2](//20191128)
- [x] [bumptech glide](https://github.com/bumptech/glide)
- [x] [罗升阳](https://blog.csdn.net/Luoshengyang "罗升阳")
- [x] [**郭霖**](https://blog.csdn.net/sinyu890807 "guolin")
- [x] [严振杰](https://blog.csdn.net/yanzhenjie1003 "严振杰")
- [x] [张鸿洋_](https://blog.csdn.net/lmj623565791 "鸿洋_")
- [ ] [一片枫叶](https://blog.csdn.net/qq_23547831 "一片枫叶_刘超")
## LICENSE
***[版权声明©️](https://zhuanlan.zhihu.com/p/80668416)***
```java
/*
 * Copyright (C) 2016 The Android Developer sunst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
```