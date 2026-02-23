import java.util.*;

public class Traversals {

  /**
   * Returns the sum of the values of all leaf nodes in the given tree of integers.
   * A leaf node is defined as a node with no children.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the sum of leaf node values, or 0 if the tree is null
   */
  public static int sumLeafNodes(TreeNode<Integer> node) {
    // if node is null, return 0;
    if (node == null) return 0;
    // if node's left and right children are null (leaf)
    if (node.left == null && node.right == null) {
      // return node's value
      return node.value;
    }
    // traverse the left subtree and save it 
    int leftSum = sumLeafNodes(node.left);
    // traverse the right subtree and save it 
    int rightSum = sumLeafNodes(node.right);
    // return the sum of leaf values from the left and right subtrees
    return leftSum + rightSum;
  }

  /**
   * Counts the number of internal nodes (non-leaf nodes) in the given tree of integers.
   * An internal node has at least one child.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the count of internal nodes, or 0 if the tree is null
   */
  public static int countInternalNodes(TreeNode<Integer> node) {
    // if node is null, return 0
    if (node == null) return 0;
    // if node's left and right child is null, return 0 (this is a leaf)
    if (node.left == null && node.right == null) return 0;
    // traverse down the left subtree and save it 
    int leftCount = countInternalNodes(node.left);
    // traverse down the right subtree and save it
    int rightCount = countInternalNodes(node.right);
    // return left subtree count + right subtree count + 1 for the current internal node
    return leftCount + rightCount + 1;
  }

  /**
   * Creates a string by concatenating the string representation of each node's value
   * in a post-order traversal of the tree. For example, if the post-order visitation
   * encounters values "a", "b", and "c" in that order, the result is "abc".
   * If node is null, returns an empty string.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a post-order traversal string, or an empty string if the tree is null
   */
  public static <T> String buildPostOrderString(TreeNode<T> node) {
    // if node is null, return an empty string
    if (node == null) return "";
    // traverse the left subtree and save it
    String left = buildPostOrderString(node.left);
    // traverse the right subtree and save it
    String right = buildPostOrderString(node.right);
    // return left subtree + right subtree + current node's value
    return left + right + node.value;
  }

  /**
   * Collects the values of all nodes in the tree level by level, from top to bottom.
   * If node is null, returns an empty list.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a list of node values in a top-to-bottom order, or an empty list if the tree is null
   */
  public static <T> List<T> collectLevelOrderValues(TreeNode<T> node) {
    // if node is null, return an empty list
    if (node == null) return new ArrayList<>();
    // Create a queue to handle nodes in first in first out (F.I.F.O) order to list node values in top-to-bottom order
    Queue<TreeNode<T>> queue = new LinkedList<>();
    // Create a list to output results
    List<T> results = new ArrayList<>();

    // add node to queue
    queue.add(node);

    // Process nodes level by level untill queue is empty
    while (!queue.isEmpty()) {
      // Remove node from queue and save it
      TreeNode<T> current = queue.remove();
      // Add the current node's value to the list
      results.add(current.value);
      // if current has a left child
      if (current.left != null) {
        // add it to the queue
        queue.add(current.left);
      }
      // if current has a right child
      if (current.right != null) {
        // add it to the queue
        queue.add(current.right);
      }
    }
    // return the results list
    return results;
  }

  /**
   * Counts the distinct values in the given tree.
   * If node is null, returns 0.
   *
   * @param node the node of the tree
   * @return the number of unique values in the tree, or 0 if the tree is null
   */
  public static int countDistinctValues(TreeNode<Integer> node) {
    // if node is null, return 0
    if (node == null) return 0;
    // set to hold distinct values
    Set<Integer> set = new HashSet<>();
    // call the helper method which takes a node and the set as a parameter
    collectDistinct(node, set);
    //return the size of the set
    return set.size();
  }

  private static void collectDistinct(TreeNode<Integer> node, Set<Integer> set) {
    // if node is null, return
    if (node == null) return;
    // add node.valuesto the set ignoring duplicates
    set.add(node.value);
    // recurse the left sub tree, collecting and adding the values to the set
    collectDistinct(node.left, set);
    // recurse the right sub tree, collecting and adding the values to the set
    collectDistinct(node.right, set);
  }

  /**
   * Determines whether there is at least one root-to-leaf path in the tree
   * where each successive node's value is strictly greater than the previous node's value.
   * If node is null, returns false.
   *
   * @param node the node of the tree
   * @return true if there exists a strictly increasing root-to-leaf path, false otherwise
   */
  public static boolean hasStrictlyIncreasingPath(TreeNode<Integer> node) {
    // if node is null return false
    if (node == null) return false;
    // start recursion at root using MIN_VALUE since root as no parent to compare against.
    return containsIncreasingPath(node, Integer.MIN_VALUE);
  }

  private static boolean containsIncreasingPath(TreeNode<Integer> node, int previous) {
    // if node is null, return false
    if (node == null) return false;
    // if the value of the current node is less than or equal to previous, return false. values have to increase
    if (node.value <= previous) return false;
    // if node.left and node.right are null, we reached a leaf, return true
    if (node.left == null && node.right == null) return true;
    // check both the left and the right side of the tree, if either have an increasing path to a leaf, return true
    return containsIncreasingPath(node.left, node.value) || containsIncreasingPath(node.right, node.value);
  }

  // OPTIONAL CHALLENGE
  /**
   * Checks if two trees have the same shape. Two trees have the same shape
   * if they have exactly the same arrangement of nodes, irrespective of the node values.
   * If both trees are null, returns true. If one is null and the other is not, returns false.
   *
   * @param nodeA the node of the first tree
   * @param nodeB the node of the second tree
   * @param <T>   the type of values stored in the trees
   * @return true if the trees have the same shape, false otherwise
   */
  public static <T> boolean haveSameShape(TreeNode<T> nodeA, TreeNode<T> nodeB) {
    return false;
  }


  // OPTIONAL CHALLENGE
  // Very challenging!
  // Hints:
  // List.copyOf may be helpful
  // Consider adding a helper method
  // Consider keeping the current path in a separate variable
  // Consider removing the current node from the current path after the node's subtrees have been traversed.
  /**
   * Finds all paths from the root to every leaf in the given tree.
   * Each path is represented as a list of node values from root to leaf.
   * The paths should be added pre-order.
   * If node is null, returns an empty list.
   * 
   * Example:
   *
   *         1
   *        / \
   *       2   3
   *      / \    \
   *     4   5    6
   * 
   * Expected output:
   *   [[1, 2, 4], [1, 2, 5], [1, 3, 6]]
   * 
   * @param node the root node of the tree
   * @return a list of lists, where each inner list represents a root-to-leaf path in pre-order
   */
  public static <T> List<List<T>> findAllRootToLeafPaths(TreeNode<T> node) {
    return null;
  }
}
