class Solution {
    private static Set<String> valid = new HashSet<>();
    // static isliye banaya qki ye 1 hi baar chalega saare text cases k liye 1 hi baar , mtlb humne pehle hi dekh liya ki kon kon se ban rhe hai 4 digit k 24 , vo store kra aur dekha ki kya cards voi 4 no. se bna hai 
    // static {
    //     for(int a=1;a<=9;a++){
    //         for(int b=a;b<=9;b++){
    //             for(int c=b;c<=9;c++){
    //                 for(int d=c;d<=9;d++){
    //                     List<Double> nums = Arrays.asList((double)a,(double)b,(double)c,(double)d);
    //                     if(canGet24(nums)){
    //                         valid.add(convertToStr(new int[]{a,b,c,d}));
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }
    public static boolean canGet24(List<Double> nums){
        if(nums.size()==1) return Math.abs(nums.get(0)-24.0) <= 1e-6; // precision check hai ye ki agr inka division p difference bht negligible hai to same hi consider hoga 
        // 2/3 = 0.66666667
        // 4/6 = 0.66666666
        for(int i=0;i<nums.size();i++){
            for(int j=i+1;j<nums.size();j++){
                double x = nums.get(i), y = nums.get(j);
                double[] pairResults = new double[]{x+y, x*y, x-y, y-x};

                List<Double> selected = new ArrayList<>();
                for(int k=0;k<nums.size();k++){if(k!=i && k!=j) selected.add(nums.get(k));} 
               
                for(double pair : pairResults){
                    selected.add(pair); 
                    if(canGet24(selected))return true;
                    selected.remove(selected.size()-1);
                }
                if(y != 0.0){
                    selected.add(x/y); 
                    if(canGet24(selected))return true;
                    selected.remove(selected.size()-1);
                }
                if(x != 0.0){
                    selected.add(y/x); 
                    if(canGet24(selected))return true;
                    selected.remove(selected.size()-1);
                }
            }   
        }
        return false;
    }
    public boolean judgePoint24(int[] cards) {
        // Arrays.sort(cards);
        // String temp = convertToStr(cards);
        // return valid.contains(temp);
        // ye waala jb 9^4 se jyada test cases honge to beat krega pr abhi test cases usse kam hai isliye brute force waala beat krega jyada
        List<Double> doubleArr = new ArrayList<>(Arrays.asList(
            (double)cards[0],
            (double)cards[1],
            (double)cards[2],
            (double)cards[3]
        ));
        return canGet24(doubleArr);
    }
    public static String convertToStr(int[] arr){
        return arr[0]+""+arr[1]+""+arr[2]+""+arr[3];
    }
}