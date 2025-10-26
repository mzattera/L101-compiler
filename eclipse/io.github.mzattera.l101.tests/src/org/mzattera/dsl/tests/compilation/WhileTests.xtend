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
class WhileTests {

	@Inject extension CompilationTestHelper
//	@Inject extension TestUtil
		
/**
 * Simple WHILE with stand-alone CONTINUE and BREAK. 
 * Checks that jumps allocated by BREAk and CONTINUE are reused.
 */
	@Test
	def void testCompilation1() {
		'''
			SHORT x IN B;
			
			DO
				PRINT;
			WHILE =x END
			
			DO
				PRINT;
				CONTINUE;
				PRINT;
				CONTINUE;
				PRINT;
			WHILE =x END
			
			DO
				PRINT;
				BREAK;
				PRINT;
				BREAK;
				PRINT;
			WHILE =x END
		'''.assertCompilesTo(
		'''
			A/V
			/◇
			B↓
			/V
			A/W
			/◇
			B↓
			/W
			/◇
		''')
	}
		
/**
 *  Optimized CONTINUE and BREAK.
 * Checks that jumps allocated by BREAk and CONTINUE are reused.
 */
	@Test
	def void testCompilation2() {
		'''
			SHORT x IN B;
			
			DO
				PRINT;
				IF =x THEN CONTINUE; END
				IF =x THEN CONTINUE; END
				PRINT;
			WHILE =x END
			
			DO
				PRINT;
				IF =x THEN BREAK; END
				IF =x THEN BREAK; END
				PRINT;
			WHILE =x END
		'''.assertCompilesTo(
		'''
			A/V
			/◇
			B↓
			/W
			B↓
			/W
			/◇
			A/W
			B↓
			/V
			A/Y
			/◇
			B↓
			/Z
			B↓
			/Z
			/◇
			B↓
			/Y
			A/Z
		''')
	}
}
