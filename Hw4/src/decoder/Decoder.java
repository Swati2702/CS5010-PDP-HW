package decoder;

public interface Decoder {


  void addSymbolToTree(char characterToEncode, String encoding)
          throws IllegalStateException, IllegalArgumentException;

  String decode(String encodedMessage) throws IllegalArgumentException, IllegalStateException;

}
