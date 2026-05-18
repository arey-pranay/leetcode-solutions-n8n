class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }
    
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        List<Integer> curs = new LinkedList<>(); // store current layer
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        int step = 0;

        // when current layer exists
        while (!curs.isEmpty()) {
            List<Integer> nex = new LinkedList<>();

            // iterate the layer
            for (int node : curs) {
                // check if reached end
                if (node == n - 1) {
                    return step;
                }

                // check same value
                for (int child : graph.get(arr[node])) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nex.add(child);
                    }
                }

                // clear the list to prevent redundant search
                graph.get(arr[node]).clear();

                // check neighbors
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    nex.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    nex.add(node - 1);
                }
            }

            curs = nex;
            step++;
        }

        return -1;
    }
}
// BFS Memory Limit exceeded
// class Solution {
//     HashSet<Integer> checking;
//     HashMap<Integer,HashSet<Integer>> hm;
//     Integer[][] memo;
    
//     public int minJumps(int[] arr) {
//         checking = new HashSet<>();
//         hm = new HashMap<>();
//         memo = new Integer[arr.length][50001];
//         for(int i=0;i<arr.length;i++){
//             HashSet<Integer> temp =  hm.getOrDefault(arr[i], new HashSet<>());
//             temp.add(i);
//             hm.put(arr[i], temp);
//         }
//         checking.add(0);
//         int ans = func(0,arr, 0);
//         return ans;
//     }
    
//     public int func(int i, int[] arr, int tillNow){
//         int n = arr.length;
//         if(i<0 || i>=n) return Integer.MAX_VALUE;
//         if(i == n-1) return 0;
//         if(memo[i][tillNow] != null) return memo[i][tillNow];
        
//         int a = Integer.MAX_VALUE;
//         int b = Integer.MAX_VALUE;
//         int c = Integer.MAX_VALUE;
        
//         for(int j : hm.get(arr[i])){
//             if(i != j){ 
//                 if(checking.contains(j)) continue; 
//                 checking.add(j);
//                 System.out.println(i + " -> "+j);
//                 a = Math.min(a,func(j,arr,tillNow+1));
//                 checking.remove(j);
//             }
//         }
        
//         if(!checking.contains(i+1)){
//             checking.add(i+1);
//             System.out.println(i + " -> "+ (i+1));
//             b =  func(i+1,arr,tillNow+1);
//             checking.remove(i+1);
//         }
//          if(!checking.contains(i-1)){
//             checking.add(i-1);
//             System.out.println(i + " -> "+ (i-1));
//             c =  func(i-1,arr,tillNow+1);
//             checking.remove(i-1);
//         }

//         int min = Math.min(a,Math.min(b,c));
//         return memo[i][tillNow] = min == Integer.MAX_VALUE ? Integer.MAX_VALUE: 1 + min;
//     }
// }