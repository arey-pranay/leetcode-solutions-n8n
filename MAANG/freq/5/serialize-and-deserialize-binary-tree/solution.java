public class Codec {
    public String serialize(TreeNode root) { //server converts to string and sends to client
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            if(temp == null){
                sb.append(' ');
            }
            else{
                sb.append(temp.val);
                q.add(temp.left);
                q.add(temp.right);
            }
            sb.append(',');
        }
        return sb.toString();
    }
    public TreeNode deserialize(String data) {
       if(data.isEmpty() || data.charAt(0)==' ') return null;
       String[] arr = data.split(",");
       TreeNode root = createNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
            TreeNode curr = q.poll();
            if(!arr[i].equals(" ")){
                curr.left = createNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            if(!arr[i].equals(" ")){
                curr.right = createNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
       return root;
    }
    public TreeNode createNode(String s){
        return new TreeNode(Integer.parseInt(s));
    }
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
