/* (c) https://github.com/MontiCore/monticore */

/* generated from model null*/
/* generated by template symboltable.SymbolReference*/



package mc.embedding.external.host._symboltable;

import de.monticore.symboltable.Scope;
import de.monticore.symboltable.MutableScope;
import de.monticore.symboltable.modifiers.AccessModifier;
import de.monticore.symboltable.references.CommonSymbolReference;
import de.monticore.symboltable.references.SymbolReference;
import java.util.Collection;

/**
 * Represents a reference of {@link HostSymbol}.
 */
public class HostSymbolReference extends HostSymbol implements SymbolReference<HostSymbol> {
  protected final SymbolReference<HostSymbol> reference;

  public HostSymbolReference(final String name, final Scope definingScopeOfReference) {
    super(name);
    reference = new CommonSymbolReference<>(name, HostSymbol.KIND, definingScopeOfReference);
  }

  /*
   * Methods of SymbolReference interface
   */

  @Override
  public HostSymbol getReferencedSymbol() {
    return reference.getReferencedSymbol();
  }

  @Override
  public boolean existsReferencedSymbol() {
    return reference.existsReferencedSymbol();
  }

  @Override
  public boolean isReferencedSymbolLoaded() {
    return reference.isReferencedSymbolLoaded();
  }

  /*
  * Methods of Symbol interface
  */

  @Override
  public String getName() {
    return getReferencedSymbol().getName();
  }

  @Override
  public String getFullName() {
    return getReferencedSymbol().getFullName();
  }

  @Override
  public void setEnclosingScope(MutableScope scope) {
    getReferencedSymbol().setEnclosingScope(scope);
  }

  @Override
  public Scope getEnclosingScope() {
    return getReferencedSymbol().getEnclosingScope();
  }

  @Override
  public AccessModifier getAccessModifier() {
    return getReferencedSymbol().getAccessModifier();
  }

  @Override
  public void setAccessModifier(AccessModifier accessModifier) {
    getReferencedSymbol().setAccessModifier(accessModifier);
  }

  /*
   * Methods of ScopeSpanningSymbol interface
   */
  //@Override
  //public Scope getSpannedScope() {
  //  return getReferencedSymbol().getSpannedScope();
  //}

  /*
  * Methods of HostSymbol class
  */


  @Override
  public Collection<ContentSymbol> getContent() {
    return getReferencedSymbol().getContent();
  }


}

