package javaDsAlgoCN.AdvDS.Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class KruskalMst {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ne = br.readLine().split(" ");
        int n = Integer.valueOf(ne[0]);
        int e = Integer.valueOf(ne[1]);

        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            String[] sdw = br.readLine().split(" ");
            int s = Integer.valueOf(sdw[0]);
            int d = Integer.valueOf(sdw[1]);
            int w = Integer.valueOf(sdw[2]);

            edges[i] = new Edge(s, d, w);
        }

        Arrays.sort(edges, (o1, o2) -> o1.weight < o2.weight ? -1 : 1);

        kruskalMst(edges, n);
    }

    public static void kruskalMst(Edge[] edges, int n) {
        int counter = 0;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int itr = 0;
        while (counter != n - 1) {
            Edge edge = edges[itr];
            int v1 = edge.source;
            int v2 = edge.destination;
            int x_set = find(parent, v1);
            int y_set = find(parent, v2);
            if (x_set != y_set) {
                if (v1 < v2)
                    System.out.println(v1 + " " + v2 + " " + edge.weight);
                else
                    System.out.println(v2 + " " + v1 + " " + edge.weight);

                //union(parent, x_set, y_set);
                parent[x_set] = parent[y_set];
                counter++;
                itr++;
            } else {
                itr++;
            }
        }
    }

    public static int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex)
            return find(parent, parent[vertex]);
        return vertex;
    }

    public static void union(int[] parent, int x, int y) {
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);

        parent[y_set_parent] = x_set_parent;
    }
}

/*    private static int unionFind(int v1, int v2, int[] parent) {
        if (parent[v1] == -1 && parent[v2] == -1) {
            parent[v1] = -2;
            parent[v2] = v1;
            return v1;
        }

        if (parent[v1] < 0 && parent[v2] < 0) {
            if (Math.abs(parent[v1]) > Math.abs(parent[v2])) {
                parent[v1] += parent[v2];
                parent[v2] = v1;
                return v1;
            } else {
                parent[v2] += parent[v1];
                parent[v1] = v2;
                return v2;
            }
        }


        if (parent[v1] == parent[v2]) {
            return -1;
        }

        if (parent[v2] < 0 && parent[v1] >= 0) {
            int p =unionFind(parent[v1], v2, parent);
            return p;
        }

        if (parent[v2] >= 0 && parent[v1] < 0) {
            int p =unionFind(v1, parent[v2], parent);
            return p;
        }

        if (parent[v1] > 0 && parent[v2] > 0) {
            int pv1 = unionFind(parent[v1], v2, parent);
            if (pv1 < 0) {
                return pv1;
            }
            *//*int pv2 = unionFind(v1, parent[v2], parent);
            if (pv2 < 0) {
                return pv2;
            }*//*
        }

        return -1;
    }*/
