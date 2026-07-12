// class Solution {
//     public int[] arrayRankTransform(int[] arr) {
//         HashMap<Integer,ArrayList<Integer>> indices = new HashMap<>();
//         TreeSet<Integer> ts = new TreeSet<>();
//         for(int i=0;i<arr.length;i++){indices.computeIfAbsent(arr[i],(k)->new ArrayList<>()).add(i); ts.add(arr[i]);}
//         int j=1;
//         for(int num : ts){
//             for(int index : indices.get(num))  arr[index] = j;
//             j++;
//         }
//         return arr;
//     }
// }
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] temp = arr.clone();
        //[10,20,30,40]
        Arrays.sort(temp);
        HashMap<Integer,Integer> map = new HashMap<>();
         int rank = 1;
        for (int num : temp) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }
        int[] ans = new int[arr.length];
        for(int i=0;i<ans.length;i++){
            ans[i] = map.get(arr[i]);
        }
        return ans;
        
    }
}