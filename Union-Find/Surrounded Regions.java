/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

*/

//bfs思路：标记边线或连接边线的O，检查四个边，如果有O的存在，则往里继续搜，直到上下左右没有O为止。
//将边线的O及连接边线O的O都找出来后，把剩下的O标记为X，再把这些标记回O。
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        
        for(int i = 0; i < row; i++) {
            check(board, i, 0, row, col);
            if(col > 1) 
                check(board, i, col-1, row, col);
        }
        for(int j = 1; j < col - 1; j++) {
            check(board, 0, j, row, col);
            if(row > 1) 
                check(board, row - 1, j, row, col);
        }
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == '1')
                    board[i][j] = 'O';
            }
        }
    }
    
    private void check(char[][] vec, int i, int j, int row, int col) {
        if(vec[i][j] == 'O') {
            vec[i][j] = '1';
            if(i > 1) 
                check(vec, i - 1, j, row, col);
            if(j > 1)
                check(vec, i, j - 1, row, col);
            if(i + 1 < row)
                check(vec, i + 1, j, row, col);
            if(j + 1 < col)
                check(vec, i, j + 1, row, col);
        }
    }
}

//Union-Find, 类似算法作业1的permutations，即在四周的O之前加个dummy node，然后使四边的O连上dummy node，之后的O继续相连。
//那么check O是否连到dummy node的话，就可以判断O是不是需要capture了
class Solution {
	int [] unionSet;
	boolean [] hasEdge0;

	public void solve (char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}

		int height = board.length; width = board[0].length;

		unionSet = new int[height * width];
		hasEdge0 = new boolean[unionSet.length];

		//unionSet标记每个数，类似id[]，id相同则证明相连。
		for (int i = 0; i < unionSet.length; i++) {
			unionSet[i] = i;
		}
		//将四边的O对应的boolean值初始化为true。
		for (int i = 0; i < hasEdge0.length; i++) {
			int x = i / width, y = i % width;
			hasEdge0[i] = (board[x][y] == 'O' && (x == 0 || x == height - 1 || y == 0 || y == width - 1));
		}
		//遍历board，如果当前char与上方或右方的char相同，连接它们。
		for (int i = 0; i < unionSet.length; i++) {
			int x = i / width, y = i % width, up = x - 1; right = y + 1;
			if (up >= 0 && board[x][y] == board[up][y]) {
				union(i, i - width);
			}
			if (right < width && board[x][y] == board[x][right]) {
				union(i, i + 1);
			}
		}
		for (int i = 0; i < unionSet.length; i++) {
			int x = i / width, y = i % width;
			if (board[x][y] == 'O' && !hasEdge0[findSet(i)]) {
				board[x][y] = 'X';
			}
		}
	}

	private void union(int x, int y) {
		int rootX = findSet(x);
		int rootY = findSet(y);
		//如果之前的union已经是hasEdgeO为true了，那么新merge的也要使merge后的对应hasEdgeO为true
		boolean hasEdge0 = this.hasEdge0[rootX] || this.hasEdge0[rootY];
		unionSet[rootX] = rootY;
		this.hasEdge0[rootY] = hasEdge0;
	}
	//找到根节点
	private int findSet(int x) {
		if (unionSet[x] == x) return x;
		unionSet[x] = findSet(unionSet[x]);
		return unionSet[x];
	}
}