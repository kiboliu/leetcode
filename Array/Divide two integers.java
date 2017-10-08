//Divide two integers without using multiplication, division and mod operator

//If it is overflow, return MAX_INT.

//位运算，左移一位相当于乘以2，multiple帮助结果同步增长
public int divide(int dividend, int divisor) {
    if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
    int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
    long divd = Math.abs((long)dividend);
    long divi = Math.abs((long)divisor);
    int res = 0;
    while(divd >= divi){
        long temp = divi;
        long multiple = 1;
        while(divd > (temp << 1)){
            temp <<= 1;
            multiple <<= 1;
        }
        divd -= temp;
        res += multiple;
    }
    return sign == 1? res : -res;
}