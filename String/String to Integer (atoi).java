//Implement atoi to convert a string to an integer.

public int myAtoi(String str) {
    int index = 0, sign = 1, total = 0;
    if(str.length()==0) return 0;
    // Remove spaces.
    while(str.charAt(index)==' '&&index<str.length()){
        index++;
    }
    // Handle signs.
    if(str.charAt(index)=='+'||str.charAt(index)=='-'){
        sign=str.charAt(index)=='+'?1:-1;
        index++;
    }
    while(index<str.length()){
        int digit=str.charAt(index)-'0'; //str.charAt(i) is a string, so convert it to an integer.
        if(digit<0||digit>9) break;
        //check if total will be overflow.
        if(Integer.MAX_VALUE/10<total||Integer.MAX_VALUE/10==total&&Integer.MAX_VALUE%10<digit) //avoid overflow.
            return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
        total=10*total+digit;
        index++;
    }
    return total*sign;
}