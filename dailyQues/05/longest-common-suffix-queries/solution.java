class Solution {
    class Pair {
        String word;
        int index;

        Pair(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }

    class Node {
        Node[] children;
        Pair p;

        Node(String word, int index) {
            this.children = new Node[26];
            this.p = new Pair(word, index);
        }
    }

    Node root = new Node("", -1);

    private boolean isBetter(String s, int i, Pair p) {
        return p.index == -1
                || s.length() < p.word.length()
                || (s.length() == p.word.length() && i < p.index);
    }

    public void addNode(String s, int i) {

        if (isBetter(s, i, root.p)) {
            root.p = new Pair(s, i);
        }

        Node curr = root;

        for (char c : s.toCharArray()) {

            int index = c - 'a';

            if (curr.children[index] == null) {
                curr.children[index] = new Node(s, i);
            } else if (isBetter(s, i, curr.children[index].p)) {
                curr.children[index].p = new Pair(s, i);
            }

            curr = curr.children[index];
        }
    }

    public int findPrefix(String s) {
        Node curr = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null)
                return curr.p.index;
            curr = curr.children[index];
        }
        return curr.p.index;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int index = 0;
        for (String s : wordsContainer)
            addNode(new StringBuilder(s).reverse().toString(), index++);
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < ans.length; i++)
            ans[i] = findPrefix(new StringBuilder(wordsQuery[i]).reverse().toString());
        return ans;
        //          ()
        //         / 
        //         d  = dcb, 1, false;
        //         /
        //        c = dcb, 1, false
        //       /
        //     b = dcb,1,true
        //   /        \
        // a = dcba,0  x = dcbx,2
    }
}