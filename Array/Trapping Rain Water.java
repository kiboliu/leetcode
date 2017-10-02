//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//For example, 
//Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

//找出当前柱子左边和右边的最高值，该柱子能容纳的面积就是min(maxleft,maxright) - height.
class Solution {
    public int trap(int[] height) {
        int[] maxleft = new int[height.length];
        int[] maxright = new int[height.length];
        for (int i = 1; i < height.length; i++) {
        		maxleft[i] = Math.max(maxleft[i - 1], height[i - 1]);
        		maxright[height.length - 1 - i] = Math.max(maxright[height.length - i], height[height.length - i]);
        }
        int res = 0;
        for (int i = 0; i < height.length; i++) {
        		int h = Math.min(maxleft[i], maxright[i]);
        		if(h > height[i]) {
        			res = res + h - height[i];
        		}
        }
        return res;
    }
}