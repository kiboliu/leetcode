//Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
//The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

//valid的要求是横行，竖行，以及所在的九个格子内，没有重复的数字。
//HashSet只会存储不重复的数字。
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int i = 0;
        while(i < 9){
        	int count1 = 0;
        	Set<Character> set1 = new HashSet<Character>();
            int count2 = 0;
            Set<Character> set2 = new HashSet<Character>();
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.') {
                		count1++;
                		set1.add(board[i][j]);
                }
                if(board[j][i] != '.') {
                		count2++;
                		set2.add(board[j][i]);
                }
            }
            if(set1.size() != count1) return false;
            if(set2.size() != count2) return false;
            int count3 = 0;
            Set<Character> set3 = new HashSet<Character>();
            //设定的对应规则是，从左先往下走，1-2-3，再回到中间那列然后往下走4-5-6，再到最后一列7-8-9
            for(int k = (i%3)*3; k < (i%3)*3 + 3; k++) {
	            for(int j = (i/3)*3; j < (i/3)*3 + 3; j++){
	                if(board[k][j] != '.') {
	                		count3++;
	                		set3.add(board[k][j]);
	                }
	            }
            }
            if(set3.size() != count3) return false;
            i++;
        }
        return true;
    }
}