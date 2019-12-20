# EX04 | Instruction Sets重要概念 & 题型

- - - -
## 重要概念
0. 关于MIPS Instruction Sets 的基本概念：
	1. 冯诺伊曼计算机的结构：CPU（Data Path + Control Unit）+ Memory
	2. MIPS: 32 位（32 bits）计算机，每个 word 长度为 32 bits。
	3. MIPS: Byte-addressable，最小可访问单位是 bytes，也就是 8 bits
	4. 三种类型的instructions: `R & I & J`
	5. 每个 instruction 实际上就是一个 word (也就是 32 个 0 或 1 的排列)，计算机通过读取这个 word 的特定位数来知道命令的详细内容（比如，通过读取这个 Word 的前 6 位 <31:26>，计算机可以知道这个 word 代表什么命令）具体关于计算机处理命令的原理可以参考后面会发的《Build A Computer》部分。
1. R - Type：有三个参数 - `$rd, $rs, $rt`
	1. 格式 `OPCODE(6) + $rs(5) + $rt(5) + $rd(5) + shamt(5) + FUNC(6)`
	2. OPCODE 和 FUNC 查表（MIPS Green Sheet）得到数据
2. I - Type：有两个参数 - `$rs, $rt, (+16-bit constant)`
	1. 格式 `OPCODE(6) + $rs(5) + $rt(5) + Const(16)` 
	2. 有一个 16 位的常数，可以直接用常数进行计算（少使用了一个 Register）
	3. 对于后面的十六位常数，因为 MIPS 实际上每个 word 是 32 位，所以需要将 16 位补长成 32 位的值。根据不同的命令，可以选择 Sign Extension 或者 Zero Extension。
		1. Sign Extension 就是编码&补码里面提到的那个 Sign Extension，也就是把这个数字的第一位填充到前面十六位空白处。这样的好处是，对于带符号的数，延长之后还能保持原数的值。
		2. Zero Extension 就是不管第一位是什么，前面十六位都填充 0。虽然现在没想到什么好处（大概是我没听课），但是在某些命令下非常重要，例如各种位运算（`andi` 和 `ori`）
	4. OPCODE 查表
3. J - Type：只有一个参数 - `$ADDR` 代表程序跳转的位置
	1. 格式为`OPCODE(6) + $ADDR(30)`
	2. 目前来说只有两个（整个 Green Sheet 里面也只有两个）就是 `j` 和 `jal`。
	3. OPCODE 查表
4. 对于常用各 Instructions 的理解与使用:
	1. 计算类: `add, mul, div, addi` 
		- 乘除法还有 `mflo & mfhi`
		- 读取大数有 `lui + ori`
	2. 比较类: `slti, beq, bne`
	3. 布朗值操作: `and, andi, or, ori, nor`
	4. Bit操作类: `sll, srl`
	5. 读取 & 存储类: `lw, sw`
	6. 跳转类：`j, jal, jr, jalr`
	- 各种 OPCODE 可以查阅 MIPS Green Sheet 得到数据，因为涉及到版权所以就不在这里放出来了，一般上课的时候都会有给这个 Green Sheet，感兴趣的同学也可以 Google 一下（似乎可以 Google 到？当然也可以在[这个链接](http://www.cs.unc.edu/~montek/teaching/Comp411-Fall18/MIPS_Green_Sheet.pdf)找到 Montek 教授上传的一份 Reference Sheet
	- [MIPS Instruction Reference](http://www.mrc.uidaho.edu/mrc/people/jff/digital/MIPSir.html)可以找到对于每一个命令的详解。
5. Registers 常见概念
	1. `$0` 的值**永远都是 0**（就算我们对其赋其他的值，`$0` 的值依旧不会改变）
	2. 大部分 Registers 都有别名，例如 `$t2 = $10`
	3. 一般 `$31` 代表 Return Address - `$31 = $ra`
- - - -
## 概念整理（手写）

![](EX04%20%7C%20Instruction%20Sets%E9%87%8D%E8%A6%81%E6%A6%82%E5%BF%B5%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x.png)

涉及命令的解析：
- `add $10, $8, $9` - 读取 $8 和 $9 保存的数字并且相加，结果放入 $10。
- `ori $10, $9, 25` - 读取 $9 的值并且将其和 25 进行 bitwise-OR 操作，结果存入 $10。
- `j 0x04` - 让计算机跳转到 0x04 位置并从这里开始读取新的命令。
- - - -
## 常见题型
#### 求二进制（十六进制）编码内容和 Instructions Literal 之间的转换
> For each instruction listed below, convert it to its 32-bit binary representation (machine code), and then convert the binary to its 8-hexit hex value. For instance, instruction “add $10, $11, $9” converts to 0x01695020.  
> `add $8, $9, $10`  
> `sll $8, $9, 10`  
> `addi $8, $9, -10`  

解题思路：直接查表后带入值写答案即可，将二进制转换为十六进制的时候别算错了。

注意要点：
- I 类型命令的 16 位常数使用 **2’s Complement** 进行表示。
- 注意不同命令的 `$rs $rt $rd` 的位置。一般我们写命令的时候，是根据自然语言的顺序写的。举例来说，上面的 MIPS Reference Sheet 中，`add` 命令的实现是 `$rd = $rs + $rt`，因此我们写 Instruction Literal 的时候，也是按照 `$rd, $rs, $rt` 的顺序写。类似的，对于 `ori` 命令，我们一般写成 `$rt = $rs | Immediate(ZeroExt)`，因此我们写的时候也是按照 `$rt, $rs, immediate`的顺序写。将命令内容转换成二进制码的时候，我们要注意这些 Registers 的顺序，不要写错了。
- 查表的时候注意命令类型，不要看到有常数就想当然套用 I 类型命令，点名 `sll` 命令就是一个 R 类型命令。
![](EX04%20%7C%20Instruction%20Sets%E9%87%8D%E8%A6%81%E6%A6%82%E5%BF%B5%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%202.png)

> Convert the following instruction to its 8-hexit hex value.   
> `loop: bne $s5, $a0, loop`  
> `lw $s1, 76($0)`  
> `mult $t2, $t3`  

这一题区别于上一题的原因是，这一题**使用了 Register 的别名**。我们在转化的时候，需要先转化成对应的 Register 编号，再转化成二进制计算。

同样的，这里我们需要注意 Registers 的位置问题，此处我们以 `lw` 命令为例：通常来说，这个命令的格式为 `$rt = Mem[$rs + Imm(SignExt)]`，也就是 “读取内存中指定位置的数据，并且将其赋值到 `$rt`”。因此，根据上面 Registers 的顺序，我们可以知道其 Instruction Literal 是 `lw $rt, imm($rs)`，因此这里 `$rt = $s1`，`$rs = $0`。

需要特别讲解的是这个 `bne` 命令。对于这个命令，其作用是 `PC = PC + 4 + 4 * OFFSET`，也就是让计算机的 Program Counter 跳转到另外一个地址并且从这里重新执行。因此 `bne` 的命令格式是 `bne $rs, $rt, OFFSET`，第三个参数实际上应该是程序跳转的偏移量（OFFSET），这个在实现的时候是需要写成一个数字的。而因为 MIPS 的一个语法糖，我们可以在编程的时候直接在某一行写一个 “label”，并且在写例如 `bne & beq & j & jal` 等命令的时候直接引用这个 “label”，而不需要我们亲自计算这个偏移量。我们这里的这一个命令就是引用到了这个 “label”，因此我们需要通过阅读这个代码的意思来计算实际带入的 OFFSET 的值。

我们可以看出，这里我们写了一行代码是 `bne $s5, $a0, loop`，而这个 loop 标签指向的实际上是它自身。因此，我们可以知道，计算机实际需要做的就是 “当 `$s5` 和 `$a0` 不相等的时候，让 Program Counter 保持不动（也就是让 Program Counter 跳转到同样的命令）”，所以可以列出方程 `PC == PC + 4 + 4 * OFFSET`，通过计算得知 OFFSET = -1。

（实际上的题目没有这么难23333，本身这个题目是一个连线题，所以直接通过排除法可以连线到对应选项上，但是我改过来的时候把题目改了改，没想到难度就陡增了哈哈哈哈哈哈哈哈）

![](EX04%20%7C%20Instruction%20Sets%E9%87%8D%E8%A6%81%E6%A6%82%E5%BF%B5%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x%203.png)

#### 基本定义考察内容
> Fill in the blanks below for the assembly instruction `addi $t1, $t2, 25`  
> Source Register ($rs) is: (   )  
> Immediate Operand (Immediate) is: (   )  
> Destination Register ($rt) is: (   )  

考察上面我们提到过的不同 Register 位置的概念。对于 `addi` 命令，我们一般的格式是 `$rt = $rs + Immediate(Sign Extend)`，因此按照顺序，这里我们写成 `addi $rt, $rs, Immediate`。所以 `$rt = $t1`，`$rs = $t2`，Immediate Operand 的值是 25.
