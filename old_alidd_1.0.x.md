## Livery 1.0.x老版本日志记录# Version LOG

[**an-aw-base0.x.x版本log.**](https://github.com/qydq/an-aw-base/releases)

**⚠️注意**
版本更新日志严格跟随代码提交内容，方便日后查阅相关记录.

### 1.0.1*
   implementation'com.sunst:alidd:1.0.1'
alidd的初始版本，从an-aw-base重构而来，alidd框架1.0.1（及以下的版本）支持的android最低版本为，minSdkVersion=19

1.0.1包含的库以及版本信息
```Groovy
appcompat          : 'com.android.support:appcompat-v7:27.0.2',
constraint         : 'com.android.support.constraint:constraint-layout:1.0.2',
design             : 'com.android.support:design:27.0.2',
recyclerview       : 'com.android.support:recyclerview-v7:27.0.2',

retrofit           : 'com.squareup.retrofit2:retrofit:2.3.0',
gson               : 'com.squareup.retrofit2:converter-gson:2.1.0',
rxjava2            : 'com.squareup.retrofit2:adapter-rxjava2:2.3.0',
okhttp             : 'com.squareup.okhttp3:okhttp:3.8.1',
glide              : 'com.github.bumptech.glide:glide:4.3.1',

xutils             : 'org.xutils:xutils:3.5.0',
glidecompiler      : 'com.github.bumptech.glide:compiler:4.3.1',
multipleimageselect: 'com.darsh.multipleimageselect:multipleimageselect:1.0.4',
crop               : 'com.soundcloud.android.crop:lib_crop:1.0.0',
advancedluban      : 'me.shaohui.advancedluban:library:1.3.2',
nineoldandroids    : 'com.nineoldandroids:library:2.4.0',
————————————————
```
### 1.0.2*
   implementation'com.sunst:alidd:1.0.2'
本次主要是添加androidx，移除升级修复相关第三方库等内容.
**更新1：**
添加androidx 库，所有的控件使用均不支持support.v4,v7这样的包(包含如RecyclerView等等)，代替的是androidx最新的库.

如你的项目已经包含了，v4，v7 ，请如下配置（可参见常见错误1）.
```Groovy
 configurations {
     all*.exclude group :'com.android.support',module:'support-compat'
     all*.exclude group :'com.android.support',module:'support-v4'
     all*.exclude group :'com.android.support',module:'support-annotations'
     all*.exclude group :'com.android.support',module:'support-fragment'
     all*.exclude group :'com.android.support',module:'support-core-utils'
     all*.exclude group :'com.android.support',module:'support-core-ui'
 }
————————————————
```
**更新2：**
移除takePhoto模块依赖的编译库，因为一些第三方库长久不更新，会导致一些问题，严重的可能出现崩溃，（如multipleimageselect 该库的作者未更新，导致更新glide后，在android8.0 ,9.0上存在兼容性问题）.

且第三方包会带来```alidd```体积变大的风险，故移除大部分非必要的引用，并修复一些已知问题，下面为相关信息.

>1.移除multipleimageselect，代替的multipleimage模块
>com.darsh.multipleimageselect:multipleimageselect:1.0.4
>使用方法同（相同）
https://github.com/darsh2/MultipleImageSelect
>
>2.移除crop
>crop库也是比较老旧，这里针对takePhoto移除crop裁剪相关的库，故本版本不支>持裁剪（请知），但是提供了ucrop模块
>使用方法具体参见demo案例.
>
>3.移除advancedluban压缩
>advancedluban这个压缩是比较通用的压缩，这里advancedluban压缩里面包含supportv4等等，跟本次构建编译环境产生严重冲突，故移除，代替的compress模块.
>
>4.其它移除
>因升级retrofit，故移除原先网络请求RetrofitClient请求的接口api，故本版本不支持最开始的网络请求调用方法，需要自己另行封装，会在后面版本>推出强大功能的网络请求（包含异常处理部分，

**更新3：**
升级一些库的最新版本，1.0.2包含库版本信息
```Groovy
//（2）网络请求等模块
retrofit           : 'com.squareup.retrofit2:retrofit:2.6.2',//20190628
gson               : 'com.squareup.retrofit2:converter-gson:2.6.2',//20190628
rxjava2            : 'com.squareup.retrofit2:adapter-rxjava2:2.6.2',//20190628
xutils             : 'org.xutils:xutils:3.5.0',

//（3）其它模块
glide              : 'com.github.bumptech.glide:glide:4.10.0',//20190628
glidecompiler      : 'com.github.bumptech.glide:compiler:4.10.0',//20190628
————————————————
```
### 1.0.3
本次主要增加```Retrofit```支持的```网络2异步请求```情景，包含```rxJava2,rxAndroid2```；原有的```网络1请求xUtils3```保留，出于对```xUtils```作者的致敬，以及```xUtils```四大模块这么多年的习惯.  <br/>
**更新1：**
添加rxandroid2和rxjava2，用于支持网络2异步请求.
```Groovy
iorxjava2 : 'io.reactivex.rxjava2:rxjava:2.2.15',//20190703
iorxandroid2 : 'io.reactivex.rxjava2:rxandroid:2.1.1',//20190703
————————————————
```
**更新2：**
新增```网络2异步请求```http情景，提供JustNetClient对网络异步请求.
### 1.0.4
修复```网络2异步请求```-已知bug.
### 1.0.5*
   implementation'com.sunst.alidd:alidd:1.0.1'
修复```网络2异步请求```-已知bug，支持JustRxManager对网络异步请求能力.
### 1.0.6
1.修复```网络2异步请求```-已知bug，主要是过滤器```null```异常处理. <br/>
2.开始支持GIF图片加持，添加Gif图片的显示加载回调，能够监听到Gif播放的次数OnGifListener==.  <br/>
3.支持GIF图片的GifImageView，并且提供GlideImageLoader完善glide加载gif存在的一些问题（如图片抖动）.  <br/>
4.```ina情景```INAStepProgressView进度条加持.
### 1.0.7*
修复Gif不显示的已知bug，优化了GIF加载显示速度的问题，此处感谢```koral--/android-gif-drawable```.
### 1.0.8
1.新增INATableLayout，，此处感谢```AndroidKun/XTabLayout```.  <br/>
2.优化新增INAStepProgressView已知性能问题.  <br/>
3.修复一些已知问题.
### 1.0.9*
本次版本为重大版本升级，涉及近400多个文件的变化，项目架构增```JUST模块```，优化```dd```，规范命名；其次涉及图片选择，并且结合takephoto模块，其它修复一些已知问题.  <br/>
**更新1：**
修复```网络请求JustNetClient```已知问题，优化了GIF加载GifImageView显示（```AppCompactImageView```），针对```1.0.8版本```添加了```INATableLayout```直接设置指示栏图标为drawable对象，且同时支持颜色设置.  <br/>
**更新2：**
重大升级拍照，系统相册选择模块，原有的takephoto模块重构，加持图片拍照能力集合，在pictureselector基础上增加选中的布局，整合takephoto.  <br/>
**更新3：**
提供预览本地网络视频能力```MediaHelper```，完善LA情景.
### 1.0.10
修复```网络2异步请求```-已知bug，提供变换能力；LA大写部分.
### 1.0.11
优化```UCrop```裁剪工具，防止文件冲突找不到ali情景系列包，重命名. 发布优化版本.
### 1.0.12*
继承```1.0.12```版本之前的版本后apk会增加13.4M左右；当前优化后```1.0.12```版本，apk大小仅仅增加1.8M；后期不新增需求，目标控制在1M以内.
### 1.0.13*
1.优化SwipeMenuRecyclerView，material design，名字变更为SwipeRecyclerView.<br/>
2.修复一些已知问题.
### 1.0.14*
年前的一个版本，```1.0.14```，知乎中准备更新```alidd-samples```第一个参考，暂时以INATabLayout为例子引入alidd情景.<br/>
**更新1：**
修复PictureSelector中存在的问题，统一FileUtils处理文件.<br/>
**更新2：**
修复一些已知问题，INA系列增加inaexpandablelistview，修复一些获取网络视频第一帧图片时的报错问题，完善mediahelper，lastorge增加文件大小获取.
### 1.0.15
2020年前第一个对外的```正式版本```，**首次**在知乎中以```INATabLayout```为例推广本```an情景```系列```alidd框架 ```的使用，提供完整的```api帮助文档```，完整的```示例apk以及源代码```，完整的构建标准体系.
### 1.0.16
2020年后第一个版本，修改<br/>1.添加inalikebutton控件.<br/>2.主要修复网络下载文件的框架，需要重新优化命名.<br/>3.添加一个AboutPage页面关于本人我.<br/>4.资源文件，toast，文件操作，跳转类参考.<br/>
### 1.0.17
9.0系统出现的网络安全访问问题```网络下载工具不支持http```-已知bug已修复，完成国际化支持操作，对图片选择控件添加国外语言支持，同时修复返回键统一处理，这里考虑要不要移除xutils3以减轻依赖的大小，这次稳定在1.8M.
### 1.0.18
移除xutils3依赖的大小，修复已知问题.
### 1.0.19
第一次引入androidx.camera的测试版本，（此版本存在兼容性问题，如需引用androidx.camera特性，需要依赖1.1.1版本:并同时修改minSdkVersion=21）.
```
api "androidx.concurrent:concurrent-futures:1.0.0-rc01"
api "androidx.camera:camera-lifecycle:1.0.0-alpha01"
api "androidx.camera:camera-core:1.0.0-alpha08"
api "androidx.camera:camera-camera2:1.0.0-alpha05"
```
### 1.0.20
移除相关资源文件，优化代码.