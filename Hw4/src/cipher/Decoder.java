package cipher;

/**
 * Package private interface.
 * */
interface Decoder {

  //adds a symbol to the tree using the {@encoding}
  void addSymbolToTree(char characterToEncode, String encoding)
          throws IllegalStateException, IllegalArgumentException;

  //Used to decode the message
  String decode(String encodedMessage) throws IllegalArgumentException, IllegalStateException;

}
