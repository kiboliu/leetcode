//Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

//Use O(m+n) space.
public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    boolean[] mb = new boolean[m];
    boolean[] nb = new boolean[n];
    for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
            if(matrix[i][j] == 0){
                mb[i] = nb[j] = true;
            }
        }
    }
    for(int i = 0; i < m; i++){
        if(mb[i]){
            for(int j = 0; j < n; j++){
                matrix[i][j] = 0;
            }
        }
    }
    for(int j = 0; j < n; j++){
        if(nb[j]){
            for(int i = 0; i < m; i++){
                matrix[i][j] = 0;
            }
        }
    }
}

//Use constant space.
public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    boolean mb = false;
    boolean nb = false;
    for(int i = 0; i < n; i++){
        if(matrix[0][i] == 0){
            mb = true;
            break;
        }
    }
    for(int i = 0; i < m; i++){
        if(matrix[i][0] == 0){
            nb = true;
            break;
        }
    }
    for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
            if(matrix[i][j] == 0){
                matrix[0][j] = 0;
                matrix[i][0] = 0;
            }
        }
    }
    for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
            if(matrix[i][0] == 0 || matrix[0][j] == 0){
                matrix[i][j] = 0;
            }
        }
    }
    if(mb){
        for(int i = 0; i < n; i++){
            matrix[0][i] = 0;
        }
    }
    if(nb){
        for(int i = 0; i < m; i++){
            matrix[i][0] = 0;
        }
    }
}