## 错误1[已解决ok]：java.lang.NoClassDefFoundError: Failed resolution of: Landroidx/multidex/MultiDex;

multidex多方法数量限制，需要在build.gradle中引入如下内容：（livery:最新版本已解决）

implementation 'androidx.multidex:multidex:2.0.1'

或者检查base框架引入版本，如果你引用的是livery:1.1.7之前的版本，则需要升级最新版本，该问题即可解决

需要修改其他模块：

implementation 'com.android.support:multidex:1.0.3' 为：implementation 'androidx.multidex:multidex:2.0.1'

备注：最新版本已包含multidex，所以无需再在自己的build.gradle中重复引用.

## 错误2[已回复]：meta-data#android.support.FILE_PROVIDER_PATHS@resource value=(@xml/file_paths) from AndroidManifest.xml:84:17-51
is also present at [com.sunsta.livery:livery:1.1.6] AndroidManifest.xml:41:17-68 value=(@xml/base_fileprovider_takephoto)

>Manifest merger failed with multiple errors, see logs

这个错误我们会经常遇到，但是日志信息不够，我们需要在终端下输入如下命令：

```
gradlew processDebugManifest -stacktrace
```

就会看到更多具体报错的信息：总的来说需要统一处理，AndroidManifest.xml问题

```
C:\Users\sunst\public\native\2020\fire18\app\src\main\AndroidManifest.xml:23:5-81 Warning:
Element uses-permission#android.permission.WRITE_EXTERNAL_STORAGE at AndroidManifest.xml:23:5-81 duplicated with element declared at AndroidManifest.xml:9:5-81
C:\Users\sunst\public\native\2020\fire18\app\src\main\AndroidManifest.xml:24:5-80 Warning:
Element uses-permission#android.permission.READ_EXTERNAL_STORAGE at AndroidManifest.xml:24:5-80 duplicated with element declared at AndroidManifest.xml:10:5-80
C:\Users\sunst\public\native\2020\fire18\app\src\main\AndroidManifest.xml:25:5-78 Warning:
Element uses-permission#android.permission.SYSTEM_ALERT_WINDOW at AndroidManifest.xml:25:5-78 duplicated with element declared at AndroidManifest.xml:21:5-78
C:\Users\sunst\public\native\2020\fire18\app\src\main\AndroidManifest.xml:28:9-51 Error:
Attribute application@name value=(com.wewin.fire18.base.MyApp) from AndroidManifest.xml:28:9-51
is also present at [com.sunsta.livery:livery:1.1.6] AndroidManifest.xml:27:9-53 value=(com.sunsta.bear.AnApplication).
Suggestion: add 'tools:replace="android:name"' to <application> element at AndroidManifest.xml:27:5-420:19 to override.
C:\Users\sunst\public\native\2020\fire18\app\src\main\AndroidManifest.xml:79:13-60 Error:
Attribute provider#androidx.core.content.FileProvider@authorities value=(com.wewin.fire18.provider) from AndroidManifest.xml:79:13-60
is also present at [com.sunsta.livery:livery:1.1.6] AndroidManifest.xml:36:13-64 value=(com.wewin.fire18.fileprovider).
Suggestion: add 'tools:replace="android:authorities"' to <provider> element at AndroidManifest.xml:77:9-85:20 to override.
C:\Users\sunst\public\native\2020\fire18\app\src\main\AndroidManifest.xml:84:17-51 Error:
Attribute meta-data#android.support.FILE_PROVIDER_PATHS@resource value=(@xml/file_paths) from AndroidManifest.xml:84:17-51
is also present at [com.sunsta.livery:livery:1.1.6] AndroidManifest.xml:41:17-68 value=(@xml/base_fileprovider_takephoto).
Suggestion: add 'tools:replace="android:resource"' to <meta-data> element at AndroidManifest.xml:82:13-84:54 to override.

See http://g.co/androidstudio/manifest-merger for more information about the manifest merger.

```

以上信息就可以直观的看出解决防范：替换如下属性：
（1）android:resource
（2）android:authorities
（3）android:name

备注：livery同时需要修改：file_paths照片权限问题.

## 错误3[参考stackoverflow]：java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String android.content.Context.getPackageName()' on a null object reference

解决办法参考:
https://stackoverflow.com/questions/43256541/attempt-to-invoke-virtual-method-java-lang-string-android-content-context-getpa

## 错误4： 如果用户没有继承AnApplication, 则要考虑各种为空的情况.

## 错误5：
instance AnApplication中的的不对 得到方法不对，直接new一个

## 错误6：

Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'android.content.res.Resources android.content.Context.getResources()' on a null object reference
at com.sunsta.bear.faster.SizeUtils.dp2px(SizeUtils.java:45)
at com.sunsta.bear.faster.LaBitmap.getStatusBarHeight(LaBitmap.java:612)

这是LaBitmap中AnApplication.getInstance() 的Context为null导致的错误.

这里可以这样判断： 写一个工具类：

getBaseApplicationContext(){

native Context = AnApplication.getInstance();
if(context ==null){
context = AnApplication.getContextByInfrence();
}
if(context ==null){
throw Exception ("Check the Context null ,should be extend AnApplication)
}

return context;

错误7：
This project uses AndroidX dependencies, but the 'android.useAndroidX' property is not enabled. Set this property to true in the gradle.properties file and retry

在gradle.properties中配置

# 启用Androidx生成支持的标志
# org.gradle.parallel=true
android.useAndroidX=true
# 启用Maven库转换的标志
# Automatically convert third-party libraries to use AndroidX
android.enableJetifier=true

## 20200427年记录

(1.)需要再次统一一下，网络请求框架，对外发布第一个请求示例,

(2.)统一命名外部请求的问题：

网络请求响应结果对应于两种： 统一为：Mode，实体entity为本地文件，angux为视图文件.

基础的就用，

BaseReplyMode =简称 rm ;

ReplyUserMode

内部类：UserMode

网络请求请求参数对应两种：统一为：Mode，实体entity为本地文件，angux为视图文件.

基础的请求：

BaseApplyMode = 简称 am ;

ApplyRequestMode

内部类：RequestMode

（3.）NetBodyResponse 内部请求统一命名

convert方法需要判断空值，否则会报错异常(该方法命名需要考虑)
总的来说需要修改 Response httpResult = gson.fromJson(response, Response.class);

(4.)需要考虑是否保存token值，往外面统一token值

（5.）实际开发时需要，验证一个问题
json1，
{

msg:"success",
code:"200",
data:"moredata"

}
json2，
{

msg:"success",
code:"200",
data:"moredata"
isSuccess:success
}

是否可以用，

实体类，比如BaseReplyMode{
String msg;
String code;
String data;
String isSuccess;
get,setter
}

需要验证的问题：

是否可以用BaseReplyMode去接受json1的内容，json1少了一个字段，而本实体类多了一个字段

实体类，比如BaseReplyMode2{
String msg;
String code;
String data;
get,setter
}

反过来，是否可以用比如BaseReplyMode2去接受json2的内容，json2中多了个以恶字段,

## 1.InternetClient中需要考虑两种情况。

（1）.清除网络请求的close方法
（2）.重置网络请求的retrofit，可以这样判断

因为有的用户会尝试用同样的地址，可以更具地址来判断是否重置网络请求retrofit

if(textutils.isEmpty(baseul)&&!baseUrl.equanl(currentUrl){
//创建重置retrofit
}else{
//重用
}

(3).建议在Application中inisilize初始化，并且设置请求的地址.

以后就可以调用（建议整合init）

InternetClient.api(NewUploadApi.class).observable();
InternetClient.api(NewUploadApi.class).observable();

(4).保存token值的设定
设置token值的名字字段
设置token值的value

setToken(String tokenName,String tokenValue){

}
(5).清除Token值

## 警告修改1：FragmentPagerAdapter is deprecated. What's the best alternative to use for this?
class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)



