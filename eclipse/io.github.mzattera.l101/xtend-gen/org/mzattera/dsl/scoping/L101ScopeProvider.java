/**
 * Copyright 2025 Massimiliano "Maxi" Zattera
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mzattera.dsl.scoping;

import java.util.List;
import java.util.Objects;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.mzattera.dsl.l101.L101Package;
import org.mzattera.dsl.l101.Variable;
import org.mzattera.dsl.l101.VariableAccess;
import org.mzattera.dsl.util.Extensions;

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
@SuppressWarnings("all")
public class L101ScopeProvider extends AbstractL101ScopeProvider {
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    IScope _xifexpression = null;
    if (((context instanceof VariableAccess) && Objects.equals(reference, L101Package.Literals.VARIABLE_ACCESS__STORE))) {
      IScope _xblockexpression = null;
      {
        final List<Variable> vars = EcoreUtil2.<Variable>getAllContentsOfType(Extensions.getProgram(context), Variable.class);
        _xblockexpression = Scopes.scopeFor(vars);
      }
      _xifexpression = _xblockexpression;
    } else {
      _xifexpression = super.getScope(context, reference);
    }
    return _xifexpression;
  }
}
