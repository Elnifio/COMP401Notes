# EX02 | Power 重要公式 & 题型

## 重要公式
1. `Power(Total) = Power(Dynamic) + Power(Static & Leakage)`
	1. 总消耗能量 = 动态消耗的能量（也就是计算消耗的能量）+ 静态消耗的能量（内存等位置保存数据的时候消耗的能量）
	2. 单位是Watt瓦特 (不排除预设单位的情况，请按需转换单位)
2. `Power(Dynamic) = (C * V(DD)^2 * f) / 2` - Energy Consumed due to *switching activity* - 电路运算的时候 (transistors改变状态的时候) 消耗的能量。
	1. `C` - Capacitance = Capacitive Load，单位是法拉第 (Farads, F)，请按需转换单位。
	2. `V(DD)` - Supply Voltage，单位是伏特 (Volt, V)，请按需转换单位。
	3. `f` - switching frequency = Clock Rate，单位是赫兹 (Hertz, Hz)，请按需转换单位。
3. `Power(Static & Leakage) = I(DD) * V(DD)` -> Energy Consumed when *idle* - 闲置时候消耗的电量。
	1. I(DD) - Supply Current，单位是安培 (Ampere, A)，请按需转换单位。
	2. V(DD)同上，不赘述。
- - - -
## 重要公式列表（手写）
![](EX02%20%7C%20Power%20%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x.png)
（单位比较少所以就直接写在旁边了）
- - - -
## 经典题型
#### 给定特定变量，计算剩余变量
> Processor A has a clock rate (i.e., switching frequency) of 2.3 GHz and supply voltage of 2.1V. Assume that, on average, it consumes 8W of static power and 80W of dynamic power. Find the capacitive load, C, for this processor.

解题思路：直接代入公式计算。

![](EX02%20%7C%20Power%20%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%202.png)

#### 某变量变化，其他变量不变。求变化后的Power
> （接上题条件）Suppose the supply voltage (Vdd) of Processor A was doubled to 4.2 V but all other quantities — capacitive load (C), switching frequency (F), and leakage current (Idd) — remain unchanged. What are the new values of A’s static and dynamic power, and also its total power?  

解题思路：直接将新的数值代入公式计算，这里就不演示了。

另一种解题思路：将量化后的数值代入计算 -> 直接计算Power翻了多少倍
	- 因为 dynamic power 正比于 Voltage 的平方，所以 Voltage × 2 得到 Dynamic Power × 4
	- 因为 static power 正比于 Voltage，所以 Voltage × 2 得到 Static Power × 2

![](EX02%20%7C%20Power%20%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%203.png)

#### Relative Power问题 - 假设某变量improve了，求总Power improve多少
> Suppose we developed a new, simpler processor that has 85% of the capacitive load of the more complex older processor. Further, assume that it has adjustable voltage so that it can reduce voltage 15% compared to processor B, which results in a 15% shrink in frequency. What is the impact on dynamic power?   

解题思路：类似上一题，我们这里不需要分别算出绝对数值，直接将其相对数值代入计算即可：

![](EX02%20%7C%20Power%20%E9%87%8D%E8%A6%81%E5%85%AC%E5%BC%8F%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%204.png)
