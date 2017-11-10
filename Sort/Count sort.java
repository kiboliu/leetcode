/*
Count sort.
1) Take a count array to store the count of each unique object, the count array calculate the number of range of input.
2) Modify the count array such that array to store the sum of previous counts
3) Output each objects from input sequence followed by decreasing its count by 1.
*/

void sort(char[] arr) {
	int n = arr.length;

	//the output char array having sorted arr
	char[] output = new char[n];

	//create a count array
	//chars and nitialize count array as 0
	int[] count = new int[256];
	for (int i = 0; i < 256; i++) {
		count[i] = 0;
	}
	//store count of each character
	for (int i = 0; i < n; i++) {
		count[arr[i]]++;
	}ß

	//change count[i] so that count[i] contains actual position of this char in output

	for (int i = 1; i < 255; i++) {
		count[i] += count[i - 1];
	}

	//build the output character array

	for (int i = 0; i < n; i++) {
		output[count[arr[i]] - 1] = arr[i];
		count[arr[i]]--;
	}

	//copy the output array to arr, finish sort
	for (int i = 0; i < n; i++) {
		arr[i] = output[i];
	}

}