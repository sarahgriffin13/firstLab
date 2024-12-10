import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Step 1: Define vertices for the labeled buildings
        String[] vertices = {
            "Liberal Arts", 
            "Student Services", 
            "Health Careers & Sciences", 
            "Health Technologies Center", 
            "Recreation Center", 
            "Technology Learning Center", 
            "Business & Technology", 
            "Theatre"
        };

        // Step 2: Define edges based on direct connections from the map
        int[][] edges = {
            {0, 1}, // Liberal Arts -> Student Services
            {1, 2}, // Student Services -> Health Careers & Sciences
            {2, 3}, // Health Careers & Sciences -> Health Technologies Center
            {2, 4}, // Health Careers & Sciences -> Recreation Center
            {4, 5}, // Recreation Center -> Technology Learning Center
            {5, 6}, // Technology Learning Center -> Business & Technology
            {6, 7}, // Business & Technology -> Theatre
            {3, 6}  // Health Technologies Center -> Business & Technology
        };

        // Step 3: Create the graph
        UnweightedGraph<String> graph = new UnweightedGraph<>(vertices, edges);

        // Step 4: Perform DFS starting from "Business & Technology"
        int startVertex = graph.getIndex("Business & Technology");
        UnweightedGraph<String>.SearchTree dfsTree = graph.dfs(startVertex);

        // Step 5: Print paths to specific destinations
        System.out.println("Paths from Business & Technology:");
        dfsTree.printPath(graph.getIndex("Health Technologies Center"));
        System.out.println();
        dfsTree.printPath(graph.getIndex("Student Services"));
        System.out.println();
        dfsTree.printPath(graph.getIndex("Recreation Center"));
        System.out.println();

        // Step 6: Print the entire DFS tree
        System.out.println("DFS Tree:");
        dfsTree.printTree();
    }
}
