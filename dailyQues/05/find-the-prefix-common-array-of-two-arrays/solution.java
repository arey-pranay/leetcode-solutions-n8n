class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int ans[] = new int[n];
        int[] freq = new int[n+1];
        int done = 0;
        for(int i = 0;i<n;i++){
            freq[A[i]]++;
            freq[B[i]]++;
            if(freq[A[i]] > 1) done++;
            if(A[i] != B[i]) if(freq[B[i]] > 1) done++;
            ans[i] = done;
        }
        return ans;
        
    }
}

// 1 3 2 4
// 3 1 2 4

//   sb1.append(A[i]);
//             sb2.append(B[i]);
//             sortSb(sb1);
//             sortSb(sb2);
//             System.out.println(sb1 + " " + sb2);
//             if(sb1.toString().equals(sb2.toString())) ans[i] = sb1.length();
//             else{
//                 if(i==0) ans[i] = 0;
//                 else ans[i] = ans[i-1];
//             }