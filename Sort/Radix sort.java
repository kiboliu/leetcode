/*
Radix sort.
1. to do digit by digit sort starting from least significant digit
   to most significant digit.
2. use count sort as subroutine.
3. remember keep original sequence of elements if they are the same
*/

class Radix {
	static int getMax(int[] arr, int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] > mx)
				mx = arr[i];
		}
		return mx;
	}

	//a function to do counting sort of arr
	static void countSort(int[] arr, int n, int exp) {
		int[] output = new int[n];
		int i;
		int[] count = new int[10];
		Arrays.fill(count, 0);

		//Store count of occurrences in count[]
		for (i = 0; i < n; i++) {
			count[ (arr[i] / exp) % 10]++;
		}

		//Change count[i] so that the count array contains actual position of this digit in output[]
		for (i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		//Build the output array
		for (i = n - 1; i >= 0; i--) {
			output[count[ (arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		//copy the output array to arr[]
		for (i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}

	//radix sort
	static void radixsort(int[] arr, int n) {
		//find max
		int m = getMax(arr, n);

		//Do count sort for every digit
		//Instead of passing digit number, exp is passed
		//exp is 10^i, where i is current digit number
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(arr, n, exp);
	}
}