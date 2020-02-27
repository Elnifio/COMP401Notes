# Java | Splay Tree & Directed Graph JUnit单元测试
> 单元测试是什么，单元测试是谁？单元测试是什么意思出自哪里？ 今天小编就来帮助大家了解一下单元测试到底是什么。  

Splay Tree 的单元测试在[这里](https://github.com/Elnifio/COMP401Notes/blob/master/Notes/Java%20%7C%20Splay%20Tree%20%26%20Directed%20Graph%20JUnit%20%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95/JUnit_SPLT.java)。

Directed Graph 的单元测试在[这里](https://github.com/Elnifio/COMP401Notes/blob/master/Notes/Java%20%7C%20Splay%20Tree%20%26%20Directed%20Graph%20JUnit%20%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95/Graph_JUnit.java)。

- - - -

##### Splay Tree JUnit 使用方法

- 将其放到对应的文件夹就可以用了。
- 无法自动导入JUnit 5 的同学可以尝试：
  - 右键对应的 Package
  - 弹出菜单中选择 New -> JUnit Test Case
  - 一路下来疯狂 Next Next Next
  - 创建好对应的 JUnit 后，将 Splay Tree 的单元测试内容复制粘贴到这个文件里面来
  - Fix Bugs，然后点 Run 就成了（大概吧
	
- 使用的OO Signature，其中我们假定：
  - 这个树的每个节点是 BST_Node 类（之前写Splay Tree的时候直接把BST的代码拿来用了，尴尬）
  - 每个节点保存的内容是一个String。

```Java
insert(String s) -> void; 
remove(String s) -> void;
findMin() -> String;
findMax() -> String;
empty() -> Boolean;
contains(String s) -> Boolean;
size() -> Integer;
height() -> Integer;
getRoot() -> BST_Node;
```
- 整体上的 OO Signature & Axioms 和 BST 的差不多吧大概

- - - -

##### Graph JUnit 使用方法

- 将其放到对应的文件夹就可以用了。
- 注意请自行阅读注释并且修改对应内容！
  - 例如，在30～32行，我们测试这个 Graph 是否成功加入了这个 Vertex，这个时候我们选择访问这个 Graph 对象的 Internal Field: Vertices。在我的 Implementation 中，这个 Field 是一个 HashMap。但是对于其他的 Implementation，你需要更改这部分的内容来保证测试能完整运行。
  - 这些内容基本只在 Test Add Node 部分出现，用于测试 AddNode 是否正常运行。应该需要改的内容很少。

- 使用的 OO Signature：

```Java
addNode(long idnum, String label) 
    -> False if: 
    	1. idnum not unique; 
	2. idnum < 0; 
	3. label not unique; 
	4. label is null. 
    -> True otherwise;

addEdge(long idNum, String source_label, String destination_label, long weight, String edge_label) 
    -> False if: 
    	1. idnum not unique or less than 0; 
	2. source or destination label does not exist; 
	3. there already have an edge on these two nodes;
    -> True otherwise;

delNode(String label)
    -> False if: Node label does not exist;
    -> True otherwise;

delEdge(String sLabel, String dLabel)
    -> False if: 
    	1. Start label does not exist; 
	2. Destination label does not exist; 
	3. Edge from the start to destination label does not exist;
    -> True otherwise;
    
numNodes() -> Integer;

numEdges() -> Integer;
```

- - - -

- 注意事项
	- 单元测试全部通过并不代表程序完全成功运行，请自己排除其他疏漏内容
	- 请根据要求自行更改单元测试内的对应内容
	- 请根据 OO Signature 自行更改 JUnit 中的 method 名和 interface 名
	- 请仔细阅读测试文件中的注释内容
	
- - - -

没有了。

> 好了，以上就是单元测试的含义和出处。希望小编精心整理的这篇内容能够解决你的困惑。  
