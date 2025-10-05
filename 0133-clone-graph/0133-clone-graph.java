/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {

    //create a hashmap to track already visited nodes
    HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        
        //check if the node is null, return node
        if(node == null){
            return node;
        }

        //check if the node is already visted, return the visited node from hashMap
        if(visited.containsKey(node)){
            return visited.get(node);
        }

        //create clone nodes of given original nodes
        Node cloneNode = new Node(node.val, new ArrayList());

        //put clone nodes into original sets of nodes
        visited.put(node, cloneNode);
        
        //create a for loop to iterate the process over all the neighbours of nodes
        for(Node neighbor: node.neighbors){
            cloneNode.neighbors.add(cloneGraph(neighbor)); //add neighbour clone nodes by calling cloneGraph function recursively
        }

        return cloneNode;

    }
}