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

import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import de.monticore.codegen.mc2cd.TransformationHelper;
import de.monticore.grammar.LexNamer;
import de.monticore.grammar.grammar._ast.ASTMCGrammar;
import de.monticore.umlcd4a.cd4analysis._ast.ASTCDCompilationUnit;
import de.monticore.umlcd4a.cd4analysis._ast.ASTCDEnum;
import de.monticore.umlcd4a.cd4analysis._ast.ASTCDEnumConstant;
import de.monticore.umlcd4a.cd4analysis._ast.CD4AnalysisNodeFactory;
import de.monticore.utils.Link;

/**
 * TODO: Write me!
 *
 * @author (last commit) $Author$
 * @version $Revision$, $Date$
 */
public class ConstantsTranslation implements
    UnaryOperator<Link<ASTMCGrammar, ASTCDCompilationUnit>> {
  
  public static final String CONSTANTS_ENUM = "Literals";
  
  private LexNamer lexNamer;
  
  /**
   * Constructor for de.monticore.codegen.mc2cd.transl.ConstantsTranslation
   * 
   * @param lexNamer
   */
  public ConstantsTranslation(LexNamer lexNamer) {
    this.lexNamer = lexNamer;
  }
  
  @Override
  public Link<ASTMCGrammar, ASTCDCompilationUnit> apply(
      Link<ASTMCGrammar, ASTCDCompilationUnit> rootLink) {
    
    ASTCDEnum constantsEnum = CD4AnalysisNodeFactory.createASTCDEnum();
    constantsEnum.setName(rootLink.source().getName() + CONSTANTS_ENUM);
    rootLink.target().getCDDefinition().getCDEnums().add(constantsEnum);
    Set<String> grammarConstants = TransformationHelper
        .getAllGrammarConstants(rootLink.source()).stream().map(c -> lexNamer.getConstantName(c))
        .collect(Collectors.toSet());
    for (String grammarConstant : grammarConstants) {
      ASTCDEnumConstant constant = CD4AnalysisNodeFactory.createASTCDEnumConstant();
      constant.setName(grammarConstant);
      constantsEnum.getCDEnumConstants().add(constant);
    }
    
    return rootLink;
  }
  
}
