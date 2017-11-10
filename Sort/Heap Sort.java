/*
Heap Sort : The below function assume the first element is A[1]
1. Build Max Heap from unordered array. 
    For i = n/2 downto 1
   		do Max-Heapify(A, i)
   	* because A[n/2 + 1, ... , n] are all leaf of the tree, so just look at the first half of the array	
Now iteration begin:
2. Find maximum element A[1]
3. Swap element A[n] and A[1], now the max element is not at the tree
4. "Discard" node n from heap (to reduce heap)
5. New root may violate max heap property, but its children are max heaps, so run Max-Heapify(A,1)
6. Go to step2 unless heap is empty

Overall: O(nlogn)
An in-place algorithm
*/

public class HeapSort {
	public void sort(int arr[]) {
		int n = arr.length;

		//Build heap
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}

		//Start iteration, one by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			//Move current root to the end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			//call max-heapify on the reduced heap
			heapify(arr, i, 0);
		}
	}

	//To heapify a subtree rooted with node i. n is the size of heap
	void heapify(int arr[], int n, int i) {
		int largest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;

		//if left child is larger than root
		if (l < n && arr[i] > arr[largest]) {
			largest = l;
		}
		//if right child is larger than largest
		if (r < n && arr[r] > arr[largest]) {
			largest = r;
		}
		//if largest is not root
		if (largest != root) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			//recursively heapify the affected subtree until each root is largest
			heapify(arr, n, largest);
		}
	}
}
