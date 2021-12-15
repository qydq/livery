<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [Bgwan](https://zhihu.com/people/qydq)*

- [**android快速集成基础框架 - Livery```1.3.x```**](#android%E5%BF%AB%E9%80%9F%E9%9B%86%E6%88%90%E5%9F%BA%E7%A1%80%E6%A1%86%E6%9E%B6---livery13x)
  - [**情景能力# Ability**](#%E6%83%85%E6%99%AF%E8%83%BD%E5%8A%9B-ability)
    - [1：核心能力](#1%E6%A0%B8%E5%BF%83%E8%83%BD%E5%8A%9B)
    - [2：可选能力](#2%E5%8F%AF%E9%80%89%E8%83%BD%E5%8A%9B)
  - [**集成方式# Binaries**](#%E9%9B%86%E6%88%90%E6%96%B9%E5%BC%8F-binaries)
    - [2.（可选）手动集成](#2%E5%8F%AF%E9%80%89%E6%89%8B%E5%8A%A8%E9%9B%86%E6%88%90)
  - [**使用步骤# Use Step**](#%E4%BD%BF%E7%94%A8%E6%AD%A5%E9%AA%A4-use-step)
    - [第1步：配置主Application](#%E7%AC%AC1%E6%AD%A5%E9%85%8D%E7%BD%AE%E4%B8%BBapplication)
    - [第2步：配置AndroidManifest.xml](#%E7%AC%AC2%E6%AD%A5%E9%85%8D%E7%BD%AEandroidmanifestxml)
  - [**模块介绍# Details Module**](#%E6%A8%A1%E5%9D%97%E4%BB%8B%E7%BB%8D-details-module)
    - [（一）核心情景能力演示](#%E4%B8%80%E6%A0%B8%E5%BF%83%E6%83%85%E6%99%AF%E8%83%BD%E5%8A%9B%E6%BC%94%E7%A4%BA)
      - [1. 基础窗口类  **BaseActivity**](#1-%E5%9F%BA%E7%A1%80%E7%AA%97%E5%8F%A3%E7%B1%BB--baseactivity)
      - [2. 基础窗口类  **BaseFragment或ParallaxFragment**](#2-%E5%9F%BA%E7%A1%80%E7%AA%97%E5%8F%A3%E7%B1%BB--basefragment%E6%88%96parallaxfragment)
      - [3. 基础适配器类](#3-%E5%9F%BA%E7%A1%80%E9%80%82%E9%85%8D%E5%99%A8%E7%B1%BB)
      - [4. 基础网络请求](#4-%E5%9F%BA%E7%A1%80%E7%BD%91%E7%BB%9C%E8%AF%B7%E6%B1%82)
      - [5. 基础文件下载](#5-%E5%9F%BA%E7%A1%80%E6%96%87%E4%BB%B6%E4%B8%8B%E8%BD%BD)
    - [（二）可选情景能力演示](#%E4%BA%8C%E5%8F%AF%E9%80%89%E6%83%85%E6%99%AF%E8%83%BD%E5%8A%9B%E6%BC%94%E7%A4%BA)
      - [高效GIF加载](#%E9%AB%98%E6%95%88gif%E5%8A%A0%E8%BD%BD)
      - [漫天飞羽弹幕使（livery1.3.0暂不支持）](#%E6%BC%AB%E5%A4%A9%E9%A3%9E%E7%BE%BD%E5%BC%B9%E5%B9%95%E4%BD%BFlivery130%E6%9A%82%E4%B8%8D%E6%94%AF%E6%8C%81)
    - [（三）DEMO演示或效果图](#%E4%B8%89demo%E6%BC%94%E7%A4%BA%E6%88%96%E6%95%88%E6%9E%9C%E5%9B%BE)
  - [**混淆配置# proguard-rules**](#%E6%B7%B7%E6%B7%86%E9%85%8D%E7%BD%AE-proguard-rules)
  - [**常见错误# Easy Mistake**](#%E5%B8%B8%E8%A7%81%E9%94%99%E8%AF%AF-easy-mistake)
    - [非常重要1：1.1.x版本用androidx，替换掉了所有的support-v4,v7包.](#%E9%9D%9E%E5%B8%B8%E9%87%8D%E8%A6%81111x%E7%89%88%E6%9C%AC%E7%94%A8androidx%E6%9B%BF%E6%8D%A2%E6%8E%89%E4%BA%86%E6%89%80%E6%9C%89%E7%9A%84support-v4v7%E5%8C%85)
    - [非常重要2： Manifest merger failed : Attribute meta-data#android.support.FILE_PROVIDER_PATHS.](#%E9%9D%9E%E5%B8%B8%E9%87%8D%E8%A6%812-manifest-merger-failed--attribute-meta-dataandroidsupportfile_provider_paths)
    - [非常重要3：This project uses AndroidX dependencies, but the 'android.useAndroidX' property is not enabled. Set this property to true in the gradle.properties file and retry.](#%E9%9D%9E%E5%B8%B8%E9%87%8D%E8%A6%813this-project-uses-androidx-dependencies-but-the-androiduseandroidx-property-is-not-enabled-set-this-property-to-true-in-the-gradleproperties-file-and-retry)
    - [注意事项1：Attribute application@theme value=(@style/AppTheme) from AndroidManifest.xml:11:9-40 is also present at [com.sunsta.livery:livery:1.2.x] AndroidManifest.xml](#%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B91attribute-applicationtheme-valuestyleapptheme-from-androidmanifestxml119-40-is-also-present-at-comsunstaliverylivery12x-androidmanifestxml)
  - [**版本日志# Version LOG**](#%E7%89%88%E6%9C%AC%E6%97%A5%E5%BF%97-version-log)
    - [1.各个历史版本日志记录](#1%E5%90%84%E4%B8%AA%E5%8E%86%E5%8F%B2%E7%89%88%E6%9C%AC%E6%97%A5%E5%BF%97%E8%AE%B0%E5%BD%95)
    - [2.重要版本记录总述](#2%E9%87%8D%E8%A6%81%E7%89%88%E6%9C%AC%E8%AE%B0%E5%BD%95%E6%80%BB%E8%BF%B0)
    - [~~1.0.x版本总述 ~~](#10x%E7%89%88%E6%9C%AC%E6%80%BB%E8%BF%B0-)
    - [~~1.1.x版本总述 ~~](#11x%E7%89%88%E6%9C%AC%E6%80%BB%E8%BF%B0-)
    - [~~1.2.x版本总述 ~~](#12x%E7%89%88%E6%9C%AC%E6%80%BB%E8%BF%B0-)
    - [~~1.3.x版本总述 ~~](#13x%E7%89%88%E6%9C%AC%E6%80%BB%E8%BF%B0-)
  - [**其它说明# More**](#%E5%85%B6%E5%AE%83%E8%AF%B4%E6%98%8E-more)
    - [关于自定义apk名说明](#%E5%85%B3%E4%BA%8E%E8%87%AA%E5%AE%9A%E4%B9%89apk%E5%90%8D%E8%AF%B4%E6%98%8E)
    - [关于应用内apk自动安装说明](#%E5%85%B3%E4%BA%8E%E5%BA%94%E7%94%A8%E5%86%85apk%E8%87%AA%E5%8A%A8%E5%AE%89%E8%A3%85%E8%AF%B4%E6%98%8E)
  - [**致谢**](#%E8%87%B4%E8%B0%A2)
  - [**LICENSE**](#license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->
# **android快速集成基础框架 - Livery```1.3.x```**

![@sunst](https://avatars0.githubusercontent.com/u/20716264?s=60&u=ec068ee954f943483fbf1516803dcd5b77520ad3&v=4)[![Apache-2.0](http://img.shields.io/badge/license-Apache2.0-brightgreen.svg?style=flat)](https://github.com/qydq/alidd-sample/blob/main/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/com.github.qydq/livery.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.qydq%22%20AND%20a:%22livery%22) [![JCenter](https://img.shields.io/badge/%20JCenter%20-1.2.18-5bc0de.svg)](https://bintray.com/qydq/maven/livery/_latestVersion)  [![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2021%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=21) [![Release Version](https://img.shields.io/badge/release-1.3.2-red.svg)](https://github.com/qydq/small-video-record/releases) [![](https://img.shields.io/badge/Author-sunst0069-blue.svg)](https://www.zhihu.com/people/qydq)

[***中文API帮助文档1.3.x下载`密码:xeq0`)***](https://pan.baidu.com/s/1yczO3lh4p8Njc_rdb9Fe5g) 一款针对Android平台下快速集成**便捷开发**框架```Livery```，帮助开发者**架构企业级**应用.

基于[**`原an框架`**](https://github.com/qydq/an-aw-base)与[**`Livery1.2.x`**](https://github.com/qydq/livery/blob/main/old_livery_1.1.x.md)版本演化而来，针对此做了很多优化，当前优化后最新体积仅有868.4KB.
![](https://github.com/qydq/livery/blob/main/image/livery_size.png?raw=true)

**⚠️注意**

>* 由于livery1.3.x开始使用了dataBinding的方式并使用了kotlin，故Livery版本`1.3.x`与`1.2.x`不兼容，1.2.x可以继续使用.
>* `Livery`遵循`免费开源协议`，你可以在此`LICENSE`下使用，借鉴修改，如果能够对你有所帮助，那真是万幸.
>* 如果不满意请友好提出，注明错误的详细信息或修改建议，好的想法亦可直接提交至repo；如果你觉得实在是没用，也请你做一个有自我修养的人.
>* `Livery`一直维护，有问题提[**`issues`**](https://github.com/qydq/livery/issues)（或在[**`知乎Bgwan`**](https://www.zhihu.com/people/qydq)上给我留言，**问题描述清楚**就行]，一般修复好周7当晚更新.

`2021年12🈷️20日`当前Livery最新版本为：![Maven Central](https://img.shields.io/maven-central/v/com.github.qydq/livery.svg?label=Maven%20Central)，建议使用最新版本。查看[**`旧版本日志`**](#%E7%89%88%E6%9C%AC%E6%97%A5%E5%BF%97-version-log)也可以了解到更多的信息.

[**我的唯一知乎地址.**](https://www.zhihu.com/people/qydq/columns)&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;&#8194;（感谢关注🙏）

专注于物联网领域，世界的通信标准从今开始改变，手机也可以是路由器，成功于视频直播，标准并不一定是Http/s，也可以是Bluetooth.


## **情景能力# Ability**

`Livery`一路走来经历了很多版本，现在是一个非常成熟的`稳定版本`；它包含一些很实用的能力和技巧， 用简洁友好的方式，助力便捷开发；以下列举当前支持的能力fuction.<br/>

### 1：核心能力
- [x] 国际化多语言（**架构企业级**应用）.
- [x] 主题切换（夜间深色模式）.
- [x] 基础类，如：BaseActivity、BaseFragment、ParallaxFragment、BaseDialog、SmartPagerAdapter等.
- [x] 网络请求（☀️ mHttp独立结合`Retrofit与RxJava`完全解耦）.
- [x] 文件下载（☀️ mHttp独立结合`DownloaderAsyncTask结合`）.
- [x] 图库选择（☀️ 基于**`PictureSelector`**的图片选择库已移除）.
- [x] StrictMode API禁 权限便捷申请.
### 2：可选能力
- [x] 快速集成你的导航栏工具 .
- [x] INA系列控件相关，如标题栏，状态视图.
- [ ] ~~~漫天飞羽弹幕使（☀️ B站有了一个烈焰弹幕使、这里命名为：`漫天飞羽`）.  ~~~
- [x] 系列实用能力集，如MD5/AES加密，日志，屏幕尺寸，模糊算法，图片处理.
- [x] 工具类以及服务，数据校验，尺寸，图片处理，网络.
- [x] GIF图片效率更友好（GifImageView可以更快的加载Gif，效率可对比之前INAPowerImageView）.
- [x] Glide图片加载引擎GlideEngine（☀️ Glide版本为：4.12.0）.
- [ ] 正在开发ing...智能语音唤醒监听能力（世界上最美的就是声音Voice ）.

&#8194;&#8194;&#8194;&#8194;&#8194;[**最新体验扫描二维码下载livery-last.apk**](https://github.com/qydq/livery/blob/main/demo/livery-last.apk)&#8194;&#8194;&#8194;&#8194;

![](https://github.com/qydq/livery/blob/main/image/livery-last.png?raw=true)

## **集成方式# Binaries**

集成方式有三种  ，集成之前，先在你项目的根目录`build.gradle`文件`allprojects→repositories`属性下加入远程库地址.
```groovy
repositories {
        google()//顺序1
//        jcenter() // Warning: this repository is going to shut down soon in 2022/02/01
        maven { url 'https://jitpack.io' }//顺序2
		mavenCentral()//顺序3
        maven { url 'https://maven.aliyun.com/repository/releases' }//可选顺序4
    }
```
**⚠️注意**
>由于[**`Jcenter服务即将关闭`**](https://zhuanlan.zhihu.com/p/363156372)，请按照以上远程库的顺序配置.
### 1.（建议）通过maven集成
在你项目`app module`或`other module`的**`build.gradle`**中添加：（致谢[**sonatype**](https://www.sonatype.com)）.
```Groovy
dependencies {
  implementation'com.github.qydq:livery:1.3.2'
}
```
**⚠️注意**
>通过JCenter的方式集成已不再实用，故以implementation'com.sunsta.livery:livery:1.2.18' 这种方式集成，则废弃.

### 2.（可选）手动集成
[**下载Livery最新版本1.3.2**](https://repo1.maven.org/maven2/com/github/qydq/livery/1.3.2/)，然后将`AAR`拷贝到libs目录中添加引用关系.

```Groovy
dependencies {
  implementation(name:'livery-1.3.2', ext:'aar')
  implementation(name:'bear-1.3.2', ext:'aar')
}
```

## **使用步骤# Use Step**

### 第1步：配置主Application

使用`网络请求`，在你的`XxxApplication`中继承`AnApplication`，然后在`onCreate()`方法中调用如下代码
```kotlin
val token = AppHelper.getUserInfo.token
val BASE_URL = "https://default_platform_host.com/"
LiveryLibrary().addHeader(
    "Client-Version",
    "youwo-android-" + PackageUtils.getVersionName()
).addHeader("UUID", PackageUtils.getUUID()).setToken(token)
    .setService(ApiService.service)
    .addTokenExpiredListener { }
  .setDefaultPlatform(BASE_URL)
    .addErrorHandler().setPrintLog(true)
```
**⚠️注意**
> `BASE_URL`是符合Retrofit的网络请求地址，如：`https://api.github.com/`，需要以`/`结尾，如果请求网络需要验证登录则需要设置token，ApiService为解耦的服务，最后把`XxxApplication`添加到`AndroidManifest.xml`中.

### 第2步：配置AndroidManifest.xml

在集成`Livery`时，一般需要合并AndroidManifest，否则可能造成冲突.

```xml
<application
 android:name="com.livery.demo.XxxApplication"
 android:allowBackup="true"
 android:icon="@drawable/hong"
 android:label="@string/app_name"
 android:requestLegacyExternalStorage="true"
 android:roundIcon="@drawable/hong"
 android:supportsRtl="true"
 android:theme="@style/ParallaxAppTheme"
 tools:replace="android:theme,android:name">
```
**⚠️注意**
> 建议继承扩展Livery中的类和主题，不然有的特性，如夜间模式，网络或不可用.

## **模块介绍# Details Module**

这里```Details Module```介绍**部分**情景能力`(核心，可选，DEMO演示)`的使用方法，更多`Livery情景`使用可以查看中文API帮助文档1.3.x，或在本人知乎[**`an情景专栏`**](https://www.zhihu.com/column/sunst)中获取.
![](https://github.com/qydq/livery/blob/main/image/livery_api.png?raw=true)


**⚠️注意**
> * `hong1.3.x.apk`为提供的安装包(可以扫描前面的二维码下载)，如果实际项目用到上传图片的功能，建议使用，PictureSelector第三方的图片框架，并建议尽量少的依赖其它库来完成your project.

### （一）核心情景能力演示

#### 1. 基础窗口类  **BaseActivity**
这里以一个简单启动页面为例：kotlin Code
```kotlin
@ActivityNoTitle
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
 private var model: SplashViewModel? = null
 override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
    override fun initView(bundle: Bundle?) {
        model = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding?.ivSplash?.animate()?.alpha(1f)?.setDuration(2000)
            ?.setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }
                override fun onAnimationEnd(animation: Animator) {
                    if (successLogin) {
                        startActivity(Intent(mThis, MainActivity::class.java))
                    } else {
                        LoginActivity.startAction(mThis)
                        finish()
                    }
                }
                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })?.start()
        binding?.tvProduct?.animate()?.alpha(0.5f)?.setDuration(500)?.start()
        // do some your work
    }
}
```
布局文件：activity_splash.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data />
    <FrameLayout
  android:layout_width="match_parent"
  android:layout_height="match_parent">
        <ImageView
  android:id="@+id/ivSplash"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:scaleType="centerCrop"
  android:src="@mipmap/em_splash_bg" />
        <TextView
  android:id="@+id/tvProduct"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:layout_gravity="center_horizontal"
  android:layout_marginTop="80dp"
  android:letterSpacing="0.2"
  android:text="@string/app_product_name"
  android:textColor="@color/white"
  android:textSize="@dimen/an_font_large" />
        <TextView
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:layout_gravity="bottom|center"
  android:layout_marginBottom="80dp"
  android:letterSpacing="0.2"
  android:text="@string/app_copyright"
  android:textColor="@color/white"
  android:textSize="@dimen/an_font_level3" />
    </FrameLayout>
</layout>
```
**⚠️说明**
>如上，ActivityNoTitle表示此窗口无标题，通过binding对象的使用，我们不再写findViewById，当然你也可以不使用dataBinding，就按照正常的写法也是一样的。

#### 2. 基础窗口类  **BaseFragment或ParallaxFragment**
这里以一个简单的聊天页面为例：kotlin Code
```kotlin
class ImImFragment : BaseFragment<MainFragmentImimBinding>() {
    companion object {
        fun instance(data: String): ImimFragment {
            val fragment = ImimFragment()
            val bundle = Bundle()
            bundle.putString("deptCode", data)
            fragment.arguments = bundle
			return fragment
		 }
    }
    override fun getLayoutId(): Int {
        return R.layout.main_fragment_imim
  }
    override fun initView(savedInstanceState: Bundle?) {
    }
}
```
布局文件：main_fragment_imim.xml
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data />
    <RelativeLayout
  android:id="@+id/llHeadView"
  android:layout_width="match_parent"
  android:layout_height="match_parent">
        <TextView
  android:id="@+id/content"
  style="@style/Text1.Large"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:layout_centerInParent="true"
  android:autoLink="all"
  android:lineSpacingMultiplier="1.5"
  android:padding="10dp"
  android:text="聊天，通讯录"
  android:textColorHighlight="@color/an_font_edit_cursor"
  android:textColorHint="@color/anTextColor"
  android:textColorLink="@color/an_font_mark"
  android:textIsSelectable="true" />
    </RelativeLayout>
</layout>
```

**⚠️说明**

>* 与BaseActivity使用方式类似，同样BaseFragment也支持dataBinding，ParallaxFragment表示此启动了懒加载。类似的还有BaseDialog，这里不再介绍。

#### 3. 基础适配器类
比如一个列表我们定义基础的适配器类，同样支持dataBinding操作.
```kotlin
override fun initView(savedInstanceState: Bundle?) {
    for (i in 1..17) {
        val mode = LevelMode()
        mode.title = "成都市双流区" + i + "号"
  data.add(mode)
    }
    mAdapter = MyAdapter()
    binding?.recyclerView?.adapter = mAdapter
 mAdapter.setOnItemClickListener { _, _, position -> toast(data[position].title) }
}

inner class MyAdapter : SmartRecyclerAdapter<ItemMallServiceBinding, LevelMode,
        BaseDataBindingHolder<ItemMallServiceBinding>>(R.layout.item_mall_service, data) {
    override fun convert(
        binding: ItemMallServiceBinding?,
        holder: BaseDataBindingHolder<ItemMallServiceBinding>,
        item: LevelMode
  ) {
        binding?.tvLevel1?.text = item.title
  }
}
```
#### 4. 基础网络请求

以Github Api请求qydq示例：https://api.github.com/users/qydq?page=1&per_page=50

返回的json数据为：
```json
{
 "login": "qydq",
 "id": 20716264,
 "node_id": "MDQ6VXNlcjIwNzE2MjY0",
 "avatar_url": "https://avatars.githubusercontent.com/u/20716264?v=4",
  "url": "https://api.github.com/users/qydq",
  "html_url": "https://github.com/qydq",
  "name": "晴雨荡气",
  "company": "qydq",
  "blog": "https://zhihu.com/people/qydq",
  "location": "SiChuan Of ChengDu from China",
  "bio": "an情景系列Livery Android作者，软件开发工程师，致力于物联网，人工智能研究"
}
```
**～第1步：定义一个实体类`QydqGithubUserRes.java`**
```java
public class QydqGithubUserRes implements Serializable {
    private static final long serialVersionUID = -4362168516197876328L;
    private int id;
    private String login;
    private String node_id;
    private String avatar_url;
    private String url;
    private String html_url;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String bio;

    //getXX and setXX method
  ...
}
```
**～第2步：创建格式（[`具体格式点击参考`](https://zhuanlan.zhihu.com/p/141592512)）为rxJava的网络请求接口（这里命名为ApiService）实现**

```kotlin
 interface ApiService {
    @GET("https://api.github.com/users/qydq")
    fun getGithubqydq1(@Query("page") page: Int, @Query("per_page") per_page: Int): Observable<QydqGithubUserRes>

    @GET("users/qydq")
fun getGithubqydq2(@QueryMap params： Map<String, Object>):Observable<QydqGithubUserRes>

    @GET("users/qydq")
fun getGithubqydq3(@Body req:QydqGithubUserReq):Observable<QydqGithubUserRes>

@POST("/users/qydq/post/samples")
    fun getHomePage(@Body req: SomethingReq): Observable<BaseResponse<List<SomethingList>>>


    //多图片上传
  @Multipart
 @POST("/api/test/sunsta/upload")
    fun uploadFiles(@PartMap map: Map<String, RequestBody>): Observable<ResponseBody>

    // 视频上传
  @Multipart
 @POST("/api/test/sunsta/uploadMovieFiles")
    fun uploadMovieFiles(@Part file: MultipartBody.Part, @Part params: MultipartBody.Part): Observable<ResponseBody?> //@PartMap Map<String, RequestBody> params

  companion object {
//        val service = LiveryLibrary.createService(ApiService::class.java, BASE_URL, listOf(PostInterceptor))
  val service = LiveryLibrary.createService(ApiService::class.java, BuildConfig.BASE_URL)
//        val service1 = LiveryLibrary.createService(ApiService::class.java)
  fun initParams() {
            //场景1
  var orderParams = OrderParams().page(1).pageSize(10)
            //场景2
  var orderParams1 = OrderParams().page(1).pageSize(10).park("ssds")

            //
  var ooo = KOrderParams().park("").page("")
            KParkParams().park("")
        }
    }
}
```
**⚠️注意**
> 建议在XxxApplication配置请求域既BASE_URL，如果在ApiService中没有指定全路径，则会拼接请求地址
> SomethingReq为请求参数，SomethingRes为请求响应的参数，QydqGithubUserRes为响应参数，以上以GET和POST作为演示。

**～第3步：请求网络**

这里先以GET请求示例，调用上面`getGithubqydq2`方法（调用POST请求类似，参考`第2步`定义的接口，使用本案例直接调用`getHomePage`方法），先模拟请求参数，如下参考：

```java
Map<String, Object> params = new HashMap<>();
params.put("page", 1);
params.put("per_page", 50);
```

在基础窗口类  **BaseFragment或ParallaxFragment，BaseActivity，直接使用下面接偶方式请求网络

```kotlin
mHttp.post("getGithubqydq3")
mHttp.post(QydqGithubUserReq("page=1","per_page=50"))
mHttp.of(QydqGithubUserReq::class.java).subscribe {
toast(it.toString())
}
```
当然在其它任何地方也可以用以下方法

```java
ApiService.service.getGithubqydq2(params)
        .subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<QydqGithubUserRes>() {
            @Override
 public void accept(QydqGithubUserRes reply) throws Exception {
                if (reply != null) {
                    toast(reply.toString())
  } else {
                    toast("成功，但数据为空");
                }
            }
        });
```
如果**确定需要**捕获异常信息（如`连接超时`，`500`，`400`，`JSON解析异常`），在BaseActivity，或BaseFragment的onError(message:String)直接监听接口请求失败的场景
```kotlin
override fun showError(error: String) {
    DialogPro.Builder()
        .setCancelable(false)
        .setMessage(error)
        .setNegativeButton("取消")
        .setNegativeTextColor(R.color.an_font_level1)
        .setPositiveTextColor(R.color.font_level3)
        .setPositiveButton("返回") {
 it.dismiss()
//do some your work
        }.build().show(mThis)
}
```
**⚠️说明**
> post使用是一样的，如上面getHomePage我们可以直接mHttp.post("getHomePage")，或者mHttp.post(SomethingReq)
> 默认post方法请求会自动启动一个loadding框，同样Livery提供了sliencePost方法，则不会有加载框，另外如果不想自动解析网络请求的数据，也可以调用orgin方法，既原始original方法，后台返回什么就是什么。。。

下图是它们直观的区别
![](http.png)

除此之外，如果我们是在Activity窗口中主动拉起showLoading()，则在网络请求的时候则不会再显示Loadding，同样关闭Loading则需要我们主动关闭。


#### 5. 基础文件下载

核心是DownloaderAsyncTask结合mHttp，支持**断点续传**（默认开启），单个\多个**文件下载**，暂停\取消，数据库状态持久化，下载完成通知提醒（可选），支持apk文件自动安装.

这里以同时下载youwo.apk更新版本作为参考

```kotlin
                                if (!TextUtils.isEmpty(mod.versionUrl)) {
                                    val downloadRops = DownloadRops(mod.versionUrl)
                                    downloadRops.autoOpen = true
//                                    downloadRops.canCancel = true
//                                    downloadRops.foldType = 1
//                                    downloadRops.openNotification = true
//                                    mHttp.serviceDownload(mThis, rops, downloadReceiver);
  val dialogRops = DialogRops(1)
                                    dialogRops.title = "正在下载..."
  dialogRops.cancelable = false
  dialogRops.titleGravity = Gravity.LEFT
  dialogRops.downloadRops = downloadRops
  mHttp.download(mThis, dialogRops)
                                } else {
                                    toast("未检测到请求服务器的地址.")
                                }
```
**⚠️注意**
> * 下载是一个耗时的过程，livery独立配置了异步任务/后台线程（可选）执行下载过程，这里不会阻塞UI.
> * Livery构造了一个下载成功以后将前台service关闭，并且创建一个下载通知（默认是关闭的），只需要在`ResponseDownloader`中开启即可启动一个通知功能.
> * 建议在下载的时候给一个下载文件的ID，不给id也可下载文件.
> * 文件下载成功默认保存的路径为：sdcard/Aliff/相应的目录文件中，如果没有读写文件权限则会自动保存到包名下面.
> * Livery1.3.0下载文件区别于1.2.x版本，使用更加简单，只需要在activity或fragment中调用mHttp.download()即可完成下载，下载完成以后会自动处理，也可以你主动监听，而不用管内部的实现.

### （二）可选情景能力演示
#### 高效GIF加载
```XML
<com.sunsta.bear.engine.gif.GifImageView
 android:id="@+id/gifImageView"
 android:layout_width="match_parent"
 android:layout_height="match_parent"
 android:src="@mipmap/gif"/>
```
循环播放
GifImageView默认播放一次就停止了，我们可以通过GifImageView获取GifDrawable，然后再通过GifDrawable设置循环播放的次数，或者设置无限循环播放
```JAVA
GifImageView gifImageView = findViewById(R.id.gifImageView);
GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
gifDrawable.setLoopCount(5); //设置具体播放次数
gifDrawable.setLoopCount(0); //设置无限循环播放
```
网络图片
```JAVA
String url = "https://p6-tt-ipv6.byteimg.com/origin/pgc-image/596f8546221046c2a394ced23120e3d8";
gifImageView.setImageUrl(url);
```
#### 漫天飞羽弹幕使（livery1.3.0暂不支持）

```XML
<com.sunsta.bear.layout.INABarrageView
 android:id="@+id/barrageView"
    android:layout_width="match_parent"
    android:layout_height="300dp"
    android:layout_marginTop="100dp"
    android:background="#FF4081"
    app:anBarrageGravity="center"
    app:anBarrageFloatTime="4000"
    app:anBarrageItemGap="15dp"
    app:anBarrageRowNum="1"
    app:anBarrageSpeed="500"
    app:asBarrageFly="true"
    app:asBarrageeFloat="true" />
```

**支持的属性（也可通过Java代码动态配置）**

| XML中属性 | 含义 |
|:-----------|:-----------|
|anBarrageRowNum |//设置弹幕有多少行，默认1行弹幕|
|anBarrageRowHeight |//设置弹幕的高度，默认39dp（当覆盖布局文件中的高度时，会使用xml中的布局高度）|
|anBarrageSpeed |//设置弹幕的滚动的速度 ，默认8000秒|
|anBarrageItemGap |//设置弹幕与弹幕之间的间距，默认10dp|
|anBarrageRowGap |//设置弹幕与弹幕之间的行距，默认2dp|
|anBarrageMode |//设置弹幕的布局方式，默认正常(normal)平均(average)|
|anBarrageGravity |//设置弹幕内容，相对于布局INABarrageView的方向， 默认default，则有可能从上到下显示top优先上半部分区域，center中间区域，bottom底部区域|
|anBarrageDuration |// 保留字段，不可用，或无效|
|anBarrageRepeatCount |//设置动画重复的次数，默认0，设置为：ValueAnimator.INFINITE 则该弹幕会无限循环播放|
|anBarrageFloatSpeed |//弹幕悬浮边缘的向左废除的速度|
|anBarrageFloatTime |//弹幕悬浮边缘的停留的时间|
|anBarrageIdleTime |//配置弹幕的空闲时间，注意空闲时间要比一条弹幕的动画时间长|
|asBarrageeFloat |//当弹幕悬浮边缘,开启向弹性向左飞出效果|
|asBarrageAuto |//设置弹幕自动播放，默认false|
|asBarrageFly |//设置弹幕是否可以漫天飞羽，默认true|
|asKeepSequence |//当漫天飞羽时，设置是否保持先后顺序，默认false表示弹幕在布局INABarrageView内随机没有顺序的，不分先后出现，true表示弹幕每次显示一行，当第一行显示完再显示第二行（当设置弹幕行数大于1（超过默认行数），则漫天飞羽关闭）|
|asBarrageInterpolator |//启用设置差值器，（保留字段）动画持续的时间，不同宽度的物体，划过同一个窗口，规定了总时间，以此获取对应的速度|

**漫天飞羽弹幕特效**

弹幕显示的核心来自Livery的`BarrageDataAdapter`提供，显示一个`满天飞羽弹幕`首先需要获取`BarrageDataAdapter`实例，如下参考：

```java
BarrageDataAdapter mBarrageAdapter = inaBarrageView.obtainBarrageAdapter(this);
```
**一个简单的列子**
```java
private void showDanMu() {
    Barrage barrage = new Barrage(BarrageDataAdapter.BarrageType.IMAGE_TEXT);
    barrage.setPrimaryIvId(R.drawable.hong);
    barrage.setContent("欢迎进入Bgwan的直播间，请多多关注哟~");
    mBarrageAdapter.addBarrage(barrage);
}
```
Barrage会要求传入一个type参数（IMAGE_TEXT图片文字组合弹幕，TEXT纯文字弹幕）

**一个复杂的效果**

漫天飞羽弹幕使支持许多自定义属性，比如：弹幕的layout，自适应文字长度，弹幕背景颜色，头像，甚至是动画，包括XML中的所有属性，这样可以加一些逻辑就可以做到动态配置每个弹幕行为，下面是一个复杂的弹幕.

```java
private void showDanMu2() {
    Barrage barrage = new Barrage(BarrageDataAdapter.BarrageType.IMAGE_TEXT);
    barrage.setGifIvId(R.mipmap.gif1);
    barrage.setGifIvId2(R.mipmap.gif2);
    barrage.setGifIvId3(R.mipmap.gif3);
    barrage.setUserName("sunst0069");
    barrage.setBarrageLayout(R.layout.an_item_barrage);//弹幕的layout，可以自定义
  barrage.setBackground(R.mipmap.image_select);
    barrage.setContent("亲爱的热爱的进入了你的房间");
    barrage.setBarrageFloat(true);
    barrage.setBarrageFloatSpeed(1800L);
//    mBarrageAdapter.addBarrage(barrage);//显示弹幕方法1（默认）
  mBarrageAdapter.addBarrage(barrage, new OnBarrageLayout() {
        @Override
 public View barrageLayout(View layoutView, Barrage barrage) {
            return mBarrageAdapter.loadBarrageData(layoutView, barrage);
        }
    });//显示弹幕方法2（该效果和方法1等同效果）
}
```
为了增加开发者控制每一条弹幕（不管Barrage属性里面有值的情况），提供以下操作
```java
mBarrageAdapter.addBarrage(barrage, new OnBarrageLayout() {
    @Override
 public View barrageLayout(View layoutView, Barrage barrage) {
        BarrageItemView barrageItem = mBarrageAdapter.getBarrageItemView(layoutView, barrage);
        //barrageItem.getXXX().setXXX()
  return barrageItem.getConverView();//显示弹幕方法3（每条弹幕单独控制）
  }
});
```
在遵循`an_item_barrage.xml`控件命名的情况下，通过barrageItem对象能够获取每一个弹幕布局的控件.


**漫天飞羽弹幕事件**

弹幕点击事件：
```java
mBarrageAdapter.setBarrageClickListener(new OnBarrageClickListener() {
    @Override
 public void onClick(Barrage barrage) {
        ToastUtils.s(BarrageActivity.this, "点击:" + barrage.getType());
    }
});
```

弹幕空闲（弹幕空闲Nms后回调，在此函数设置监听）
```java
inaBarrageView.setIdleListener(new OnBarrageIdleListener() {
    @Override
 public void onIdle(long idleTimeMs, INABarrageView view) {
        LaLog.d("hong---弹幕空闲时间，idleTimeMs = " + idleTimeMs + "view=" + view.toString());
    }
});
```
**⚠️特别注意**
> * 弹幕的属性通过xml，java，Barrage设置优先级：Barrage > java > xml，即Barrage中优先级最高.
> * 漫天飞宇弹幕使在`livery1.2.0`之后开始支持设置**3张Gif**，配合自定义layout可以实现更多更炫的特效.
> * **自定义弹幕布局**时控件的命名应与livery中`an_item_barrage.xml`一致，否则在调用`mBarrageAdapter.addBarrage()`中需要你自己去findViewById()（除非livery提供的特效不能满足需求）.

### （三）DEMO演示或效果图
部分`demo_livery1.3.0.apk`效果图可以扫描前面的demo二维码体验，包含了livery情景能力，**在我的知乎可以获取更多实用的技巧.**

以下为部分效果图：

## **混淆配置# proguard-rules**
混淆规则一定要看：[**Android App代码混淆解决方案click**](https://zhuanlan.zhihu.com/p/34559807)
```BASH
#---------------------------4.(反射实体)个人指令区-qy晴雨（请关注知乎Bgwan）---------------------
# livery 1.2.x
-keep class com.sunsta.bear.view.activity.**{*;}
-keep class androidx.support.widget.helper.**{*;}
-keep class com.sunsta.bear.**{*;}
-keep class com.sunsta.very.**{*;}
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
## **常见错误# Easy Mistake**
```xml
./gradlew processDebugManifest --stacktrace
```
### 非常重要1：1.1.x版本用androidx，替换掉了所有的support-v4,v7包.
由于谷歌`android版本开发库`更新升级，`livery`在版本`1.1.x`以后用`androidx`替换了所有的`support-v4，v7`等，如果你的项目已经包含了```v4，v7，```建议删除跟```v4，v7```的依赖，如不能删除，请降低使用`livery1.1.0`之前的版本，或参考如下配置.

在项目的根目录的build.gradle中添加，这样就可以忽略support相关的包引用问题
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
### 非常重要2： Manifest merger failed : Attribute meta-data#android.support.FILE_PROVIDER_PATHS.
这是`FileProvider`冲突，修改AndroidManifest.xml文件，（[**`参考：`**](https://blog.csdn.net/lbqcsdn/article/details/84795775)）.
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
在你项目的gradle.properties配置文件中加入
```xml
android.useAndroidX=true
```
**参考配置：**
```Groovy
org.gradle.jvmargs=-Xmx1536m
# org.gradle.parallel=true
android.useAndroidX=true
# Automatically convert third-party libraries to use AndroidX
android.enableJetifier=true
android.useDeprecatedNdk=true
```
### 注意事项1：Attribute application@theme value=(@style/AppTheme) from AndroidManifest.xml:11:9-40 is also present at [com.sunsta.livery:livery:1.2.x] AndroidManifest.xml

即`AndroidManifest``资源冲突，在你的AndroidManifest.xml application标签中添加（根据需要添加）.
```xml
tools:replace="android:icon,android:theme,android:label,android:allowBackup,android:name"
```
**⚠️提示**
>也可以删除`application`标签中重复的资源属性.
### 注意事项2： More than one file was found with OS independent path 'META-INF/rxjava.properties'
这是rxJava冲突，在app目录的build.gradle下添加
```Groovy
 packagingOptions {
   exclude 'META-INF/rxjava.properties'
  }
```
### [更多：其它android中常见错误解决方法点击这里查看。](https://github.com/qydq/livery/blob/main/error.md)

## **版本日志# Version LOG**
`Livery`框架AAR`与`初始APP或DEMO版本`日志记录

| **版本** *`(日期)`*   |项目|APK  |AAR`release` |备注（版本大小演变说明） |
| --------                   | ----:|-----:   |-----: |                :---- |
|**~~v0.0.69~~** *`(2018/06/09)`*|#|#|#|`18年`初始版本为**小团子芳儿**开发的一款应用级APP**|
|**~~v1.0.1~~** *`(2019/12/31)`*| 180M |   7.5M  | 6.4M  |[推广引入`livery`首发-INATabLayout使用案例](https://zhuanlan.zhihu.com/p/100098139)|
|**~~v1.0.20~~** *`(2020/01/02)`*|255M|13.4M|12.2M|新版本新增视频 ,`livery`引入GIF引擎动画;加入了几张超大资源gif图，这是造成APK，AAR体积变大的直接原因|
|**~~v1.1.10~~** *`(2020/03/26)`*|259M|16M|2M|新提供引入videobeauty,xmediac,xrecord**视频录制**压缩上传**C语言**功能;`livery`引入导航栏，提供MD的UI设计风格，移除超大资源GIF图，这也是AAR体积减小的直接原因|
|**v1.1.111** *`(2020/12/30)`*|240M|17.1M|603KB|`livery`针对资源文件进一步优化，移除大量的布局资源和一些非必须的资源文件，进行统一分类，**视频模块测试代码放开**，修复已知问题；**并且引入**了`漫天飞羽弹幕使`，AAR的体积没增加却再次减小到566KB;本项目完成`漫天飞羽弹幕使`**使用案例**|
|**v1.2.0** *`(2020/06/03)`*| 230M |  17.1M   | 586KB | 1.2.0版本开始支持**架构企业级**应用，国际化支持`(简体中文rCN，繁体中文rTW，英文en，德语de，日文ja，葡萄牙pt)`，**视频模块测试代码关闭**，修复了已知问题|
|**v1.2.69** *`(2021/05/20)`*| 210M |  16.1M   | 686KB | 1.2.69版本为1.2.x系列的最后一个版本|
|**v1.3.0** *`(2021/12/15)`*| 569M |  30.1M   | 867.4KB | 1.3.0版本支持jitpack的dataBinding，并且支持了kotlin的语言适配，这是一个全新的版本，不兼容1.2.x系列|

**⚠️说明**
* 初始`APP0.0.69`为小团子开发的hong项目，项目的灵感来源.
* 本samples为`Livery框架`提供的DEMO，方便开发者使用的参考.
* 表格中版本以此类推，如：`(1.0.1-1.0.20)`为一组表示`1.0.x`发行了`20`个版本，`1.0.1`为**起始版本**,`1.0.20`为**最高版本**，同时~~删除线~~表示此版本废弃.

### 1.各个历史版本日志记录

* [**版本an-aw-base0.x.x日志记录log.**](https://github.com/qydq/an-aw-base/releases)
* [**版本1.0.x日志记录log.**](https://github.com/qydq/livery/blob/main/old_alidd_1.0.x.md)
* [**版本1.1.x日志记录log.**](https://github.com/qydq/livery/blob/main/old_livery_1.1.x.md)
* [**版本1.2.x日志记录log.**](https://github.com/qydq/livery/blob/main/old_livery_1.2.x.md)

**⚠️注意**
>代码提交严格跟随日志内容，方便日后查阅相关记录，为控制字数；这里只记录Livery版本日志总述（可以点击以上旧版本log了解详细）和1.1.8之后的重要版本记录.

### 2.重要版本记录总述
以下记录各个版本发行记录日志简介

### ~~1.0.x版本总述 ~~
`Livery`的**发行版本**，从基础的an-base仓库([#原an框架](https://github.com/qydq/an-aw-base))重构而来，livery框架1.0.x（及以下的版本）支持的android最低版本为`minSdkVersion=19`，总共发行了`20个实际版本`，依赖方法如下：
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
### ~~1.1.x版本总述 ~~
**`1.1.x`相比`1.0.x`版本，不再支持`support包`，为了控制性能，最低版本`minSdkVersion=21`，也就是说，`livery1.1.x`不支持`android5.0`以下的系统集成** .

  **⚠️特别注意（记录内容）**
* 1.1.x版本主要是添加androidx，移除升级修复减小体积，相关第三方库bug，完善**稳定网络请求**，修改最低支持版本为21，也就是说livery在以后不再支持android5.0以下的手机.
* 所有的控件使用均不支持support.v4,v7这样的包(包含如RecyclerView等等)，代替的是androidx最新的库.
* 优化和移除takePhoto模块依赖的编译库，因为一些第三方库长久不更新，会导致一些问题，严重的可能出现崩溃，（如multipleimageselect 该库的作者未更新，导致更新glide后，在android8.0 ,9.0上存在兼容性问题）.
* 引入**androidx.camera**的测试版本，（此版本存在兼容性问题，如需引用androidx.camera特性，依赖1.1.x版本并同时修改minSdkVersion=21）优化并且**统一框架中的资源命名**规范问题，涉及到字符串，颜色资源，属性定义，布局文件，类文件标准命名等等.
```
api "androidx.concurrent:concurrent-futures:1.0.0-rc01"
api "androidx.camera:camera-lifecycle:1.0.0-alpha01"
api "androidx.camera:camera-core:1.0.0-alpha08"
api "androidx.camera:camera-camera2:1.0.0-alpha05"
```
**具体依赖方法如下类似**
```Groovy
implementation 'com.sunsta:livery:1.1.x'
```
### ~~1.2.x版本总述 ~~
1.2.x于1.1.x相比更新了许多最新的库文件，比如glide版本，另外由于jcenter关闭了，项目的迁移到maven，依赖的规则也发生了变化.

### ~~1.3.x版本总述 ~~
本篇内容即为Livery1.3.x的发行中文帮助文档总述.

## **其它说明# More**
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

## **致谢**
非常感谢以下前辈（or开源组织机构）的开源精神，当代互联网的发展离不开前辈们的分享，Livery的成功发布也是.  <br/> 再次感谢🙏。最后感谢优秀的[Github](https://github.com)代码管理平台（排名不分先后） .
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

## **LICENSE**
***[版权声明©️](https://zhuanlan.zhihu.com/p/80668416)***