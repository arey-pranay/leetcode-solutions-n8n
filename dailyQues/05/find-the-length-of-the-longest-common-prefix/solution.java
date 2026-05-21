class Solution {
    class TrieNode {
        TrieNode[] mChildren = new TrieNode[10];
    }
    
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        TrieNode root = new TrieNode();
        for (int num : arr1) {
            addToTrie(root, num);
        }

        int maxLen = 0;

        for (int num : arr2) {
            maxLen = Math.max(maxLen, getCommonPrefixLen(root, num));
        }

        return maxLen;
    }

    private void addToTrie(TrieNode root, int num) {
        String numStr = String.valueOf(num);
        TrieNode currNode = root;
        for (int i = 0; i < numStr.length(); i++) {
            int charIndex = numStr.charAt(i) - '0';
            if (currNode.mChildren[charIndex] == null) {
                currNode.mChildren[charIndex] = new TrieNode();
            }
            currNode = currNode.mChildren[charIndex];
        }
    }

    private int getCommonPrefixLen(TrieNode root, int num) {
        String numStr = String.valueOf(num);
        TrieNode currNode = root;
        for (int i = 0; i < numStr.length(); i++) {
            int charIndex = numStr.charAt(i) - '0';
            if (currNode.mChildren[charIndex] == null) {
                return i;
            }
            currNode = currNode.mChildren[charIndex];
        }
        return numStr.length();
    }
}