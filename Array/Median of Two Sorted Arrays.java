//There are two sorted arrays nums1 and nums2 of size m and n respectively.
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//利用递归的方法，可以求两个有序数列合并后第k大的值的位置。
//比较数列A和数列B的第k/2个元素，如果A[k/2-1]<B[k/2-1]，那么A[0]～A[k/2-1]的元素不可能大于合并后的第k个元素
//因此，可以删除A数组的这k/2个元素

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        ArrayList<Integer> list1 = new ArrayList<Integer>();  
        for (int i=0; i<m; i++) {  
            list1.add(nums1[i]);  
        }
        ArrayList<Integer> list2 = new ArrayList<Integer>();  
        for (int j=0; j<n; j++) {  
            list2.add(nums2[j]);  
        }
        if (total % 2 != 0)
            return find_kth(list1, m, list2, n, total/2 + 1);
        else {
        	int x = find_kth(list1, m, list2, n, total/2);
            ArrayList<Integer> list3 = new ArrayList<Integer>();  
            for (int i=0; i<m; i++) {  
                list3.add(nums1[i]);  
            }
            ArrayList<Integer> list4 = new ArrayList<Integer>();  
            for (int j=0; j<n; j++) {  
                list4.add(nums2[j]);  
            }
            int y = find_kth(list3, m, list4, n, total/2 + 1);
        		return (x + y) / 2.0;
        }
    }
    private static int find_kth(ArrayList<Integer> list1, int m, ArrayList<Integer> list2, int n, int k){
        //always assume that m is equal of smaller than n.
        if(m > n) return find_kth(list2, n, list1, m, k);
        if(m == 0) return list2.get(k - 1);
        if(k == 1) return Math.min(list1.get(0), list2.get(0)); //k==1时，已经是到了最接近的时候，可以return了。
        //divide k into two parts.
        int a = Math.min(k/2, m);
        int b = k - a;
        if (list1.get(a - 1) < list2.get(b - 1)){
            list1.subList(0,a).clear(); //删除不需要的数列中前k/2-1个元素
            list1.trimToSize();
            return find_kth(list1, m - a, list2, n, k - a);
        }
        else if (list1.get(a - 1) > list2.get(b - 1)){
            list2.subList(0,b).clear();
            list2.trimToSize();
            return find_kth(list1, m, list2, n - b, k - b);
        }
        else
            return list1.get(a - 1); //此时A[k/2-1]==B[k/2-1]，返回两者之一
    }
}