## 异常场景：向ViewPager的中间位置动态的插入Fragment。
### 1. 描述
&emsp;&emsp;有这样一个需要Fragment填充的ViewPager，没有设置任何缓存策略，adapter继承自FragmentPagerAdapter。假如最初有三页。当ViewPager滑到第三页的时候，向ViewPager动态插入数量不等的fragments，并且插入的位置不是在最后，而是将新的fragment插入到中间的某个位置，然后notify更新。
### 2. 出现的问题
- 滑动到最后的位置，crash
> 错误日志实例
```
java.lang.IllegalStateException:
Can't change tag of fragment BigErrFragment{82f3ac3 #2 id=0x7f070099 android:switcher:2131165337:2}: was android:switcher:2131165337:2 now android:switcher:2131165337:7
```  
- 如果在插入新fragments之前的当前页正好是要插入的位置，插入并刷新后，这一页并没有换成插入的新fragment。  

### 3. 原因分析
&emsp;&emsp;出现这个问题的原因主要是两方面：
- FragmentPagerAdapter的自身机制。ViewPager默认将会缓冲当前界面的上下两页，也就是不设置缓存策略的情况下，会有三个fragment在内存中，其他的fragment不会被杀死，但是fragment的UI会从flow中移除(detach)，当需要这个fragment的时候会根据他的tag来查找他，如果查找到就直接attach，如果查找不到的会连同新分配的tag，add进FragmentManager中。
- add进Fragment时的tag。这里的tag的组成依赖于一个方法-getItemId，在默认情况下，这个方法返回的是当前页面的position。然后根据这个position生成一个tag，再用这个tag查找fragment或者连同一个fragment被add进去。

```
public Object instantiateItem(ViewGroup container, int position) {

        // ..........................................

        // 默认返回当前position
        final long itemId = getItemId(position);

        // 根据position生成一个tag
        String name = makeFragmentName(container.getId(), itemId);
        // 根据生成的tag查找fragment
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            if (DEBUG) Log.v(TAG, "Attaching item #" + itemId + ": f=" + fragment);
            mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(position);
            if (DEBUG) Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
            // 连同tag一起add到manager中
            mCurTransaction.add(container.getId(), fragment,
                    makeFragmentName(container.getId(), itemId));
        }

        // ..........................................

        return fragment;
    }
```

&emsp;&emsp;add进的fragment会有一个tag，但是这个tag默认情况下是依赖于当前position的。那就有这样的一个场景：当我滑动到某一页的时候，这一页的fragment已经add到FragmentManager中，附带一个tag，这是我在这个插入了多个fragments，然后向后滑动，直到回到这一页，adapter内部会根据当前的position生成的tag，去FragmentManager中查找它，但是因为他的位置变了，但是tag没有变，所以查找它使用的tag并不是它的tag，所以不能查到它，就会按照新fragment处理，连同新生成的tag再次add到FragmentManager中，这时，由于它已经在manager中并且已经有了tag，就会出现上述的异常。

### 4. 解决办法
&emsp;&emsp;根据上边的分析就会知道，关键问题在于方法getItemId默认返回的是当前的position。只要重写这个方法，使其返回一个和fragment绑定的不变的value即可。  
&emsp;&emsp;这样会有另外一个显示上的问题，就是因为插入位置的这一页fragment已经在内存中，即便插入之后刷新也不会用新插入的fragment来替换这个fragment。为什么？每次刷新一个fragment，ViewPager都会通过Adapter的getItemPosition(Fragment)方法在判断，这个fragment是否已经改变了，比如位置变了，被删除了等。默认情况下，这个方法返回POSITION_UNCHANGED，代表当前fragment没有任何变化，可以不用刷新它。还有一个返回值POSITION_NONE，代表这个fragment已经被删除了，要移除这一页。所以解决这个显示上的问题，只要，重写这个方法，返回传入framgent在数据源中的位置，即可迫使ViewPager每次都根据返回的position来判断，是否改变了位置。
