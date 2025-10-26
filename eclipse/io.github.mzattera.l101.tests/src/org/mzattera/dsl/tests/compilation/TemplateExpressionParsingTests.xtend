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
package org.mzattera.dsl.tests.compilation

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.mzattera.dsl.tests.L101InjectorProvider
import org.mzattera.dsl.tests.TestUtil

/**
 * Code example to test expressions.
 * Test is performed by building a parse tree for the expressions, as a string, and checking it against the expected result.
 */
@ExtendWith(InjectionExtension)
@InjectWith(L101InjectorProvider)
final class TemplateExpressionParsingTests {

	@Inject extension TestUtil

	/**
	 * Test template
	 */
	@Test
	def void testEpressions() {

		// Test a valid expression
		testEpression("=A;", "(exp)")

		// Test malformed expression (code that does not compile)
		// parseCodeWithErrors("wrong code", "XtextSyntaxDiagnostic: null:1 mismatched input '<EOF>' expecting ';'")
	}

	// //////// Utility methods /////////////////////////////////////////////////////////////////////////////
	/**
	 * Checks that given expression (exp) compiles as the tree in expected.
	 * 
	 * @param the expression to test. 
	 * @param expected a representation of the expression parse tree, where the expression is parenthesized.
	 */
	def private testEpression(CharSequence exp, CharSequence expected) {
		// Parse the expression, notice that only syntax is checked, not validation.
		parseCode(exp)

		// fetch back the parse tree for the expression
		// TODO 3 - here implement the code that from the whole parse tree isolates the root node for the expression
//		val block = model.items.get(0) as Block
//		val stmt = block.statements.get(7) as StatementExpression
//		val assign = stmt.innerExp as AssignmentOperator
//		var expression = assign.right as Expression;
		var Object expression = "(exp)";

		Assertions.assertTrue(
			expression.asString == expected, '''Wrong parse tree for: "«exp»": «expression.asString»''')
	}

	/**
	 * Transforms the parse tree for one expression into a string (with parenthesis) so it can be easily checked for validity.
	 */
	def CharSequence asString(Object exp) {
		// TODO 3 - implement
		"(exp)"
	}
}
