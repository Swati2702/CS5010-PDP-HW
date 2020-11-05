package cipher;

import java.util.HashMap;
import java.util.Map;

/**
 * Package private class.
 * */
class LeafNode implements Node {

  private  final char decodedCharacter;
  private final Map<Character, Node> subTree;

  LeafNode(char symbol) {
    this.decodedCharacter = symbol;
    this.subTree = new HashMap<>();
  }


  @Override
  public void addNode(char symbol, Node node) {
    throw new IllegalStateException("Invalid operation -  adding new node to leaf node.");
  }

  @Override
  public Map<Character, Node> getSubTree() {
    return subTree;
  }

  @Override
  public char getDecodedCharacter() throws IllegalStateException {
    return decodedCharacter;
  }

  @Override
  public NodeType getNodeType() {
    return NodeType.LEAFNODE;
  }
}
