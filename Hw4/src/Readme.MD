**How to run Program:**

PLace Hw4 jar in src folder and Run Hw4.jar from src folder using the following commands: 
1. Run Sample run 1 with command:

java -jar Hw4.jar -1 

This runs the program in interactive mode. It builds a Huffman encoding using inputs from the user.
It then asks user for a message he wants to encode and displays the encoded message.
It will then asks user for a message he wants to decode and displays the decoded message. 

2. Run Sample run 2 with command:

java -jar Hw3.jar -2

This reads the message from file baseMessage.txt and used this message to generate a
binary huffman encoding which is written to file encodingSchemeRun2.txt.
This scheme is used to encode the message in MessageToEncodeRun2.txt and writes the
encoded message to encodedMessageRun2.txt.
The same scheme is used to decode the message in MessageToDecodeRun2.txt and writes the
decoded message to decodedMessageRun2.txt.

3. Run Sample run 3 with command:

java -jar Hw3.jar -3

This reads the message from file baseMessage.txt and used this message to generate a
HEXADECIMAL huffman encoding which is written to file encodingSchemeRun3.txt.
This scheme is used to encode the message in MessageToEncodeRun3.txt and writes the
encoded message to encodedMessageRun3.txt.
The same scheme is used to decode the message in MessageToDecodeRun3.txt and writes the
decoded message to decodedMessageRun3.txt.

4. Run Sample run 4 with command:

java -jar Hw3.jar -4

This reads the encoding scheme from file encodingSchemeRun4.txt.
This scheme is used to encode the message in MessageToEncodeRun4.txt and writes the
encoded message to encodedMessageRun4.txt.
The same scheme is used to decode the message in MessageToDecodeRun4.txt and writes the
decoded message to decodedMessageRun4.txt.

All the parts of the program are complete.

List of features:
1. The cipher  is used to create a cipher - can be created with user provided encoding scheme,
  or an encoding scheme can be generated from a message given by the user along with the
 characters that should be used in the encoding scheme.
 The main component of encoding and decoding can be thought of as a
 symbol -> code dictionary representing a coding scheme.
 It provides methods to encode, decode a character as well as get the encoding scheme that
 is being used by the cipher.


2. The decoding is done using a Tree which is implemented in the Decoder. The DecoderImpl
creates a tree using Node. A Node interface is a node of the decoding tree. LeafNode and
NonLeafNode(Transition node) are the two types of nodes. LeafNode contains the decoded
character. Transition node contains the last symbol that was used in reaching it through its branch.
It also contains a map of its children. 

3. The DecoderImpl creates a tree when the Cipher is created. This tree is traversed for decoding
a message. It is package private since I do not want it to be accesses publicly.
 It is internal implementation for working of cipher.
 
4. I have created an enum which is used to tell the type of the node in the tree.
 