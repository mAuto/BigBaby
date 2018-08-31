- then：
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
