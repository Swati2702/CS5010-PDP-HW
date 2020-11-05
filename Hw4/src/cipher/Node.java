package cipher;

import java.util.Map;

/**
 * Package private interface.
 * */
interface Node {


  void addNode(char symbol, Node node);

  Map<Character, Node> getSubTree();

  char getDecodedCharacter() throws IllegalStateException;

  NodeType getNodeType();
}
