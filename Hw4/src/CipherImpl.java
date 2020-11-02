import com.sun.tools.javac.util.Pair;

import decoder.Decoder;
import decoder.DecoderImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * Cipher.
 * */
public class CipherImpl implements Cipher {

  private Map<Character, String> prefixMap;
  private final Decoder decoder;

  public CipherImpl(Map<Character, String> symbolCodeDictionary) {
    //validation
    prefixMap = symbolCodeDictionary;
    StringBuilder symbols = new StringBuilder();
    for (Character character : prefixMap.keySet()) {
      symbols.append(character);
    }
    this.decoder = new DecoderImpl(symbols.toString());
    initDecoder();

  }

  public CipherImpl(String message, String symbols) throws IllegalArgumentException {
    if (!checkValidSymbolSet(symbols)) {
      throw new IllegalArgumentException("Invalid symbols.");
    }
    if (message.length() == 0) {
      throw new IllegalArgumentException("Invalid message.");
    }
    createPrefixTable(message, symbols);
    this.decoder = new DecoderImpl(symbols.toString());
    initDecoder();
  }

  private void initDecoder() {
    for (Character character : prefixMap.keySet()) {
      decoder.addSymbolToTree(character, prefixMap.get(character));
    }
  }

  private boolean checkValidSymbolSet(String symbols) {
    if (symbols.length() <= 1 ) {
      return false;
    }
    Set<Character> codingSymbols = new HashSet<>();
    for (int i = 0; i < symbols.length(); i++) {
      codingSymbols.add(symbols.charAt(i));
    }
    return codingSymbols.size() == symbols.length();

  }

  Map<Character, Integer> createFrequencyTable(String message) {
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

  void createPrefixTable(String message, String symbols) {

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
  public String encodeMessage(String message) {
    StringBuilder encoding = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      encoding.append(prefixMap.get(message.charAt(i)));
    }
    return encoding.toString();
  }

  @Override
  public String decodeMessage(String message) {
    return decoder.decode(message);
  }
}
