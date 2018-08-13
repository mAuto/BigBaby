### 响应式编程
##### 1 有这样几行很简单的代码
```
/**
 * code_0
 */
int a = 1;
int b = 2;
int c = a + b; // c = 3
```
> 这是一种赋值动作，而非一种变量之间的关系。也就是说此后无论a和b怎样改变value，c都不会发生改变。而响应式编程试图用某种范式来建立对象之间的这种关系，做到“响应式变化”。  

> 那么可以试着建立一种这样的单向的模式：

```
/**
 * code_1
 */
int a = 1;
int b = 2;
int c = Stream.just(a).just(b).op(plus);// c = 3
b = 3;// c = 4;
a = 2;// c = 5;
```

> 这像是什么开发模式？观察者模式。一个被观察事物的变化引起观察者的变化。

> flag_0：有没有人想到一种架构设计模式？

> 那么code_1还可以这样扩展。这里的变量不再只是一些基础变量，数量也不是两个，而是一些真真切切数量比较大结构比较复杂的数据，比如一个很长的array，一系列连续的点击事件，甚至是另一个这样的数据链等等，而c也不再是简单的赋值操作。c将是一个逻辑模块，一个对这一连串数据进行某种逻辑操作方法函数，这个操作也许是过滤，整合，删除，拆包等等，经过这个逻辑之后原来的数据变成了一个新的数据，而这个新的数据可以继续接入其他的操作。

> 那这一连串发生的逻辑的本质又是什么？
> 这是一个对数据流层层处理的过程：

> 输入数据 --------------------- stream --------------------> 输出  

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逻辑_0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逻辑_1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逻辑_x&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;......