import org.junit.Test;

import java.util.Map;

public class CipherTest {

  CipherImpl h = new CipherImpl("SHE SELLS SEA SHELLS BY THE SEA SHORE", "01");
  @Test
  public void test() {
    h.encodeMessage("SHE SELLS SEA SHELLS BY THE SEA SHORE");
  }

  @Test
  public void test1() {
    //h.encodeMessage("SHE SELLS SEA SHELLS BY THE SEA SHORE");
    String msg = h.decodeMessage("100101111101011101101110100");
    int a = 1;
  }


}
