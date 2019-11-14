# Java开发笔记|0.开发环境搭建

在正式开始编写Java程序之前，我们需要安装Java的运行和开发环境。
- - - -
#### 开发&运行环境简单介绍
有人可能会疑惑，这个环境指的是什么呢？正如我们人类的生存离不开地球的环境一样，程序的运行也无法离开程序自身的环境。换而言之，如果不恰当设置程序的运行环境，程序就无法运行。比如，用Java编写的程序需要在Java Virtual Machine（Java虚拟机，JVM）里面运行，而这个JVM又需要只有电脑安装了Java之后才能成功创建。再比如，用Python编写的.py文件在Windows和OS X下也不能直接运行，需要安装Python解释器，将Python “解释“ 为系统底层能够”理解“的命令，才可以保证.py文件能够在电脑上运行。而这个Python解释器，就需要在官网中下载、安装。因此，我们作为开发人员，才需要下载并且设置程序的运行环境。

~~我也不知道开发环境是啥（笑~~
~~那么就这么愉快的省略掉开发环境的介绍了~~
- - - -
#### Java开发环境搭建
我们需要开发Java程序，需要安装下列程序：
1. Java Development Kit (JDK) [下载链接](https://www.oracle.com/technetwork/java/javase/overview/index.html) **注意是JDK不是JRE**
2. Eclipse (Java开发专用，~~13亿人都在用的选择~~)可用IntelliJ Idea替换。

* 非常不幸的是自从2019年4月起，Oracle的官方Java SE更新了协议，下载时需要注册账号。

从官网中分别下载了对应安装文件并安装到电脑后，可以尝试下列步骤：
> 在Windows上，打开Cortana搜索功能，搜索cmd  
> 在OS X上，打开启动台，搜索Terminal  
打开后输入`java --version`按回车，如出现对应Java版本号则为安装成功。**注意这里一定要是对应的版本号，比如下载Java 11.0.3 显示版本号则为11.0.3。有些计算机内置Java，要注意版本号一定要正确。

如果版本号不正确其实问题也不会太大。一般电脑中如果已经内置了Java其他版本的话，Eclipse是可以在运行环境中选择对应的环境的。
- - - -
#### 创建一个新的Java Project
第一次打开Eclipse时会需要设置Working Directory，随便选择一个喜欢的就
可以了。设置完成后会进入这个Welcome界面：
![](Java%E5%BC%80%E5%8F%91%E7%AC%94%E8%AE%B0%7C0.%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-06-29%20%E4%B8%8B%E5%8D%8810.13.08.png)
在左上角的菜单栏中选择File -> New -> Java Project创建新的Java项目：
![](Java%E5%BC%80%E5%8F%91%E7%AC%94%E8%AE%B0%7C0.%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-06-29%20%E4%B8%8B%E5%8D%8810.27.28.png)
之后会弹出这个界面，给这个project起个名字后点击Finish：
![](Java%E5%BC%80%E5%8F%91%E7%AC%94%E8%AE%B0%7C0.%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-06-29%20%E4%B8%8B%E5%8D%8810.15.17.png)
注意在这里，如果电脑中预先安装过其他版本Java的话，可以在JRE中的下拉菜单选择specific JRE。
选择完Finish后，Java 11会询问是否创建Package Module Name之类的东西，目前可以先选择Don’t Create.
然后**把Welcome Page关掉**，就进入主页面了：
![](Java%E5%BC%80%E5%8F%91%E7%AC%94%E8%AE%B0%7C0.%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202019-06-29%20%E4%B8%8B%E5%8D%8810.32.46.png)
