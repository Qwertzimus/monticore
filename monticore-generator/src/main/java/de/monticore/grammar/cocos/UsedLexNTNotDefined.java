/* (c) https://github.com/MontiCore/monticore */

package de.monticore.grammar.cocos;

import java.util.Optional;

import de.monticore.codegen.mc2cd.MCGrammarSymbolTableHelper;
import de.monticore.grammar.grammar._ast.ASTLexNonTerminal;
import de.monticore.grammar.grammar._cocos.GrammarASTLexNonTerminalCoCo;
import de.monticore.grammar.symboltable.MCGrammarSymbol;
import de.monticore.grammar.symboltable.MCProdSymbol;
import de.se_rwth.commons.logging.Log;

/**
 * Checks that used nonterminals are defined.
 *
 * @author KH
 */
public class UsedLexNTNotDefined implements GrammarASTLexNonTerminalCoCo {

  public static final String ERROR_CODE = "0xA4016";

  public static final String ERROR_MSG_FORMAT = " The lexical production %s must not" +
          " use the nonterminal %s because there exists no lexical production defining %s.";

  @Override
  public void check(ASTLexNonTerminal a) {
    Optional<MCGrammarSymbol> grammarSymbol = MCGrammarSymbolTableHelper
        .getMCGrammarSymbol(a);
    
    Optional<MCProdSymbol> ruleSymbol = MCGrammarSymbolTableHelper.getEnclosingRule(a);
    String ruleName = ruleSymbol.isPresent() ? ruleSymbol.get().getName() : "";
    if (grammarSymbol.isPresent()
        && !grammarSymbol.get().getProdWithInherited(a.getName()).isPresent()) {
      Log.error(String.format(ERROR_CODE + ERROR_MSG_FORMAT, ruleName, a.getName(), a.getName()),
          a.get_SourcePositionStart());
    }
  }
}
