//Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

//Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

//One-pass algorithm use constant space.
//Method 1: mark the number of different colors and give the 0 or 1 or 2 to them.
void sortColors(int A[], int n) {
    int n0 = -1, n1 = -1, n2 = -1;
    for (int i = 0; i < n; ++i) {
        if (A[i] == 0) 
        {
            A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
        }
        else if (A[i] == 1) 
        {
            A[++n2] = 2; A[++n1] = 1;
        }
        else if (A[i] == 2) 
        {
            A[++n2] = 2;
        }
    }
}

//Method 2: put the 0 to the start and the 2 to the end.
void sortColors(int A[], int n) {
    int j = 0, k = n - 1;
    for (int i = 0; i <= k; ++i){
        if (A[i] == 0 && i != j)
            swap(A[i--], A[j++]);
        else if (A[i] == 2 && i != k)
            swap(A[i--], A[k--]);
    }
}
void swap(int A[], int p, int q) {
	A[p] ^= A[q];
	A[q] ^= A[p];
	A[p] ^= A[q];
}
