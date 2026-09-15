class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        // 4A 10A 15A 24A 26A 0B 9B 12B 20B 5C 18C 22C 30C
        // sort it 
        // 0B 4A 5C 9B 10A 12B 15A 18C 20B 22C 24A 26A 30C => 0,1 4,1 5,2 10,1
        // then find the smallest range that has 1 element at least with each group
        
       
        List<int[]> list = new ArrayList<>();
        int k = nums.size();
        for(int i=0;i<k;i++) for(int num : nums.get(i)) list.add(new int[]{num,i});
        Collections.sort(list, (a,b) -> a[0]-b[0]);
        
        int left = 0;
        int[] count = new int[k];
        int found = 0;
        int bestR = Integer.MAX_VALUE, bestL = 0;
        for(int right = 0;right < list.size(); right++){
            int group = list.get(right)[1];
            if(count[group] == 0) found++;
            count[group]++;
            
            while(found==k){
                int l = list.get(left)[0]; //left pointer ka index
                int r = list.get(right)[0]; 
                int range = r-l;
                if(range < bestR - bestL){
                    bestR = r;
                    bestL = l;
                }
                
                group = list.get(left)[1];
                count[group]--;
                if(count[group] == 0) found--;
                
                left++;
            }
        }
        return new int[]{bestL, bestR};
    }
}
// 2-4
// 6-8