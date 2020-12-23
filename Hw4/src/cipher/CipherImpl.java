package cipher;

import com.sun.tools.javac.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;



/**
 * Provides implementation for Cipher Interface.
 * It is used to create a cipher - can be created with user provided encoding scheme,
 * or an encoding scheme can be generated from a message given by the user along with the
 * characters that should be used in the encoding scheme.
 * It provides methods to encode, decode a character as well as get the encoding scheme that
 * is being used by the cipher.
 * */
public class CipherImpl implements Cipher {

  private Map<Character, String> prefixMap;
  private final Decoder decoder;

  /**
   * Constructor to create a cipher where Huffman encoding scheme of cipher is provided.
   * This scheme should be prefix codes and are often used since a sequence of codes
   * then requires no delimiters between codes, and will have only one possible decoding.
   * @param symbolCodeDictionary : symbol -> code dictionary which is prefix code
   * @throws IllegalArgumentException if the encoding for any of the characters is null,
   *        blank or empty
   * */
  public CipherImpl(Map<Character, String> symbolCodeDictionary) throws IllegalArgumentException {
    validateSymbolCodeDictionary(symbolCodeDictionary);
    prefixMap = symbolCodeDictionary;
    this.decoder = new DecoderImpl(getSymbolString());
    initDecoder();
  }

  /**
   * Constructor to create a cipher where Huffman encoding scheme of cipher is created from the
   * message and the symbols provided.
   * This scheme should be prefix codes and are often used since a sequence of codes
   * then requires no delimiters between codes, and will have only one possible decoding.
   * @param message : the message which wil be used to create huffman encoding
   * @param symbols : the symbols which wil be used to create huffman encoding
   * @throws IllegalArgumentException if any of the symbols provided is repeated, or if the message
   *            is null or empty
   * */
  public CipherImpl(String message, String symbols) throws IllegalArgumentException {
    if (!checkValidSymbolSet(symbols)) {
      throw new IllegalArgumentException("Invalid symbols.");
    }
    if (null == message || message.length() == 0) {
      throw new IllegalArgumentException("Invalid message.");
    }
    createPrefixTable(message, symbols);
    this.decoder = new DecoderImpl(symbols);
    initDecoder();
  }

  private void initDecoder() throws IllegalArgumentException {
    for (Character character : prefixMap.keySet()) {
      decoder.addSymbolToTree(character, prefixMap.get(character));
    }
  }

  private boolean checkValidSymbolSet(String symbols) {
    if (null == symbols || symbols.length() <= 1 ) {
      return false;
    }
    Set<Character> codingSymbols = new HashSet<>();
    for (int i = 0; i < symbols.length(); i++) {
      codingSymbols.add(symbols.charAt(i));
    }
    return codingSymbols.size() == symbols.length();

  }


  private String getSymbolString() {
    Set<Character> symbols = new HashSet<>();
    for (String encoding : prefixMap.values()) {
      char[] encodingSymbols = encoding.toCharArray();
      for (char ch: encodingSymbols) {
        symbols.add(ch);
      }
    }
    StringBuilder s = new StringBuilder();
    for (char ch: symbols) {
      s.append(ch);
    }
    return s.toString();
  }

  private void validateSymbolCodeDictionary(Map<Character, String> symbolCodeDictionary)
          throws IllegalArgumentException {
    for (String str: symbolCodeDictionary.values()) {
      if (null == str || str.isEmpty() || str.isBlank() ) {
        throw new IllegalArgumentException("Dictionary not valid.");
      }
    }

  }


  private Map<Character, Integer> createFrequencyTable(String message) {
    Map<Character, Integer> frequencyMap = new HashMap<>();
    for (int i = 0; i < message.length(); i++) {
      char c = message.charAt(i);
      if (frequencyMap.containsKey(c)) {
        int freq = frequencyMap.get(c);
        frequencyMap.put(c, freq + 1);
      }
      else {
        frequencyMap.put(c, 1);
      }
    }
    return frequencyMap;
  }

  private void createPrefixTable(String message, String symbols) {

    Map<Character, Integer> frequencyMap =  createFrequencyTable(message);
    PriorityQueue<Pair<String, Integer>> priorityQueue = new PriorityQueue<>((a, b) ->
            a.snd.equals(b.snd) ? a.fst.compareTo(b.fst) : a.snd - b.snd );

    prefixMap = new HashMap<>();
    for (Character c : frequencyMap.keySet()) {
      Pair<String, Integer> pair = new Pair<>("" + c, frequencyMap.get(c));
      priorityQueue.add(pair);
      prefixMap.put(c , "");
    }

    int length = symbols.length();

    while (priorityQueue.size() != 1) {

      StringBuilder combined = new StringBuilder();
      int freq = 0;

      for (int i = 0; i < length && !priorityQueue.isEmpty(); i++) {
        Pair<String, Integer> temp = priorityQueue.remove();
        combined.append(temp.fst);
        freq += temp.snd;
        char symbol = symbols.charAt(i);
        for (int j = 0; j < temp.fst.length(); j++) {
          prefixMap.put(temp.fst.charAt(j), symbol + prefixMap.get(temp.fst.charAt(j)));
        }
      }
      priorityQueue.add(new Pair<>(combined.toString(), freq));
    }

  }

  @Override
  public String encodeMessage(String message) throws IllegalArgumentException {

    if (null == message || message.isBlank() || message.isEmpty()) {
      throw new IllegalArgumentException("Invalid message.");
    }
    StringBuilder encoding = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      if (!prefixMap.containsKey(message.charAt(i))) {
        throw new IllegalArgumentException("Message has invalid characters not in coding scheme.");
      }
      encoding.append(prefixMap.get(message.charAt(i)));
    }
    return encoding.toString();
  }

  @Override
  public String decodeMessage(String message) {
    return decoder.decode(message);
  }

  @Override
  public Map<Character, String> getCodingScheme() {
    return prefixMap;
  }

  @Override
  public String toString() {
    return "CipherImpl{" + "prefixMap=" + prefixMap.toString() + '}';
  }


}
