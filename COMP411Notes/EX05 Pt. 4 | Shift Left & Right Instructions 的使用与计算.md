# EX05 Pt. 4 | Shift Left & Right Instructions 的使用与计算

## Shift Left Logical 命令
命令内容：`sll $rd, $rt, shamt`

OPCODE：`0x0` ；FUNCT：`0x00` 

命令作用：将 `$rt` 的数字（二进制）左移，左移位数由 immediate (shamt) 规定。空缺部分（后面部分）补 0，多余部分（前面部分）舍去，然后将计算结果保存到 `$rd`。这等同于计算 `$rt * (2^shamt)`。

命令格式：`OP(6) + 0(5) + $rt(5) + $rd(5) + shamt(5) + FUNCT(6)` (R-type)

通过使用其性质，我们可以根据给定的数字和规定移位数来计算位运算结果。
- 例子：`sll $rd, -1, 16`
	- 首先我们写出其二进制形式 `0xFFFFFFFF`（此处用十六进制代替）
	- 然后左移 16 位，空缺部分补 0（十六进制下左移 4 位）结果为 `0xFFFF0000`
	- `Result: $rd = 0xFFFF0000` 
- 例子：`sll $rd, 20, 1`
	- 首先我们写出其二进制形式 `00010100`（此处仅写出末尾 8 位，前 24 位省略）
	- 然后左移 1 位，空缺部分补 0，结果为 `00101000`
	- `Result: $rd = 00101000 = 40`

![](EX05%20Pt.%204%20%7C%20Shift%20Left%20&%20Right%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/bear_sketch@2x.png)

## Shift Right Logical 命令
命令内容：`srl $rd, $rt, shamt` 

OPCODE：`0x0` ；FUNCT：`0x02` 

命令作用：将 `$rt` 的数字（二进制）右移，右移位数由 immediate (shamt) 规定。空缺部分（会出现在前面）**补 0**，多余部分（会出现在后面）舍去，然后将计算结果保存到 `$rd`。在 `$rt` 为**非负数**的时候，这等同于计算 `$rt / (2^shamt)`。

命令格式：`OP(6) + 0(5) + $rt(5) + $rd(5) + shamt(5) + FUNCT(6)` (R-type)

通过使用上面的性质，我们可以根据给定的数字和规定位移数计算位运算结果。具体过程和上面的 Shift Left Logical 类似。
- 例子：`srl $rd, -1, 16`
	- `Result: $rd = 0x0000FFFF`
- 例子：`srl $rd, 20, 1`
	- 等价于`srl $rd, 00010100, 1`
	- `Result: $rd = 00001010 = 10`

![](EX05%20Pt.%204%20%7C%20Shift%20Left%20&%20Right%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/bear_sketch@2x%202.png)

## Shift Right Arithmetic 命令
命令内容：`sra $rd, $rt, shamt`

OPCODE：`0x0` ；FUNCT：`0x03`

命令作用：等价于 Shift Right Logical，但空缺部分是 **Sign Extend** 而不是补 0. 也就是说，前面部分的填充内容和 `$rt` 的第一位保持一致。等同于计算 `$rt / (2^shamt)`。

命令格式： `OP(6) + 0(5) + $rt(5) + $rd(5) + shamt(5) + FUNCT(6)` (R-type)

同样的，通过使用其性质，我们可以根据给定的数字和规定位移数来计算位运算结果。注意 Sign Extend。

- 例子：`sra $rd, -1, 16`
	- `Result: $rd = 0xFFFFFFFF` 
	- 注意，这里 -1 是 `0xFFFFFFFF`  ，开头第一位是 1，因此我们前面部分补 1，结果为 `0xFFFFFFFF`。
	- 注意，这里我们如果计算 `-1 / 16` 的话实际上并不是 -1，但是因为我们目前暂时不考虑表示小数，所以**向下取整**得到 -1。类似的，我们可以尝试计算 `23 >>> 1`，得到结果为 11，确实是 23/2 向下取整。
- 例子：`sra $rd, -20, 1`
	- 等价于 `sra $rd, 11101100, 1`
	- `Result: $rd = 11110110 = -10`
![](EX05%20Pt.%204%20%7C%20Shift%20Left%20&%20Right%20Instructions%20%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E8%AE%A1%E7%AE%97/bear_sketch@2x%203.png)


