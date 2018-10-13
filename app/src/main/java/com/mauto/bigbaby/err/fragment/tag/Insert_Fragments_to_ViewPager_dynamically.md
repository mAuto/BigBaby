### 1. 描述
&emsp;&emsp;有这样一个需要Fragment填充的ViewPager，没有设置任何缓存策略，adapter继承自FragmentPagerAdapter。假如最初有三页。当ViewPager滑到第三页的时候，想ViewPager动态插入数量不等的fragments，并且插入的位置不是在最后，而是将新的fragment插入到中间的某个位置，然后notify更新。
### 2. 出现的问题
- 滑动到最后的位置，crash
> 错误日志实例
```
java.lang.IllegalStateException:
Can't change tag of fragment BigErrFragment{82f3ac3 #2 id=0x7f070099 android:switcher:2131165337:2}: was android:switcher:2131165337:2 now android:switcher:2131165337:7
```  
- 如果在插入心fragments之前的当前页正好是要插入的位置，插入并刷新后，这一页并没有换成插入的新fragment。  

### 3. 原因分析
&emsp;&emsp;ViewPager在将fragment加入到Fragment的时候，会先根据fragment的位置生成一个tag，然后根据这个tag去FragmentManager查找是否有这个fragment，如果有的话就会重新将fragment attach，如果没有的话，会从adapter中取出将要加载的fragment，然后加入到FragmentManager中，并将tag分配给它。默认情况下，每滑动一页，都会自动把下一页的fragment创建并加入到FragmentManager中，并分配一个tag(mTag)。但是，当新fragments插入到中间的某个位置的时候，就会出现这种情况：当滑动到后面的时候，原本已经被added的fragment(带有一个mTag)，会被再次加载。因为加载之前会新生成tag，并从FragmentManager中查找是否存在，由于新tag并没有fragment占用，所以查不到，然后就会从被插入的数据中取fragment，取出的fragment是已经被加入FragmentManager并分配mTag的，然后会再次被add进FragmentManager，在add的时候就会因为本身已有mTag并且与新tag不一样，而抛出异常。
### 4. 解决办法
***不对***
