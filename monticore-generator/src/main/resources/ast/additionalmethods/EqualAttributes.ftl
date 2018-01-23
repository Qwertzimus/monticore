<#--
****************************************************************************
MontiCore Language Workbench, www.monticore.de
Copyright (c) 2017, MontiCore, All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimer in the documentation
and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its
contributors may be used to endorse or promote products derived from this
software without specific prior written permission.

This software is provided by the copyright holders and contributors
"as is" and any express or implied warranties, including, but not limited
to, the implied warranties of merchantability and fitness for a particular
purpose are disclaimed. In no event shall the copyright holder or
contributors be liable for any direct, indirect, incidental, special,
exemplary, or consequential damages (including, but not limited to,
procurement of substitute goods or services; loss of use, data, or
profits; or business interruption) however caused and on any theory of
liability, whether in contract, strict liability, or tort (including
negligence or otherwise) arising in any way out of the use of this
software, even if advised of the possibility of such damage.
****************************************************************************
-->
${tc.signature("ast","astType")}
   <#assign genHelper = glex.getGlobalVar("astHelper")>
   <#assign astName = genHelper.getPlainName(astType)>
   <#if genHelper.hasOnlyAstAttributes(astType)>
    return o instanceof ${astName};
   <#else>
      ${astName} comp;
    if ((o instanceof ${astName})) {
      comp = (${astName}) o;
    } else {
      return false;
    }
      <#-- TODO: attributes of super class - use symbol table -->
       <#list astType.getCDAttributeList()  as attribute>  
         <#assign attributeName = genHelper.getJavaConformName(attribute.getName())>
         <#if !genHelper.isAstNode(attribute) && !genHelper.isOptionalAstNode(attribute) && !genHelper.isListAstNode(attribute)>
	// comparing ${attributeName} 
	      <#if genHelper.isPrimitive(attribute.getType())>
    if (!(this.${attributeName} == comp.${attributeName})) {
      return false;
    }
         <#elseif genHelper.isOptional(attribute)>
    if ( this.${attributeName}.isPresent() != comp.${attributeName}.isPresent() ||
       (this.${attributeName}.isPresent() && !this.${attributeName}.get().equals(comp.${attributeName}.get())) ) {
      return false;
    }
	      <#else>
    if ( (this.${attributeName} == null && comp.${attributeName} != null) 
      || (this.${attributeName} != null && !this.${attributeName}.equals(comp.${attributeName})) ) {
      return false;
    }
	      </#if>
	    </#if>  
      </#list>      
    return true;     
    </#if> 

