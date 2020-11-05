package cipher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Package private class.
 * */

class DecoderImpl implements Decoder {

  private final Node root;

  private final Set<Character> symbols;

    private final Map<Character, String> symbolCodeMap;

  DecoderImpl(String symbols) throws IllegalArgumentException {
    if (null == symbols || symbols.isBlank() || symbols.isEmpty()) {
      throw new IllegalArgumentException("Coding symbols cannot be null or empty");
    }
    this.symbols = new HashSet<>();
    for (int i = 0; i < symbols.length(); i++) {
      this.symbols.add(symbols.charAt(i));
    }
    root = new NonLeafNode();
    symbolCodeMap = new HashMap<>();
  }

  @Override
  public void addSymbolToTree(char characterToEncode, String encoding)
          throws IllegalStateException, IllegalArgumentException {

    if (null == encoding || encoding.isEmpty() || encoding.isBlank()) {
      throw new IllegalArgumentException("Invalid encoding for character.");
    }

    if (symbolCodeMap.containsKey(characterToEncode)) {
      throw new IllegalStateException("this character is already in the tree.");
    }

    for (int i = 0; i < encoding.length(); i++) {
      if (!symbols.contains(encoding.charAt(i))) {
        throw new IllegalStateException("The encoding symbol does not exist in the map. ");
      }
    }

    Node temp = root;
    int length = encoding.length();

    for (int i = 0; i < length - 1; i++) {
      Node newNode = new NonLeafNode();
      Map<Character, Node> subTree = temp.getSubTree();
      char charAt = encoding.charAt(i);
      if (subTree.containsKey(charAt)) {
        if (subTree.get(charAt).getNodeType() == NodeType.LEAFNODE) {
          throw new IllegalStateException("This is not a prefix code.");
        }
      } else {
        temp.addNode(charAt, newNode);
      }
      temp = temp.getSubTree().get(charAt);
    }

    Node leafNode = new LeafNode(characterToEncode);
    char finalCode = encoding.charAt(length - 1);
    if (temp.getSubTree().containsKey(finalCode)) {
      if (temp.getSubTree().get(finalCode).getNodeType().equals(NodeType.NONLEAFNODE)) {
        throw new IllegalStateException("Not a prefix coding.");
      } else {
        throw new IllegalStateException("This encoding has already been used"
        + " for another character.");
      }
    }
    temp.addNode(finalCode, leafNode);
    symbolCodeMap.put(characterToEncode, encoding);
  }

  @Override
  public String decode(String encodedMessage)
          throws IllegalStateException, IllegalArgumentException {
    if (null == encodedMessage || encodedMessage.isBlank() || encodedMessage.isEmpty()) {
      throw new IllegalArgumentException("Invalid message.");
    }

    Node node = root;
    char[] encodedSymbols = encodedMessage.toCharArray();
    StringBuilder decodedMessage = new StringBuilder();

    for (char encodedSymbol : encodedSymbols) {
      if (!symbols.contains(encodedSymbol)) {
        throw new IllegalStateException("This encoding is not valid. Contains invalid symbols.");
      }
      Map<Character, Node> treeMap = node.getSubTree();

      if (treeMap.containsKey(encodedSymbol)) {
        if (treeMap.get(encodedSymbol).getNodeType().equals(NodeType.NONLEAFNODE)) {
          node = treeMap.get(encodedSymbol);
        } else {
          decodedMessage.append(treeMap.get(encodedSymbol).getDecodedCharacter());
          node = root;
        }
      } else {
        throw new IllegalStateException("Unable to decode message.");
      }
    }
    return decodedMessage.toString();
  }

}
