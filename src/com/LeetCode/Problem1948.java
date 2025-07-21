package com.LeetCode;

// 1948. Problem : Delete Duplicate Folders in System

import java.util.*;
public class Problem1948 {
    /**
     * Inner class representing a node in the Trie.
     * Each node corresponds to a folder.
     */
    static class Node {
        String val; // The folder name
        Map<String, Node> children;
        String subFolderSignature; // A serialized string representing the entire subfolder structure

        Node(String val) {
            this.val = val;
            this.children = new HashMap<>();
            this.subFolderSignature = ""; // Initially empty
        }
    }

    /**
     * Inserts a path into the Trie.
     */
    private static void insert(Node root, List<String> path) {
        Node current = root;
        for (String folder : path) {
            current.children.putIfAbsent(folder, new Node(folder));
            current = current.children.get(folder);
        }
    }

    /**
     * Performs a post-order DFS to serialize each subfolder's structure into a unique signature.
     * This signature is used to identify duplicate structures.
     *
     * @param node The current node in the Trie.
     * @param signatureCounts A map to store the frequency of each unique subfolder signature.
     * @return The serialized signature string for the subtree rooted at 'node'.
     */
    private static String serializeSubfolders(Node node, Map<String, Integer> signatureCounts) {
        // Base case: If a node has no children, its structure is empty.
        if (node.children.isEmpty()) {
            return "";
        }

        // To ensure a canonical representation, process children in alphabetical order.
        List<String> childKeys = new ArrayList<>(node.children.keySet());
        Collections.sort(childKeys);

        StringBuilder signatureBuilder = new StringBuilder();
        for (String key : childKeys) {
            Node child = node.children.get(key);
            // Recursively get the signature of the child's subfolder structure.
            String childSignature = serializeSubfolders(child, signatureCounts);

            // Append the child's folder name and its structure signature.
            signatureBuilder.append('(').append(key).append(childSignature).append(')');
        }

        String signature = signatureBuilder.toString();

        // Store the generated signature in the node.
        node.subFolderSignature = signature;

        // Count the occurrences of this signature.
        signatureCounts.put(signature, signatureCounts.getOrDefault(signature, 0) + 1);

        return signature;
    }

    /**
     * Traverses the Trie and removes any subfolder nodes whose structure is a duplicate.
     *
     * @param node The current node in the Trie.
     * @param signatureCounts The map containing the frequency of each signature.
     */
    private static void pruneDuplicates(Node node, Map<String, Integer> signatureCounts) {
        // Use an iterator for safe removal during iteration.
        Iterator<Map.Entry<String, Node>> iterator = node.children.entrySet().iterator();

        while (iterator.hasNext()) {
            Node child = iterator.next().getValue();

            // If the child's subfolder signature has been seen more than once, it's a duplicate.
            // We only care about non-empty structures being duplicates.
            if (!child.subFolderSignature.isEmpty() && signatureCounts.get(child.subFolderSignature) > 1) {
                iterator.remove(); // Prune this entire subfolder from the Trie.
            } else {
                // If not a duplicate, continue traversing down this path.
                pruneDuplicates(child, signatureCounts);
            }
        }
    }

    /**
     * Performs a pre-order DFS on the pruned Trie to construct the final list of paths.
     *
     * @param node The current node in the Trie.
     * @param currentPath The path built so far.
     * @param result The final list of non-duplicate folder paths.
     */
    private static void buildResultPaths(Node node, List<String> currentPath, List<List<String>> result) {
        for (String key : node.children.keySet()) {
            Node child = node.children.get(key);

            // Add current folder to the path
            currentPath.add(child.val);
            result.add(new ArrayList<>(currentPath));

            // Recurse to children
            buildResultPaths(child, currentPath, result);

            // Backtrack: remove current folder to explore siblings
            currentPath.remove(currentPath.size() - 1);
        }
    }

    public static List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = new Node("/");

        // Step 1: Build the Trie from the input paths.
        for (List<String> path : paths) {
            insert(root, path);
        }

        // Step 2: Serialize all subfolder structures and count their frequencies.
        Map<String, Integer> signatureCounts = new HashMap<>();
        serializeSubfolders(root, signatureCounts);

        // Step 3: Prune the Trie by removing nodes that correspond to duplicate structures.
        pruneDuplicates(root, signatureCounts);

        // Step 4: Construct the final list of paths from the pruned Trie.
        List<List<String>> result = new ArrayList<>();
        buildResultPaths(root, new ArrayList<>(), result);

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Create an instance of the class to call the method
        Problem1948 solution = new Problem1948();

        System.out.print("Enter the number of test cases: ");
        int numTestCases = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline character after reading the integer

        for (int t = 1; t <= numTestCases; t++) {
            System.out.println("\n--- Test Case #" + t + " ---");
            System.out.print("Enter the total number of paths: ");
            int numPaths = sc.nextInt();
            sc.nextLine(); // Consume the newline again

            List<List<String>> allPaths = new ArrayList<>();
            System.out.println("Enter each path on a new line (e.g., 'a b x'):");

            // Loop to get each path from the user
            for (int i = 0; i < numPaths; i++) {
                System.out.print("Path " + (i + 1) + ": ");
                String line = sc.nextLine();

                // Split the user's input line into a list of strings
                String[] folders = line.split(" ");
                allPaths.add(new ArrayList<>(Arrays.asList(folders)));
            }

            // Call the instance method and print its result
            List<List<String>> result = solution.deleteDuplicateFolder(allPaths);
            System.out.println("<- Result for Test Case #" + t + ": " + result);
        }

        sc.close();
    }
}
