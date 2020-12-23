import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cipher.Cipher;
import cipher.CipherImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Cipher - encoding, decoding.
 * */
public class CipherTest {

  Cipher cipherWithEncodingProvided;
  Cipher cipherWithEncodingGenerated;

  @Before
  public void setCipher() {
    cipherWithEncodingGenerated = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA SHORE",
            "01");
    Map<Character, String> symbolCodeDictionary = new HashMap<>();
    symbolCodeDictionary.put('a', "z");
    symbolCodeDictionary.put('b', "y");
    symbolCodeDictionary.put('c', "x");
    symbolCodeDictionary.put('d', "w");
    cipherWithEncodingProvided = new CipherImpl(symbolCodeDictionary);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCipherConstructorDisallowsEmptyEncodingForProvidedEncoding() {
    Map<Character, String> symbolCodeDictionary = new HashMap<>();
    symbolCodeDictionary.put('a', "z");
    symbolCodeDictionary.put('b', "");
    cipherWithEncodingProvided = new CipherImpl(symbolCodeDictionary);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCipherConstructorDisallowsNullEncodingForProvidedEncoding() {
    Map<Character, String> symbolCodeDictionary = new HashMap<>();
    symbolCodeDictionary.put('a', "z");
    symbolCodeDictionary.put('b', null);
    cipherWithEncodingProvided = new CipherImpl(symbolCodeDictionary);
  }

  @Test(expected = IllegalStateException.class)
  public void testCipherConstructorDisallowsNonPrefixEncodingForProvidedEncoding() {
    Map<Character, String> symbolCodeDictionary = new HashMap<>();
    symbolCodeDictionary.put('a', "z");
    symbolCodeDictionary.put('b', "zp");
    cipherWithEncodingProvided = new CipherImpl(symbolCodeDictionary);
  }

  @Test
  public void testCipherConstructorForProvidedEncoding() {
    assertEquals("CipherImpl{prefixMap={a=z, b=y, c=x, d=w}}",
            cipherWithEncodingProvided.toString());
  }

  @Test
  public void testCipherConstructorForGeneratedEncoding() {
    assertEquals("CipherImpl{prefixMap={ =110, A=0001, B=00100, R=00110, S=10, "
            + "T=00111, E=111, H=010, Y=0000, L=011, O=00101}}",
            cipherWithEncodingGenerated.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCipherConstructorDisallowsRepeatedSymbolsForGeneratedEncoding() {
    cipherWithEncodingGenerated = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA SHORE",
            "011");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCipherConstructorDisallowsEmptyMessageForGeneratedEncoding() {
    cipherWithEncodingGenerated = new CipherImpl("",
            "01");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCipherConstructorDisallowsBlankMessageForGeneratedEncoding() {
    cipherWithEncodingGenerated = new CipherImpl("    ",
            "01");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCipherConstructorDisallowsNullMessageForGeneratedEncoding() {
    cipherWithEncodingGenerated = new CipherImpl(null,
            "01");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCipherConstructorDisallowsNullSymbolSetForGeneratedEncoding() {
    cipherWithEncodingGenerated = new CipherImpl("abc",
            null);
  }

  @Test
  public void testGetCodingSchemeForGeneratedEncoding() {
    assertEquals("{ =110, A=0001, B=00100, R=00110, S=10, T=00111, E=111, H=010, "
            + "Y=0000, L=011, O=00101}", cipherWithEncodingGenerated.getCodingScheme().toString());
  }

  @Test
  public void testGetCodingSchemeForProvidedEncoding() {
    assertEquals("{a=z, b=y, c=x, d=w}",
            cipherWithEncodingProvided.getCodingScheme().toString());
  }

  @Test
  public void testEncodedMessageForProvidedEncoding() {
    assertEquals("zyzxw", cipherWithEncodingProvided.encodeMessage("abacd"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEncodeMessageDisallowsIllegalMessageForProvidedEncoding() {
    cipherWithEncodingProvided.encodeMessage("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEncodeMessageDisallowsIllegalMessageForProvidedEncoding2() {
    cipherWithEncodingProvided.encodeMessage("hkjsjdk");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEncodeMessageDisallowsIllegalMessageForGeneratedEncoding() {
    cipherWithEncodingGenerated.encodeMessage("SEE HE BOXB");
  }

  @Test
  public void testEncodedMessageForGeneratedEncoding() {
    assertEquals("10111111110010111110001000010100100",
            cipherWithEncodingGenerated.encodeMessage("SEE HE BOB"));
  }

  @Test(expected = IllegalStateException.class)
  public void testDecodeMessageDisallowsIllegalMessageForProvidedEncoding() {
    cipherWithEncodingProvided.decodeMessage("Bazinga");
  }

  @Test(expected = IllegalStateException.class)
  public void testDecodeMessageDisallowsIllegalMessageForGeneratedEncoding() {
    cipherWithEncodingGenerated.decodeMessage("BAZINGA");
  }


  @Test
  public void testDecodedMessageForGeneratedEncoding() {
    assertEquals("SEE HE BOB",
            cipherWithEncodingGenerated.decodeMessage("10111111110010111110001000010100100"));
  }

  @Test
  public void testDecodedMessageForProvidedEncoding() {
    assertEquals("abacd", cipherWithEncodingProvided.decodeMessage("zyzxw"));
  }



  @Test
  public void testEncodedMessageForGeneratedEncodingOctal() {
    cipherWithEncodingGenerated = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA "
            + " SHORE","01234567");
    assertEquals("20013601303130",
            cipherWithEncodingGenerated.encodeMessage("SEE HE BOB"));
  }


  @Test
  public void testDecodedMessageForGeneratedEncodingOctal() {
    cipherWithEncodingGenerated = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA "
            + " SHORE",
            "01234567");
    assertEquals("SEE HE BOB",
            cipherWithEncodingGenerated.decodeMessage("20013601303130"));
  }

  @Test
  public void testEncodedMessageForGeneratedEncodingHexaDecimal() {
    cipherWithEncodingGenerated = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA "
            + " SHORE",
            "0123456789abcdef");
    assertEquals("a889689010",
            cipherWithEncodingGenerated.encodeMessage("SEE HE BOB"));
  }


  @Test
  public void testDecodedMessageForGeneratedEncodingHexaDecimal() {
    cipherWithEncodingGenerated = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA "
            + " SHORE",
            "0123456789abcdef");
    assertEquals("SEE HE BOB",
            cipherWithEncodingGenerated.decodeMessage("a889689010"));
  }
  @Test
  public void testPlainTextEncodingThenDecodingGivesBackPlainText() {
    cipherWithEncodingGenerated = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA "
            + " SHORE",
            "0123456789abcdef");
    String plaintext = "SEE HE BOB";
    String encoded = cipherWithEncodingGenerated.encodeMessage(plaintext);
    String decoded = cipherWithEncodingGenerated.decodeMessage(encoded);
    assertEquals(plaintext, decoded);
  }


}
