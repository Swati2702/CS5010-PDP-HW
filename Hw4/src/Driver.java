import cipher.Cipher;
import cipher.CipherImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



/**
 * Driver class to run the program.
 * */
public class Driver {

  static Scanner scanner = new Scanner(System.in);

  /**
   * Driver program to run the maze.
   *
   * @param args : 1 -  for User input,  2 - for sample run 1, 3 - for sample run 2 ,
   *            4 - for sample run 3
   */
  public static void main(String... args) throws FileNotFoundException {
    Driver driver = new Driver();
    switch (args[0]) {
      case "-1" : driver.run1();
      break;
      case "-2" : driver.run2();
      break;
      case "-3" : driver.run3();
        break;
      case "-4" : driver.run4();
        break;
      default: System.out.println("Invalid argument.");
    }

  }

  /**
   * Reads message from user to make a binary huffman encoding.
   * */
  void run1() {
    scanner = new Scanner(System.in);
    System.out.println("Enter message which will be used for creating binary huffman "
                + "encoding.");
    String msg = scanner.nextLine();
    System.out.println("Enter symbols which will be used for creating binary huffman "
            + "encoding.");
    String symbols = scanner.nextLine();
    Cipher cipher = new CipherImpl(msg, symbols);
    System.out.println("The coding scheme generated is:");
    Map<Character, String> codingScheme =  cipher.getCodingScheme();
    for (Character ch: codingScheme.keySet()) {
      System.out.println(ch + ":" + codingScheme.get(ch));
    }
    System.out.println("Enter message you want to encode using this coding scheme.");
    msg = scanner.nextLine();
    try {
      System.out.println("The encoded message is " + cipher.encodeMessage(msg));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    System.out.println("Enter message you want to decode using this coding scheme.");
    msg = scanner.nextLine();
    try {
      System.out.println("The decoded message is " + cipher.decodeMessage(msg));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  void run2() throws FileNotFoundException {
    scanner = new Scanner(new FileInputStream("baseMessage.txt"));
    StringBuilder msg = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg.append(scanner.nextLine());
    }

    Cipher cipher = new CipherImpl(msg.toString(), "01");
    Map<Character, String> codingScheme =  cipher.getCodingScheme();
    System.setOut(new PrintStream("encodingSchemeRun2.txt"));
    for (Character ch: codingScheme.keySet()) {
      System.out.println(ch + ":" + codingScheme.get(ch));
    }

    scanner = new Scanner(new FileInputStream("MessageToEncodeRun2.txt"));
    StringBuilder msg2 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg2.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("encodedMessageRun2.txt"));
    try {
      System.out.println(cipher.encodeMessage(msg2.toString()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }


    scanner = new Scanner(new FileInputStream("MessageToDecodeRun2.txt"));
    StringBuilder msg3 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg3.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("decodedMessageRun2.txt"));

    try {
      System.out.println(cipher.decodeMessage(msg3.toString()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  void run3() throws FileNotFoundException {
    scanner = new Scanner(new FileInputStream("baseMessage.txt"));
    StringBuilder msg = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg.append(scanner.nextLine());
    }

    Cipher cipher = new CipherImpl(msg.toString(), "0123456789abcdef");
    Map<Character, String> codingScheme =  cipher.getCodingScheme();
    System.setOut(new PrintStream("encodingSchemeRun3.txt"));
    for (Character ch: codingScheme.keySet()) {
      System.out.println(ch + ":" + codingScheme.get(ch));
    }

    scanner = new Scanner(new FileInputStream("MessageToEncodeRun3.txt"));
    StringBuilder msg2 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg2.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("encodedMessageRun3.txt"));
    try {
      System.out.println(cipher.encodeMessage(msg2.toString()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }


    scanner = new Scanner(new FileInputStream("MessageToDecodeRun3.txt"));
    StringBuilder msg3 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg3.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("decodedMessageRun3.txt"));
    try {
      System.out.println(cipher.decodeMessage(msg3.toString()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  void run4() throws FileNotFoundException {
    scanner = new Scanner(new FileInputStream("encodingSchemeRun4.txt"));

    Map<Character, String> symbolCodeDictionary = new HashMap<>();
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      char ch = line.charAt(0);
      String encoding = line.substring(2);
      symbolCodeDictionary.put(ch, encoding);
    }

    Cipher cipher = new CipherImpl(symbolCodeDictionary);

    scanner = new Scanner(new FileInputStream("MessageToEncodeRun4.txt"));
    StringBuilder msg2 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg2.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("encodedMessageRun4.txt"));
    try {
      System.out.println(cipher.encodeMessage(msg2.toString()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }


    scanner = new Scanner(new FileInputStream("MessageToDecodeRun4.txt"));
    StringBuilder msg3 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg3.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("decodedMessageRun4.txt"));
    try {
      System.out.println(cipher.decodeMessage(msg3.toString()));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }



}