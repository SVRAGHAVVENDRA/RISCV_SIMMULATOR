.data
base1:    .word 80
array:   .word 4,17,4,18,18,14,1,2,30,5,9,6,11,12,19,9,8,19,11,12,25,3,74,82,45

.text
la x3, base1       # here x3 is the base1 address.
la x4, base1       # iterator
la x18, base1      # this is used for the j loop first element
la x19, base1      # this is used for i loop first element

addi x5, x0, 25    # size for inner loop and printing

addi x15, x0, 24   # no of running loops for the outer loop.

addi x14, x0, 0    # index i

addi x9, x0, 1     # index j

li x12, 0          # this is for printing.

li x20, 0          # for minpostion index for swapping


Outer_loop:
  beq x14, x15, exit   # break statement for the outer loop
  addi x16, x14, 1 
  mv x9, x16            # for every outer loop, x9 will be initialized to x14+1.
  addi x18, x18, 4       # incrementing the address for the next loop.
  mv x4, x18            # storing address in x4 for traversal.
  addi x14, x14, 1       # i increment
  lw x10, 0(x19)         # for comparing min position element.
  jal x1, Inner_loop
  j Outer_loop

Inner_loop:
  beq x9, x5, swap 
  lw x11, 0(x4)         # second element will be stored in x11.
  addi x9, x9, 1         # updating j.
  blt x11, x10, move     # if the value of x10 is greater than x11 then store in minpos. so go to move.
  addi x4, x4, 4         # modify the address
  j Inner_loop

move:
  # store the x11 element address in x20
  mv x10,x11
  mv x20, x4
  addi x4, x4, 4         # modify the address
  j Inner_loop

swap:
  # swap the values at 0(x19) and address at x20
  lw x21, 0(x20)        # load the value at the address stored in x20
  lw x22, 0(x19)        # store the value at the address 0(x19)
  sw x22,0(x20)
  sw x21,0(x19)
  addi x19,x19,4
  mv x20,x19 # again initializing the address of the next ith element
  # other the previous value will be stored.
  j Outer_loop

exit:
add x30 x2 x1
