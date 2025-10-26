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
package org.mzattera.dsl.tests;

import com.google.inject.Inject;
import java.util.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mzattera.dsl.l101.Program;

/**
 * Utilities to make writing tests easier.
 */
@ExtendWith(InjectionExtension.class)
@InjectWith(L101InjectorProvider.class)
@SuppressWarnings("all")
public class TestUtil {
  @Inject
  @Extension
  private ParseHelper<Program> _parseHelper;

  /**
   * @return parse tree for given code. It checks that parsing was successful.
   * 
   * @param code valid code for compilation. Notice that you might want to create a test harness to embed code sniplets you want to test, so they can be compiled.
   * This is NOT done in this method.
   */
  public Program parseCode(final CharSequence code) {
    try {
      final Program model = this._parseHelper.parse(code);
      Assertions.assertNotNull(model);
      final EList<Resource.Diagnostic> errors = model.eResource().getErrors();
      boolean _isEmpty = errors.isEmpty();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Unexpected errors: >");
      String _join = IterableExtensions.join(errors, ", ");
      _builder.append(_join);
      _builder.append("<");
      Assertions.assertTrue(_isEmpty, _builder.toString());
      return model;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * Compiles code and verifies the error returned match those passed (if possible).
   * 
   * @param code code for compilation. Notice that you might want to create a test harness to embed code sniplets you want to test, so they can be compiled.
   * THis is NOT done in this method.
   */
  public void parseCodeWithErrors(final CharSequence code, final CharSequence errorString) {
    try {
      final Program model = this._parseHelper.parse(code);
      Resource _eResource = null;
      if (model!=null) {
        _eResource=model.eResource();
      }
      EList<Resource.Diagnostic> _errors = null;
      if (_eResource!=null) {
        _errors=_eResource.getErrors();
      }
      final EList<Resource.Diagnostic> errors = _errors;
      Assertions.assertTrue(((errors == null) || Objects.equals(IterableExtensions.join(errors, ", "), errorString)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
