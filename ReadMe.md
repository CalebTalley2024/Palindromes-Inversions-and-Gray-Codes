# HW 3
- `palidromecheck` is the only function not commented out
  - you have to comment out the other to test them in Main.java
## Problem 1: palindromecheck
- uses scanner to get input from keyboard
### Bonus
- program is case insensitive, and ignores white space and punctuation
## Problem 2: easyinversioncount and fastinversioncount
- Input for both `easyinversioncount` and `fastinversioncount`: `ArrayList<Integer>`
  
## Problem 3: graycodesarefun
- There is a scanner that lets you pick how long you pick your order
- You can also just check the function with an order of 4
- the "Action" column zip zags a bit, but is accurate
### Bonus: 
  - With this code, **you can produce a Binary Reflected Gray Code Table of orders 1 through 11**
    - you can use the scanner code that is commented out in Main.java, or you can just use the greycodearefun function directly
### Patterns
  - The order of the first letter of the child's name refers to the binary digit location
    - example: (A)lice is moved in or out based on the 1's place
    - example: (B)ob is moved in or out based on the 10's place (the 2nd digit)
    
