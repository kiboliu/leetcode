//Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

//For "(()", the longest valid parentheses substring is "()", which has length = 2.

//Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

//Use stack, store the index in the stack. Calculate the substring length all the time.
public int longestValidParentheses(String s) {
    Stack<Integer> stack = new Stack<Integer>();
    int max=0;
    int left = -1;
    for(int j=0;j<s.length();j++){
        if(s.charAt(j)=='(') stack.push(j);            
        else {
            if (stack.isEmpty()) left=j;
            else{
                stack.pop();
                if(stack.isEmpty()) max=Math.max(max,j-left);
                else max=Math.max(max,j-stack.peek());
            }
        }
    }
    return max;
}

// Not use stack, update the substring length when get a new () pair.
public int longestValidParentheses(String s) {
    char[] S = s.toCharArray();
    int[] V = new int[S.length];
    int open = 0;
    int max = 0;
    for (int i=0; i<S.length; i++) {
        if (S[i] == '(') open++;
        if (S[i] == ')' && open > 0) {
            // matches found
            V[i] = 2+ V[i-1];
            // add matches from previous
            if(i-V[i]>0)
                V[i] += V[i-V[i]];
            open--;
        }
        if (V[i] > max) max = V[i];
    }
    return max;
}