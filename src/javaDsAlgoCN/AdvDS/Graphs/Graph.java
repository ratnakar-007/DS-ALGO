package javaDsAlgoCN.AdvDS.Graphs;

import java.util.*;

public class Graph {

    public static void print(int[][] edges) {
        boolean[] visited = new boolean[edges.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i])
                printHelper(edges, i, visited);
        }
    }

    private static void printHelper(int[][] edges, int sv, boolean[] visited) {
        System.out.print(sv + " ");
        visited[sv] = true;
        for (int i = 0; i < edges.length ; i++) {
            if (edges[sv][i] == 1 && !visited[i]) {
                printHelper(edges, i, visited);
            }
        }
    }

    public static void printBFS(int[][] edges, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        int vis;
        while (!q.isEmpty()) {
            vis = q.poll();
            System.out.print(vis + " ");
            for (int i = 0 ; i < edges.length ; i++) {
                if (edges[start][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void BFS(int[][] edges) {
        boolean[] visited = new boolean[edges.length];
        for (int i = 0 ; i < edges.length ; i++) {
            if(!visited[i]) {
                printBFS(edges, i, visited);
            }
        }
    }

    private static boolean hasPath(int[][] edges, int sv, int ev) {
        boolean[] visited = new boolean[edges.length];
        visited[sv] = true;
        return hasPathHelper(edges, sv, ev, visited);

    }

    public static boolean hasPathHelper(int[][] edges, int sv, int ev, boolean[] visited) {

        if(edges[sv][ev] == 1) {
            return true;
        }

        for (int i = 0 ; i < edges.length ; i++) {
            if (edges[sv][i] == 1 && !visited[i]) {
                visited[i] = true;
                boolean ans = hasPathHelper(edges, i, ev, visited);
                if (ans) {
                    return ans;
                }
            }
        }

        return false;
    }

    private static ArrayList<Integer> getPath(int[][] edges, int sv, int ev) {
        boolean[] visited = new boolean[edges.length];
        visited[sv] = true;
        ArrayList<Integer> ans = getPathHelper(edges, sv, ev, visited);
        if (ans != null)
            ans.add(sv);
        return ans;
    }

    public static ArrayList<Integer> getPathHelper(int[][] edges, int sv, int ev, boolean[] visited) {

        if (sv == ev) {
            ArrayList<Integer> ans = new ArrayList<>();
            return ans;
        }
        /*if(edges[sv][ev] == 1) {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(ev);
            return ans;
        }*/

        for (int i = 0 ; i < edges.length ; i++) {
            if (edges[sv][i] == 1 && !visited[i]) {
                visited[i] = true;
                ArrayList<Integer> ans = getPathHelper(edges, i, ev, visited);
                if (ans != null) {
                    ans.add(i);
                    return ans;
                }
            }
        }
        return null;
    }

    private static ArrayList<Integer> getPathBFS(int[][] edges, int sv, int ev) {

        if (sv == ev) {
            ArrayList<Integer> output = new ArrayList<>();
            output.add(sv);
            return  output;
        }

        boolean[] visited = new boolean[edges.length];
        visited[sv] = true;
        ArrayList<Integer> output = new ArrayList<>();
        HashMap<Integer, Integer> hm = getPathHelperBFS(edges, sv, ev, visited);

        if (hm != null) {
            output.add(ev);
            for (int i = 0 ; i < hm.size() ; i++ ) {
                int k = hm.get(ev);
                output.add(k);
                ev = k;
                if (k == sv) {
                    break;
                }
            }
        }
        return output;
    }

    public static HashMap<Integer, Integer> getPathHelperBFS(int[][] edges, int sv, int ev, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        visited[sv] = true;
        q.add(sv);
        int vis;
        while (!q.isEmpty()) {
            vis = q.poll();
            if (vis == ev) {
                return hm;
            }
            for (int i = 0 ; i < edges.length ; i++) {
                if (edges[vis][i] == 1 && !visited[i]) {
                    hm.put(i, vis);
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        return null;
    }

    public static int getMinimumVertex(int[] weight, boolean[] visited) {
        int minWeight = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0 ; i < weight.length ; i++) {
            if (!visited[i] && minWeight > weight[i]) {
                minWeight = weight[i];
                vertex = i;
            }
        }

        return vertex;
    }

    public static void primsMST(int[][] edges, int n) {
        boolean[] visited = new boolean[n];
        int[] parents = new int[n];
        int[] weight = new int[n];

        for (int i = 0 ; i < n ; i++ ) {
            weight[i] = Integer.MAX_VALUE;
        }

        weight[0] = 0;
        parents[0] = -1;

        for (int i = 0 ; i < n ; i++){
            int vertex = getMinimumVertex(weight, visited);
            visited[vertex] = true;

            for (int j = 0 ; j < n ; j++) {
                if(edges[vertex][j] > 0) {
                    if (!visited[j] && edges[vertex][j] < weight[j]) {
                        weight[j] = edges[vertex][j];
                        weight[j] = edges[vertex][j];
                        parents[j] = vertex;
                    }
                }
            }
        }

        for (int i = 1 ; i < n ; i++) {
            if (parents[i] < i) {
                System.out.println(parents[i] + " " + i + " " + edges[parents[i]][i]);
            } else {
                System.out.println(i + " " + parents[i] + " " + edges[parents[i]][i]);
            }
        }
    }

    public static void dijkstra(int[][] edges, int n) {
        boolean[] visited = new boolean[n];
        int[] parents = new int[n];
        int[] weight = new int[n];

        for (int i = 0 ; i < n ; i++ ) {
            weight[i] = Integer.MAX_VALUE;
        }

        weight[0] = 0;
        parents[0] = -1;

        for (int i = 0 ; i < n ; i++){
            int vertex = getMinimumVertex(weight, visited);
            visited[vertex] = true;

            for (int j = 0 ; j < n ; j++) {
                if(edges[vertex][j] > 0) {
                    if (!visited[j] && edges[vertex][j] + weight[vertex] < weight[j]) {
                        weight[j] = edges[vertex][j] + weight[vertex];
                        parents[j] = vertex;
                    }
                }
            }
        }

        for (int i = 0 ; i < n ; i++) {
            System.out.println(i + " " + weight[i]);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int V = s.nextInt();
        int E = s.nextInt();

        int edges[][] = new int[V][V];
        for (int i = 0; i < E; i++) {
            int fv = s.nextInt();
            int sv = s.nextInt();
            int w  = s.nextInt();
            edges[fv][sv] = w;
            edges[sv][fv] = w;
        }

        dijkstra(edges, V);
    }
}
