package cipher;

/**
 * Package private interface.
 * */
interface Decoder {

  void addSymbolToTree(char characterToEncode, String encoding)
          throws IllegalStateException, IllegalArgumentException;

  String decode(String encodedMessage) throws IllegalArgumentException, IllegalStateException;

}
