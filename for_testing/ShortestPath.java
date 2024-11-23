import java.util.*;

public class ShortestPath {

    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        List<List<Edge>> adjacencylist;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjacencylist.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist.get(source).add(edge);

            edge = new Edge(destination, source, weight);
            adjacencylist.get(destination).add(edge); // For undirected graph
        }

        public List<Integer> dijkstra(int source, int destination) {
            int[] dist = new int[vertices];
            int[] prev = new int[vertices];
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v]));

            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(prev, -1);

            dist[source] = 0;
            pq.offer(source);

            while (!pq.isEmpty()) {
                int u = pq.poll();

                if (u == destination) break;

                for (Edge edge : adjacencylist.get(u)) {
                    int v = edge.destination;
                    int weight = edge.weight;
                    if (dist[v] > dist[u] + weight) {
                        dist[v] = dist[u] + weight;
                        prev[v] = u;
                        pq.offer(v);
                    }
                }
            }

            List<Integer> path = new ArrayList<>();
            int curr = destination;
            if (prev[curr] != -1 || curr == source) {
                while (curr != -1) {
                    path.add(0, curr);
                    curr = prev[curr];
                }
            }

            return path;
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 5, 11);
        graph.addEdge(4, 3, 4);
        graph.addEdge(4, 5, 2);

        int source = 0;
        int destination = 5;
        List<Integer> shortestPath = graph.dijkstra(source, destination);

        System.out.println("Shortest path from " + source + " to " + destination + ": " + shortestPath);
    }
}
