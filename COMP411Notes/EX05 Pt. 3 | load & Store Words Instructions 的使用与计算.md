# EX05 Pt. 3 | load & Store Words Instructions 的使用与计算

## Load Word 命令
命令内容：`lw $rt, imm($rs)`

OPCODE：`0x23`

命令作用：将`immediate + $rs`计算结果作为地址，读取内存中对应内容，并且将读取内容保存至`$rt`

命令格式：`OP(6) + $rs + $rt + 16-bit constant(16)` (I-type)

注意事项：
- 注意 immediate 和 Register 的顺序（可以写 0($0)，但是不能写 $0(0)）
-  **只能读取 Immediate + Register 的值**，如果需要读取 Register + Register 的值的话不能用单一一条 load word 命令搞定。
- `$rs` 是 parameter，存放内容的是 `$rt` 
- immediate 这里是 Sign Extension
- 特别的，我们需要注意，既然是 Load **Word**，那么就需要 word addressable，也就是说地址的结尾必须是 `0x00`，即值可以被 4 整除。这是由于 MIPS 的 “Byte-addressable" 和 “32-bit” 设计。因此，我们读取的时候需要对齐，如果不对齐的话容易出现错误。
	- 类似的，我们有 `Load Bytes & Store Bytes` 命令，可以读取一个 word 里面的指定 byte。此时不需要结尾是 `0x00`。

#### 命令图解示例

假设我们有以下程序（命令部分从 `0x3000` 开始，数据存储内容从 `0x0` 开始），部分状态如图所示。对于这个程序，我们期待我们能够拿到第二个 word （也就是读取 `0x0000006F`）并且存入 `$8` 中。程序从 `0x3000` 开始运行：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/image.png)

首先读取 Program Counter 对应地址的命令，得到 `$8 = 1`。Program Counter += 4，值为 `0x00003004`

读取 Program Counter 对应地址的命令，`$8 << 2` 得到 `$8 = 4`。ProgramCounter += 4，值为 `0x00003008`

读取 Program Counter 对应地址的命令，计算读取地址为 `0 + 0x4 = 0x4`，是对齐的内存地址，因此读取地址处保存的内容为 `0x0000006F`，存入 `$8` 中。此时 `$8 = 0x0000006F`，Program Counter += 4，值为 `0x0000300C`。

这样，我们就实现通过 Load Word 命令来读取对应地址的内容了。

#### 汇编示例
上面的例子在 MARS 模拟器中编程如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/4683F3A6-C86B-48D7-A25F-62A69749FEB6.png)

部分代码解析：
> 第一行，规定了各种数据的保存地址，从 0x0 开始（最多到 0x2FFC 结束，从 0x3000 开始是保存代码的地方）  
>
> 第 2~4 行：在 MARS 编译器中如果需要保存数据的话也是像 jump & branch 命令一样设置 Label。此时我们设置的 Label 如下：  
>
> `newline`：指向内存地址 `0x0 & 0x4`，即上图中 `0x6C6C6568` 和 `0x0000006F`  
>
> `one`：指向内存地址 `0x8`，保存数据为 `0x00000001`  
>
> `misc`：指向内存地址 `0xC`，保存数据为 `0x1010023F`  
>
> data 部分保存数据格式介绍：`LABEL: .TYPE VALUE`  
>
> LABEL 部分保存 label (..) 相当于变量名字。后续如果需要引用的话为方便起见可以直接引用对应的 Label。  
>
> .TYPE 部分保存这个数据的类型，其中 `.asciiz` 代表带有 “\0” 的字符串，`.word` 代表一个 Word （也就是 32 bit）`.space` 代表预先声明是用空间。  
>
> VALUE 部分代表保存的值。对于 `.asciiz` 类型可以直接保存字符串明文，`.word` 类型可以直接保存对应数据明文，`.space` 类型则需要输入需要分配的空间大小（以 byte 来记，因此如果需要声明 20 words 的空间则需要输入 `.space 80`）  

编译后代码如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/2748DD4E-4AF5-4577-9930-F7C996D61EEE.png)

对应内存使用如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/E81259C5-196A-4BB4-90F1-0EE73F5E66E5.png)

这个表中我们可以通过表格的第一列和表头来找到对应的内存地址。比如，在上面的表中，我们如果需要查看 `0x1010023F` 的内存地址，我们首先找到对应数据在第一行第四列，随后我们计算第一行代表 `0x0` 开始，然后加上第四列代表 `+0C`，则最终内存地址为 `0x0C`。

同样的，我们也可以找到 `0x3000` 之后的内存内容，也就是程序源代码，内容如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/481D7CFD-0A7A-411E-ACEF-79F70B029B8A.png)

可以看到，如果我们寻找 `0x3000` 位置的内容，为 `0x20080001`，解码后反过来计算源代码，可以算出确实是 `addi $8, $0, 1`。

运行结果如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/96CA78B6-2135-4DF1-ADB8-3B7890D33D63.png)

（读取内容为 0x6F，也就是 6 * 16 + 15 = 111）

当然，前面我们提到过可以直接用 LABEL 来引用内容，而不需要写对应内存地址，示例如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/5D527832-7C24-442B-AA8C-89EEF1C1D0A6.png)

编译出来的源代码和刚才的一样：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/30A48030-09F9-468E-A799-CF1B44479C3C.png)

运行结果也是一样的。

## Store Word 命令
命令内容：`sw $rt, imm($rs)` 

OPCODE：`0x2B`

命令作用：读取 `$rt` 的值，并且将其存入 `Mem[immediate + $rs]` 位置 - 也就是改变内存对应内容，改变内容地址由 `immediate + $rs`  决定。

命令格式：`OP(6) + $rs + $rt + 16-bit constant(16)` (I-type)

注意事项：和上面的注意事项相同。

#### 命令图解示例
假设我们有以下程序（命令部分从 `0x3000` 开始，数据存储内容从 `0x0` 开始），部分状态如图所示。对于这个程序，我们期待我们能够将 `0x6F` 存入第二个 word（也就是内存地址 `0x00000004`）程序从 `0x3000` 开始运行。

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/image%202.png)
> 第三行命令是 `0x00000000` 的 instruction literal。这代表整个程序在这里结束。  

首先读取 Program Counter 对应地址的命令，得到 `$8 = 0x6F`。 Program Counter += 4，值为 `0x00003004`

读取 Program Counter 对应地址的命令，计算地址为 `0x4 + 0 = 0x4` ，是对其的内存地址，因此将 `$8` 的内容保存到对应地址，`Mem[4] = 0x6F`。Program Counter += 4，值为 `0x00003008`，结束。

此时我们分析计算机状态如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/image%203.png)

这样，我们就实现通过 Store Word 直接改写内存内容了。

#### 汇编示例
上面的例子在 MARS 模拟器中编程如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/C2B4384B-CA3D-4A9C-AA5B-F279C2CEDEDD.png)

编译后代码如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/363C0151-48C6-47DA-9DF9-D5F99FFF38D0.png)

可以注意到，第三行源代码确实为 `0x00000000`，代表程序的终止。

运行后内存使用如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/5CCC063B-2D25-4F4E-A6A1-901E64B4D682.png)

可以看到，我们查看 0x04 地址的内容，首先找到第一行，然后计算 `0x04 = 0x00 + 4`，因此找第一行第二列，确实内容为 `0x6F`。

当然，我们也可以通过使用 Label 来访问内存地址，代码如下：

![](EX05%20Pt.%203%20%7C%20load%20&%20Store%20Words%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/A0338176-2B91-4B78-8274-9F09925DBCA8.png)

编译后代码和上面的一样。
