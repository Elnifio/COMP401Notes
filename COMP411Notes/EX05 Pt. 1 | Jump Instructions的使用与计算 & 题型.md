# EX05 Pt. 1 | Jump Instructions的使用与计算 & 题型

## Jump 命令
命令内容：`j $ADDRESS`

OPCODE：`0x2`

命令作用：直接跳转至指定的 ADDRESS，也就是让 Program Counter 指向新的位置并且读取对应的内容。

命令格式：`OP(6) + 26-bit constant(26)` (J-type)

命令实际作用：`PC(new) = (PC[31-28] || CONST[25:0] * 4)` - 计算并改变新的 Program Counter 的值。

通过直接带入上面的公式，我们可以根据给定的命令内容来计算新的 Program Counter 的值：
- 例子：`j 0x30` with `PC = 0x00000003`
	- 首先找到 immediate 值乘以 4：`0x30 * 4 = 0xC0`（注意这不是十进制计算，有的时候题目会给十进制的数字，这时候就需要转换成二进制或者十六进制来计算）
	- 然后将这个值的后 28 位（这里我们的值是 0xC0，我们对其进行 Sign Extend 成为 0x000000C0，因此这里的值的后 28 位是 00000C0）和目前的 Program Counter (也就是 0x00000003) 的前四位（在这里的前四位就是用十六进制表示的 0）拼接起来，得到最终结果。
	- `Result: $ADDR = 0x000000C0`
- 例子：`j 0x4` with `PC = 0xF0000030`
	- 同样，我们首先计算 immediate * 4: `0x4 * 4 = 0x10`
	- 然后我们将其的后 28 位（也就是 0000010）和目前的 Program Counter 的前四位（也就是 F）拼接起来，得到最终结果。
	- `Result: $ADDR = 0xF0000010`

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/bear_sketch@2x.png)

#### 命令图解示例
假设我们有以下程序（部分命令省略，地址从 `0x3030` 开始），部分状态如图所示，程序从 `0x00003030` 开始运行：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/image%203.png)

首先执行 `0x00003030` 这一行的命令，`$8 = 0 + 1 = 1`，此时 `$8 = 0x01`，命令执行完毕后 Program Counter += 4，值为 `0x00003034`。

读取 Program Counter 的值，并且在 Memory 中读取对应地址的命令。

此时执行 `j 0x0C10` 命令，`addr = 0x0C10 * 4 = 0x3040`，得到 `PC = 0x00003040`，此时跳过 Program Counter += 4 的步骤。

读取 Program Counter 的值，并且在 Memory 中读取对应地址的命令。

此时执行 `sll $8, $8, 16` 命令，将 `$8` 对应的值**左移 16 位**，得到 `$8 = 0x00010000` （注意计算机的所有运算都是二进制的运算，这里为了简洁起见将二进制的值转化成为了十六进制）命令执行完毕后 Program Counter += 4，值为 `0x00003044`。

读取 Program Counter 的值，并且在 Memory 中读取对应地址的命令。

此时执行 `addi $8, $8, 0`，Register 的值保持不变。命令执行完毕，Program Counter += 4，值为 `0x00003048`。

读取 Program Counter 的值，并且在 Memory 中读取对应地址的命令。

此时执行 `addi $8 $8, 0`，Register 的值保持不变，命令执行完毕，Program Counter += 4，值为 `0x0000304C`。

读取 Program Counter 的值，并且在 Memory 中读取对应地址的命令。

执行 `srl $8, $8, 14`，将 `$8` 对应的值**右移 14 位，空位补 0**，得到 `$8 = 0x00000004`，命令执行完毕后 Program Counter += 4，值为 `0x00003050`，咋瓦路多。

此时我们分析计算机状态，得到新的状态为：PC = `0x3050`，`$8` = `0x04`

注意，因为我们执行了这里的 jump 命令，所以计算机不会执行 `0x303C` 地址的 `addi $8, $8, 1024`（如果执行了的话 `$8` 最后结果大概就会是 0x0C00 了）

#### 汇编示例
上面的例子在 MARS 编译器中编程如下：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/E57271DB-A1EE-4612-A7BC-4C3457C0C647%202.png)

部分代码解析：
> 第一行 `.text 0x3000` 规定了计算机保存指令的位置，从内存地址为 0x3000 开始保存代码。  
> 第四行 `j JumpTarget` 因为 MARS 编译器仅支持 Jump 后面直接带 label 的写法，所以无法写成上面 `j 0x0C10` 的形式。  
> 第 15~17 行 `syscall` 函数目前不会深入讲解。  
> `#` 后面的内容全部为注释内容。  

编译后代码如下：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/ACC420D4-531C-4D33-8A06-A799AACEE323%202.png)

如上图，第二列为指令地址，第三列为指令编码（也就是转换成 0 和 1 的结果）第四列是啥我也不知道（23333，第五列和后面的内容是源代码（也就是我们自己写的代码和对应的行数，包括注释内容）

可以看到，我们代码保存位置从 0x3000 开始，第一行代码的保存位置也就是 0x3000. 对于第二行的 Jump 指令，虽然第四列对应的值是 0x3010，但是实际上我们通过第三列的指令编码可以反推出实际的值还是 `j 0x0C04`，和我们上面画的图类似。

大家也可以通过编译器生成的这些源代码来自行训练手动编译计算机器码的过程，具体步骤参考 [这里](https://github.com/Elnifio/COMP401Notes/blob/master/COMP411Notes/EX04%20%7C%20Instruction%20Sets%E9%87%8D%E8%A6%81%E6%A6%82%E5%BF%B5%20%26%20%E9%A2%98%E5%9E%8B.md)。

运行结果如下：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/2E40DCAF-AB58-4845-AA9E-C7B5930DAE70%202.png)
- - - -
## Jump And Link 命令
命令内容： `jal $ADDRESS`

OPCODE：`0x3`

命令格式：`OP(6) + 26-bit constant(26)` (J-type)

命令作用：先保存当前位置的**下一行**（也就是 PC + 4 的值）到`$31`，然后像 `j` 命令一样跳转新的 ADDRESS（一般配合 `jr` 使用）

实际作用：`PC(new) = (PC[31:28] || CONST[25:0] * 4)` + `$31 = PC + 4`
> 备注：这里对于 Register 31 的处理目前来说是有一部分争议的，比如我们用来参考的 MIPS Green Sheet 上面就写的是 `$31 = PC + 8` 而不是 PC + 4。这里我们上课的时候采用的是 PC + 4 的计算方式，后面的计算也都采取 PC + 4 的计算方式。[这个链接](https://stackoverflow.com/questions/9548927/mips-jal-confusion-ra-pc4-or-pc8)里可以找到一个比较可靠的解释（概括一下大概就是实际上 PC + 8 也是正确答案，取决于编译器的实现，因为有些编译器会在 `jal` 命令后面再添加一个 Delay Slot，占用了一个 Word 的位置）。  

通过直接带入上面的公式，我们可以根据给定的命令内容计算新的 Program Counter 和 Register 31 的值：

- 例子：`jal 0x30` with `PC = 0x00000020` 
	- 计算 Program Counter 新的值的过程和上面的一样。
	- 对于 Register 31，我们直接将原本的 Program Counter（此处为 0x20）加上 4 即可（也就得到了 0x24）
	- `Result: $ADDR = 0x000000C0; $31 = 0x00000024`
- 例子：`jal 0x40` with `PC = 0xF0000033` 
	- 同样的计算方式。0xF00000033 + 4 = 0xF0000037
	- `Result: $ADDR = 0xF0000100; $31 = 0xF0000037`

## Jump To Register 命令
命令内容：`jr $ADDRESS`

OPCODE：`0x0`；FUNCT：`0x08`

命令格式：`OP(6) + $rs(5) + 0(15) + FUNCT(6)` （中间没有用到的部分，包括 $rt, $rd, Shamt, 全部用 0 填充）R-type

命令作用：跳转至 Register 提供的位置

实际作用：`PC(new) = $Address = $rs` - 将 `$rs` 的值赋值给 Program Counter

有什么用？
> 配合 `jal` 使用，我们先在程序某段部分插入一个 `jal` 命令，这时 `$31` Register 的值就是 Jump 之后的一条命令了。然后我们后续再使用 `jr $31` 的时候，我们就可以返回到我们跳过来之前的地方了。  

出题目的时候一般配合 `jal` 出题，考察一套组合👊下来 Program Counter 最后的值。这种题目我们一般只用计算出 `jal` 之后的 Register 31 的值就可以了，因为后面跟着的指令一般都是 `jr $31`（不过不能保证后面一定跟这个命令，所以建议大家看清楚题会比较好嗯，说不定什么题目突然就来一个 `jr $0` 呢哈哈哈哈哈哈哈哈）

- 例子：`jal 0x30; jr $31` with `PC = 0x000000CC`
	- `Result: PC = 0x000000C0 -> PC = 0x000000D0`
	- 步骤解析：
		- 先保存 PC 位置：`$31 = PC + 4 = 0x00CC + 4 = 0x00D0` 
		- 然后执行`jal` 的 Jump 部分：`PC = 0x00C0 = 0x0030 * 4`
		- 最后执行`jr`：`PC = $31 = 0x00D0`

#### Jump and Link & Jump to Register 命令图解示例
假设我们有以下程序，部分状态显示如下，程序从地址 0x3030开始运行：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/image%204.png)

同样类似的，我们首先执行 `0x00003030`地址的命令，得到 `$8 = 1`，Program Counter += 4，得到 `PC = 0x00003034`

读取 PC 对应命令，执行 `j 0x0C10`，计算新的 PC 值 = `0x0C10 * 4 = 0x3040`，并且将其赋值到 PC 中。

读取 PC 对应命令，执行 `addi $8, $8, 0`，Register 8 保持不变。Program Counter += 4，得到 `PC = 0x00003044`

读取 PC 对应命令，执行 `jal 0x0B0E` ，首先计算 Register 31 (Return address) 的值：目前的 Program Counter 值为 `0x3044`，所以 `$31 = PC + 4 = 0x3048`，注意这里 0x3048 正好指向了 `jal` 的下一个指令（也就是  `ori`命令）。

计算新的 PC 值：`0x0B0E * 4 = 0x3038`，并且将其赋值到 PC 中。PC = 0x3038。

读取 PC 对应命令，执行 `sll $8, $8, 2`，Register 8 的值向左移两位，得到新的 `$8 = 0x04`。Program Counter += 4，得到 `PC = 0x0000303C` 

读取 PC 对应命令，执行 `jr $ra`，这里 `$ra` 就等价于 Register 31，读取对应值为 `0x3048`，并且赋值给 Program Counter，得到 `PC = 0x3048`

读取 PC 对应命令，执行 `ori $8, $8, -1`，注意 ori 的命令是 Zero-Extend，因此这里实际上需要进行的操作是 `0x00000004 | 0x0000FFFF`，并且将结果保存至 `$8`，此时 `$8 = 0x0000FFFF = 65535`。Program Counter += 4，得到 `PC = 0x0000304C` 

读取 PC 对应命令，执行 `addi $8, $8, 1`，得到 `$8 = 0x0000FFFF + 1 = 0x00010000 = 65536`。Program Counter += 4，得到 `PC = 0x00003050`，咋瓦路多。

此时我们分析计算机状态，`PC = 0x3050`，`$31 = 0x3048`，`$8 = 0x10000`。

#### 汇编示例
上面例子在 MARS 中编译器中编程如下：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/54844CC2-72ED-4B1D-BAB2-BFD75366A2EC%202.png)

部分代码解析：
> 第 12 行同样因为 MARS 编译器不支持直接写地址，所以写成 `jal LABEL` 的形式。  
> 第十三行 `ori` 命令会由 MARS 编译器进行特殊处理，和我们上面算出来的结果不一样。  

编译后代码如下：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/A93F76BF-36B1-4937-86BF-FBEEBF1117B7%202.png)

明显的，我们可以注意到 MIPS 中间对于 `jal` 和 `jr` 的处理方式。

在这里我们可以观察到，实际上编译器将 `ori $8, $8, -1` 拆分成了两个命令。这样是因为 `ori` 命令是 Zero-Extend，但是 MARS 编译器自动假设如果需要与一个负数进行 OR 的话就给 Sign Extend，因此在这里先对 -1 进行了预先处理。所以这里的结果会和我们上面算出来的不一样 - `$8 = 0xFFFFFFFF | 0x00000004 = 0xFFFFFFFF`，然后再 + 1 得到最终结果 `$8 = 0`。

当然，如果我们将第十三行改成 `ori $8, $8, 0x0000FFFF` 就可以避免这个问题，输出 65536.

运行结果如下：

![](EX05%20Pt.%201%20%7C%20Jump%20Instructions%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97%20&%20%E9%A2%98%E5%9E%8B/55FE71CF-82A9-4DD8-B5E4-70A52F5309B7%202.png)
- - - -
#### Jump To Register and Link 命令
命令内容：`jalr $ADDRESS1, $ADDRESS2`

OPCODE：`0x0`；FUNCT：`0x09`

命令格式：`OP(6) + $rs + 0 + $rd + 0 + FUNCT(6)` R-type

命令作用：跳转至 $ADDR1 指定的address，并且将 PC + 4 保存至 $ADDR2（相当于 `jal + jr` 的组合）这里就不详细展开讨论了。
