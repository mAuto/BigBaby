[TOC]

## UnitTest

### 一 本地单元测试
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

#### 1 JUnit（JUnit4）
&emsp;&emsp;通过Android Studio创建项目会自动引入JUnit，所以可以忽略手动添加JUnit的dependency。   
##### 1.1 怎样创建一个测试文件。
&emsp;&emsp;主要有两种方法：在需要测试的方法或者类名上点击右键选择Goto&ensp;->&ensp;test，然后在弹出的对话框中创建;快捷键ctrl+shift+T直接弹出创建测试文件的对话框。  
<div align=center>![avatar](/res/ut_create_test_dialog.png)   
##### 1.2 基础的注解的含义和作用
&emsp;&emsp;创建好一个测试文件，里边是这个样子的
<div align=center>![avatar](/res/ut_junit_use_case.png)</div>   
- @Before：每个@Test方法被调用之前都会调用一次，多用来做一些初始化的操作，比如获得一个输入流。  
- @Test：测试方法。  
- @After：每个@Test方法执行完毕后都会调用一次，多用来做一些善后工作，比如关闭一个数据流。

 &emsp;&emsp;这里就会有一个问题，如果在测试中需要一些全局的变量怎么办，放在@Before中明显不行，因为被修饰过得方法每次都会调用，并不能用来保存数据。JUnit提供了两个不常用的注解来解决这个问题。
- @BeforeClass：在执行所有测试方法之前执行一次被@BeforeClass修饰过得方法，可以用来初始化一个全局的变量。   
- @AfterClass：在执行完毕所有测试方法之后执行@AfterClass修饰的方法，用来做最后的善后工作。  
但是这两个注解修饰的方法都必须是静态方法。  

<div align=center>![avatar](/res/ut_junit_use_case_anno.png)


##### 1.3 如何去写一个测试方法
&emsp;&emsp;创建测试文件并且明白了几个基本注解的含义和作用之后，下一步，就是如何去写一个测试方法，验证我们的代码逻辑是否正确。JUnit提供了最基本的一些验证API：Assert(断言)，此外还可以直接抛异常或者验证异常的方式编写测试用例。  
###### 1.3.1 Assert断言  
&emsp;&emsp;断言的方法很多，一般都是成对出现的：
```
/********** 前后的值是否相等 *********/
// 如果前后的值是String ，就是前后是否想等
// 如果前后的值非String ，就会走Object的equals方法，  
// 如果没有自定义equals方法，就是前后是否同一;  
// 如果自定义了equals，根据具体情况而定。
Assert.assertEquals(expected, actual)
Assert.assertNotEquals(expected, actual)
/***********************************/

/******** 前后是否指向同一对象 ********/
Assert.assertSame(expected, actual)
Assert.assertNotSame(expected, actual)
/***********************************/

/********* 是否为true/false *********/
Assert.assertTrue(condition)
Assert.assertFalse(condition)
/***********************************/

/************ 是否为null ************/
Assert.assertNUll(obj)
Assert.assertNotNull(obj)
/***********************************/
```   
&emsp;&emsp;此外还有一些不常用的断言方法：
```
// 用来比较前后两个数组是否相等
// 这个断言的底层实际上是assertEquals，  
// 所以针对数组item的比较和assertEquals是一样的
Assert.assertArrayEquals(expected, actual)

// 判断actual是否符合matcher的规则
// 提供了更加灵活的断言方式
// 例如判断actual是否大于5(greaterThan(5))
// 例如数组actual的大小是否小于5(arrayWithSize(lessThan(5)))
// 方法众多，随意组合，有兴趣者自行参悟，不一一赘述
Assert.assertThat(actual, matcher)
```  
&emsp;&emsp;最后，Assert的以上方法都有一个对应的重载方法，多了一个String类型的参数message，这个重载方法的意义在于，如果方法failed可以将传进去的massage作为失败信息返回，增加返回结果的可理解性，否则都将是默认的失败信息。
<div align=center>![avatar](/res/ut_junit_assert.png  )  

###### 1.3.2 异常测试
&emsp;&emsp;加入我有一个这样的方法：
```
public boolean equals(Object obj) {
    if (obj == null)
        throw new IllegalArgumentException("obj is null");
    return super.equals(obj);
}
```  
&emsp;&emsp;要给这个方法添加测试用例，用来测试异常的情况，可以这样写：
```
@Test(expected = IllegalArgumentException.class)
public void equals() throws Exception {
    mSample.equals(null);
}
```  
&emsp;&emsp;在执行这个方法的时候，就会通过测试，因为目标方法成功的返回了一个期望的异常。但是如果传值不是null而是一个新对象的话，这个测试方法就会failed，因为在@Test注解中已经标注，在这个测试方法中期望情况是得到一个异常，任何不返回期望异常的结果都是不对的。@Test也可以给测试方法设置一个超时时间，比如@Test(timeout = 100)，如果测试方法执行超过100毫秒就会失败，可以用来进行性能测试。  

###### 1.3.3 其他
&emsp;&emsp;Assert还有一些不常用的方法，作用不是很大，比如fail()，主动让测试方法失败。只在一些情境中用些用处;还有一个用来忽略测试方法的注解@Ignore，比如某个测试方法还没有实现或者实际代码还没有写，就可用这个注解标注测试方法，这样在run测试文件的时候就会忽略这个测试方法。

##### 1.4 执行
<div align=center>![avatar](/res/ut_junit_use_case_debug.png)  

&emsp;&emsp;点击左侧的黑框就可进行针对单个方法的执行，点击之后会有三个选项：Run XXX(ctrl+shift+f10)，Debug XXX，Run XXX with Coverage。第一个的意思是执行这个测试方法;第二个的意思是调试这个测试方法，可以打断点就行调试;第三个的意思是执行这个方法并且输出一份覆盖率报告，覆盖率报告是可以导出成html的。

##### 1.5 小结
*** flag_0: Assert测试的是什么？ ***

&emsp;&emsp;Assert是用来测试目标方法的结果的，对Assert而言目标方法的具体实现就是一个黑盒子，并不关心具体业务逻辑，只在乎结果是否符合预期。使用断言提供的API可以全面的测试目标方法的返回值是否符合预期，不管是什么类型都可以测试。      
&emsp;&emsp;** 但是目标方法的具体实现怎么测试？**   

#### 2 Mockito
&emsp;&emsp;Mockito不会像Junit那样在AndroidStudio中自动引入dependency，需要手动加入：
```
testImplementation "org.mockito:mockito-core:2.7.6"
```   
&emsp;&emsp;在上一节的末尾有个问题，Junit用来给有返回值的方法做测试，但是目标方法的内部逻辑如何做测试，或者，哪些地方值得测试？   
&emsp;&emsp;**在目标方法的内部实现中，主要测试里边的某个对象或者某个对象的某个方法是否得到了调用，调用的顺序是什么样的，调用的次数，传入的参数是否正确等等内部的逻辑。** 但是对于JUnit而言，目标方法的实现是个黑盒子，JUnit拿不到里边的某个对象，所以这时候就需要一个工具来提供内部的那个对象。Mockito是Java圈里使用最广泛的mock工具。
##### 2.1 基本概念 —— Mock
Mock：mock产生的对象仅记录它们的调用信息，在断言中我们需要验证这些对象进行了符合期望的调用。如果只是单纯的调用这些对象的方法而没有提前**描述**任何逻辑，返回值都是 **返回类型默认值**。Object -> null，int/long -> 0。  
> &emsp;&emsp;在单元测试里边还有一个概念容易和mock混淆 —— Fake   
&emsp;&emsp;Fake产生的对象是目标类的具体实现的简化版。比如测试某个业务逻辑需要一个Repository从数据库拿数据，这时候Fake出来的Repositroy是不会真的从数据库拿数据，可能使用了一个简单的列表或者Map来保存数据。  
&emsp;&emsp;这两个概念可以这样理解：**Mock是虚的，只是表面架子不能用(调用);&ensp;Fake是假的，但是可以用。**    

&emsp;&emsp;但是有一点需要注意的是，mock是用来生成一个虚拟的对象的，并不是用来注入一个虚拟对象的。也就是说我们可以通过mock()方法或者@Mock注解生成一个虚拟的对象，但是无法通过这两个方式将生成的对象注入到目标方法中，如果不把生成的对象注入到目标方法中，那么目标方法中的对象仍然是内部new出来的，我们是无法对他在目标方法中的行为进行测试的。所以我们还要把对象注入到目标方法中，可以写setter方法注入，也可以使用Dagger注入，或者其他的方法。

##### 2.2 Mockito的使用
&emsp;&emsp;Mockito同样提供了全面的API用来构建测试用例，但是这些API基本都是组合使用的，单独使用不会有什么效果。  
- verify(T)/verify(T, VerificationMode)：用来验证mock对象的某个方法被执行过几次，以及传入的参数是否正确。VerificationMode表示的是校验模式，可以自由设定方法被执行了几次，比如times(5)，atLeast(2)，never()这些模式。默认也就是verify(T)，是times(1)。
  - BigUnitTestSample.java
```
// 目标方法
public String appendString(String a, String b) {
        if (TextSys.isEmpty(a) || TextSys.isEmpty(b))
            return null;

        String result = new StringBuffer(a).append(b).toString();

        if (mPrinter != null){
            mPrinter.print(result);
        }

        return result;
    }
```
```
// 注入setter
public void addPrinter(Printer printer) {
        mPrinter = printer;
    }
```
  - BigUnitTestTest.java
```
// 测试方法
@Test
    public void appendString() throws Exception {
        // 具体的测试方法
        Printer printer = mock(Printer.class);
        mSample.addPrinter(printer);
        mSample.appendString("Hello ", "Mockito !!!");
        verify(printer).print("Hello Mockito !!!");// verify(printer, times(1)).print("Hello Mockito !!!");
        // verify(printer).print("Hello World !!!");
    }
```
&emsp;&emsp;在这个测试方法中，是可以通过的，因为printer对象在appendString中只被执行了一次，并且传入的参数的值是一致的。如果把注释的地方打开就会报错，因为尽管执行次数是正确的，但是传入的参数是不对的。  
```
Wanted but not invoked:
printer.print("Hello World !!!");
-> at com.mauto.bigbaby.ut.BigUnitTestTest.appendString(BigUnitTestTest.java:42)
However, there was exactly 1 interaction with this mock:
printer.print("Hello Mockito !!!");
-> at com.mauto.bigbaby.ut.BigUnitTestSample.appendString(BigUnitTestSample.java:20)
```  
-  when(T)：这个方法不能单独使用。传入的参数是mock生成的对象，返回值也是这个对象，可以按照字面上的意思解释，就是当mock对象怎么怎么做的时候。比如：   

  - BigUnitTestTest.java
```
// 当mock对象调用appendString方法的时候
when(sample).appendString("Hello ", "Mockito !!!")
```  
是不是缺少什么东西？缺少一个动作结果。  
-  doAnswer(Answer)：
-  doReturn(Object)&ensp;/&ensp;doReturn(Object...)：
-  doThrow(Throwable...)&ensp;/&ensp;doThrow(class extends Throwable...)
-  doCallRealMethod()
-  doNothing：啥也不做，但是只有mock对象的void方法才能用，否则会报错。有什么作用？比如说有一个目标方法，里边包含了一个repository需要向网络获取数据，但是这个数据在这个目标方法中并不需要，所以测试的时候的不希望去调用它，那么就可以mock生成这个repository对象，然后doNothing获取网络数据的方法，就可以规避了。   

  - refresh()的具体实现中包含Repository夫人fetchDataFromRemote方法，但是测试refresh方法时并不需要申请网络数据，就可以这样让网络请求do nothing。
```
Repository repository = mock(Repository.class);
mRefreshHelper.add(repository);
// 顺序很重要，如果doNothing放在refresh之后就没有作用了。
// 这个例子有个问题，发现了吗？
doNothing().when(mReposirory).fetchDataFromRemote(id);
mRefreshHelper.refresh();
```

##### 2.3 小结

verify inOrder
when  


> Assert来判断测试的结果，verfiy来判断执行的次数和顺序，doAnswer用来判断执行的方法和方法的参数。

> 时间  
在项目开发过程中使用单元测试不仅不会增加时间，还会一定程度的减少开发时间，增加工作效率。  
1 学习单元测试需要一些时间。  
2 写单元测试的时候不熟悉，会慢一点，需要时间多琢磨多练习。   
3 在一个没有单元测试的项目中接入单元测试，需要不少时间取调整结构，有单元测试的项目和没有单元测试的项目的结构是有一定差别的。   

> 小细节  
1 在实际写代码中难免会用到一些android包中比较好用的工具类，比如TextUtils，用来check字符串的isEmpty方法。这个方法虽然在android包中，但是实现代码都是java包中的内容，所以可以自己写一个工具类来实现这个功能，这样就可以在代码中放心使用而不用担心UT是报异常。Log也可以用System的api来替代。

关于单元测试的建议  
&emsp;&emsp;建议分成四个阶段去做这件事情，总体上按照先本地测试后UI测试的顺序  
- 第一阶段：先要熟悉JUnit和Mockito这些本地单元测试的工具，弄清楚它们可以做什么不可以做什么，然后怎样结合起来做测试。可以自己动手写一些demo，尝试一下。这个阶段争取一个这样的成果：脑子里要清楚的知道一个什么样子的方法适合被测试。一个新的方法如何去写，一个老方法如何去调整。  
- 第二阶段：接入老项目之前要选好应该被接入的模块，然后按照第一步学到的去调整这些模块的结构(可以单拉分支去做)。可以边调整边做单元测试也可以调整结束后接入。  
- 第三阶段：经过第二步之后熟练度就会得到提升，然后尽量在新业务的开发中使用UT。这个时候自己可以抽一些时间去做个简单的对比，对比一下有UT的开发和无UT的开发的时间，代码质量，项目结构。如果足够熟练的话，有UT的开发时间是丝毫不会比普通开发慢的，甚至更快，并且代码质量更高，项目结构更加清晰。在真机或者模拟器上费时的调试，完全可以用更快更全面的单元测试替代。  
- 第四阶段：做UI测试。UI测试相对于本地测试比较复杂，掌握起来可能会需要更多的时间。并且对于一个适合做UT的项目来说，一定在尽量的使UI层更轻，业务逻辑层更清晰分明。用于输出展示的UI重要保证业务层提供的数据正确，基本上就不会有太大问题，所以UI测试用在以UI层作为输入的场景下会比较多。但是一个单纯的点击事件，实现里调用了业务层的逻辑，有必要非得给点击事件加一个UseCase吗？   

&emsp;&emsp;最后，不要为了完成任务而去写单元测试，每写一个UseCase每弄明白一个细节都是在给项目加分给自己加分。  


http://chriszou.com/2016/06/07/android-unit-testing-everything-you-need-to-know.html

### 二 真机测试(UI测试)
Espresso与Robolectric   
#### 1 Espresso  
&emsp;&emsp;Espresso是一个Google官方提供的Android应用UI自动化测试框架。
