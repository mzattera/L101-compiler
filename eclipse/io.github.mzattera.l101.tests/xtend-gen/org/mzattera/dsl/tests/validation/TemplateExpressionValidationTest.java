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
 * This class is a template for testing validation.
 */
@ExtendWith(InjectionExtension.class)
@InjectWith(L101InjectorProvider.class)
@SuppressWarnings("all")
public class TemplateExpressionValidationTest {
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;

  @Inject
  @Extension
  private TestUtil _testUtil;

  @Test
  public void testExpressions() {
    Program model = this._testUtil.parseCode("IF =A END");
    this._validationTestHelper.assertError(model, L101Package.eINSTANCE.getIfStatement(), L101Validator.EMPTY_IF);
  }
}
