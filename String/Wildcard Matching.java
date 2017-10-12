
//Implement wildcard pattern matching with support for '?' and '*'.
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).

//The matching should cover the entire input string (not partial).

//The function prototype should be:
//bool isMatch(const char *s, const char *p)

//Some examples:
//isMatch("aa","a") → false
//isMatch("aa","aa") → true
//isMatch("aaa","aa") → false
//isMatch("aa", "*") → true
//isMatch("aa", "a*") → true
//isMatch("ab", "?*") → true
//isMatch("aab", "c*a*b") → false


//The same as regular expresssion matching, use dynamic matching.



public boolean isMatch(String s, String p) {
    if(s == null || p == null) return false;
    if(p.equals("*")) return true;
    boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
    dp[0][0] = true;
    if(p.length() > 0 && p.charAt(0) == '*'){
        int k = 0;
        while(k < p.length() && p.charAt(k) == '*'){
            dp[0][k + 1] = true;
            k++;
        }
    }
    for(int i = 1; i < dp.length; i++){
        for(int j = 1; j < dp[0].length; j++){
            if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?'){
                dp[i][j] = dp[i - 1][j - 1];
            }
            if(j > 1 && p.charAt(j - 1) == '*'){
                if(s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '?' && p.charAt(j - 2) != '*'){
                    dp[i][j] = dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1] || dp[i - 1][j];
                }     
            }
            if(j == 1 && p.charAt(j - 1) == '*'){
                dp[i][j] = true;
            }
        }
    }
    return dp[s.length()][p.length()];
}