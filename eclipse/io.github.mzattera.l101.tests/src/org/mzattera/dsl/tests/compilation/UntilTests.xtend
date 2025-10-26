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
class UntilTests {

	@Inject extension CompilationTestHelper
//	@Inject extension TestUtil
		
/**
 * Simple WHILE loop. 
 */
	@Test
	def void testCompilation1() {
		'''
			SHORT x IN B, y IN B/;
			
			UNTIL =x DO
				PRINT;
			END
		'''.assertCompilesTo(
		'''
			BV
			B↓
			/V
			/◇
			CV
			A/V
		''')
	}
		
/**
 * CONTINUE and BREAK inside the loop. Checks that jumps allocated by BREAK and CONTINUE are reused.
 */
	@Test
	def void testCompilation2() {
		'''
			SHORT x IN B, y IN B/;
			
			UNTIL =x DO
				PRINT x;
				CONTINUE;
				PRINT x;	
				CONTINUE;
				PRINT x;	
			END
			
			UNTIL =y DO
				PRINT x;
				BREAK;
				PRINT x;	
				BREAK;
				PRINT x;	
			END
		'''.assertCompilesTo(
		'''
			BV
			B↓
			/V
			B◇
			CV
			A/V
			B/↓
			/W
			B◇
			CW
			A/W
			BW
		''')
	}
		
/**
 * CONTINUE and BREAK optimized inside IF. Checks that jumps allocated by BREAk and CONTINUE are reused.
 */
	@Test
	def void testCompilation3() {
		'''
			SHORT x IN B, y IN B/;
			
			UNTIL =x DO
				PRINT;
				IF =1 THEN CONTINUE; END
				IF =1 THEN CONTINUE; END
				PRINT;	
			END
			UNTIL =y DO
				PRINT;
				IF =1 THEN BREAK; END
				IF =1 THEN BREAK; END
				PRINT;	
			END
		'''.assertCompilesTo(
		'''
			A/V
			BV
			B↓
			/W
			/◇
			A/↑
			D/↓
			↓
			/V
			A/↑
			D/↓
			↓
			/V
			/◇
			CV
			A/W
			BW
			B/↓
			/Y
			/◇
			A/↑
			D/↓
			↓
			/Y
			A/↑
			D/↓
			↓
			/Y
			/◇
			CW
			A/Y
		''')
	}
}
