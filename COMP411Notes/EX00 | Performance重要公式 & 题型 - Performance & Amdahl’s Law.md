# EX00 | Performance重要公式 & 题型 - Performance & Amdahl’s Law

## 重要公式
1. Performance = 1 / Execution time <- 一个电脑的 performance 是其**执行一个程序需要的时间的倒数**
	1. 派生公式：Performance(x) / Performance(y) = Execution(y) / Execution(x)
	2. 比较两个程序的 Performance: Performance(x) / Performance(y) = n 
		1. 常见表述 -> A is **n times faster** than B = A is **n times as fast as** B = A is **faster than** B **by a factor of n**
2. Pitfalls of using %:
	1. 常见表述：**By how much** is A **faster** than B?
		1. 使用公式：(Performance(A) - Performance(B)) / Performance(B)
	2. 常见表述：**By how much** is B **slower** than A?
		1. 使用公式：(Performance(B) - Performance(A)) / Performance(A)
		2. 注意上面问的是 Slower。我们直接套公式的时候求的是 faster，所以求出来的百分比需要去掉负号（如果有的话；不过如果没有的话大概率是求错了23333）。
3. Amdahl’s Law: Time(Improved) = (Time(Affected) / Rate(Speedup)) + Time(Unaffected)
	1. 用于计算程序中可以优化的幅度。
	2. 一个程序的优化最终会被这个程序内部**不能优化的部分**所限制
		1. 类比：可以无限提升最高限速，但是不能取消红绿灯。只要红绿灯依然存在，就一定会有个运行时间的下限。
	3. Rate 的数值 = “we need to improve *this rate* in order to …”
		1. 比如，如果 Rate = 16x，我们会说 ”We need to *improve the speed of xxx by 16x* if we want the program to run xxx times faster”.
		2. 其他例子参考下面的经典题型。
- - - -
#### 重要公式列表（手写）
![](EX00%20%7C%20Performance%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B%20-%20Performance%20&%20Amdahl%E2%80%99s%20Law/bear_sketch@2x.png)
- - - -
## 经典题型
#### 给定两个 performance，求 n
> Suppose car A has a fuel efficiency of 12 mpg (miles per gallon), and car B gives 23 mpg. Complete each of the following statements:  
> 1. B is ( ) times as fuel efficient as A  
> 2. B has ( )% higher fuel efficiency than A  
> 3. For the same travel distance, A consumes ( ) times as much fuel as B  
> 4. For the same amount of fuel, A travels ( )% less distance than B  
- 计算方法：直接套用公式进行计算，记得看清楚问的是什么。
	- 对于直接问 ( ) times 的问题 -> 问n
	- 对于问 ( )% higher & lower 的问题 -> 问 ∆ / Performance (**than后面对应的那一项**)
		- 比如第二问，就应该是 ∆ / Performance(A)，因为 than 后面接的是A。
![](EX00%20%7C%20Performance%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B%20-%20Performance%20&%20Amdahl%E2%80%99s%20Law/bear_sketch@2x%202.png)

#### 给定 Relative Performance (n) 以及一个的运行时间，求第二个的运行时间
> Suppose computer A is **2 times faster than** computer B. Suppose A takes 5 seconds to execute a program. How much time does B take to execute the same program?  
- 计算方法：直接套用公式计算，记得看清楚relative performance的表述是什么。
	- 一般而言后面不接百分比的Relative Performance都是代表n。
	- 对于上面这题就是要我们求`Perf(A) / Perf(B)`，因为问的是**2 times faster than**，参考公式部分的常见表述。
![](EX00%20%7C%20Performance%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B%20-%20Performance%20&%20Amdahl%E2%80%99s%20Law/bear_sketch@2x%203.png)

#### Amdahl’s Law，给定一系列操作的详细数据，然后 improve 某一项操作，求新的操作时间。
> Consider a computer running a program that requires **200 seconds**, with **80 seconds** spent executing floating-point (FP) instructions, **40 seconds** spent executing integer (INT) instructions, **35 seconds** spent executing branch (B) instructions, and **45 seconds** executing load/store (L/S) instructions.  
> By how much is the total time reduced if the time for **FP operations** is reduced by 20%?  
- 解题思路：直接套用公式，分清楚哪些是 Time(affected) 哪些是 Time(unaffected)。
![](EX00%20%7C%20Performance%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B%20-%20Performance%20&%20Amdahl%E2%80%99s%20Law/bear_sketch@2x%204.png)

#### 通过使用 Amdahl’s Law 判断某个优化指标是否可以达到。
> Suppose a program runs in 100 seconds on a machine, where multiplies are executed 80% of the time. How much do we need to improve the speed of multiplication if we want the program to run 5 times faster ?  
- 解题思路：先计算达到指标后的新的时间，然后和**不能优化的部分**进行比较。
	- 上一题中 Time(Improved) = 100 * 20% = 20s，但是通过计算得到 Time(Unaffected) = 20s，所以**无法优化**。
![](EX00%20%7C%20Performance%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B%20-%20Performance%20&%20Amdahl%E2%80%99s%20Law/bear_sketch@2x%205.png)
