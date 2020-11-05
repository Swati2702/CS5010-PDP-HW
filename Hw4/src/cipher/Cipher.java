package cipher;

import java.util.Map;

/**
 * Interface for Cipher.
 * It is used to create a cipher - can be created with use provided encoding scheme,
 * or an encoding scheme can be generated from a message given by the user along with the
 * characters that should be used in the encoding scheme.
 * The main component of encoding and decoding can be thought of as a
 * symbol -> code dictionary representing a coding scheme.
 * It provides methods to encode, decode a character as well as get the encoding scheme that
 * is being used by the cipher.
 * */
public interface Cipher {

  /** Used to encode a message.
   *
   * @param message : the message to be encoded
   * @throws IllegalArgumentException if provided message has characters which is not present in
   *            coding scheme
   * */
  String encodeMessage(String message) throws IllegalArgumentException;

  /**Used to decode a message.
   *
   * @param message : the message to be decoded
   * @throws IllegalArgumentException if provided message has characters which is not present in
   *            coding scheme
   * */
  String decodeMessage(String message) throws IllegalArgumentException;


  /**
   * Gets the encoding scheme used for encoding-decoding a message.
   * symbol -> code dictionary representing a coding scheme.
   * */
  Map<Character, String> getCodingScheme();

}
