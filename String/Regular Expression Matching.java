//Implement regular expression matching with support for '.' and '*'.

//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.

//The matching should cover the entire input string (not partial).

//The function prototype should be:
//bool isMatch(const char *s, const char *p)

//Some examples:
//isMatch("aa","a") → false
//isMatch("aa","aa") → true
//isMatch("aaa","aa") → false
//isMatch("aa", "a*") → true
//isMatch("aa", ".*") → true
//isMatch("ab", ".*") → true
//isMatch("aab", "c*a*b") → true

//Dynamic Planning:
//If p.charAt(j) == s.charAt(i) or p.charAt(j) == '.':  dp[i][j] = dp[i-1][j-1]
//If p.charAt(j) == '*':
//1. if p.charAt(j-1) != s.charAt(i):dp[i][j] = dp[i][j-2].
//2. if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == '.':
//   dp[i][j] = dp[i-1][j] // a* counts as multiple a.
//  or dp[i][j] = dp[i][j-1] // a* counts as single a.
//  or dp[i][j] = dp[i][j-2] // a* counts as empty.
public boolean isMatch(String s, String p) {
    if(s == null || p == null) return false;
    boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
    dp[0][0] = true;
    for(int j = 1; j < dp[0].length; j++){
        if(p.charAt(j - 1) == '*'){
            if(dp[0][j - 1] || (j > 1 && dp[0][j - 2])){
                dp[0][j] = true;
            }
        }
    }
    for(int i = 1; i < dp.length; i++){
        for(int j = 1; j < dp[0].length; j++){
            if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'){
                dp[i][j] = dp[i - 1][j - 1];
            }
            if(p.charAt(j - 1) == '*'){
                if(s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.'){
                    dp[i][j] = dp[i][j - 2];
                }else{
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                }     
            }
        }
    }
    return dp[s.length()][p.length()];
}