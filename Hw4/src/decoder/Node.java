package decoder;

import java.util.Map;

interface Node {


  void addNode(char symbol, Node node);

  Map<Character, Node> getSubTree();

  char getDecodedCharacter() throws IllegalStateException;

  NodeType getNodeType();
}
