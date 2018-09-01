- then：Mockito还提供了另外一套与do系列相对应的测试API。例如：thenReturn, thenThrow, thenAnswer, thenCallRealCode。它们之间有什么区别呢？首先调用方法上。这里以doReturn和thenReturn举例，其他的方法大同小异，基本相同。
  - BigUnitTestTest.java
  ```
  // doReturn是前置于when的
  doReturn("test").when(sample).appendString(anyString(), any(String.class));
  ```
  ```
  // thenReturn是后置于when的
  when(sample.appendString("a", "b")).thenReturn("Hello");
  ```

  &emsp;&emsp;这是最直观的语法差别。根本的区别在于，**doReturn修饰的方法不会被真实执行，只是返回了指定的返回值而已；而thenReturn，会先执行真实的逻辑，然后返回指定的返回值。**
  <div align=center>![avatar](/res/ut_mockito_dorenturn.png)</div>
  <div align=center>![avatar](/res/ut_mockito_thenreturn.png)</div>
这两种方式的区别有哪些应用场景？主要有两个比较常见的场景。1 屏蔽某个方法重的已知异常或者对测试目标方法造成的影响；2 可以用doReturn屏蔽测试代码中的thenReturn。  
  - BigUnitTestSample.java
  ```
  private List<String> targetData;
    private NetHunter mHunter;

    public String getDataByPos(int pos) {
        // something operation

        // 非常耗时的操作，获取数据
        mHunter.fetchData(targetData);

        return targetData.get(pos);
    }
  ```

  &emsp;&emsp;这个方法中有个耗时操作，我不希望经过这个耗时操作，很简单，只要注入一个NetHunter的mock对象就好了。但是我还需要targetData在get之后给我返回我想要的值，不要出错。我可以这样写：
    - BigUnitTestTest.java
    ```
    @Test
    public void testThen(){
        List<String> mockList = mock(ArrayList.class);
        // 注入mock
        mSample.addMockList(mockList);
        mSample.addMockNetHunter(mock(NetHunter.class));

        int posWanted = 1;
        // 使mock对象执行真实逻辑
        doCallRealMethod().when(mockList).get(posWanted);

        //doReturn("pos: "+posWanted).when(mockList).get(posWanted);
        when(varStrs.get(posWanted)).thenReturn("pos: "+posWanted);

        mSample.getDataByPos(posWanted);
    }
    ```  

  &emsp;&emsp;方法中向目标方法中注入了一个mock的NetHunetr，但是不做任何其他的操作，因为mock对象不执行方法，所以耗时操作就会被屏蔽。然后还注入了一个mock的list，我们需要操作这个list，并get我们想要的pos对应的值。所以必须使用doCallRealMethod保证mock方法能够执行。最后如果使用thenReturn会发生什么事情？  
    <div align=center>![avatar](/res/ut_mockito_thenreturn_ex.png)</div>   
  <!-- &emsp;&emsp;方法中向目标方法中注入了一个mock的NetHunetr，但是不做任何其他的操作，因为mock对象不执行方法，所以耗时操作就会被屏蔽。然后还注入了一个mock的list，我们需要操作这个list，并get我们想要的pos对应的值。所以必须使用doCallRealMethod保证mock方法能够执行。最后如果使用thenReturn会发生什么事情？ -->  

  &emsp;&emsp;因为list没有经过hunter装填数据，里边是空的，所以贸然调用get会错。而thenReturn是先执行真实方法然后返回，所以会在执行真实方法的时候抛异常。所以这时候就应该使用doReturn，禁止list执行会出错的方法，还能返回我想要的数据。而另一个使用场景的意思就是，doReturn发生在之前，会屏蔽掉同一个mock对象同一个方法同样参数的调用的thenReturn。因为mock方法调用之前直接通过代理方法返回指定值，是不会执行真实逻辑的，也就不会走到thenReturn。

  &emsp;&emsp;*** 难道每次遇到这样的场景，我都需要用doCallRealMethod保证mock方法执行真实逻辑吗？有没有其他的方法可以得到一个可操作的还执行真实方法的对象？ ***

- spy：(间谍?)这个方法会生成一个真实对象的“代理”，通过这个代理调用的方法会执行真正的逻辑，直到这个方法被提前的设定打断，按照设定的逻辑执行。  

  - BigUnitTestTest.java
  ```
  @Test
    public void testSpy() throws Exception {
        BigUnitTestSample sample = new BigUnitTestSample();
        BigUnitTestSample spy = spy(sample);// tag_0 spy(Object)
        BigUnitTestSample spy = spy(BigUnitTestSample.class);// tag_1 spy(Class)
        when(spy.appendString("a", "b")).thenReturn("Hello");
        LogSys.print(spy.appendString("a", "b"));
    }
  ```

 &emsp;&emsp;spy方法有两个API，一个参数是一个真实对象(即便传进去一个mock对象也不会影响结果，因为spy(Object)的底层其实就是mock，但是设置了CALLS_REAL_METHODS)，一个参数是一个类，得到的结果是一样的。   
 <div align=center>![avatar](/res/ut_mockito_spy.png)

 &emsp;&emsp;所以then的例子我可以这样写：
 ```
 List<String> mockList = spy(ArrayList.class);
 ......
 //doCallRealMethod().when(mockList).get(posWanted);
 ```


** 源码是最好的文档 **
