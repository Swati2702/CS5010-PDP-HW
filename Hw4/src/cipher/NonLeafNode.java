package cipher;

import java.util.HashMap;
import java.util.Map;

/**
 * Package private class.
 * */
class NonLeafNode implements Node {

  private final Map<Character, Node> subTree;

  NonLeafNode() {
    this.subTree = new HashMap<>();
  }

  @Override
  public void addNode(char symbol, Node node) {
    this.subTree.put(symbol, node);

  }

  @Override
  public Map<Character, Node> getSubTree() {
    return subTree;
  }

  @Override
  public char getDecodedCharacter() throws IllegalStateException {
    throw new IllegalStateException("Non leaf node does not contain decoded character.");
  }

  @Override
  public NodeType getNodeType() {
    return NodeType.NONLEAFNODE;
  }
}
