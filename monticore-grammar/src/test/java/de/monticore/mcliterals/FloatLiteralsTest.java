/* (c) https://github.com/MontiCore/monticore */

package de.monticore.mcliterals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.monticore.mcliterals._ast.ASTFloatLiteral;
import de.monticore.mcliterals._ast.ASTLiteral;
import de.se_rwth.commons.logging.Log;
import de.se_rwth.commons.logging.LogStub;

/**
 * @author Martin Schindler
 */
public class FloatLiteralsTest {
  
  @BeforeClass
  public static void init() {
    // replace log by a sideffect free variant
    LogStub.init();
    Log.enableFailQuick(false);
  }
  
  @Before
  public void setUp() {
    Log.getFindings().clear();
  }
  
  private void checkFloatLiteral(float f, String s) throws IOException {
    ASTLiteral lit = MCLiteralsTestHelper.getInstance().parseLiteral(s);
    assertTrue(lit instanceof ASTFloatLiteral);
    assertEquals(f, ((ASTFloatLiteral) lit).getValue(), 0);
  }
  
  @Test
  public void testFloatLiterals() {
    // decimal number
    try {
      checkFloatLiteral(0F, "0F");
      checkFloatLiteral(0.0F, "0.0F");
      checkFloatLiteral(5F, "5F");
      checkFloatLiteral(.4F, ".4F");
      checkFloatLiteral(000009.3F, "000009.3F");
      checkFloatLiteral(5.F, "5.F");
      checkFloatLiteral(009.f, "009.f");
      checkFloatLiteral(009f, "009f");
      checkFloatLiteral(009e2f, "009e2f");
      checkFloatLiteral(23.4F, "23.4F");
      checkFloatLiteral(2e3F, "2e3F");
      checkFloatLiteral(2E-3F, "2E-3F");
      checkFloatLiteral(009f, "009f");
      checkFloatLiteral(.1e1F, ".1e1F");
      checkFloatLiteral(.1F, ".1F");
      checkFloatLiteral(.11e12F, ".11e12F");
      checkFloatLiteral(.11e+12F, ".11e+12F");
      checkFloatLiteral(29.18e08F, "29.18e08F");
      checkFloatLiteral(0029.0008e-00008F, "0029.0008e-00008F");
      
      // hexadezimal number
      checkFloatLiteral(0x5.p1f, "0x5.p1f");
      // checkFloatLiteral(0x.5p1f, "0x.5p1f");
      checkFloatLiteral(0xFp-9f, "0xFp-9f");
      checkFloatLiteral(0xfP2F, "0xfP2F");
      checkFloatLiteral(0xfp1F, "0xfp1F");
      checkFloatLiteral(0x.fP1F, "0x.fP1F");
      checkFloatLiteral(0x0p0F, "0x0p0F");
      checkFloatLiteral(0x0.0p1F, "0x0.0p1F");
      checkFloatLiteral(0x.0p1F, "0x.0p1F");
      checkFloatLiteral(0x.5AFp1f, "0x.5AFp1f");
      checkFloatLiteral(0x0050AF.CD9p-008f, "0x0050AF.CD9p-008f");
      checkFloatLiteral(0x1.fffffeP+127f, "0x1.fffffeP+127f");
      checkFloatLiteral(0x0p-5f, "0x0p-5f");
      checkFloatLiteral(0x0p1F, "0x0p1F");
      checkFloatLiteral(0x0p-5F, "0x0p-5F");
      
      // Examples from Java Language Specification
      checkFloatLiteral(1e1f, "1e1f");
      checkFloatLiteral(2.f, "2.f");
      checkFloatLiteral(.3f, ".3f");
      checkFloatLiteral(0f, "0f");
      checkFloatLiteral(3.14f, "3.14f");
      checkFloatLiteral(6.022137e+23f, "6.022137e+23f");
      
      // underscores
      checkFloatLiteral(678910.9978f, "678_910.997_8f");
      checkFloatLiteral(16780e14F, "16_780e1_4F");
      checkFloatLiteral(2.7898765444f, "2.789___8765444f");
      checkFloatLiteral(0x0.8645p1F, "0x0.864_5p1F");
      checkFloatLiteral(0xefab.abcdp67f, "0xef_ab.abcdp6_7f");
      
    }
    catch (IOException e) {
      fail(e.getMessage());
    }
  }
}
