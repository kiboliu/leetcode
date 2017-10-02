//You are given an n x n 2D matrix representing an image.
//Rotate the image by 90 degrees (clockwise).
//You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

//顺时针旋转后 == 沿对角线左上与右下翻转 + 沿中线上方与下方翻转
class Solution {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for(int i = 0; i < len - 1; i++){
            for(int j = 0; j < len - 1 - i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - j][len - 1 - i];
                matrix[len - 1 - j][len - 1 - i] = temp;
            }
        }
        for(int i = 0; i < len/2; i++){
            for(int j = 0; j < len; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - i][j];
                matrix[len - 1 - i][j] = temp;
            }
        }
    }
}