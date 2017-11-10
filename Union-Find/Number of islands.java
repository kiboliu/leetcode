/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/
//BFS
class Solution {
    private int row;
    private int col;
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        row = grid.length;
        col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i ,j);
                    count++;
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}

//Union-Find
class Solution {
	int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		UnionFind uf = new UnionFind(grid);
		int row = grid.length;
		int col = grid[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '1') {
					for (int[] d : distance) {
						int x = i + d[0];
						int y = j + d[1];
						if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == '1') {
							int id1 = i * col + j;
							int id2 = x * col + y;
							uf.union(id1, id2);
						}
					}
				}
			}
		}
		return uf.count;
	}
}
class UnionFind {
	int[] father;
	int m, n;
	int count = 0;
	UnionFind(char[][] grid) {
		m = grid.length;
		n = grid[0].length;
		father = new int[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					int id = i * n + j;
					father[id] = id;
					count++;
				}
			}
		}
	}
	public void union(int node1, int node2) {
		int find1 = find(node1);
		int find2 = find(node2);
		if (find1 != find2) {
			father[find1] = find2;
			count--;
		}
	}
	public int find(int node) {
		if (father[node] == node) {
			return node;
		}
		father[node] = find(father[node]);
		return father[node];
	}
}