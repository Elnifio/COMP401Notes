# Java | Priority Queue & Binary Heap JUnit单元测试
> 单元测试是什么，单元测试是谁？单元测试是什么意思出自哪里？ 今天小编就来帮助大家了解一下单元测试到底是什么。  

单元测试在[这里](https://github.com/Elnifio/COMP401Notes/blob/master/Notes/Java%20%7C%20Priority%20Queue%20%26%20Binary%20Heap%20JUnit%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95/A3_JUnit.java)。
- - - -
- 使用方法
	- 将其下载到对应的文件夹中就能用了
	
- 使用的OO Signature
```Java
insert(EntryPair entry) -> void;
delMin() -> void;
getMin() -> EntryPair;
size() -> int;
build(EntryPair[] entries) -> void;
getHeap() -> EntryPair[];
// --------
EntryPair: String s, int priority
```
（好像没有 Axioms？）
- 注意事项
	- **单元测试全部通过并不代表程序完全成功运行，请务必自己排除其他疏漏内容**
	- **请务必根据要求自行更改单元测试内的对应内容**
	- 特别的，我们需要注意到针对**负数priority的处理**。这里assume**负数priority是可以接受的**，你需要根据自己的要求自行更改这里的内容和测试内容。
	- 请根据 OO Signature 自行更改 JUnit 中的 method 名和 interface 名（比如 EntryPair 这个类，在这里代表的是一个 String 和 Priority 的组合）
	- 请务必仔细阅读测试文件中的注释内容
- - - -
没有了。

> 好了，以上就是单元测试的含义和出处。希望小编精心整理的这篇内容能够解决你的困惑。  
