### free note
[ToC]
#### 1.从FM白屏问题延伸开去的一系列系统源码分析
##### 1.1　复现步骤：  
&emsp;&emsp;前提：activity -> fragment(VIewPager) -> fragments，至少两层fragments嵌套的UI flow。  
&emsp;&emsp;app home键退入后台或者熄屏，android device monitor中打开DDMS，然后选中进程点击右上角STOP按钮，模拟应用在内存不足的情况下被强行杀掉的场景，然后打开app，就会出现fragment不会被完整加载出来的情况。  
##### 1.2 按home键之后的生命周期走向   

<div align=center>![avatar](/res/note_1_1.png)</div>    

##### 1.3 当app被DDMS强行杀死后，重新唤起到前台不会经过app的任何生命周期，也不经过重新创建application的过程，为什么？  

##### 1.4 重新唤起到前台的app，再次点击home键的话是可以经过正常的生命周期的。  

##### 1.5 在重新唤起的app的pause方法中debug，调试此时的mFragments，发现fragment的总数多了一个，是全新的一级fragment，为什么会重新创建一个一级fragment？  
