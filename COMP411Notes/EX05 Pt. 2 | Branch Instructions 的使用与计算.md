# EX05 Pt. 2 | Branch Instructions 的使用与计算

## Branch on Equal 命令
命令内容：`beq $rs, $rt, OFFSET`

OPCODE：`0x4`

命令作用：如果两个Register的值相等，则跳转至指定行（由 OFFSET 计算得出）；否则不跳转。

命令格式：`OP(6) + $rs(5) + $rt(5) + 16-bit constant(16)` (I-type)

命令实际作用：根据条件判断计算新的 Program Counter。
- 如果 `$rs == $rt`：`PC(new) = PC(old) + 4 + 4 * OFFSET(Sign Extend)`
- 如果 `$rs != $rt`：`PC(new) = PC(old) + 4`

通过带入上面的公式，我们可以计算得到新的 Program Counter 的值。
-  例子：`beq $8, $9, 0x0001` with `PC = 0x00003021`
	- 首先我们计算 `4 * OFFSET`：`4 * 0x0001 = 0x0004` 
	- 然后我们计算最终结果：`PC = 0x3021 + 4 + 0x0004 = 0x3029` 
	- `Result: PC = 0x00003029`
- 例子：`beq $8, $9, 0xFFFF` with `PC = 0xF00FCDA2`
	- 同样的，我们还是计算 `4 * OFFSET`：`4 * OFFSET = 0xFFFB`
	- 然后计算最终结果：`PC = 0xF00FCDA2 + 4 + 0xFFFB = 0xF00FCDA2` 
	- `Result: PC = 0xF00FCDA2`。注意，这里 Program Counter 没有变，此时会造成一个 infinite loop.

这里就不放手写计算过程了。

> 在 MARS 编译器中编程的时候不需要提供 OFFSET 的准确值，而只用提供 label 就可以了。MARS 编译器会自动帮我们将 label 转换为 offset。也就是说，像 Jump 命令一样，我们不需要明确写出 Jump 地址，而只用提供对应 Label 就可以直接跳转了。  

#### 命令图解示例

假设我们有以下程序（部分命令省略，地址从 `0x3000` 开始），部分状态如图所示，程序从 `0x3000` 开始运行：

![](EX05%20Pt.%202%20%7C%20Branch%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/image.png)

首先读取 Program Counter 对应地址的命令，`$8 = 0`。命令执行完毕后 Program Counter += 4，值为 `0x00003004`

读取 Program Counter 对应地址的命令，`¥9 = 1` 。命令执行完毕后 Program Counter += 4，值为 `0x00003008` 

读取 Program Counter 对应地址的命令，`¥10 = 1`。命令执行完毕后 Program Counter += 4，值为 `0x0000300C`

读取 Program Counter 对应地址的命令，判断 `$9 == $10`，满足条件，计算新的 Program Counter 的值为 `0x300C + 4 + 4 * 0x2 = 0x3018` 。命令执行完毕，此时跳过 Program Counter += 4 的步骤。

读取 Program Counter 对应地址的命令（此时为 `0x00003018`），`¥8 += 3` 得到 `$8 = 3`。命令执行完毕后 Program Counter += 4，值为 `0x301C`

读取 Program Counter 对应地址的命令，`¥8` 的值保持不变。命令执行完毕后 Program Counter += 4，值为 `0x3020`

此时分析计算机状态，可以得到 `PC = 0x3020`, `$8 = 3`, `$9 = $10 = 1`。

注意，这里我们执行了 Branch 命令，所以不会执行 Shift Left Logical 命令。如果我们忽略 Branch 指令的话，最终我们可以得到 `$8 = 19`。

在这里我们可以注意到一件很有趣的事情，就是这个 Branch Target 部分其实就是 “跳转到后面的第 `i+1` 行”。比如，如果后面接的是 0，则对应跳转 branch 命令后面一行。我们上面 OFFSET 是 0x2，所以也就是跳转 branch 命令后面的第 2+1=3 行，也就是 `addi $8, $8, 3` 这个命令了。

因此我们也可以比较好的理解上面例题中间出现的 infinite loop 了：跳转的 OFFSET 是 0xFFFF，也就是跳转 branch 命令后面的第 -1+1=0 行，也就是 branch 命令本身。那么 Program Counter 在运行结束 Branch 命令之后又会重新指向 branch 命令自身，随后又重新计算 branch 命令，因此也就形成一个 infinite loop 了。

#### 汇编示例

上面的例子在 MARS 模拟器中编程如下：

![](EX05%20Pt.%202%20%7C%20Branch%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/4C55C834-131B-497C-B6F0-9C51913EBC6F.png)

部分代码解析：

> 第六行 & 第十行因为 MARS 模拟器仅支持 branch 后面直接带 Label 的写法，所以无法写成上面的 `beq $9, $10, 0x2` 的形式。  

编译后代码如下：

![](EX05%20Pt.%202%20%7C%20Branch%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/0DA9E677-E406-48D2-B0D5-D02CCD055A03.png)

注意，如上图中我们可以看到，编译器确实将我们写的 `beq LABEL` 转换成了我们上面写的 `beq $9, $10, 0x2` 的形式。

运行结果如下：

![](EX05%20Pt.%202%20%7C%20Branch%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/50325AF7-89D2-4353-A2B5-AEC57A605C2C.png)

##### Extra

对于上面的那个 infinite loop，我们在 MARS 模拟器中编程如下：

![](EX05%20Pt.%202%20%7C%20Branch%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/E6F24559-634B-4E73-ADAA-B44D3BF8ACD5.png)

编译后代码如下：

![](EX05%20Pt.%202%20%7C%20Branch%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/241BB1AA-486D-442C-954B-7C190015EE5A.png)

可以看到，确实是 `beq $8, $9, -1`。

运行结果如下：

![](EX05%20Pt.%202%20%7C%20Branch%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/u=2969771637,3268081191&fm=26&gp=0.jpg)
- - - -
## Branch on Not Equal 命令

命令内容：`bne $rs $rt $OFFSET`

OPCODE：`0x5` 

命令作用：如果两个Register的值不等，则跳转指定行（由 OFFSET 计算得出）；否则不跳转。

命令格式：`OP(6) + $rs(5) + $rt(5) + 16-bit constant(16)` (I-type)

命令实际作用：根据条件判断计算新的 Program Counter。

内容上除了判断条件不同以外其他和 Branch on Equal 相同，故不多解释。
