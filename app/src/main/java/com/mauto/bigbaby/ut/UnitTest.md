 ### UnitTest

#### 一 本地单元测试
- 位于 module-name/src/test/java/。  

&emsp;&emsp;这些测试在计算机的本地JVM上运行。当测试没有 Android 框架依赖项或当可以模拟 Android 框架依赖项时，可以利用这些测试来尽量缩短执行时间。  

&emsp;&emsp;本地单元测试是运行在开发环境的JVM中的，在这个环境中并没有android包中类的具体实现的，在测试方法中不能出现android包下的类，否则会出现无法mock的异常。比如我在测试的目标方法里边用到了TextUtils的isEmpty()方法，就会这样出错：
```
Method isEmpty in android.text.TextUtils not mocked.
```   
这是官方文档给出的解释：  
> The android.jar file that is used to run unit tests does not contain any actual code - that is provided by the Android system image on real devices. Instead, all methods throw exceptions (by default). This is to make sure your unit tests only test your code and do not depend on any particular behaviour of the Android platform (that you have not explicitly mocked e.g. using Mockito).  

> 单元测试使用到的android相关的文件并不包含任何实际上的代码(实际上开发环境中是没有android文件的具体实现的，只是一些API的定义，并且所有的实现都是throw new RuntimeException("stub")，而我们所能看到的源码实际上来自SDK下载的Android System Image)，因为这些文件只有真机或者虚拟机的android系统映像能够提供。所以所有调用android文件的地方都会报异常。这样做是为了确保你的单元测试是用来测试你的代码，而不受制于android平台的任何特殊的行为（没有使用Mockito显式地mock这些类的前提下）  

&emsp;&emsp;在本地单元测是中使用的基本都是JUnit + Mockito。JUnit用来正常的编写测试用例，Mockito用来生成模拟的目标类，并加以管理。

##### 1 JUnit（JUnit4）

###### 1.1 怎样创建一个测试文件(测试用例)。
&emsp;&emsp;主要有两种方法：在需要测试的方法或者类名上点击右键选择Goto&ensp;->&ensp;test，然后在弹出的对话框中创建;快捷键ctrl+shift+T直接弹出创建测试文件的对话框。  
<div align=center>![avatar](/res/ut_create_test_dialog.png)   
###### 1.2 基础的注解的含义和作用
&emsp;&emsp;创建好一个测试文件，里边是这个样子的
<div align=center>![avatar](/res/ut_junit_use_case.png)

- @Before：每个@Test方法被调用之前都会调用一次，多用来做一些初始化的操作，比如获得一个输入流。  
- @Test：测试方法。  
- @After：每个@Test方法执行完毕后都会调用一次，多用来做一些善后工作，比如关闭一个数据流。

这里就会有一个问题，如果在测试中需要一些全局的变量怎么办，放在@Before中明显不行，因为被修饰过得方法每次都会调用，并不能用来保存数据。JUnit提供了两个不常用的注解来解决这个问题。
- @BeforeClass：在执行所有测试方法之前执行一次被@BeforeClass修饰过得方法，可以用来初始化一个全局的变量。   
- @AfterClass：在执行完毕所有测试方法之后执行@AfterClass修饰的方法，用来做最后的善后工作。  
但是这两个注解修饰的方法都必须是静态方法。  

<div align=center>![avatar](/res/ut_junit_use_case_anno.png)


###### 1.3 基础的注解的含义和作用


> 断言 Assert
- 1 assertEquals(expected， actual)和assertSame(expected， actual)的区别，前者对比的是双参的值，后者对比的是双参的地址。

> 时间  
在项目开发过程中使用单元测试不仅不会增加时间，还会一定程度的减少开发时间，增加工作效率。  
1 学习单元测试需要一些时间。  
2 写单元测试的时候不熟悉，会慢一点，需要时间多琢磨多练习。   
3 在一个没有单元测试的项目中接入单元测试，需要不少时间取调整结构，有单元测试的项目和没有单元测试的项目的结构是有一定差别的。   

> 小细节  
1 在实际写代码中难免会用到一些android包中比较好用的工具类，比如TextUtils，用来check字符串的isEmpty方法。这个方法虽然在android包中，但是实现代码都是java包中的内容，所以可以自己写一个工具类来实现这个功能，这样就可以在代码中放心使用而不用担心UT是报异常。Log也可以用System的api来替代。

> 建议  
1 写单元测试之前一定要知道单元测试能做到什么，知道怎样写合适，不要为了写而写。  
2 对于项目中的重要模块，建议按照现调整结构在接入的顺序做单元测试。  
3 对单元测试比较熟悉之后，在新需求开发中，边开发边写单元测试，业务逻辑在真机或者模拟器上费时的调试，完全可以用更快更全面的单元测试替代。   

http://chriszou.com/2016/06/07/android-unit-testing-everything-you-need-to-know.html

#### 二 真机(模拟器)测试
