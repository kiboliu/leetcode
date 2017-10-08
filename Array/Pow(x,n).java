//Realize Pow(x,n)

//利用位运算，将n拆成2的幂函数
public double myPow(double x, int n) {
    if(n==0) return 1;
    long num = (long) n;
    if(num<0) {
        num = Math.abs((long) num);
        x = 1/x;
    }
    double ans = 1;
    while(num>0){
        if(num%2 == 1) ans *= x;
        x *= x;
        num >>= 1;
    }
    return ans;
}