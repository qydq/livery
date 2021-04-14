## livery1.2.o升级说明

`Livery版本`1.2.x`与`1.1.x不完全兼容，由`1.1.x`升级到`1.2.x`主要内容有：

### 重要升级：

* （1）国际化（简体中文rCN，繁体中文rTW，英文en，德语de，日文ja，葡萄牙pt）
* （2）对于AliActivity,AliFragment新增了saveInstance,layzfragment,这是造成livery1.2.o不兼容1.1.x的主要原因

### 其它升级：

* 1.弹幕控件相关
（1）BarrageItem. getViewTreeObser有可能导致的空指针
（2）弹幕INABarrageView新增了三种gif图片，并且支持自定义属性动画

* 2.删除
（1）INAResizeRelativeLayout 键盘布局自适应控件移除

* 3.新增
（1） KeyboardStatusDetector键盘是否开启的监听类
（2） default_imageview_description字符串
（3） ParallaxFragment下支持layzInint(),并且ViewPager下实现懒加载

>Fragment懒加载，新增博客文章[(androidx下fragment懒加载与生命周期演进过程)](https://zhuanlan.zhihu.com/p/348847249)

* 4.优化
INABadLayout重命名为：INAStatusLayout
整合了原livery1.1.x AnimUtils，ViewUtils,LaUi，现在统一与Ui操作的部分为：ViewUtils
PictureSelect修复了10.0以上小米手机路径可能获取不到的统一方法

* 5.其它
（1）减小优化livery框架的性能，有一些确定无用的代码移除
（2）统一了自定义控件的命名attr，style，string等命名习惯
（3）修复了一些已知问题
（4）所有的1.2.0以后统一，所有的rTxt，表示rich文本+（原方法调用不影响）
