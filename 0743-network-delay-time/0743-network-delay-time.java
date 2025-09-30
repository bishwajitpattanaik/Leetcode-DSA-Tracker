class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Create a graph represented as an adjacency list where integer - nodes; edges - linkedlist
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            //mark very first node as key and second and third as edge and time to reach that edge
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[] { edge[1], edge[2] });
        }

        // Use a priority queue to select the node with the minimum distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { k, 0 }); // Start from node k with a distance of 0 or starting point for min heap for pq

        // Initialize distances array with infinity
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0; // Distance to itself is 0

        //if pq is not empty, pop out very first element in pq, update currentNode with very first element and update currentDist with time taken toreach second element of that tuple

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDist = current[1];

            // If we have already found a shorter path before, continue
            if (currentDist > distances[currentNode]) {
                continue;
            }

            // Update the distances of neighboring nodes

            //check what all neighbours of that particular node - ifcndition
            //marking neighbours in that graph like data structure - for loop
            //adding those neighbours into pq - pq.offer
            if (graph.containsKey(currentNode)) {
                for (int[] neighbor : graph.get(currentNode)) {
                    int nextNode = neighbor[0];
                    int nextDist = currentDist + neighbor[1];
                    if (nextDist < distances[nextNode]) {
                        distances[nextNode] = nextDist;
                        pq.offer(new int[] { nextNode, nextDist });
                    }
                }
            }
        }

        // Find the maximum distance from the source node to all other nodes; skipping very first element for k = 1 to n
        int maxDist = Arrays.stream(distances).skip(1).max().getAsInt();
        return maxDist == Integer.MAX_VALUE ? -1 : maxDist;
    }

}