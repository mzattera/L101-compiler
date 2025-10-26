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
package org.mzattera.dsl.tests.compilation;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mzattera.dsl.tests.L101InjectorProvider;

@ExtendWith(InjectionExtension.class)
@InjectWith(L101InjectorProvider.class)
@SuppressWarnings("all")
public class ForTests {
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;

  /**
   * Simple FOR, 4 possible forms.
   */
  @Test
  public void testCompilation1() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SHORT i IN B;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR 1->i; =(i-3); i<->i+1 DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT i;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR ; =(i-3); i<->i+1 DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT i;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR 1->i; =(i-3);  DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT i;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR ; =(i-3);  DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT i;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("BV");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/V");
      _builder_1.newLine();
      _builder_1.append("B◇");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("+");
      _builder_1.newLine();
      _builder_1.append("B↕");
      _builder_1.newLine();
      _builder_1.append("CV");
      _builder_1.newLine();
      _builder_1.append("A/V");
      _builder_1.newLine();
      _builder_1.append("BW");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/W");
      _builder_1.newLine();
      _builder_1.append("B◇");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("+");
      _builder_1.newLine();
      _builder_1.append("B↕");
      _builder_1.newLine();
      _builder_1.append("CW");
      _builder_1.newLine();
      _builder_1.append("A/W");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("BY");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("B◇");
      _builder_1.newLine();
      _builder_1.append("CY");
      _builder_1.newLine();
      _builder_1.append("A/Y");
      _builder_1.newLine();
      _builder_1.append("BZ");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/Z");
      _builder_1.newLine();
      _builder_1.append("B◇");
      _builder_1.newLine();
      _builder_1.append("CZ");
      _builder_1.newLine();
      _builder_1.append("A/Z");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * CONTINUE and BREAK alone in the loop.
   * Notice CONTINUE is tested in 2 cases as it results in different compilation.
   * Checks that jumps allocated by BREAk and CONTINUE are reused.
   */
  @Test
  public void testCompilation2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SHORT i IN B;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR 1->i; =(i-3); i<->i+1 DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("CONTINUE;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("CONTINUE;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR 1->i; =(i-3); DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("CONTINUE;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("CONTINUE;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR 1->i; =(i-3); DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("BREAK;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("BREAK;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("BV");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("+");
      _builder_1.newLine();
      _builder_1.append("B↕");
      _builder_1.newLine();
      _builder_1.append("CV");
      _builder_1.newLine();
      _builder_1.append("A/V");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("BW");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/W");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("CW");
      _builder_1.newLine();
      _builder_1.append("A/W");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("CY");
      _builder_1.newLine();
      _builder_1.append("A/Y");
      _builder_1.newLine();
      _builder_1.append("BY");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * Optimized CONTINUE and BREAK. Checks that jumps allocated by BREAK and CONTINUE are reused.
   */
  @Test
  public void testCompilation3() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SHORT i IN B;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR 1->i; =(i-3); i<->i+1 DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =i THEN CONTINUE; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =i THEN CONTINUE; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("FOR 1->i; =(i-3); DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =i THEN BREAK; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =i THEN BREAK; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("BV");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("/W");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("/W");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("A/W");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("+");
      _builder_1.newLine();
      _builder_1.append("B↕");
      _builder_1.newLine();
      _builder_1.append("CV");
      _builder_1.newLine();
      _builder_1.append("A/V");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("BW");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↕");
      _builder_1.newLine();
      _builder_1.append("-");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("CW");
      _builder_1.newLine();
      _builder_1.append("A/Y");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
