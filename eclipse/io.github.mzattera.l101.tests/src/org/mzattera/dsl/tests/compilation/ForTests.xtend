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
class ForTests {

	@Inject extension CompilationTestHelper
//	@Inject extension TestUtil
		
/**
 * Simple FOR, 4 possible forms.
 */
	@Test
	def void testCompilation1() {
		'''
			SHORT i IN B;
			
			FOR 1->i; =(i-3); i<->i+1 DO
				PRINT i;
			END
			
			FOR ; =(i-3); i<->i+1 DO
				PRINT i;
			END
			
			FOR 1->i; =(i-3);  DO
				PRINT i;
			END
			
			FOR ; =(i-3);  DO
				PRINT i;
			END

		'''.assertCompilesTo(
		'''
			A/↑
			D/↓
			B↑
			BV
			B↓
			A/↑
			D/↕
			-
			/V
			B◇
			B↓
			A/↑
			D/↓
			+
			B↕
			CV
			A/V
			BW
			B↓
			A/↑
			D/↕
			-
			/W
			B◇
			B↓
			A/↑
			D/↓
			+
			B↕
			CW
			A/W
			A/↑
			D/↓
			B↑
			BY
			B↓
			A/↑
			D/↕
			-
			/Y
			B◇
			CY
			A/Y
			BZ
			B↓
			A/↑
			D/↕
			-
			/Z
			B◇
			CZ
			A/Z
		''')
	}
		
/**
 * CONTINUE and BREAK alone in the loop.
 * Notice CONTINUE is tested in 2 cases as it results in different compilation.
 * Checks that jumps allocated by BREAk and CONTINUE are reused.
 */
	@Test
	def void testCompilation2() {
		'''
			SHORT i IN B;
			
			FOR 1->i; =(i-3); i<->i+1 DO
				PRINT;
				CONTINUE;
				CONTINUE;
				PRINT;
			END
			
			FOR 1->i; =(i-3); DO
				PRINT;
				CONTINUE;
				CONTINUE;
				PRINT;
			END
			
			FOR 1->i; =(i-3); DO
				PRINT;
				BREAK;
				BREAK;
				PRINT;
			END
		'''.assertCompilesTo(
		'''
			A/↑
			D/↓
			B↑
			BV
			B↓
			A/↑
			D/↕
			-
			/V
			/◇
			B↓
			A/↑
			D/↓
			+
			B↕
			CV
			A/V
			A/↑
			D/↓
			B↑
			BW
			B↓
			A/↑
			D/↕
			-
			/W
			/◇
			CW
			A/W
			A/↑
			D/↓
			B↑
			B↓
			A/↑
			D/↕
			-
			/Y
			/◇
			CY
			A/Y
			BY
		''')
	}
		
/**
 * Optimized CONTINUE and BREAK. Checks that jumps allocated by BREAK and CONTINUE are reused.
 */
	@Test
	def void testCompilation3() {
		'''
			SHORT i IN B;

			FOR 1->i; =(i-3); i<->i+1 DO
				PRINT;
				IF =i THEN CONTINUE; END
				IF =i THEN CONTINUE; END
				PRINT;
			END
			
			FOR 1->i; =(i-3); DO
				PRINT;
				IF =i THEN BREAK; END
				IF =i THEN BREAK; END
				PRINT;
			END
		'''.assertCompilesTo(
		'''
			A/↑
			D/↓
			B↑
			BV
			B↓
			A/↑
			D/↕
			-
			/V
			/◇
			B↓
			/W
			B↓
			/W
			/◇
			A/W
			B↓
			A/↑
			D/↓
			+
			B↕
			CV
			A/V
			A/↑
			D/↓
			B↑
			BW
			B↓
			A/↑
			D/↕
			-
			/Y
			/◇
			B↓
			/Y
			B↓
			/Y
			/◇
			CW
			A/Y
		''')
	}
}
