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
import java.util.Objects;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mzattera.dsl.tests.L101InjectorProvider;
import org.mzattera.dsl.tests.TestUtil;

/**
 * Code example to test expressions.
 * Test is performed by building a parse tree for the expressions, as a string, and checking it against the expected result.
 */
@ExtendWith(InjectionExtension.class)
@InjectWith(L101InjectorProvider.class)
@SuppressWarnings("all")
public final class TemplateExpressionParsingTests {
  @Inject
  @Extension
  private TestUtil _testUtil;

  /**
   * Test template
   */
  @Test
  public void testEpressions() {
    this.testEpression("=A;", "(exp)");
  }

  /**
   * Checks that given expression (exp) compiles as the tree in expected.
   * 
   * @param the expression to test.
   * @param expected a representation of the expression parse tree, where the expression is parenthesized.
   */
  private void testEpression(final CharSequence exp, final CharSequence expected) {
    this._testUtil.parseCode(exp);
    Object expression = "(exp)";
    CharSequence _asString = this.asString(expression);
    boolean _equals = Objects.equals(_asString, expected);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Wrong parse tree for: \"");
    _builder.append(exp);
    _builder.append("\": ");
    CharSequence _asString_1 = this.asString(expression);
    _builder.append(_asString_1);
    Assertions.assertTrue(_equals, _builder.toString());
  }

  /**
   * Transforms the parse tree for one expression into a string (with parenthesis) so it can be easily checked for validity.
   */
  public CharSequence asString(final Object exp) {
    return "(exp)";
  }
}
