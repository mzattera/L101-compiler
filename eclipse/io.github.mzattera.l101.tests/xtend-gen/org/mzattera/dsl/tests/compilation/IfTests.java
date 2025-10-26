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
public class IfTests {
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;

  /**
   * Tests non-optimizable IF THEN ELSE; optimizable code is tested in tests for loops.
   */
  @Test
  public void testCompilation1() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SHORT x IN B, y IN B/;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("start ON V:");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("2->x;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("1->y;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =(x-y) THEN PRINT x; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT 111;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =(x-y) ELSE PRINT y; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT 222;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =(x-y) THEN PRINT x; ");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("ELSE PRINT y;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("PRINT 333;");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("AV");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↑");
      _builder_1.newLine();
      _builder_1.append("B↑");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("B/↑");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("B/-");
      _builder_1.newLine();
      _builder_1.append("/V");
      _builder_1.newLine();
      _builder_1.append("CV");
      _builder_1.newLine();
      _builder_1.append("A/V");
      _builder_1.newLine();
      _builder_1.append("B◇");
      _builder_1.newLine();
      _builder_1.append("BV");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("R/↓");
      _builder_1.newLine();
      _builder_1.append("R↓");
      _builder_1.newLine();
      _builder_1.append("D↓");
      _builder_1.newLine();
      _builder_1.append("◇");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("B/-");
      _builder_1.newLine();
      _builder_1.append("/W");
      _builder_1.newLine();
      _builder_1.append("B/◇");
      _builder_1.newLine();
      _builder_1.append("A/W");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("R/↑");
      _builder_1.newLine();
      _builder_1.append("R↑");
      _builder_1.newLine();
      _builder_1.append("D↑");
      _builder_1.newLine();
      _builder_1.append("◇");
      _builder_1.newLine();
      _builder_1.append("B↓");
      _builder_1.newLine();
      _builder_1.append("B/-");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("B/◇");
      _builder_1.newLine();
      _builder_1.append("CW");
      _builder_1.newLine();
      _builder_1.append("A/Y");
      _builder_1.newLine();
      _builder_1.append("B◇");
      _builder_1.newLine();
      _builder_1.append("BW");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("R/↕");
      _builder_1.newLine();
      _builder_1.append("R↕");
      _builder_1.newLine();
      _builder_1.append("D↕");
      _builder_1.newLine();
      _builder_1.append("◇");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
