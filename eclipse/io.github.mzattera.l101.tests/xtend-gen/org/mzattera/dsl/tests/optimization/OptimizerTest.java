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
package org.mzattera.dsl.tests.optimization;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mzattera.dsl.generator.L101Generator;
import org.mzattera.dsl.tests.L101InjectorProvider;

@ExtendWith(InjectionExtension.class)
@InjectWith(L101InjectorProvider.class)
@SuppressWarnings("all")
public class OptimizerTest {
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;

  /**
   * optLoadA0()
   */
  @Test
  public void testOptimization1() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SHORT x IN B;");
      _builder.newLine();
      _builder.append("=0;");
      _builder.newLine();
      _builder.append("x<->0;");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("A");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("B");
      _builder_1.append(L101Generator.UP_DOWN);
      _builder_1.newLineIfNotEmpty();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * optDoubleA()
   */
  @Test
  public void testOptimization2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SHORT x IN B;");
      _builder.newLine();
      _builder.append("=A*2;");
      _builder.newLine();
      _builder.append("*=2;");
      _builder.newLine();
      _builder.append("=x*2;");
      _builder.newLine();
      _builder.append("x<->x*2;");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("A+");
      _builder_1.newLine();
      _builder_1.append("A+");
      _builder_1.newLine();
      _builder_1.append("B");
      _builder_1.append(L101Generator.DOWN);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A+");
      _builder_1.newLine();
      _builder_1.append("B");
      _builder_1.append(L101Generator.DOWN);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A+");
      _builder_1.newLine();
      _builder_1.append("B");
      _builder_1.append(L101Generator.UP_DOWN);
      _builder_1.newLineIfNotEmpty();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * optNegX()
   */
  @Test
  public void testOptimization3() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("LONG x IN B;");
      _builder.newLine();
      _builder.append("SHORT y IN F/;");
      _builder.newLine();
      _builder.append("SHORT z IN C/;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("start ON V:");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("5->x;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("7->y;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("0->z;");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("x<->NEG(x);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("y<->NEG(y);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("z<->NEG(z);");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("AV");
      _builder_1.newLine();
      _builder_1.append("A/");
      _builder_1.append(L101Generator.UP);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("D/");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("B");
      _builder_1.append(L101Generator.UP);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A/");
      _builder_1.append(L101Generator.UP);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("D/");
      _builder_1.append(L101Generator.DIV);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("F/");
      _builder_1.append(L101Generator.UP);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A/");
      _builder_1.append(L101Generator.UP);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("D/S");
      _builder_1.newLine();
      _builder_1.append("C/");
      _builder_1.append(L101Generator.UP);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("B");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("B");
      _builder_1.append(L101Generator.UP_DOWN);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("F/");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("F/");
      _builder_1.append(L101Generator.UP_DOWN);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("A");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("C/");
      _builder_1.append(L101Generator.MINUS);
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("C/");
      _builder_1.append(L101Generator.UP_DOWN);
      _builder_1.newLineIfNotEmpty();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * Tests that jumps placeholders are properly optimized.
   */
  @Test
  public void testOptimizeJumps() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("SHORT x IN B;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("start ON V:");
      _builder.newLine();
      _builder.newLine();
      _builder.append("lbl001:");
      _builder.newLine();
      _builder.append("lbl004:");
      _builder.newLine();
      _builder.append("lbl002:");
      _builder.newLine();
      _builder.append("lbl005:");
      _builder.newLine();
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append(" ");
      _builder.newLine();
      _builder.append("IF =A THEN GOTO lbl001; END");
      _builder.newLine();
      _builder.append("IF =A THEN GOTO lbl002; END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("UNTIL =A DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =A THEN GOTO lbl003; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO lbl005;");
      _builder.newLine();
      _builder.append("END");
      _builder.newLine();
      _builder.append("lbl003:");
      _builder.newLine();
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("lbl006:");
      _builder.newLine();
      _builder.append("DO");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =A THEN GOTO lbl006; END");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO lbl005;");
      _builder.newLine();
      _builder.append("WHILE = A END");
      _builder.newLine();
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("lbl007 ON W:");
      _builder.newLine();
      _builder.append("lbl008:");
      _builder.newLine();
      _builder.append("lbl009 ON Y:");
      _builder.newLine();
      _builder.append("lbl010:");
      _builder.newLine();
      _builder.newLine();
      _builder.append("GOTO lbl004;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("GOTO lbl008;");
      _builder.newLine();
      _builder.append("GOTO lbl010;");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("AV");
      _builder_1.newLine();
      _builder_1.append("A/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("/W");
      _builder_1.newLine();
      _builder_1.append("V");
      _builder_1.newLine();
      _builder_1.append("A/W");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("A/Y");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("V");
      _builder_1.newLine();
      _builder_1.append("AW");
      _builder_1.newLine();
      _builder_1.append("AY");
      _builder_1.newLine();
      _builder_1.append("V");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
