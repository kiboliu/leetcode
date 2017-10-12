// Implement strStr(). 
// Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.


// O(m*n), 在haystack上移动，每移动一次比较下接下来的needle长度内的字符是不是与needle匹配
public int strStr(String haystack, String needle) {
    for (int i = 0; ; i++) {
        for (int j = 0; ; j++) {
            if (j == needle.length()) return i;
            if (i + j == haystack.length()) return -1;
            if (needle.charAt(j) != haystack.charAt(i + j)) break;
        }
    }
}