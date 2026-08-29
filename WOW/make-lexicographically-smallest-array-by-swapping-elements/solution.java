class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        List<List<Integer>> groups = new ArrayList<>(); // grouping elements which have less than equal to k
        HashMap<Integer,Integer> hm =  new HashMap<>(); // groupID of every element
        int groupID = -1;
        for(int i=0;i<n;i++){
          if(i==0 || Math.abs(sorted[i]-sorted[i-1]) > limit){//creating a new group and updating the latest running groupID
            groups.add(new ArrayList<>());
            groupID++;
          }
          groups.get(groupID).add(sorted[i]);//add this number to current group
          hm.put(sorted[i],groupID);//mark the ID of this number in hashmap
        }
        int[] indices = new int[groups.size()]; // hum har ek group ke kitne indices already cover kr chuke hai
        for(int i=0;i<n;i++){
          groupID = hm.get(nums[i]); // current element ka group
          List<Integer> members = groups.get(groupID); // us group ke saare members
          nums[i] = members.get(indices[groupID]++); // un members me se jo latest index humne cover nhi kara hai
        }
        return nums;
    }
}