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
package org.mzattera.dsl.tests.validation

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.mzattera.dsl.l101.L101Package
import org.mzattera.dsl.tests.L101InjectorProvider
import org.mzattera.dsl.tests.TestUtil
import org.mzattera.dsl.validation.L101Validator

/**
 * This class tests all validation rules.
 */
@ExtendWith(InjectionExtension)
@InjectWith(L101InjectorProvider)
class ValidationTest {

	@Inject extension ValidationTestHelper
	@Inject extension TestUtil

// TODO 3 - validation test is not always performed on OK code, to check there are no false positives.
// TODO 3 - implement tests for all check done in validation.
	@Test
	def void checkLabelAssignments() {

		var model = parseCode(
			'''
			start1 ON V:
			start2 ON W:
			start3 ON Y:
			start4 ON Z:
			start5 ON Y:
		''')
		model.assertError(L101Package.eINSTANCE.label, L101Validator.DUPLICATED_KEY)
	}

	@Test
	def void checkLabelAssignments02() {

		var model = parseCode(
			'''
			start ON V:
			DO
			start02 ON V:
			WHILE =A END
		''')
		model.assertError(L101Package.eINSTANCE.label, L101Validator.DUPLICATED_KEY)
	}

	@Test
	def void checkUnusedLabels() {

		var model = parseCode(
			'''
			start ON V:
			lbl01:
			GOTO lbl01;			
			lbl03:
		''')
		model.assertWarning(L101Package.eINSTANCE.label, L101Validator.UNUSED_LABEL)
	}

	@Test
	def void checkUnreachableCode() {

		var model = parseCode(
		'''
			start ON V:			
				GOTO start;
				PRINT;
		''')
		model.assertWarning(L101Package.eINSTANCE.statements, L101Validator.UNREACHABLE_CODE)

		model = parseCode(
		'''
			start ON V:			
			DO
				GOTO start;
				PRINT;
			WHILE =1 END
		''')
		model.assertWarning(L101Package.eINSTANCE.statements, L101Validator.UNREACHABLE_CODE)

		model = parseCode(
		'''
			start ON V:			
			DO
				CONTINUE;
				PRINT;
			WHILE =1 END
		''')
		model.assertWarning(L101Package.eINSTANCE.statements, L101Validator.UNREACHABLE_CODE)

		model = parseCode(
		'''
			start ON V:			
			DO
				BREAK;
				PRINT;
			WHILE =1 END
		''')
		model.assertWarning(L101Package.eINSTANCE.statements, L101Validator.UNREACHABLE_CODE)

		// check this is legit
		model = parseCode(
		'''
			start ON V:
			
				GOTO start;
			lbl1:
				PRINT;
			
			UNTIL =1 DO 
				GOTO start;
			lbl2:
				PRINT;
			END	
			
			UNTIL =1 DO 
				BREAK;
			lbl3:
				PRINT;
			END	
			
			UNTIL =1 DO 
				IF =A THEN CONTINUE; END
				PRINT;
			END
			
			IF =A THEN GOTO lbl1; ELSE GOTO lbl2; END	
			IF =A THEN GOTO lbl3; END	
		''')
		model.assertNoIssues()
	}

	@Test
	def void checkAllocatedJumpsCount() {

		val code = '''
			label1:
			PRINT;
			label2:
			PRINT;
			label3:
			PRINT;
			label4:
			PRINT;
			label5:
			PRINT;
			label6:
			PRINT;
			label7:
			PRINT;
			label8:
			PRINT;
			label9:
			PRINT;
			label10: 
			PRINT;
			label11:
			PRINT;
			label12:
			PRINT;
			label13:
			PRINT;
			label14:
			PRINT;
			label15:
			PRINT;
			label16:
			PRINT;
			
			IF = A THEN GOTO label1; END
			IF = A THEN GOTO label2; END
			IF = A THEN GOTO label3; END
			IF = A THEN GOTO label4; END
			IF = A THEN GOTO label5; END
			IF = A THEN GOTO label6; END
			IF = A THEN GOTO label7; END
			IF = A THEN GOTO label8; END
			IF = A THEN GOTO label9; END
			IF = A THEN GOTO label10; END
			IF = A THEN GOTO label11; END
			IF = A THEN GOTO label12; END
			IF = A THEN GOTO label13; END
			IF = A THEN GOTO label14; END
			IF = A THEN GOTO label15; END
			IF = A THEN GOTO label16; END
		'''

		val model = parseCode(code)
		model.assertNoIssues()
	}
}
