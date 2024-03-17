import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int V, E, K;
	static List<Edge>[] graph;
	
	public static void main(String[] args) throws Exception {
		readInput();
		solve();
	}
	
	static void readInput() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b, c));
		}
	}
	
	static void solve() {
		final int INF = 1_000_000;
		int[] dist = new int[V + 1];
		boolean[] visit = new boolean[V + 1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
		dist[K] = 0;
		pq.offer(new Edge(K, 0));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (visit[cur.node]) continue;
			visit[cur.node] = true;
			
			for (Edge e : graph[cur.node]) {
				if (dist[e.node] > cur.cost + e.cost) {
					dist[e.node] = cur.cost + e.cost;
					pq.offer(new Edge(e.node, dist[e.node]));
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			answer.append(dist[i] == INF ? "INF" : dist[i]).append('\n');
		}
		System.out.print(answer);
	}
	
	static class Edge {
		int node;
		int cost;
		
		Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
}
