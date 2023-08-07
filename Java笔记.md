# Java

## 基础模块

### Java环境

- jdk 是开发环境 
- jre 是运行环境
- jvm 是虚拟机
- jdk 包括 jre jre 包括 jvm

### 数据连接

JDBC： Java提供给数据库方的一套API

数据库连接驱动：各个数据库针对Java提供的API设计的具体连接驱动

Java程序通过 数据库连接驱动 就可连接上数据库

### JDBC 执行过程

1. 注册驱动
2. JDBC执行
3. 执行预编译
4. 执行SQL
5. 封装结果集
6. 释放资源

### MySQL 数据库驱动

`com.mysql.cj.jdbc.Driver`和`com.mysql.jdbc.Driver`是MySQL JDBC驱动程序的不同版本，其中`com.mysql.jdbc.Driver`是早期版本的驱动程序，而`com.mysql.cj.jdbc.Driver`是更新的版本。

下面是它们之间的一些区别：

1. JDBC API版本：`com.mysql.jdbc.Driver`使用JDBC 4.0 API，而`com.mysql.cj.jdbc.Driver`使用JDBC 4.2 API。

2. 协议：`com.mysql.jdbc.Driver`使用MySQL专有协议，而`com.mysql.cj.jdbc.Driver`使用标准的JDBC协议。

3. 时区处理：`com.mysql.cj.jdbc.Driver`支持更多的时区，包括UTC、GMT和ISO-8601，而`com.mysql.jdbc.Driver`只支持JVM默认时区。

4. 字符编码：`com.mysql.cj.jdbc.Driver`支持更广泛的字符集，包括UTF-8、UTF-16和UTF-32，而`com.mysql.jdbc.Driver`只支持ISO-8859-1和US-ASCII。

5. 其他特性：`com.mysql.cj.jdbc.Driver`还支持SSL加密、XA事务、IPv6和其他一些新特性，而`com.mysql.jdbc.Driver`则不支持这些特性。

因此，建议使用`com.mysql.cj.jdbc.Driver`，特别是在使用新的MySQL版本（5.7及以上）时，以支持更广泛的字符集、更多的时区和其他新特性。





## IO操作

### IO

**概念：** IO是计算机中Input和Output简称  即输入和输出
`输入流来读数据   输出流来写数据`

简单的可以将Java中的流分为四类 流的四种形式：

![](img\Java流的形式.png)

字节写入：

```java
//输出流
public static void outputStream() throws IOException{
    //创建一个File实例
    File file = new File("/home/wenhaibo/IOTest.txt");
    //FileOutputStream为文件输出流
    FileOutputStream out = new FileOutputStream(file);
    //将内容转换为字节码输出
    out.write("This is IOTest".getBytes());
    //强制输出内存中所有内容
    out.flush();
    //关闭输出流
    out.close();
}
```

字节读取：

```java
//输入流
public static void inputStream() throws IOException{
    //创建一个File实例
    File file = new File("/home/wenhaibo/IOTest.txt");
    //FileInputStream为文件输入流
    FileInputStream in = new FileInputStream(file);
    byte[] b = new byte[1024];
    //将 byte.length 个字节的数据读入一个 byte 数组中
    int len =in.read(b);
    //将字节码转为字符串打印输出
    System.out.println(new String(b, 0, len));
    //关闭输入流
    in.close();
}
```

字符写入：

```java
    //字符流
    public static void outputStreamWriter() throws IOException{
        //创建一个File实例
        File file = new File("/home/wenhaibo/IOTest.txt");
        //FileWriter为文件输出流
        Writer out = new FileWriter(file);
        //直接输出字符
        out.write("This is IOTest");
        //强制输出内存中所有内容
        out.flush();
        //关闭输出流
        out.close();
    }
```

字符读取：

```java
    public static void inputStreamReader() throws IOException{
        //创建一个File实例
        File file = new File("/home/IOTest.txt");
        //Reader为文件输入流
        Reader in=new FileReader(file);
        char[] c=new char[1024];
        //将 byte.length 个字节的数据读入一个 byte 数组中
        int len =in.read(c);
        //将字节码转为字符串打印输出
        System.out.println(new String(c, 0, len));
        //关闭输入流
        in.close();
    }
```

#### 缓冲流

Java缓冲流是在**输入流**和**输出流**之上进行了一次包装（装饰器设计模式）
目的是解决频繁写入/读取数据时效率差的问题  `缓冲流先将数据缓存起来  然后一起写入或读取出来`

字节缓冲流类：**BufferedInputStream 和** **BufferedOutputStream**
字符缓冲流类：**BufferedReader 和** **BufferedWriter**

- BIO：Block IO 同步阻塞式 IO，就是我们平常使用的传统 IO，它的特点是模式简单使用方便，并发处理能力低。
- NIO：Non IO 同步非阻塞 IO，是传统 IO 的升级，客户端和服务器端通过 Channel（通道）通讯，实现了多路复用。
- AIO：Asynchronous IO 是 NIO 的升级，也叫 NIO2，实现了异步非堵塞 IO ，异步 IO 的操作基于事件和回调机制。

### New IO

因为传统的IO是阻塞而且低效的  JDK 1.4 提供了NIO（New IO）API

#### 缓冲区

NIO中引入了缓冲区的概念，缓冲区作为传输数据的基本单位块，所有对数据的操作都是基于将数据移进/移出缓冲区而来；
读数据的时候从缓冲区中取，写的时候将数据填入缓冲区。
尽管传统JavaIO中也有相应的缓冲区过滤器流（BufferedInputStream等），但是移进/移出的操作是由程序员来包装的，它本质是对数据结构化和积累达到处理时的方便，并不是一种提高I/O效率的措施。
NIO的缓冲区则不然，对缓冲区的移进/移出操作是由底层操作系统来实现的。

简单理解就是 `NIO的缓冲区是由底层操作系统来实现的 比传统的更高效`

#### 阻塞

传统的IO是阻塞的 单线程下IO操作是阻塞的
而NIO的同步非阻塞模式  是当一个线程取读取数据 不论它能不能读取到数据 他都不会阻塞
阻塞的情况



### 序列化

#### 概念

**序列化：**把对象转化为可传输的字节序列过程称为序列化
**反序列化：**把字节序列还原为对象的过程称为反序列化

#### 实现

**java 实现序列化很简单，只需要实现 Serializable 接口即可**

#### 注意

- **static 属性不能被序列化** =》 静态属性
  序列化序列的是对象 而静态属性是属于类本身的部分 不属于对象
- **Transient 属性不会被序列化** =》 标志忽略
- **序列化版本号serialVersionUID** =》 版本匹配
  所有实现序列化的对象都必须要有个版本号  这个版本号可以由我们自己定义
  当我们没定义的时候JDK工具会按照我们对象的属性生成一个对应的版本号
  JDK生成的版本号 只要这个类有一点修改 就会重新生成 所以可能不是很友好
- **父类、子类序列化 ** =》 正向递归
  序列化是以正向递归的形式进行的  如果父类实现了序列化那么其子类都将被序列化
  子类实现了序列化而父类没实现序列化  那么只有子类的属性会进行序列化  而父类的属性是不会进行序列化的



## 注解反射

### 注解



### 反射

获取class对象的三种方法

~~~java
public class Get {
    //获取反射机制三种方式
    public static void main(String[] args) throws ClassNotFoundException {
        //方式一(通过建立对象)
        Student stu = new Student();
        Class classobj1 = stu.getClass();
        
        //方式二（所在通过路径-相对路径）
        Class classobj2 = Class.forName("fanshe.Student");
        
        //方式三（通过类名）
        Class classobj3 = Student.class;
    }
}
~~~



## 集合框架

### 集合概述

Java中的集合类可以分为两大类：

- Collection接口：Collection是一个基本的集合接口  `Collection中可以容纳一组集合元素`（Element）
- Map接口：`Map提供键（key）到值（value）的映射`  一个Map中不能包含相同的键  每个键只能映射一个值

Map没有继承Collection接口  与Collection是并列关系

两个接口的结构概述：

![image-20210802104054847](img\集合结构概述.png)

集合关系结构：

![image-20210802104942661](img\集合关系结构.png)



### Collection

#### 迭代概念

**介绍：**在介绍Collection框架之前 先说明一下Collection框架中比较重要的一个概念 **迭代**
在Collection顶层接口里就继承了Iterable 所以所有的Collection集合容器都是可迭代的

**概念：**迭代的概念其实就是指 `可以一个个的拿到集合容器中的元素`
实现了Iterable接口的集合 就可以通过Iterator取出集合中的元素
实现了Iterable说明是可迭代的 而Iterator是迭代器 最终是通过迭代器取出集合中的元素

**意义：**我们并不是只能通过迭代器去遍历集合  只不过通过`迭代器遍历集合能够解耦  把取数据这个操作交给迭代器 而不是我们直接去取数据` 这样在后面如果需要更换集合类型的时候只需要满足迭代条件即可 实现数据访问和底层数据的分离  这个设计模式也叫做迭代器模式

**注意：**`使用迭代器循环的时候不要用集合本身的添加和删除操作 否则会抛出ConcurrentModificationException异常`
没有确认过 但是大部分的集合都有一个modCount属性用来记录集合做了多少次修改 在使用迭代器循环的时候会校验这个修改次数 如果有改变说明有修改
`实现Iterable接口允许对象成为 for-each循环 语句的目标` （for-each 其实是Java提供的语法糖  实际上还是通过迭代器Iterator迭代遍历）

~~~java
// 源代码
List<Integer> list = new ArrayList<>();
for (Integer i : list) {
    // 代码块
}

// 编译后的.class文件
List<Integer> list = new ArrayList();
Iterator var2 = list.iterator();
while(var2.hasNext()) {
    Integer i = (Integer)var2.next();
    // 代码块
}
~~~

源码解析

1. 可迭代：**Iterable**

```java
   Iterator<T> iterator();// 返回迭代器。
   default void forEach(Consumer<? super T> action) {// 就是一个for循环写法的包装
       Objects.requireNonNull(action);
       for (T t : this) {
           action.accept(t);
       }
   }
   default Spliterator<T> spliterator() {// 通过一个顺序遍历的Iterator对象获取一个并行遍历的Spliterator对象
       return Spliterators.spliteratorUnknownSize(iterator(), 0);
   }
```

2. 迭代器：**Iterator**
   
~~~java
   // 迭代器的顶级接口 ==>>  Iterator
   boolean hasNext();// 是否存在下一个元素
   
   E next();// 取下一个元素
   
   default void remove() {
       throw new UnsupportedOperationException("remove");
   }
   
   default void forEachRemaining(Consumer<? super E> action) {// 遍历迭代器中剩余的元素
       Objects.requireNonNull(action);
       while (hasNext())
           action.accept(next());
   }
~~~

3. **Iterable** 和 **Iterator** 之间的关系
   Iterable 的作用是用来标注当前容器可以使用迭代器遍历 它会返回一个迭代器Iterator
   Iterator 的作用才是迭代功能的实现
   `Iterable是为了foreach循环设计的  Iterable表示集合可以返回Iterator对象  最终还是使用Iterator进行遍历。`

4. 也有人说为什么不直接将Iterator的功能整合到Iterable中？
   我看到的比较合理的解释是：**有些集合类可能不止一种遍历方式**  所以可能有不止一个的迭代器
   如：LinkedList实现了双向遍历和逆序遍历



#### Collection

**概述：**Collection是Java集合容器的顶级接口 和它并列的还有Map映射容器
Collection继承了Iterable 所以Java中的集合容器都是可迭代的

源码解析

~~~java
int size();// 容器的逻辑长度
int hashCode();// 容器的hashCode计算
boolean isEmpty();// 容器为空判断
boolean equals(Object o);// 容器相同判断

boolean contains(Object o);// 容器中是否包含参数元素
boolean containsAll(Collection<?> c);// 容器中是否包含参数集合的所有值

Object[] toArray();// 集合容器转数组
<T> T[] toArray(T[] a);// 也是将集合容器转成数组 但是不同的是可以通过参数指定转换出来的数组

boolean add(E e);// 容器添加元素+
boolean addAll(Collection<? extends E> c);// 容器批量添加 参数也需是集合类型数据

boolean remove(Object o);// 删除指定元素
boolean removeAll(Collection<?> c);// 批量移除 移除指定集合数据
void clear();// 清空
boolean retainAll(Collection<?> c);// 保留参数集合中的元素 删除参数集合中不存在的元素
// 删除所有满足条件的容器元素
default boolean removeIf(Predicate<? super E> filter) {
    Objects.requireNonNull(filter);// 过滤条件不能为null 为空抛出异常：NullPointerException
    boolean removed = false;// 最终返回值 标志是否有删除元素
    final Iterator<E> each = iterator();// 取出容器中的迭代器 通过迭代器循环删除
    while (each.hasNext()) {// 不存在下一个 跳出循环
        if (filter.test(each.next())) {// 判断是否符合过滤条件
            each.remove();// 符合就删除
            removed = true;// 记录删除成功
        }
    }
    return removed;
}

Iterator<E> iterator();// 取容器的迭代器

// 流操作 暂时留空
@Override
default Spliterator<E> spliterator() {
    return Spliterators.spliterator(this, 0);
}
default Stream<E> stream() {
    return StreamSupport.stream(spliterator(), false);
}
default Stream<E> parallelStream() {
    return StreamSupport.stream(spliterator(), true);
}
~~~



#### List

**概述：**List 接口继承于 Collection 接口  `它可以定义一个允许重复的有序集合`

##### 特性

* 有序 可以重复 (顺序为存入的顺序)
* 允许多个null元素对象
* 可以使用迭代器`Iterator`取对象 也可以通过索引下标 
* List没有直接实现迭代器Iterator 而是实现了ListIterator

##### 源码解析

接口分析：

~~~java
//collection中介绍过的方法就直接注释了 不浪费时间
//int size();
//boolean isEmpty();
//boolean contains(Object o);
//boolean containsAll(Collection<?> c);
//boolean equals(Object o);
//int hashCode();
E get(int index);// 根据索引位置 取元素
E set(int index, E element);// 根据索引位置 设置元素
int indexOf(Object o);// 返回指定元素在 容器集合 中的索引位置
int lastIndexOf(Object o);// 返回指定元素在 容器集合 中最后一次出现的位置
List<E> subList(int fromIndex, int toIndex);// 从容器集合中截取一段 返回值是List接口

//Object[] toArray();
//<T> T[] toArray(T[] a);

//boolean add(E e);
void add(int index, E element);// 在指定位置插入元素
//boolean addAll(Collection<? extends E> c);
boolean addAll(int index, Collection<? extends E> c);// 在指定的位置批量插入

E remove(int index);// 删除指定索引位置元素
//boolean remove(Object o);
//boolean removeAll(Collection<?> c);
//void clear();
//boolean retainAll(Collection<?> c);
default void replaceAll(UnaryOperator<E> operator) {// 用指定的操作替换集合容器中的所有元素
    Objects.requireNonNull(operator);
    final ListIterator<E> li = this.listIterator();
    while (li.hasNext()) {
        li.set(operator.apply(li.next()));
    }
}

@SuppressWarnings({"unchecked", "rawtypes"})
default void sort(Comparator<? super E> c) {// 对集合容器中的元素进行排序 参数为比较器
    Object[] a = this.toArray();// 将当前List容器转换成数组
    Arrays.sort(a, (Comparator) c);// 用数组的排序
    ListIterator<E> i = this.listIterator();// 通过迭代器维护顺序
    for (Object e : a) {
        i.next();
        i.set((E) e);
    }
}

//Iterator<E> iterator();
ListIterator<E> listIterator();// 取List的迭代器
ListIterator<E> listIterator(int index);// 取指定位置的迭代器

@Override
default Spliterator<E> spliterator() {
    return Spliterators.spliterator(this, Spliterator.ORDERED);
}
~~~

ListIterator：

~~~java
// List接口的迭代器 =>>  ListIterator
// ListIterator和Iterator的区别在于 ListIterator是可以前后移动的 而Iterator是只能单向的迭代
//boolean hasNext();
//E next();

boolean hasPrevious();// 是否存在上一个元素
E previous();// 取上一个元素

int nextIndex();// 下一个元素的索引位置
int previousIndex();// 上一个元素的索引位置

void remove();// 删除
void set(E e);// 修改
void add(E e);// 添加
~~~



#### ArrayList

##### 概述

- 概述：`底层数据结构是数组` ArrayList是一个动态数组  它允许任何符合规则的元素插入甚至包括null

- 特性：`查改快`、`增删慢`、非线程安全、效率高

- 容量：ArrayList 的初始容量是（10） `ArrayList 存在扩容机制`
  ArrayList 在1.8之前都是直接创建一个长度10的数组 1.8是等到第一次赋值的时候才会创建
  - 扩容机制：**第一次赋值或超过数组容量时才触发扩容    扩容为1.5倍左右** 
    扩容机制是将原来的数组对象复制到新的数组对象中 新的数组长度是原来的1.5倍
    ![image-20210803193244475](img\ArrayList扩容机制.png)

###### 优势

由于底层实现是数组  查找效率为O(1)
因为数组内的对象都是同一类型  所以在查询的时候只需要根据单个对象内存和索引下标就可以找到对应的元素

###### 缺点

增删操作慢 因为增加删除会涉及到元素的复制移动以及数组整体的扩容
另外数组的长度是固定的 相对来说 会造成一定的空间浪费

###### 性能问题

ArrayList 存在两个问题
因为底层的数据结构是数组 数组的长度是固定的 所以在ArrayList数据存储达到最大值时 会触发扩容机制 而`扩容机制相对来说比较消耗性能`
第二个问题是ArrayList在进行头插入和中间插入时 会将所有受到影响的元素 重新排序  而`重新排序相对来说比较消耗性能`

###### 解决方案

针对ArrayList存在的两个问题 也有对应的解决方案
第一个 针对扩容机制 `推荐在数组创建的时候 就指定好数组的长度`
第二个 针对头插入中间插入需要重新排序 `推荐使用尾插入`

###### 画图解析

结构：

![image-20210803191611538](img\ArrayList结构.png)

增删操作：

![image-20210804141544402](img\ArrayList数组结构增删原理.png)

##### 源码解析

###### 构造函数

~~~java
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
public ArrayList() {
    // 在JDK8中无参构造 得到的是一个空数组引用 并没创建数组
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

private static final Object[] EMPTY_ELEMENTDATA = {};
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {// 指定长度大于0 那么创建指定长度的数组
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {// 长度为0 那么也是指向一个预定义的空数组 而不是去创建一个空数组
        this.elementData = EMPTY_ELEMENTDATA;
    } else {// 负数抛出异常
        throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
    }
}

public ArrayList(Collection<? extends E> c) {
    Object[] a = c.toArray();// 将继承了Collection接口的参数 转换成数组
    if ((size = a.length) != 0) {// 先将数组的长度赋值为arraylist的长度 并判断是否不为0
        if (c.getClass() == ArrayList.class) {// 判断继承collection对象的是不是arraylist
            elementData = a;// 如果是直接赋值替换
        } else {
            elementData = Arrays.copyOf(a, size, Object[].class);// 如果不是那就将数组的元素复制进arraylist
        }
    } else {// 如果传进来的collection为空 那么也指向预定义的空数组
        elementData = EMPTY_ELEMENTDATA;
    }
}
~~~

###### 长度方法

为什么要把这个简单的方法单独提出来呢 因为方便下面的理解 
ArrayList中的size并不是数组的长度  他只是一个逻辑长度  只是元素的长度   `数组的长度永远都是固定` 比如长度为10的数组里面只维护了8个元素 size是8

~~~java
// 获取数组的长度
public int size() {
    return size;
}
~~~

###### 添加方法

~~~java
//元素添加方法
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // 判断是否能存储进
    elementData[size++] = e;// 把需要添加的元素放到指定索引位置
    return true;
}
private void ensureCapacityInternal(int minCapacity) {
    ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
}
private static int calculateCapacity(Object[] elementData, int minCapacity) {// 参数minCapacity是当前数组应该有的最小长度
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {// 判断当前数组对象是否是预定义的空数组
        return Math.max(DEFAULT_CAPACITY, minCapacity);// 返回默认的数组长度10 如果传进来的参数大于默认长度 使用传进来的长度
    }
    return minCapacity;// 如果不是直接返回
}
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;// 记录修改次数
    if (minCapacity - elementData.length > 0)// 判断数组添加后的最小长度 是否比当前数组长度更长
        grow(minCapacity);// 扩容
}
private void grow(int minCapacity) {// 扩容
    // overflow-conscious code
    int oldCapacity = elementData.length;// 取当前数组的长度
    int newCapacity = oldCapacity + (oldCapacity >> 1);// 取当前数组位移1后的长度 也就是1.5倍
    if (newCapacity - minCapacity < 0)// 如果新长度小于现在长度
        newCapacity = minCapacity;// 那么取现有的长度
    if (newCapacity - MAX_ARRAY_SIZE > 0)// 如果新长度大于数组的最大值
        newCapacity = hugeCapacity(minCapacity);// 大于数组的最大值 那么取Integer.MAX_VALUE
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);// 使用数组的复制
}

//指定索引位置的添加
public void add(int index, E element) {
    rangeCheckForAdd(index);
    ensureCapacityInternal(size + 1);
    //最重要的就是这句 复制 数组中的元素是能移动的 只能一个个的复制
    System.arraycopy(elementData, index, elementData, index + 1, size - index);
    elementData[index] = element;
    size++;
}
~~~

###### 删除方法

~~~java
// 通过索引删除数组元素
public E remove(int index) {
    rangeCheck(index);
    modCount++;// 记录操作次数
    E oldValue = elementData(index);// 记录需要删除的元素
    int numMoved = size - index - 1;
    if (numMoved > 0)// 逻辑长度减索引再减1 如果大于0 说明不是最后一个元素
        System.arraycopy(elementData, index+1, elementData, index, numMoved);// 删除的元素不是最后一位的话 需要移动受影响的元素 从而保证数组的正常访问
    elementData[--size] = null; // clear to let GC do its work  源代码中这里也写了注解 把数组最后一位的引用置空 让GC去回收
    return oldValue;// 返回前面被删除的对象
}

// 通过对象删除数组元素
public boolean remove(Object o) {
    if (o == null) {// 判断需要删除的元素是否为null
        for (int index = 0; index < size; index++)// 如果是null 那就循环数组进行删除
            if (elementData[index] == null) {
                fastRemove(index);
                return true;
            }
    } else {
        for (int index = 0; index < size; index++)// 如果要删除的元素不是null 那么就通过元素的equals方法进行对比 判断是相同的就删除
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
    }
    return false;
}
private void fastRemove(int index) {// 这个就不写了  基本和索引删除一致
    modCount++;
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work
}
~~~

##### 总结

`ArrayList 本质上就是一个操作数组的容器 在数组的基础上封装了一系列的方法：扩容、复制、序列化、插入、删除、修改`
这里只列举了ArrayList的初始化、添加删除的源码  修改（set）和查询（get）都没有列举  因为逻辑都比较简单没必要在这里浪费时间

根据上面源码的分析 得出的结论是 **当我们需要处理大量数据的时候 频繁插入/删除元素 会使底层数组频繁拷贝 效率不高内存空间浪费**
当然 也有例外 如果我们能在一开始就知道 数据的长度 以及只做尾操作  也是能保证ArrayList的时间复杂度为O(1)



#### LinkedList

##### 概述

- 概述：`底层数据结构是链表 双向链表` **非同步的**
  LinkedList是一个基于链表实现的**双向队列** 所以链表也是双向链表

- 特性：`查改慢`、`增删快`、非线程安全、效率高

- 注意
  - 除了基础的List接口的功能外 在LinkedList中还维护了 头尾两个对象 **可以直接对头尾进行操作** 
    而LinkedList也继承了队列的接口  头尾对象是根据队列的接口维护的 队列接口中还提供了对头尾进行操作的方法
    除了头尾操作外push、pop、poll....等方法也都是有实现 不过这里是作为List接口下的LinkedList来介绍 关于队列的用法 后面可能会补充吧
  - 由于是双向链表所以 **LinkedList 不能随机访问**  因为链表结构决定LinkedList只能通过节点的信息去循环查找（没有索引所以不能直接找到节点）

###### 优势

`增删比较快 时间复杂度是O(1)`
因为不管是头、尾、中间那个地方操作  都影响的节点最多就是前后两个节点
不过需要注意的是 LinkedList 在删除前需要找到需要删除的对象  在指定位置添加前也需要找到指定位置的元素
而查询的过程需要循环查找 所以并不是说LinkedList的增删就一定快

###### 缺点

查改比较慢 时间复杂度是O(n)
LinkedList不能随机访问 因为他没有索引 不能根据索引直接找到数据 `必须通过循环查找`

###### 画图解析

LinkedList结构：

![image-20210804235858072](img\LinkedList结构.png)

堆中的结构：

![image-20210804230257530](img\LinkedList在堆中的结构.png)

##### 源码分析

###### 构造函数

~~~java
// LinkedList的两个构造函数没什么好说的 比较简单
public LinkedList() {
}
public LinkedList(Collection<? extends E> c) {
    this();// 调用无参构造
    addAll(c);// 把传进来的collection参数添加进链表对象
}

// 构造函数没啥好说的  说说LinkedList的属性
// 链表的逻辑长度（跟数组不同逻辑长度就是实际长度）
transient int size = 0;
// 头节点对象
transient Node<E> first;
// 尾节点对象
transient Node<E> last;
// 这个Node就是链表的一个个节点对象  是LinkedList的一个内部类
private static class Node<E> {
    E item;// 当前节点的元素
    Node<E> next;// 下一节点
    Node<E> prev;// 上一节点
    // 注意这里存的是前后节点 而不是前后节点中的元素 因为链表需要通过节点串联起来
    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
~~~

###### 添加方法

~~~java
// 直接添加 相当于尾插入
public boolean add(E e) {
    linkLast(e);// 直接调了尾插入
    return true;
}
// 头插入
public void addFirst(E e) {
    linkFirst(e);// 直接调了头插入
}
// 尾插入
public void addLast(E e) {
    linkLast(e);// 直接调了尾插入
}
// 指定位置插入 并不是说是中间插入 
// 因为如果指定的位置是最后一个 实际就相当于是尾插入
// 不过不能进行头插入 会抛出异常：IndexOutOfBoundsException
public void add(int index, E element) {
    checkPositionIndex(index);// 指定的位置 必须大于零 小于链表的size
    //index >= 0 && index <= size
    if (index == size)// 判断指定位置和逻辑长度
        linkLast(element);// 相等直接调用尾插入
    else
        linkBefore(element, node(index));// 不相等 说明为中间插入
}
// 根据指定位置取节点对象
Node<E> node(int index) {// LinkedList中取元素 除了直接取头尾节点 都是通过这个方法循环取指定位置的节点
    // 性能相对ArrayList的直接根据索引取来说 是比较慢的
    if (index < (size >> 1)) {
        Node<E> x = first;// 先赋头节点的值
        for (int i = 0; i < index; i++)// 循环取到index位置的节点对象
            x = x.next;
        return x;
    } else {// 逻辑同上
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}

void linkLast(E e) {
    final Node<E> l = last;// 先记录原来的尾节点
    final Node<E> newNode = new Node<>(l, e, null);// 把当前传进来的元素包装成节点
    last = newNode;// 把当前节点 设置为新的尾节点
    if (l == null)// 原来的尾节点不存在
        first = newNode;// 把头节点也指向当前节点
    else// 原来的尾节点存在
        l.next = newNode;// 把原尾节点的下一节点引用设置为新尾节点
    size++;// 逻辑长度加1
    modCount++;// 记录修改次数
}
private void linkFirst(E e) {// 头插入的逻辑与尾插入的逻辑 基本一直
    final Node<E> f = first; 
    final Node<E> newNode = new Node<>(null, e, f); 
    first = newNode;
    if (f == null)
        last = newNode; 
    else
        f.prev = newNode;
    size++;
    modCount++;
}
// 中间插入
void linkBefore(E e, Node<E> succ) {// 基本逻辑一致 没啥好说的
    // assert succ != null; 
    final Node<E> pred = succ.prev;
    final Node<E> newNode = new Node<>(pred, e, succ);
    succ.prev = newNode;
    if (pred == null)
        first = newNode;
    else
        pred.next = newNode;
    size++;
    modCount++;
}
~~~

###### 删除方法

~~~java
// 删除指定位置
public E remove(int index) {
    checkElementIndex(index);// 校验index 保证是正确数据 错误数据抛出异常：IndexOutOfBoundsException
    // 校验规则：index >= 0 && index < size
    return unlink(node(index));// 校验通过 直接调用nulink（node方法在上面说了 不再重复）
}
// 删除指定对象 需要equals方法支持
public boolean remove(Object o) {// 参数o是需要删除的对象
    if (o == null) {// 如果需要删除的对象是null
        for (Node<E> x = first; x != null; x = x.next) {// 从头节点开始 取下一节点 一直取到下一节点是null
            if (x.item == null) {// 当前节点元素为null 直接调用unlink移除元素
                unlink(x);
                return true;
            }
        }
    } else {// 逻辑基本同上 唯一不同是判断对象相同的手段 这里用的是equals方法
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) {
                unlink(x);
                return true;
            }
        }
    }
    return false;
}

// 移除指定节点
E unlink(Node<E> x) {// 参数x是需要移除的节点对象
    // assert x != null;
    final E element = x.item;// 取出需要移除节点的元素
    final Node<E> next = x.next;// 取处需要移除节点的下一节点引用
    final Node<E> prev = x.prev;// 取出需要移除节点的上一节点引用

    if (prev == null) {// 如果上一节点为null 说明需要移除的节点是头节点
        first = next;// 直接将LinkedList中的头节点对象设置为 需要移除节点的下一节点
    } else {// 如果不是头节点
        prev.next = next;// 设置上一节点的下一节点为需要移除节点的下一节点
        x.prev = null;// 置空需要移除节点的上一节点
    }

    //处理next的逻辑同上面的prev 不再重复
    if (next == null) {
        last = prev;
    } else {
        next.prev = prev;
        x.next = null;
    }

    x.item = null;// 最后置空需要移除节点的元素本身 （到此为止 链表中已经不存在对item的引用 GC会在何时的时候处理掉item）
    size--;// 逻辑长度更新
    modCount++;// 操作记录加一
    return element;// 返回之前保存的item的引用
}
~~~

###### 查改方法

~~~java
// 查改放在一起是因为逻辑比较简单 这里简单提下
// 取指定位置的元素
public E get(int index) {
    checkElementIndex(index);// 这个校验在删除的时候提过 不在重复
    return node(index).item;// 通过node方法取到 节点对象 返回节点中的元素
}
public E getFirst() {// 直接取头节点
    final Node<E> f = first;
    if (f == null)// 头节点为空抛出异常
        throw new NoSuchElementException();
    return f.item;
}
public E getFirst() {// 直接取尾节点
    final Node<E> f = first;
    if (f == null)// 尾节点为空抛出异常
        throw new NoSuchElementException();
    return f.item;
}

// 修改方法 修改指定位置的元素
public E set(int index, E element) {
    checkElementIndex(index);
    Node<E> x = node(index);
    E oldVal = x.item;
    x.item = element;
    return oldVal;
}
~~~

##### 总结

`LinkedList就是一个实现了双向链表双向对列结构的容器  `
`并对这个双向链表结构的数据实现了：插入、删除、修改、查询、序列化等一系列操作的包装`





#### ~~Vector~~

概述：`底层数据结构是数组`  **Vector 是线程安全的动态数组**   Vector 是同步的  它的操作与 ArrayList 几乎一样

特性：查改快、增删慢、线程安全、效率低  不推荐使用

注意：

- **只要是关键性的操作，方法前面都加了synchronized关键字，来保证线程的安全性**。 
- **Vector的扩容机制是1倍**

#### ~~Stack~~

概述：`Stack实现的是一个后进先出的堆栈` Stack 继承自 Vector 
Stack 提供 5 个额外的方法使得 Vector 得以被当作堆栈使用 
基本的 push 和 pop 方法 还有 

- peek 方法得到栈顶的元素
- empty 方法测试堆栈是否为空
- search 方法检测一个元素在堆栈中的位置



#### Set

- 概述：`Set是一种不包含重复的元素的 Collection 无序` 即任意的两个元素 e1 和 e2 都有 e1.equals(e2)=false  Set最多有一个 null 元素
  `Set 集合中的去重和 hashcode 与 equals 方法直接相关` 通过这两个方法来判断两个对象是否相等
  关于Set的具体实现  我就不做过多介绍 因为HashSet的底层是HashMap的实现 很多功能都是直接套用的HashMap
  TreeSet也一样 基本是TreeMap的套用
- 特性：
  - 无序 不可重复 (`元素的位置是固定的 但顺序不是固定的`)
  - 最多允许一个null元素对象
  - 取对象时只能通过迭代器 `Iterator` 因为没有索引下标



#### HashSet

概述：底层数据结构是HashMap  (无序、唯一、非同步)  它是由 HashMap 实现的
HashSet 允许使用null元素  但只能有一个
**HashSet 按 Hash 算法来存储集合的元素，因此具有很好的存取和查找性能。**

依赖两个方法：hashCode()和equals() 保证元素唯一性

1. hashCode()：
2. equals()：

HashSet底层由HashMap实现
HashSet的值存放于HashMap的key上
HashMap的value统一为PRESENT



#### TreeSet

概述：底层数据结构是红黑树  (唯一、有序)   其底层是基于 TreeMap 实现的  非线程安全
TreeSet 可以确保集合元素处于排序状态。**TreeSet 支持两种排序方式，自然排序和定制排序，其中自然排序为默认的排序方式。**当我们构造 TreeSet 时，若使用不带参数的构造函数，则 TreeSet 的使用自然比较器；若用户需要使用自定义的比较器，则需要使用带比较器的参数。

注意：TreeSet 集合不是通过 hashcode 和 equals 函数来比较元素的. 它是通过 compare 或者 comparaeTo 函数来判断元素是否相等. compare 函数通过判断两个对象的 id，相同的 id 判断为重复元素，不会被加入到集合中。



#### LinkedHashSet

概述：底层数据结构是链表和哈希表  (FIFO插入有序、唯一)
LinkedHashSet 继承自 HashSet  其底层是**基于 LinkedHashMap 来实现的**  有序、非同步
LinkedHashSet 集合同样是根据元素的 hashCode 值来决定元素的存储位置  但是它同时使用链表维护元素的次序   这样使得元素看起来像是以插入顺序保存的
当遍历该集合时候  **LinkedHashSet 将会以元素的添加顺序访问集合的元素。**

1. 由链表保证元素有序
2. 由哈希表保证元素唯一



### Map

#### HashMap

##### 概述

HashMap 键值对、无序、非线程安全、效率高
HashMap允许null值（key和value都允许）  key不能重复
数据结构 在 1.7及之前是：**数组加链表**    1.8是：**数组加链表/红黑树**

##### 优缺点

- 数组 -- 查询效率 O(1)  能直接通过hash值找到位置 不用做其他操作
  不过数组的长度是固定的  如果超出长度  需要进行扩容  而数组扩容是一个比较消耗性能的操作
  总结：`在HashMap中最好一开始指定好长度 减少扩容`
- 链表 -- 查询效率 O(n)  需要通过循环  一个个链过去 不能直接找到数据
  在1.7的JDK中 链表是通过头插入的  在1.8的JDK中 链表是通过尾插入的  尾插入属于优化
  头插的优点在于：比尾插效率更高 不需要遍历找到最后一个节点 只需要把新节点的下一个指向原头节点 把hash桶中的引用改为新节点 
  头插的缺点在于：在多线程的情况下 多个线程一起扩容的时候可能会造成一个循环链表 循环链表会导致永远找不到尾节点 导致死循环
  总结：在HashMap中链表的长度最好控制的比较低 因为长链表影响查询效率 HashMap中给出的长度是8超过就要考虑树化 长度低于6就会转成链表
- 红黑树 -- 查询效率 O(log(n))  需要通过树来查找  决定效率的是树的深度
  因为红黑树的特性：左子节点比根节点小 右子节点比根节点大 可以在查询的时候减少查询次数
  总结：红黑树是一个自平衡的二叉树  在HashMap中最好不要用到  虽然他会提高链表过长导致的查询效率问题（链表过长的情况很少出现 hash碰撞的概率还是比较小的）
  但它是自平衡的 所以需要考虑在插入和删除数据的时候 维护平衡所带来的性能问题 左旋右旋 而且树节点比链表节点更大
  另外需要注意的是HashMap中 树化需要两个条件  第一个是链表的长度超过8 第二个是数组的长度超过64
- 总结：`推荐在使用HashMap的时候 最好给一个初始化容量 减少数组扩容次数 提高性能  也能降低链表的长度 最好不要使用到红黑树`

##### 源码解析

关系结构：

![image-20210808180505556](img\HashMap结构.png) 

###### HashMap内部结构

![image-20210808211021858](img\HashMap内部结构.png)

###### 常量

~~~java
// 数组默认容量 2的四次方 位运算 16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
// 数组最大容量 2的三十次方 Integer的最大值 但这是hash桶的最大值 并不是hashmap的最大值 到这个最大值hash桶就不会再扩容
static final int MAXIMUM_CAPACITY = 1 << 30;
// 负载因子 触发扩容机制的比例
static final float DEFAULT_LOAD_FACTOR = 0.75f;
// 树化的阈值 在链表长度达到8的时候 就可能会触发树化
static final int TREEIFY_THRESHOLD = 8;
// 链表化阈值 在树的长度小于6的时候 就会触发链表化
static final int UNTREEIFY_THRESHOLD = 6;
// 触发树化的最小hash桶值 触发树化的第二个决定条件 hash桶的长度最少要有64才华
static final int MIN_TREEIFY_CAPACITY = 64;
~~~

###### 变量

~~~java
// Hash桶对象
transient Node<K,V>[] table;

transient Set<Map.Entry<K,V>> entrySet;
// 逻辑长度
transient int size;
// 修改次数
transient int modCount;
// 数组容器扩容阈值
int threshold;
// 实际的负载因子
final float loadFactor;
~~~

###### 添加

~~~java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
// 实际的添加数据方法
// 参数：key的hash值 | key | value | 是否覆盖值 | 
final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    // tab hash桶的临时变量
    // p hash桶中新数据需要存入的位置
    // n hash桶长度的临时变量
    // i 新数据需要存入的索引
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // 首先把hash桶赋值给临时变量tab 然后判断hash桶是否为null 或者hash桶长度为零
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;// 符合条件说明 hash桶需要通过resize方法调整大小 最终把调整后的大小赋值给变量n
    // 从hash桶中取数据 数据位置是根据 hash桶长度和key的hash值 求出来的：(n - 1) & hash
    if ((p = tab[i = (n - 1) & hash]) == null)// p的位置 数据为null 说明没有数据
        tab[i] = newNode(hash, key, value, null);// 直接将新数据 创建成node节点存入
    else {// p位置 不为null 说明存在数据
        Node<K,V> e; K k;
        // p的hash 等于 新数据的hash 并且 （新数据的key等于p的key 或者 他们equals相等）
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))// 键相等 说明是需要覆盖数据
            e = p;// 直接把p赋值给变量e
        else if (p instanceof TreeNode)// 如果p是树节点
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);// 执行树节点的数据存入
        else {// 不符合以上两种情况 说明：需要以链表的形式存入数据
            for (int binCount = 0; ; ++binCount) {// 死循环 只有break跳出
                // 通过赋值 e是p的下一节点
                if ((e = p.next) == null) {// p的下一节点为null
                    p.next = newNode(hash, key, value, null);// 将p的下一节点指向新数据
                    if (binCount >= TREEIFY_THRESHOLD - 1) // 判断链表是否需要树化
                        treeifyBin(tab, hash);// 树化操作
                    break;// 结束循环
                }
                // e的hash等于新数据的hash 并且 （e的key等于新数据的key）
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    break;// 新数据等于链表上的一个节点数据 结束循环
                p = e;//不符合 上面两种情况 把e赋值给p p变成了自己的下一节点
            }
            // for循环中 第一个if判断的是下一节点为null没有数据 直接把新数据赋值上去  属于新增操作
            // 第二个if判断的是下一节点的key和新数据的key相等  属于修改操作
        }
        // 通过上面的判断 
        // 1- e可能是hash桶中的第一个节点
        // 2- e可能是树节点
        // 3- e可能是链表上的节点
        if (e != null) {// e不为空 上面三种情况下e不为null 只会是e是原本就存在的数据
            V oldValue = e.value;// 先保留下e原本的数据 oldvalue
            if (!onlyIfAbsent || oldValue == null)// 如果参数onlyIfAbsent为false 或者原数据为null
                e.value = value;// 直接给e赋新值
            afterNodeAccess(e);// 预留位置
            return oldValue;// 返回被覆盖的原数据
        }
    }
    ++modCount;// 记录修改次数
    if (++size > threshold)// 判断逻辑长度是否大于阈值
        resize();// 大于阈值的化 调整容器尺寸
    afterNodeInsertion(evict);// 预留位置
    return null;// 不是覆盖数据的情况 就返回null
}
// 这两个方法是预留的位置  在LinkedHashMap中有实现
void afterNodeAccess(Node<K,V> p) { }
void afterNodeInsertion(boolean evict) { }

// 容器尺寸调整
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;// 保留一个原数组容器
    int oldCap = (oldTab == null) ? 0 : oldTab.length;// 原数组容器的长度
    int oldThr = threshold;// 原阈值
    int newCap, newThr = 0;// 新的长度 新的阈值
    if (oldCap > 0) {// 原数组容器长度大于零
        if (oldCap >= MAXIMUM_CAPACITY) {// 原数组容器长度大于或等于数组最大值
            threshold = Integer.MAX_VALUE;// 阈值最大只能是Integer的最大值 因为threshold是int类型
            return oldTab;// 直接返回原数组容器
        }
        // 通过旧长度计算出新长度 如果新长度小于数组容器最大值 并且 旧长度大于默认数组容器大小
        // 扩容的大小是 oldCap << 1 左移一位 也就是扩容两倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // 这种情况下计算出来的 新长度 = oldCap << 1 新阈值 = oldThr << 1
    }
    else if (oldThr > 0) // 如果原阈值 大于零 原数组容器等于零 （说明是hashmap刚刚初始化）
        newCap = oldThr;// 直接将原阈值 赋给新长度
    else {// 原阈值 原数组容器长度 都为零 一切都取默认值
        newCap = DEFAULT_INITIAL_CAPACITY;// 新长度取默认长度
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);// 新阈值取 默认负载因子 乘 默认长度
    }
    if (newThr == 0) {// 如果 新阈值为零
        float ft = (float)newCap * loadFactor;// 新长度 乘 负载因子
        // 新阈值 = 新长度小于容器最大长度 并且 新长度乘负载因子小于容器最大长度 为真取计算结果 为假取Integer最大值
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
    }
    // 通过上面的一系列判断处理 只会有两种情况
    // 第一：原长度大于等于数组容量最大值 不再进行扩容处理 直接返回原数组容器
    // 第二：重新计算新长度和新阈值 接下来根据新长度进行扩容
    threshold = newThr;// 把新阈值赋给阈值属性
    @SuppressWarnings({"rawtypes","unchecked"})// 用于抑制编译器产生警告信息
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];// 创建一个新长度的数组容器
    table = newTab;// 把新容器赋给原数组
    if (oldTab != null) {// 原数组容器不为空
        for (int j = 0; j < oldCap; ++j) {// 根据原长度循环
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {// 原hash桶节点赋值e e不为空
                oldTab[j] = null;// 原节点先置空
                if (e.next == null)// 如果 原节点不存在下一节点 只有一层
                    newTab[e.hash & (newCap - 1)] = e;// 直接在新数组容器中存放e
                else if (e instanceof TreeNode)// 如果e是树节点类型
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);// 进行树节点的处理
                else { // 剩下的就是链表结构的处理
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;// 下一节点
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {// 如果e在hash桶数组容器中的位置是0 需要考虑null的情况
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);// 循环链表 组成一个链表对象
                    // 将组织好的链表对象 赋到数组容器中
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    // 返回新数组容器
    return newTab;
}
// treeifyBin 树化
final void treeifyBin(Node<K,V>[] tab, int hash) {
    int n, index; Node<K,V> e;
    if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
        resize();// 优先进行数组扩容 而不是树化
    else if ((e = tab[index = (n - 1) & hash]) != null) {
        TreeNode<K,V> hd = null, tl = null;
        do {
            TreeNode<K,V> p = replacementTreeNode(e, null);// 把链表节点替换成树节点
            if (tl == null)
                hd = p;
            else {
                p.prev = tl;
                tl.next = p;
            }
            tl = p;
        } while ((e = e.next) != null);// 循环替换
        if ((tab[index] = hd) != null)
            hd.treeify(tab);// 把该节点连接的所有节点组成一棵树（树化的过程）
    }
}
~~~

###### 删除

```java
public V remove(Object key) {
    Node<K,V> e;
    return (e = removeNode(hash(key), key, null, false, true)) == null ? null : e.value;
}

final Node<K,V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
    Node<K,V>[] tab; Node<K,V> p; int n, index;
    if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {
        Node<K,V> node = null, e; K k; V v;
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
            node = p;// 如果直接能在hash桶数组容器中查到 直接赋值给node
        else if ((e = p.next) != null) {// 不在hash桶中 也就是要么在树节点上 要么在链表上
            if (p instanceof TreeNode)// 是树节点对象 说明在树上
                node = ((TreeNode<K,V>)p).getTreeNode(hash, key);// 获取树节点对象
            else {
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key ||
                         (key != null && key.equals(k)))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);// 循环取链表上节点
            }
        }
        if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
            if (node instanceof TreeNode)// 如果是树节点 通过树节点的删除方法删除
                ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
            else if (node == p)// 如果是hash桶中的头节点 直接把hash桶中的位置指向头节点
                tab[index] = node.next;
            else// 把上一节点的下一节点指向node的下一节点
                p.next = node.next;
            ++modCount;// 记录修改次数
            --size;// 逻辑长度减一
            afterNodeRemoval(node);// 预留位置
            return node;// 返回删除的节点
        }
    }
    return null;
}
```

##### 总结：

hashmap在1.7和1.8之间的区别
1.7是数组加链表
1.8是数组加链表加红黑树







#### TreeMap

**概述：**有序、非线程安全、效率高（O(logN)）  但比不上HashMap (O(1))



#### HashTable

**概述：**无序、线程安全、效率低 数组加链表构成
除构造函数外  HashTable的所有 public 方法声明中都有 synchronized关键字  所以是线程安全的
**HashTable和HashMap的区别 除了同步之外 就是HashTable不支持null（KV都不允许）**



#### ConcurrentHashMap

##### 概述

线程安全的HashMap 性能高于HashTable
ConcurrentHashMap 同样也分为 1.7 、1.8 版，两者在实现上略有不同

1.7 的实现：如图所示 是由 Segment 数组、HashEntry 组成   和 HashMap 一样  仍然是数组加链表

![image-20210830153540138](img\ConcurrentHashMap1.7实现.png)

##### Segment 源码

Segment 是ConcurrentHashMap的内部类

~~~java
static final class Segment<K,V> extends ReentrantLock implements Serializable {
    private static final long serialVersionUID = 2249069246763182397L;
    // 和 HashMap 中的 HashEntry 作用一样，真正存放数据的桶
    transient volatile HashEntry<K,V>[] table;
    transient int count;
    transient int modCount;
    transient int threshold;
    final float loadFactor;
}
~~~

![image-20210830154157661](img\1.7的HashEntry结构.png)

可以看出来跟 HashMap 基本没啥差别 （唯一的差别的就是用 volatile 修饰了 value 和 next volatile 的作用我就不解释了）

原理上来说：**ConcurrentHashMap 采用了分段锁技术**，其中 Segment 继承于 ReentrantLock。不会像 HashTable 那样不管是 put 还是 get 操作都需要做同步处理，理论上 ConcurrentHashMap 支持 CurrencyLevel (Segment 数组数量)的线程并发。每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment。

**1.8 在 1.7 的数据结构上做了大的改动**：

- 采用红黑树之后可以保证查询效率（`O(logn)`）
- 甚至取消了 ReentrantLock 改为了 synchronized
- 也使用到了CAS



##### 总结：

**简单的来说 ConcurrentHashMap 就是在HashMap的基础上又加了一层 segment**

segment 的长度也决定了 ConcurrentHashMap 的并发程度

而 ConcurrentHashMap 保证线程安全的办法是：

- 成员变量通过 volatile 标识
- put 的时候通过 CAS、synchronized 保证

![image-20210830164734719](img\ConcurrentHashMap结构.png)



## 并发编程

#### 基础认知

**并发：**并发的关键是你有处理多个任务的能力  不一定要同时

**并行：**并行的关键是你有同时处理多个任务的能力

**同步：**通过控制和调度  来保证多线程中共享资源的数据一致性

 线程安全：用来描绘一段代码 指在并发的情况之下 该代码经过多线程使用 线程的调度顺序不影响任何结果
 就是指一段代码在处理多个任务的过程中 `不会因为多个任务而影响最终的结果`

##### 线程状态

 ![image-20210727164712902](img\线程状态.png)

##### 线程池

###### 定义

线程池 其实就是一种基于`池化思想`的管理线程的工具
线程池 维护多个线程  等待监督管理者分配可并发执行的任务

- 避免了处理任务时创建销毁线程开销的代价
- 避免了线程数量膨胀导致的过分调度问题  保证了对内核的充分利用

###### 好处

- `降低资源消耗`：通过池化技术重复利用已创建的线程，降低线程创建和销毁造成的损耗。
- `提高响应速度`：任务到达时，无需等待线程创建即可立即执行。
- `提高线程的可管理性`：线程是稀缺资源，如果无限制创建，不仅会消耗系统资源，还会因为线程的不合理分布导致资源调度失衡，降低系统的稳定性。使用线程池可以进行统一的分配、调优和监控。
- `提供更多更强大的功能`：线程池具备可拓展性，允许开发人员向其中增加更多的功能。比如延时定时线程池ScheduledThreadPoolExecutor，就允许任务延期执行或定期执行。



#### 线程创建

##### Thread

~~~java
public static void main(String[] args) {
    System.out.println("主线程启动："+Thread.currentThread().getName());
    new Thread02().start();
}

public class Thread02 extends Thread {
    @Override
    public void run() {
        System.out.println("子线程启动："+Thread.currentThread().getName());
    }
}
~~~

- 通过继承thread类实现的多线程 虽然实现的是`run`方法  但是要启动线程的话 应该是通过`start`方法 
  启动了多少次start就相当于启动了多少个子线程

  run方法相当于只是指定了子线程中需要处理的事情  而真正的创建、启动线程是start方法

  `start方法也只是将线程调整到准备就绪的状态  cpu并不一定是立马执行 需要等待调度`

- run方法中的代码执行完毕后 线程就是死亡

- 不推荐使用 避免OOP单继承局限性

##### Runnable

~~~java
public static void main(String[] args) {
    // 通过lambda表达式的形式实现runnable接口 启动线程
    new Thread(()->{System.out.println("子线程启动："+Thread.currentThread().getName());}).start();
}
~~~

- 一般都是使用匿名内部类或者`lambda表达式`的形式实现runnable接口 实现新线程的启动 本质上thread类也是实现了runnable接口的 构造函数中也是可以接口runnable接口的 所以才支持这样的线程启动方式
- 推荐使用 避免了单继承的局限性

##### Callable

~~~java
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 实现任务对象
        FutureTask<String> task = new FutureTask<String>(() -> {
            System.out.println("子线程启动" + Thread.currentThread().getName());
            return "成功了";
        });
        // 将任务对象传递到新线程 启动
        new Thread(task).start();
        // 获取打印任务返回的结果
        System.out.println(task.get());
    }
~~~

- 与runnable接口不同 callable接口可以实现有返回值的线程创建  并且可以抛出异常
- 实现了callable接口任务 需要装载futuretask对象中 因为futuretask对象继承了runnable接口 所以可以通过futuretask对象来启动线程 多线程任务的返回结果可以通过futuretask获取
- 通过get获取线程任务的返回值  需要注意的是 get可能会造成阻塞  因为在get的时候是在等待线程任务完成 如果在get之前线程任务完成了 那么就不会造成阻塞

##### 线程池

~~~java
public static void main(String[] args)  {
    // region thread pool 实现方式
    // 使用executors创建一个线程池
    ExecutorService executorService = Executors.newCachedThreadPool();
    
    // 线程池实现runnable接口启动线程
    executorService.execute(() -> {
        System.out.println("子线程启动：" + Thread.currentThread().getName());
    });

    // 线程池实现callable接口启动线程
    Future<?> submit = executorService.submit(() -> {
        System.out.println("子线程启动：" + Thread.currentThread().getName());
        return "成功了";
    });
    // 还是通过futuretask获取返回值
    System.out.println(submit.get());
    // endregion
}
~~~

- 线程池的话 不用手动创建thread类  而是通过线程池的Executors框架来创建线程开启线程
- 使用Executors对象创建线程池 返回一个线程服务对象 通过线程服务对象 来操作和管理线程
- 线程池种类：
  - newCachedThreadPool  创建一个`可缓存线程池`
    如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
  - newFixedThreadPool 创建一个定长线程池
    可控制线程最大并发数  超出的线程会在队列中等待
  - newScheduledThreadPool 创建一个定长线程池
    支持定时及周期性任务执行
  - newSingleThreadExecutor 创建一个`单线程化的线程池`
    它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

##### @Async

~~~java
@Async
public void springThread() throws InterruptedException {
    Thread.sleep(3000);
    System.out.println("子线程启动："+Thread.currentThread().getName());
}
~~~

- 标记了@Async的方法在spring中将被认作异步方法  在调用的时候会开启新线程来处理
  需要注意@Async方法不能和调用者在同一个类中
- spring的@async注解底层实现应该使用了线程池  这里不做过深的研究



#### 常见方法

##### run

作用：子类通常需要重写Thread类中的run()方法，run()中写你想让该线程执行的代码。

##### start

作用：启动当前线程  并调用当前线程中的run()方法

并不是真正的启动 只是把当前线程状态调整为准备就绪 可以被cpu调度

##### yield

作用：释放当前线程的操作

执行yield()方法，cpu会释放当前线程，去执行其它的线程，过后还是会继续执行该线程，
具体什么时候执行该线程，看cpu调度。这里放弃的应该时间片段

##### join

作用：阻塞当前线程 等待join的线程完成

如果在线程a中调用了线程b的join()方法，此时线程a就会进入阻塞状态，直到线程b完全执行完以后，线程a才会结束阻塞状态，由于join方法中会抛出异常，所以我们还需要try-catch来捕捉处理异常

##### sleep

作用：阻塞当前线程 并定时唤醒 不会释放锁

sleep()方法内的形参为long millitime,调用该方法会让当前线程“睡眠”指定的millitime（毫秒），在指定的millitime毫秒的时间内，当前的线程是阻塞状态。

##### isAlive

作用：判断当前线程是否活跃

isAlive()方法用来判断当前线程是否存活，如果当前线程存活，返回true,如果消亡，返回false

##### currrentThread

作用：返回当前执行代码的线程，该方法为静态方法，直接通过Thread类名调用

##### wait

作用：线程自动释放其占有的对象锁 并等待notify

wait notify notifyall 三个方法 也是与线程相关的方法  但是`这三个方法是用Object提供的`

需要注意的是 `使用这三个方法的时候当前线程必须获取到当前对象的锁` 如果没有那么会抛出异常

##### notify

作用：唤醒一个正在wait当前对象锁的线程  并让它拿到对象锁

具体唤醒哪一个线程由虚拟机控制

##### notifyAll

作用：唤醒所有正在wait前对象锁的线程

被唤醒的线程 它们只是跳出了wait状态 接下来它们还会是竞争对象锁



#### 线程安全

##### 锁概念

在Java中 保证线程安全的方案分为两种：同步、无同步、

造成线程安全问题的原因就是：多线程情况下共享变量的写操作不安全  所以解决方案也就是针对共享的变量进行的 同步共享变量 或者 不同步共享变量

![image-20210729191933444](img\Java保证线程安全手段.png)

###### 同步

- 互斥(阻塞)：互斥是实现同步的一种手段 属于**悲观的并发策略**

  `多线程并发访问共享数据时 保证共享数据在同一时刻只被一个线程使用`  临界区、互斥量和信号量都是主要的互斥实现方式

  Java中实现互斥的手段：

  - synchronized 关键字
  - lock 接口

  互斥最主要的问题就是进行`线程阻塞和唤醒`所带来的性能问题

- 非阻塞：**基于冲突检测的乐观并发策略**

  非阻塞的同步 其实就是在多线程访问共享变量的时候 先操作的线程会成功 后面的线程会失败 而失败的线程并不会阻塞挂起 而实采取其他的补偿措施 比如不断重试直到成功

  非阻塞的实现 CAS：

  - CAS指令需要有3个操作数，分别是内存地址（在java中理解为变量的内存地址，用V表示）、旧的预期值（用A表示）和新值（用B表示）
  - CAS指令执行时，CAS指令指令时，当且仅当V处的值符合旧预期值A时，处理器用B更新V处的值，否则它就不执行更新，但是无论是否更新了V处的值，都会返回V的旧值，上述的处理过程是一个原子操作。

  CAS的缺点：

  - ABA问题：因为CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，但是一个值原来是A，变成了B，又变成了A，那么使用CAS进行检查时会发现它的值没有发生变化，但是实际上却变化了。

    ABA问题的解决思路：使用版本号 记录修改次数
  
  在Java中`java.util.concurrent.atomic`包下面的原子变量类就是使用了乐观锁的一种实现方式**CAS**实现的：
  
  - 基本类型
  
    - `AtomicBoolean` - 布尔类型原子类
  
    - `AtomicInteger` - 整型原子类
  
    - `AtomicLong` - 长整型原子类
  
      .......

###### 无同步

- 对象不共享

  直接不使用共享变量

- 不可变对象

  多线程对共享变量只能进行读操作

- 线程本地变量

  直接把变量交给线程封闭起来 这样也不会被其他线程共享 自然也就不会有安全问题

###### 总结

- Java中线程安全的解决方案可以简单的理解为 **同步** **不同步** 两种 --> 针对共享变量
- 在同步方案中又分为 **互斥同步**(悲观实现) 和 **非阻塞同步**(乐观实现) 两种 --> 针对同步方式
- 而不同步方案 其实就是只对共享变量进行读操作 或者直接没有共享变量 --> 针对程序设计

##### synchronized

###### 概述

synchronized是Java中的关键字 可以给方法和代码块加锁  (synchronized底层好像是通过C实现的) 
可以保证方法或者代码块在运行时  同一时刻只有一个线程可以进入到临界区（临界区就是synchronized锁住的区域）
Java中每一个对象都可以作为锁  这是synchronized实现同步的基础 这里需要知道的是为什么Java的每个对象都可以作为锁
因为在Java中每个对象都有一个内置锁(监视器,也可以理解成锁标记)，而synchronized就是使用对象的内置锁(监视器)来将代码块(方法)锁定的

- synchronized可以给方法和代码块加锁
- 加锁解锁的过程都是自动的 
- synchronized关键字编译之后
  会在同步块的前后分别形成`monitorenter`和`monitorexit`这两个字节码指令 
  这两个字节码指令都需要一个reference类型的参数来指明要锁定和解锁的对象

###### 示例

~~~java
// 直接加在方法上 相当于当前方法为同步方法
// 加锁对象的话 实例类是this 静态类是class对象 （区别在于 一个锁住的是一个单独的实例 一个是整个静态类对象）  
public synchronized void Test1() {
    System.out.println("-----Test1------");
}

// 直接使用synchronized关键字 实现同步代码块 加锁对象程序员指定 一般指定为this
public void Test2(){
    synchronized (this){
        System.out.println("-----Test2------");
    }
}
~~~

###### 优点

- 加锁 解锁 过程都是底层帮我们处理了
- java的关键字 jvm原生支持

###### 缺点

- 效率低，`试图获取锁时不能设定超时时间`
  未得到锁的线程只能不停的尝试获得锁  而不能中断  高并发的情况下会导致性能下降
- 不够灵活  只能使用某个对象作为锁  且加锁和释放的时机单一
- 无法知道是否成功获得锁
- 锁的类型不可更改：可重入 不可中断 非公平

###### 优化

synchronized 属于重量级锁 1.6后Java对他进行了优化 出现了一个锁升级的概念（`锁是不能降级的`）
升级路线是：无锁 =》 偏向锁 =》 轻量级锁 =》 重量级锁

- 无锁：没有线程访问同步代码块时的状态
- 偏向锁：当有一个线程访问同步代码块时  无锁的就会升级为偏向锁 `偏向当前线程`（也就是第一个访问同步代码块的线程）
- 轻量级锁：当出现锁竞争的时候 偏向锁就会升级为轻量级锁
  轻量级锁也就是自旋锁 线程会自旋十次 `自旋是指竞争失败的线程循环获取锁`（正常应该是竞争失败的线程阻塞）
  失败十次后 就会锁膨胀 （线程的自旋次数可以自己指定）
- 重量级锁：在轻量级锁自旋失败后 就会升级为重量级锁 也就是synchronized本来的级别

##### lock

###### 概述

本质上Lock仅仅是一个接口
Lock有三个实现类

- ReentrantLock：表示重入锁  它是唯一一个实现了Lock接口的类
  重入锁指的是线程在获得锁之后 再次获取该锁不需要阻塞 而是直接关联一次计数器增加重入次数
- ReentrantReadWriteLock：重入读写锁 它实现了ReadWriteLock接口
  在这个类中维护了两个锁
  - ReadLock：读锁
  - WriteLock：写锁

###### 示例

~~~java
Lock l = ...; //根据不同的实现Lock接口类的构造函数得到一个锁对象 
l.lock(); //获取锁位于try块的外面 
try { 
      // 代码块
} finally { 
     l.unlock(); // 推荐把锁放到finally中 这样不管怎样都会被释放
}
~~~

###### 接口方法

~~~java
//尝试获取锁，获取成功则返回，否则阻塞当前线程
void lock(); 

//尝试获取锁，线程在成功获取锁之前被中断，则放弃获取锁，抛出异常 
void lockInterruptibly() throws InterruptedException; 

//尝试获取锁，获取锁成功则返回true，否则返回false 
boolean tryLock(); 

//尝试获取锁，若在规定时间内获取到锁，则返回true，否则返回false，未获取锁之前被中断，则抛出异常 
boolean tryLock(long time, TimeUnit unit) throws InterruptedException; 

//释放锁
void unlock(); 

//返回当前锁的条件变量，通过条件变量可以实现类似notify和wait的功能，一个锁可以有多个条件变量
Condition newCondition();
~~~

推荐文章：[一文带你理解 Java 中 Lock 的实现原理](https://juejin.cn/post/6844903680399917069)

#### Java锁分类

在Java中有很多锁的名词 如：偏向锁、轻量级锁、重量级锁、悲观锁、乐观锁、公平锁、非公平锁、可重入锁、不可重入锁、自旋锁、自适应自旋锁、共享锁、独享(排他)锁

虽然听起来好像有很多锁  但他们都是根据锁的特性对锁的分类 并不是真正的锁

| 锁/类型                | 公平/非公平锁 | 可重入/不可重入锁 | 共享/独享锁          | 乐观/悲观锁 |
| ---------------------- | ------------- | ----------------- | -------------------- | ----------- |
| synchronized           | 非公平锁      | 可重入锁          | 独享锁               | 悲观锁      |
| ReentrantLock          | 都支持        | 可重入锁          | 独享锁               | 悲观锁      |
| ReentrantReadWriteLock | 都支持        | 可重入锁          | 读锁-共享，写锁-独享 | 悲观锁      |

![image-20210728231919318](img\Java主流锁.png)

##### 悲观锁 乐观锁

- synchronized 和 lock 都是悲观锁的实现
- Java中原子包下的类 是ACS的实现

悲观锁 乐观锁 在Java中的实现：

~~~java
// ------------------------- 悲观锁的调用方式 -------------------------
// synchronized
public synchronized void testMethod() {
	// 操作同步资源
}
// ReentrantLock
private ReentrantLock lock = new ReentrantLock(); // 需要保证多个线程使用的是同一个锁
public void modifyPublicResources() {
	lock.lock();
	// 操作同步资源
	lock.unlock();
}

// ------------------------- 乐观锁的调用方式 -------------------------
private AtomicInteger atomicInteger = new AtomicInteger();  // 需要保证多个线程使用的是同一个AtomicInteger
atomicInteger.incrementAndGet(); //执行自增1
~~~

###### 升锁 -- 无锁、偏向锁、轻量级锁、重量级锁

###### 共享锁 排他锁

###### 可重入锁 不可重入锁

###### 公平锁 非公平锁



## 网络编程	

#### Servlet

概念：

本质：

使用：



#### 过滤器



#### 拦截器



#### 监听器





## 设计模式

目标：

1. 模式的介绍：名 字、别名、摘要以及模式的用意
2. 模式的结构：UML图，java源代码
3. 模式的优势和劣势
4. 模式应用的场景
5. 关于模式的实现理论：说明模式在java中实现的时候会遇到的问题，以及相应的解决方案
6. 举例
7. 相关的模式





![img](https://picx.zhimg.com/80/v2-52e457eeca30e8bb5ef827948cc12c2f_720w.webp?source=1940ef5c)



### 六大原则



#### 开闭原则

开闭原则的意思是：**对扩展开放，对修改关闭**。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。

`简单说就是尽量使用接口和抽象类 便于后期程序扩展`



#### 里氏转换原则

里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，而派生类也能够在基类的基础上增加新的行为。**里氏代换原则是对开闭原则的补充**。实现开闭原则的关键步骤就是抽象化，而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

`简单说就是子类可以复用基类 所以说里氏原则是对开闭原则的补充`



#### 依赖倒转原则

这个原则是开闭原则的基础，具体内容：**针对接口编程，依赖于抽象而不依赖于具体。**



#### 接口隔离原则

使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。

`简单说就是 接口内的方法尽量少 降低接口的耦合`



#### 迪米特法则

一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。



#### 合成复用原则

尽量使用合成/聚合的方式，而不是使用继承。





### 创建型



#### 工厂模式



#### 抽象工厂模式



#### 单例模式



#### 建造者模式



#### 原型模式









### 结构型



#### 适配器模式



#### 桥接模式



#### 过滤器模式



#### 组合模式



#### 装饰器模式



#### 外观模式



#### 享元模式



#### 代理模式









### 行为型



#### 责任链模式



#### 命令模式



#### 解释器模式



#### 迭代器模式



#### 中介者模式



#### 备忘录模式



#### 观察者模式



#### 状态模式



#### 空对象模式



#### 策略模式



#### 模板模式



#### 访问者模式








