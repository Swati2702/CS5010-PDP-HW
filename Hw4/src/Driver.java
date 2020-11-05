import cipher.Cipher;
import cipher.CipherImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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
    driver.run3();

  }

  /**
   * Reads message from user to make a binary huffman encoding.
   * */
  void run1() {
    scanner = new Scanner(System.in);
    System.out.println("Enter message which will be used for creating binary huffman "
                + "encoding.");
    String msg = scanner.nextLine();
    Cipher cipher = new CipherImpl(msg, "01");
    System.out.println("The coding scheme generated is:");
    Map<Character, String> codingScheme =  cipher.getCodingScheme();
    for (Character ch: codingScheme.keySet()) {
      System.out.println(ch + ":" + codingScheme.get(ch));
    }
    System.out.println("Enter message you want to encode using this coding scheme.");
    msg = scanner.nextLine();
    System.out.println("The encoded message is " + cipher.encodeMessage(msg));

    System.out.println("Enter message you want to decode using this coding scheme.");
    msg = scanner.nextLine();
    System.out.println("The decoded message is " + cipher.decodeMessage(msg));
  }

  void run2() throws FileNotFoundException {
    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\baseMessage.txt"));
    StringBuilder msg = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg.append(scanner.nextLine());
    }

    Cipher cipher = new CipherImpl(msg.toString(), "01");
    Map<Character, String> codingScheme =  cipher.getCodingScheme();
    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\encodingSchemeRun2.txt"));
    for (Character ch: codingScheme.keySet()) {
      System.out.println(ch + ":" + codingScheme.get(ch));
    }

    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\MessageToEncodeRun2.txt"));
    StringBuilder msg2 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg2.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\encodedMessageRun2.txt"));
    System.out.println(cipher.encodeMessage(msg2.toString()));

    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\MessageToDecodeRun2.txt"));
    StringBuilder msg3 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg3.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\decodedMessageRun2.txt"));
    System.out.println(cipher.decodeMessage(msg3.toString()));
  }

  void run3() throws FileNotFoundException {
    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\baseMessage.txt"));
    StringBuilder msg = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg.append(scanner.nextLine());
    }

    Cipher cipher = new CipherImpl(msg.toString(), "0123456789abcdef");
    Map<Character, String> codingScheme =  cipher.getCodingScheme();
    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\encodingSchemeRun3.txt"));
    for (Character ch: codingScheme.keySet()) {
      System.out.println(ch + ":" + codingScheme.get(ch));
    }

    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\MessageToEncodeRun3.txt"));
    StringBuilder msg2 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg2.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\encodedMessageRun3.txt"));
    System.out.println(cipher.encodeMessage(msg2.toString()));

    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\MessageToDecodeRun3.txt"));
    StringBuilder msg3 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg3.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\decodedMessageRun3.txt"));
    System.out.println(cipher.decodeMessage(msg3.toString()));
  }

  void run4() throws FileNotFoundException {
    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\encodingSchemeRun4.txt"));
    StringBuilder msg = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg.append(scanner.nextLine());
    }

    Cipher cipher = new CipherImpl(msg.toString(), "0123456789abcdef");
    Map<Character, String> codingScheme =  cipher.getCodingScheme();
    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\encodingSchemeRun3.txt"));
    for (Character ch: codingScheme.keySet()) {
      System.out.println(ch + ":" + codingScheme.get(ch));
    }

    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\MessageToEncodeRun3.txt"));
    StringBuilder msg2 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg2.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\encodedMessageRun3.txt"));
    System.out.println(cipher.encodeMessage(msg2.toString()));

    scanner = new Scanner(new FileInputStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\MessageToDecodeRun3.txt"));
    StringBuilder msg3 = new StringBuilder();
    while (scanner.hasNextLine()) {
      msg3.append(scanner.nextLine());
    }

    System.setOut(new PrintStream("E:\\CS 5010\\CS5010-PDP-HW\\Hw4\\src\\decodedMessageRun3.txt"));
    System.out.println(cipher.decodeMessage(msg3.toString()));
  }



}