class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> arr = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        for(int i=0;i<Math.min(k,nums1.length);i++) pq.offer(new int[]{nums1[i]+nums2[0],i,0});
        for(int I=0;I<k;I++){
            int[] group = pq.poll();
            int i = group[1];
            int j = group[2];
            List<Integer> l = new ArrayList<>();
            l.add(nums1[i]);
            l.add(nums2[j]);
            arr.add(l);
            if(j<nums2.length-1)pq.offer(new int[]{nums1[i]+nums2[j+1], i, j+1});
        }
        return arr;
    }
}

// [1,2,4,5,6]
// [3,5,7,9]

// sum, i, j
// 1+3,0,0
// 2+3,1,0
// 4+3,2,0
// 5+3,3,0
// 6+3,4,0


// 4,0,0


// 13,0 15,1 17,2 19,3
// 23 15 17 19 
// 15 25 17 19 

// k break;
// our: [[1,3],[2,3],[4,3]]
// exp: [[1,3],[2,3],[1,5]]
