/*
 * ******************************************************************************
 * MontiCore Language Workbench
 * Copyright (c) 2015, MontiCore, All rights reserved.
 *
 * This project is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this project. If not, see <http://www.gnu.org/licenses/>.
 * ******************************************************************************
 */

package de.monticore.codegen.mc2cd.transl;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Test;

import com.google.common.collect.Iterables;

import de.monticore.codegen.mc2cd.TransformationHelper;
import de.monticore.codegen.mc2cd.TestHelper;
import de.monticore.umlcd4a.cd4analysis._ast.ASTCDAttribute;
import de.monticore.umlcd4a.cd4analysis._ast.ASTCDClass;
import de.monticore.umlcd4a.cd4analysis._ast.ASTCDCompilationUnit;
import de.monticore.utils.ASTNodes;

public class TerminalWithUsageNameTest {
  
  private ASTCDClass astA;
  
  public TerminalWithUsageNameTest() {
    ASTCDCompilationUnit cdCompilationUnit = TestHelper.parseAndTransform(Paths
        .get("src/test/resources/mc2cdtransformation/TerminalWithUsageNameGrammar.mc4")).get();
    astA = TestHelper.getCDClass(cdCompilationUnit, "ASTA").get();
  }
  
  /**
   * Checks that the terminal testname : "literal" results in a reference to String with the name
   * "testname".
   */
  @Test
  public void testTerminalUsageName() {
    ASTCDAttribute cdAttribute = Iterables.getOnlyElement(ASTNodes.getSuccessors(astA,
        ASTCDAttribute.class));
    
    assertEquals("testname", cdAttribute.getName());
    assertEquals("String", TransformationHelper.typeToString(cdAttribute.getType()));
  }
}
