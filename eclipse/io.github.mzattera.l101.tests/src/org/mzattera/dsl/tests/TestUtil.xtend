/*
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
package org.mzattera.dsl.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.^extension.ExtendWith
import org.mzattera.dsl.l101.Program

/**
 * Utilities to make writing tests easier.
 */
// @RunWith(XtextRunner)
@ExtendWith(InjectionExtension)
@InjectWith(L101InjectorProvider)
class TestUtil {

	@Inject extension ParseHelper<Program>
//	@Inject extension CompilationTestHelper

	/**
	 * @return parse tree for given code. It checks that parsing was successful.
	 * 
	 * @param code valid code for compilation. Notice that you might want to create a test harness to embed code sniplets you want to test, so they can be compiled.
	 * This is NOT done in this method.
	 */
	def parseCode(CharSequence code) {
		val model = code.parse

		Assertions.assertNotNull(model)
		val errors = model.eResource.errors
		Assertions.assertTrue(errors.isEmpty, '''Unexpected errors: >«errors.join(", ")»<''')

		return model
	}

	/**
	 * Compiles code and verifies the error returned match those passed (if possible).
	 * 
	 * @param code code for compilation. Notice that you might want to create a test harness to embed code sniplets you want to test, so they can be compiled.
	 * THis is NOT done in this method.
	 */
	def parseCodeWithErrors(CharSequence code, CharSequence errorString) {
		val model = code.parse

		val errors = model?.eResource?.errors
		Assertions.assertTrue(errors === null || errors.join(", ") == errorString)
	}
}
