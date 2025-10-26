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
package org.mzattera.dsl.tests.validation;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mzattera.dsl.l101.L101Package;
import org.mzattera.dsl.l101.Program;
import org.mzattera.dsl.tests.L101InjectorProvider;
import org.mzattera.dsl.tests.TestUtil;
import org.mzattera.dsl.validation.L101Validator;

/**
 * This class tests all validation rules.
 */
@ExtendWith(InjectionExtension.class)
@InjectWith(L101InjectorProvider.class)
@SuppressWarnings("all")
public class ValidationTest {
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;

  @Inject
  @Extension
  private TestUtil _testUtil;

  @Test
  public void checkLabelAssignments() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("start1 ON V:");
    _builder.newLine();
    _builder.append("start2 ON W:");
    _builder.newLine();
    _builder.append("start3 ON Y:");
    _builder.newLine();
    _builder.append("start4 ON Z:");
    _builder.newLine();
    _builder.append("start5 ON Y:");
    _builder.newLine();
    Program model = this._testUtil.parseCode(_builder);
    this._validationTestHelper.assertError(model, L101Package.eINSTANCE.getLabel(), L101Validator.DUPLICATED_KEY);
  }

  @Test
  public void checkLabelAssignments02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("start ON V:");
    _builder.newLine();
    _builder.append("DO");
    _builder.newLine();
    _builder.append("start02 ON V:");
    _builder.newLine();
    _builder.append("WHILE =A END");
    _builder.newLine();
    Program model = this._testUtil.parseCode(_builder);
    this._validationTestHelper.assertError(model, L101Package.eINSTANCE.getLabel(), L101Validator.DUPLICATED_KEY);
  }

  @Test
  public void checkUnusedLabels() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("start ON V:");
    _builder.newLine();
    _builder.append("lbl01:");
    _builder.newLine();
    _builder.append("GOTO lbl01;\t\t\t");
    _builder.newLine();
    _builder.append("lbl03:");
    _builder.newLine();
    Program model = this._testUtil.parseCode(_builder);
    this._validationTestHelper.assertWarning(model, L101Package.eINSTANCE.getLabel(), L101Validator.UNUSED_LABEL);
  }

  @Test
  public void checkUnreachableCode() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("start ON V:\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("GOTO start;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("PRINT;");
    _builder.newLine();
    Program model = this._testUtil.parseCode(_builder);
    this._validationTestHelper.assertWarning(model, L101Package.eINSTANCE.getStatements(), L101Validator.UNREACHABLE_CODE);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("start ON V:\t\t\t");
    _builder_1.newLine();
    _builder_1.append("DO");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("GOTO start;");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("PRINT;");
    _builder_1.newLine();
    _builder_1.append("WHILE =1 END");
    _builder_1.newLine();
    model = this._testUtil.parseCode(_builder_1);
    this._validationTestHelper.assertWarning(model, L101Package.eINSTANCE.getStatements(), L101Validator.UNREACHABLE_CODE);
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("start ON V:\t\t\t");
    _builder_2.newLine();
    _builder_2.append("DO");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("CONTINUE;");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("PRINT;");
    _builder_2.newLine();
    _builder_2.append("WHILE =1 END");
    _builder_2.newLine();
    model = this._testUtil.parseCode(_builder_2);
    this._validationTestHelper.assertWarning(model, L101Package.eINSTANCE.getStatements(), L101Validator.UNREACHABLE_CODE);
    StringConcatenation _builder_3 = new StringConcatenation();
    _builder_3.append("start ON V:\t\t\t");
    _builder_3.newLine();
    _builder_3.append("DO");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("BREAK;");
    _builder_3.newLine();
    _builder_3.append("\t");
    _builder_3.append("PRINT;");
    _builder_3.newLine();
    _builder_3.append("WHILE =1 END");
    _builder_3.newLine();
    model = this._testUtil.parseCode(_builder_3);
    this._validationTestHelper.assertWarning(model, L101Package.eINSTANCE.getStatements(), L101Validator.UNREACHABLE_CODE);
    StringConcatenation _builder_4 = new StringConcatenation();
    _builder_4.append("start ON V:");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("GOTO start;");
    _builder_4.newLine();
    _builder_4.append("lbl1:");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("PRINT;");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("UNTIL =1 DO ");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("GOTO start;");
    _builder_4.newLine();
    _builder_4.append("lbl2:");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("PRINT;");
    _builder_4.newLine();
    _builder_4.append("END\t");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("UNTIL =1 DO ");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("BREAK;");
    _builder_4.newLine();
    _builder_4.append("lbl3:");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("PRINT;");
    _builder_4.newLine();
    _builder_4.append("END\t");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("UNTIL =1 DO ");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("IF =A THEN CONTINUE; END");
    _builder_4.newLine();
    _builder_4.append("\t");
    _builder_4.append("PRINT;");
    _builder_4.newLine();
    _builder_4.append("END");
    _builder_4.newLine();
    _builder_4.newLine();
    _builder_4.append("IF =A THEN GOTO lbl1; ELSE GOTO lbl2; END\t");
    _builder_4.newLine();
    _builder_4.append("IF =A THEN GOTO lbl3; END\t");
    _builder_4.newLine();
    model = this._testUtil.parseCode(_builder_4);
    this._validationTestHelper.assertNoIssues(model);
  }

  @Test
  public void checkAllocatedJumpsCount() {
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
    final String code = _builder.toString();
    final Program model = this._testUtil.parseCode(code);
    this._validationTestHelper.assertNoIssues(model);
  }
}
