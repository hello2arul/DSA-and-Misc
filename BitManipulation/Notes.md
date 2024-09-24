## Finding binary of negative numbers:
1. Start with the Positive Binary 
2. Invert the Bits:
3. Change all 0s to 1s and all 1s to 0s 
4. Add 1 to the inverted binary number. This gives you the two's complement representation of the negative number.


## Notes
- least significant bit (LSB) - right most bit
- most significant bit (MSB) - left most bit
- x << y = x * 2^y
- x >> y = x / 2^y 
- num &= -num => LSB which is set to 1