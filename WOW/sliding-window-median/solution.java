class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n==1) return new double[]{nums[0]};
        int m = n-k+1;
        int j=0;
        double[] ans = new double[m];
        Comparator<Integer> comparator = (a,b) -> nums[a]==nums[b] ? Integer.compare(a,b) : Integer.compare(nums[a],nums[b]);
        TreeSet<Integer> smalls = new TreeSet<>(comparator.reversed()); // because we need to manually remove using index, but treeset should sort and respond based on nums[]
        TreeSet<Integer> bigs = new TreeSet<>(comparator);
        for(int i=0;i<n;i++){
          if(i>=k)remove(smalls,bigs,i-k);
          add(smalls,bigs,i);
          if(i>=k-1)ans[j++] = median(smalls,bigs,nums,k%2==0);
        }
        return ans;
    }
    public void remove(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      if(smalls.contains(index)){
        smalls.remove(index);
        if(smalls.size()<bigs.size()) smalls.add(bigs.pollFirst());
      } else {
         bigs.remove(index);
         if(bigs.size()<smalls.size()) bigs.add(smalls.pollFirst());
      }    
    }
    public void add(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      smalls.add(index);
      bigs.add(smalls.pollFirst());
      if(bigs.size()>smalls.size())smalls.add(bigs.pollFirst());
    }
    public double median(TreeSet<Integer> smalls,TreeSet<Integer> bigs, int[] nums, boolean isEven){
      double a = nums[smalls.first()];
      if(smalls.size()==bigs.size()) return (double)(a+nums[bigs.first()])/2.0;
      else return a;
    }
}