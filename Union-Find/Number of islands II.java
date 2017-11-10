/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]
*/

//类似number of islands I的UF方法，

class Solution {
    int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return null;
        }
        List<Integer> res = new ArrayList<Integer>();
        int num = 0;
        char[][] grid = new char[m][n];
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < positions.length; i++) {
            int a = positions[i][0];
            int b = positions[i][1];
            grid[a][b] = '1';
            // set用来存储加入1的时候，周围的1的father们。num需要减去不重复的father们。
            Set<Integer> set = new HashSet<Integer>();
            for (int[] d : distance) {
                int x = a + d[0];
                int y = b + d[1];
                if (x >=0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                    int id1 = a * n + b;
                    int id2 = x * n + y;
                    set.add(uf.find(id2));
                    uf.union(id1, id2);
                }
            }
            num = num - set.size();
            res.add(++num);
        }
        return res;
    }
}
class UnionFind {
    int[] father;
    int m,n;
    UnionFind (char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        father = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                father[id] = id;
            }
        }
    }
    public void union(int node1, int node2) {
        int find1 = find(node1);
        int find2 = find(node2);
        if (find1 != find2) {
            father[find1] = find2;
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