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
class IfTests {

	@Inject extension CompilationTestHelper
//	@Inject extension TestUtil

	/**
	 * Tests non-optimizable IF THEN ELSE; optimizable code is tested in tests for loops.
	 */
	@Test
	def void testCompilation1() {
		'''
		SHORT x IN B, y IN B/;
		
		start ON V:
			
			2->x;
			1->y;
			
			IF =(x-y) THEN PRINT x; END
			PRINT 111;
			IF =(x-y) ELSE PRINT y; END
			PRINT 222;
			IF =(x-y) THEN PRINT x; 
				ELSE PRINT y;
			END
			PRINT 333;
		'''.assertCompilesTo(
		'''
			AV
			A/↑
			D/↑
			B↑
			A/↑
			D/↓
			B/↑
			B↓
			B/-
			/V
			CV
			A/V
			B◇
			BV
			A/↑
			R/↓
			R↓
			D↓
			◇
			B↓
			B/-
			/W
			B/◇
			A/W
			A/↑
			R/↑
			R↑
			D↑
			◇
			B↓
			B/-
			/Y
			B/◇
			CW
			A/Y
			B◇
			BW
			A/↑
			R/↕
			R↕
			D↕
			◇
		''')
	}
}
