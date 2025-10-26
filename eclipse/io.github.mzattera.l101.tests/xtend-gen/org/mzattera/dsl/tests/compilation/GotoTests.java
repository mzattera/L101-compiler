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
public class GotoTests {
  @Inject
  @Extension
  private CompilationTestHelper _compilationTestHelper;

  /**
   * Simple GOTO to labels associated to keys. No duplicated labels created no unnecessary labels created.
   */
  @Test
  public void testCompilation1() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("start ON V:");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO start;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO end;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO end;");
      _builder.newLine();
      _builder.append("jumpX:\t\t\t");
      _builder.newLine();
      _builder.append("keyZ ON Z:\t\t\t");
      _builder.newLine();
      _builder.append("end ON W:");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("AV");
      _builder_1.newLine();
      _builder_1.append("V");
      _builder_1.newLine();
      _builder_1.append("AZ");
      _builder_1.newLine();
      _builder_1.append("AW");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * Simple GOTO to labels associated to normal labels. No duplicated labels created no unnecessary labels created.
   */
  @Test
  public void testCompilation2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("jump1:");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO jump1;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO jump2;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO jump1;");
      _builder.newLine();
      _builder.append("jump2:");
      _builder.newLine();
      _builder.append("jumpX:");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("BV");
      _builder_1.newLine();
      _builder_1.append("CV");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * GOTO optimized inside IF. Jumps to a label already used for unconditional jumps.
   * Allocated jumps are reused.
   */
  @Test
  public void testCompilation3() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("start ON V:");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =1 THEN GOTO start; ELSE PRINT; END\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("GOTO start;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("IF =2 THEN GOTO start; END\t");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("AV");
      _builder_1.newLine();
      _builder_1.append("A/V");
      _builder_1.newLine();
      _builder_1.append("A/↑");
      _builder_1.newLine();
      _builder_1.append("D/↓");
      _builder_1.newLine();
      _builder_1.append("↓");
      _builder_1.newLine();
      _builder_1.append("/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("V");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * Label count.
   */
  @Test
  public void checkAllocatedJumpsCount() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("label1:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label2:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label3:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label4:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label5:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label6:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label7:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label8:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label9:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label10: ");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label11:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label12:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label13:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label14:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label15:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.append("label16:");
      _builder.newLine();
      _builder.append("PRINT;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label1; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label2; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label3; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label4; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label5; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label6; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label7; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label8; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label9; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label10; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label11; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label12; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label13; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label14; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label15; END");
      _builder.newLine();
      _builder.append("IF = A THEN GOTO label16; END");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("A/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("A/W");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("A/Y");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("A/Z");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("B/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("B/W");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("B/Y");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("B/Z");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("E/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("E/W");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("E/Y");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("E/Z");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("F/V");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("F/W");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("F/Y");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("F/Z");
      _builder_1.newLine();
      _builder_1.append("/◇");
      _builder_1.newLine();
      _builder_1.append("/V");
      _builder_1.newLine();
      _builder_1.append("/W");
      _builder_1.newLine();
      _builder_1.append("/Y");
      _builder_1.newLine();
      _builder_1.append("/Z");
      _builder_1.newLine();
      _builder_1.append("C/V");
      _builder_1.newLine();
      _builder_1.append("C/W");
      _builder_1.newLine();
      _builder_1.append("C/Y");
      _builder_1.newLine();
      _builder_1.append("C/Z");
      _builder_1.newLine();
      _builder_1.append("D/V");
      _builder_1.newLine();
      _builder_1.append("D/W");
      _builder_1.newLine();
      _builder_1.append("D/Y");
      _builder_1.newLine();
      _builder_1.append("D/Z");
      _builder_1.newLine();
      _builder_1.append("R/V");
      _builder_1.newLine();
      _builder_1.append("R/W");
      _builder_1.newLine();
      _builder_1.append("R/Y");
      _builder_1.newLine();
      _builder_1.append("R/Z");
      _builder_1.newLine();
      this._compilationTestHelper.assertCompilesTo(_builder, _builder_1);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  /**
   * Label count.
   */
  @Test
  public void checkAllocatedJumpsCount02() {
    try {
      try {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("label1:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label2:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label3:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label4:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label5:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label6:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label7:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label8:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label9:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label10: ");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label11:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label12:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label13:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label14:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label15:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label16:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.append("label17:");
        _builder.newLine();
        _builder.append("PRINT;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label1; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label2; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label3; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label4; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label5; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label6; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label7; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label8; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label9; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label10; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label11; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label12; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label13; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label14; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label15; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label16; END");
        _builder.newLine();
        _builder.append("IF = A THEN GOTO label17; END");
        _builder.newLine();
        this._compilationTestHelper.assertCompilesTo(_builder, "THE ABOVE SHOULD GENERATE AN EXCEPTION IN COMPILATION");
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          return;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      throw new Exception("The above should have failed with an exception.");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
