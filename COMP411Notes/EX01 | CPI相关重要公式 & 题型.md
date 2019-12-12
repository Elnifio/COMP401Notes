# EX01 | CPI相关重要公式 & 题型

## 重要公式
1. `CPU Time = # Cycles * Cycle time` <- 运行程序的时间 = 运行程序需要的 Clock Cycle 数量 * 单个 Clock Cycle 的时间。
	1. 单位换算：CPU Time单位是sec，# Cycles单位是1，Cycle Time单位是sec
	2. 派生公式：`CPU Time = #Cycles / Clock Rate` (Clock Rate单位是Hz - Cycles / sec)
	3. Clock Rate 和 Cycle Time 之间的关系：`Cycle Time = 1 / Clock Rate`
	4. 一般而言，`Execution Time = CPU Time` （除非题目特别声明）
2. `#Cycles = #Instructions * CPI` <- Cycles 数量 = Instructions 数量 * CPI
	1. 派生公式：`CPU Time = #Instructions * CPI * Cycle Time` = `(#Instructions * CPI) / Clock Rate`
		1. 解释：运行一个程序需要的时间 = 程序拥有的 Instructions 数量 * 执行一个 Instruction 需要的 Cycle 数量 * 一个 Cycle 对应的时间
	2. 派生公式：`CPI = #Cycles / #Instructions`
3. MIPS - Millions of instructions per second = `(#Instructions in Millions) / sec`
	1. 派生公式：`MIPS = (Clock Rate in MHz) / CPI`
		1. 推导方法：![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x.png)
		(这里1M = 1000) -> 1GHz = 1000 MHz
- - - -
## 重要公式列表（手写）
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%202.png)
- - - -
#### 单位列表（手写）
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%203.png)
- - - -
## 经典题型
#### 给定Clock Rate和CPI，求 # Ins / sec
> Consider three different processors, P1, P2, P3 executing the same instruction set:  
> - P1 has 5 GHz clock rate and a CPI of 2  
> - P2 has a 6 GHz clock rate and a CPI of 1.2  
> - P3 has a 8 GHz clock rate and a CPI of 2.4  
> What is the rate of instructions executed for each processor, i.e., how many instructions executed per second?   
- 计算方法：通过单位计算 - `Instructions / sec = (Instructions / cycles) * (cycles / sec) = Clock Rate / CPI`
- 注意，这里我们没有对应的公式，所以只能通过单位来换算。这样的思路同样适合于其他关于这些的题型。在我们找不到公式的时候，不妨从单位入手分析。公式分析和选择 P1 processor 的分析如下：![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%204.png)

#### 给定Clock Rate & CPI & Time，求 # Cycles & # Instructions
> （本题接上题条件）The processors each execute a program for 20 seconds.   
> - How many cycles have passed for each processor?   
> - How many instructions has each processor executed?   
对于 # Cycles 计算方法：套用 公式2 -> 派生公式1 -> 移项: `#Cycles = CPU Time * Clock Rate`;

对于 # Instructions 计算方法：
	1. 直接用上题中计算出的 # Ins / sec 与 Times 相乘。
	2. 套用 公式3 -> 移项: # Ins = # Cycles / CPI
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%205.png)

#### 给定提升百分比，通过代入公式约分化简得到 “为了达到这个整体提升的效果，各部分需要提升多少” 计算的百分比（好像不会说中文了）
> We are trying to reduce the execution time by 25% but the hardware modifications we made had a side effect of increasing the CPI by 10%. What clock rate should we have in order to get this time reduction?  
计算方法：列出公式移项计算。
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%206.png)

#### 给定Clock Rate & CPI & # Instructions，并且假定多个Instruction Classes，给出每个Instruction Classes的CPI和 # Instructions 中的占比，求 Average CPI
> Consider two different processor with the same instruction set architecture P1 and P2. The instructions can be divided into four classes according to their CPI (class A, B, C, D). And we have the following information:  
> - P1 has: Clock rate **4.5 GHz**; CPIs: 4, 2, 5, and 4 for each of the A, B, C, and D classes  
> - P2 has: Clock rate **3 GHz**; CPIs: 2, 1, 3, and 4 for each of the A, B, C, and D classes  
> Given a program which has **2.0E6** instructions, divided into classes as follows:  
> - Class A: 15%  
> - Class B: 20%  
> - Class C: 35%  
> - Class D: 30%  
> What is the average CPI for each processor?  
简单来说就是我们有两个 CPU，P1 和 P2；与此同时我们还有四种类型的代码（instruction），
分别为 A, B, C, D。这两个 CPU 对于这四种不同的 Instructions 有不同的 CPI，并且自身各自也都有不同的 Clock Rate。
现在我们给定一个由这四种不同类型的代码构成的程序，要求计算对于每一个处理器的 Average CPI。

计算方法：通过使用公式3 -> 派生公式2 -> 变体：`Average CPI = #Cycle / #Instructions` 可以计算结果。

先将各自 Class 对应的 Instructions 数量通过百分比算出来，然后各自乘上对应的CPI，加起来的总和就是 Cycles 总数。用 Cycles 总数除以 Instructions 总数，得到 Average CPI。我们这里用 P1 作为示例：

![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%207.png)

当然，我们在这里也可以直接通过各个不同的 Class 占比百分比算出来，使用手写第三个公式的注释内容。这里我们同样用 P1 来举例：

![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%208.png)

#### 给定Average CPI & Clock Rate，计算CPU Time
> (接上题条件) How long does it take each processor to execute the entire program?   
计算方法：直接用公式4。这里以 P1 作为示例：

![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%209.png)

#### 给定Rate & # Instructions & Time，计算 CPI
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/785B9CDE-F367-40AD-929E-1B588E9BC177.png)
> Compilers can have a profound impact on the performance of an application. Assume that for a program, compiler A results in an **instruction count of 2.0E9** and has an execution time of **3.0 seconds**, while compiler B results in an **instruction count of 4.0E9** and an execution time of **2.0 seconds**. Find the average CPI for each program given that the processor has a **clock cycle time of 0.5 nano-seconds (i.e., the clock rate is 2 GHz)**  

计算方法：公式2 -> 派生公式1 -> 移项: `CPI = # Cycle / # Instructions = (Rate * Time) / # Instructions`
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%2010.png)

#### 给定CPI和Clock Rate，计算MIPS
> A common fallacy is to use MIPS to compare the performance of two different processors, and consider that the processor with the larger MIPS rating has the higher performance. Let us check if this is true for the original P1 and P2, which has clock rate of 5 GHz and 2.5 GHz, respectively, and CPIs of 3 and 1.2, respectively.

![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/70DE5942-6075-40C1-90AD-9B33891B8429.png)

计算方法：公式3 -> 派生公式1 `MIPS = (Clock Rate / CPI)/10^6`，记得转换单位。
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%2011.png)

#### 给定部分变量和恒量，判断其他变量是否会变
> (True/False) If one program P1 is run on two different machines —computer A and computer B — then the computer with the higher clock speed will always have a smaller execution time provided **the two computers have the same instruction set architecture (ISA)**.  
- 解题思路：写出公式和对应的变量，如下：
![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%2012.png)

在这里，因为我们**不知道CPI是如何变化**，所以不能预测时间的变化，答案是 False。

> (True/False) If one program P1 is compiled using two different compilers —C1 and C2 —and **run on the same computer A,** then the compiled version that has the higher MIPS rating will always have a smaller execution time, **if the instruction counts are the same**  
- 解题思路同上：![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%2013.png)

#### 多核问题 - 假设多个CPU同时跑同一个程序，求运行时间。
信息整合：
- 每个 CPU 的频率是 3.6 GHz
- 对于某个程序，我们有其统计的 Instruction 数量：
	1. Arithmetic 3.74E9 
	2. Load/Store 2.20E9 
	3. Branch 420E6
- 对于任意一个 CPU，我们有以下的 Relative CPI 数据：
	1. Arithmetic 2
	2. Load/Store 7
	3. Branch 6
- 与此同时我们有额外的条件：
	1. Branch Instructions无法被分配至多核
	2. Instructions数量和核心数量的关系：
		- 单个CPU的Instructions数量 = Instructions总数量 / (0.6 * p) -> p 代表CPU的数量。
		- 举例，对于单核 CPU 而言，Arithmetic Instructions 的数量为 3.74E9。但是对于两个 CPU，**分配到每一个 CPU 的 Arithmetic Instructions** 的数量为 `3.74E9/(0.6*2) ≈ 3.1167E9`
		
解题思路：根据题目中给定的 **Instructions 数量和核心数量之间的关系**来分别计算 Instructions 的数量，然后再分别代入公式计算。具体代入公式参考上文中关于多个 Instruction Classes 的题型。这里我们以双核心为例：

![](EX01%20%7C%20CPI%E7%9B%B8%E5%85%B3%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%2014.png)
