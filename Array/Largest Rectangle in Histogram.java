//Given n non-negative integers representing the histogram's bar height where the width of each bar is 1
//find the area of largest rectangle in the histogram.

//Use stack. Store the index into stack. When the new index is larger than the top of stack, just put it in the stack;
//When the new index is smaller than the top of stack, pop the top one, and calculate the rectangle which height is the top one;
//Remember "i--" to make the index remain until the stack's elements are smaller than the index's, which means finish the current calculation;
//Remember put the last '0' in the stack, for calculating the elements in stack(which have not waited for calculation until the end).
//这个解法太妙了！！！
public int largestRectangleArea(int[] heights) {
    int len = heights.length;
    Stack<Integer> s = new Stack<Integer>();
    int maxArea = 0;
    for(int i = 0; i <= len; i++){
        int h = (i == len) ? 0 : heights[i];
        if(s.isEmpty() || h >= heights[s.peek()]){
            s.push(i);
        }else{
            int tp = s.pop();
            maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
            i--;
        }
    }
    return maxArea;
}

//和一开始自己的想法一样，找到每一个元素左边第一个比当前元素小的index1和右边第一个比当前元素小的index2;
//然后height[index2]-height[index1]-1就是以当前元素为高的长方形的底边。

public static int largestRectangleArea(int[] height){
	if(height == null || height.length == 0) return 0;
	int[] lessFromLeft = new int[height.length];
	int[] lessFromRight = new int[height.length];
	lessFromRight[height.length - 1] = height.length;
	lessFromLeft[0] = -1;
	//利用了动规，让p等于左边已有的最小值，而不是重新计算一次，复杂度降到O(n).
	//和之前求雨的容量的那题比较像。
	for(int i = 1; i < height.length; i++){
		int p = i - 1;
		while(p >= 0 && height[p] >= height[i]){
			p = lessFromLeft[p];
		}
		lessFromLeft[i] = p;
	}

	for(int i = height.length - 2; i >= 0; i--){
		int p = i + 1;
		while(p < height.length && height[p] >= height[i]){
			p = lessFromRight[p];
		}
		lessFromRight[i] = p;
	}

	int maxArea = 0;
	for(int i = 0; i < height.length; i++){
		maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
	}
	return maxArea;
}
