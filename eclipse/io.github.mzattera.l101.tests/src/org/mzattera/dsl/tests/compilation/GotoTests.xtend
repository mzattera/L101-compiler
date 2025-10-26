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
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.mzattera.dsl.tests.L101InjectorProvider

@ExtendWith(InjectionExtension)
@InjectWith(L101InjectorProvider)
class GotoTests {

	@Inject extension CompilationTestHelper
//	@Inject extension TestUtil

	/**
	 * Simple GOTO to labels associated to keys. No duplicated labels created no unnecessary labels created.
	 */
	@Test
	def void testCompilation1() {
		'''
			start ON V:
				GOTO start;
				GOTO end;
				GOTO end;
			jumpX:			
			keyZ ON Z:			
			end ON W:
		'''.assertCompilesTo(
		'''
			AV
			V
			AZ
			AW
		''')
	}

	/**
	 *  Simple GOTO to labels associated to normal labels. No duplicated labels created no unnecessary labels created.
	 */
	@Test
	def void testCompilation2() {
		'''
			jump1:
				GOTO jump1;
				GOTO jump2;
				GOTO jump1;
			jump2:
			jumpX:
		'''.assertCompilesTo(
		'''
			BV
			CV
		''')
	}

	/**
	 * GOTO optimized inside IF. Jumps to a label already used for unconditional jumps.
	 * Allocated jumps are reused.
	 */
	@Test
	def void testCompilation3() {
		'''
			start ON V:
				IF =1 THEN GOTO start; ELSE PRINT; END	
				GOTO start;
				IF =2 THEN GOTO start; END	
		'''.assertCompilesTo(
		'''
			AV
			A/V
			A/↑
			D/↓
			↓
			/V
			/◇
			V
		''')
	}

	/**
	 * Label count.
	 */
	@Test
	def void checkAllocatedJumpsCount() {

		'''
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
		'''.assertCompilesTo(
		'''
			A/V
			/◇
			A/W
			/◇
			A/Y
			/◇
			A/Z
			/◇
			B/V
			/◇
			B/W
			/◇
			B/Y
			/◇
			B/Z
			/◇
			E/V
			/◇
			E/W
			/◇
			E/Y
			/◇
			E/Z
			/◇
			F/V
			/◇
			F/W
			/◇
			F/Y
			/◇
			F/Z
			/◇
			/V
			/W
			/Y
			/Z
			C/V
			C/W
			C/Y
			C/Z
			D/V
			D/W
			D/Y
			D/Z
			R/V
			R/W
			R/Y
			R/Z
		''')
	}

	/**
	 * Label count.
	 */
	@Test
	def void checkAllocatedJumpsCount02() {
		try {
			'''
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
				label17:
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
				IF = A THEN GOTO label17; END
			'''.assertCompilesTo("THE ABOVE SHOULD GENERATE AN EXCEPTION IN COMPILATION")
		} catch (Exception e) {
			// exception expected
			return
		}

		throw new Exception("The above should have failed with an exception.");
	}
}
