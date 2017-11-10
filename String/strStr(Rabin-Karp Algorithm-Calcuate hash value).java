//对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。

public class Solution {
    //为了对其取模，使其不超过最大整数限制。
    private int BASE = 1000000;
    /*
     * @param source: source string to be scanned.
     * @param target: target string containing the sequence of characters to match
     * @return: a index to the first occurrence of target in source, or -1  if target is not part of source.
     */
    public int strStr(String source, String target) {
        if (source == null || target == null) return -1;
        if (target.length() == 0 || source.equals(target)) return 0;
        
        int len = target.length();
        
        int power = 1;
        for (int i = 0; i < len; i++) {
            //31 is a experience value.
            power = (power * 31) % BASE;
        }
        
        int targetCode = 0;
        for (int i = 0; i < len; i++) {
            targetCode = (targetCode * 31 + target.charAt(i)) % BASE;
        }
        
        int hashCode = 0;
        for (int i = 0; i < source.length(); i++) {
            hashCode = (hashCode * 31 + source.charAt(i)) % BASE;
            if (i <= len - 1) {
                continue;
            }
            if (i >= len) {
                //丢掉上一个
                hashCode = hashCode - (source.charAt(i - len) * power) % BASE;
                if (hashCode < 0) {
                    hashCode += BASE;
                }
            }
            if (hashCode == targetCode) {
                if (source.substring(i - len + 1, i + 1).equals(target)) {
                    return i - len + 1;
                }
            }
        }
        return -1;
    }
}