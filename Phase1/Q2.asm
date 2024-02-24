.data
base2: .word 320
array: .word 5,2,7,15,24,8,35,23,76,43,86,14,3,42,12,65,17,29,36,53

.text
la x3, base2    # here x3 is the base2 address.
la x4, base2    # iterator
addi x5, x0, 20 # size
li x6, 20 # final index
addi x14, x0, 0 # index i
addi x9, x0, 0 # index j
li x12,0
Outer_loop:
beq x14, x6, exit # break statement for the outer loop
li x9, 0 # for every outerloop x9 will be initialized to 0.
mv x4, x3 # storing address in x4 for traversal.
addi x14, x14, 1 # i increment
sub x7,x6,x14 #x7 is the maximum of j .
jal x1, Inner_loop
j Outer_loop
Inner_loop:
beq x9, x7, Outer_loop
lw x10, 0(x4) #first element will be stored in x10
lw x11, 4(x4) #second element will be stored in x11.
addi x9, x9, 1 #updating j.
bgt x10, x11, swap #if the value of x10 is greater than x11 then swap
addi x4, x4, 4 # modify the address
j Inner_loop
swap:
sw x10, 4(x4)
sw x11, 0(x4)
addi x4, x4, 4
j Inner_loop

exit:
add x30 x2 x2
