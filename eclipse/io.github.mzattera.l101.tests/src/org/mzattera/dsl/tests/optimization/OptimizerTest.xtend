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
package org.mzattera.dsl.tests.optimization

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.xbase.testing.CompilationTestHelper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.mzattera.dsl.generator.L101Generator
import org.mzattera.dsl.tests.L101InjectorProvider

@ExtendWith(InjectionExtension)
@InjectWith(L101InjectorProvider)
class OptimizerTest {

	@Inject extension CompilationTestHelper
//	@Inject extension TestUtil

	/**
	 * optLoadA0()
	 */
	@Test
	def void testOptimization1() {
		'''
			SHORT x IN B;
			=0;
			x<->0;
		'''.assertCompilesTo(
		'''
			A«L101Generator.MINUS»
			A«L101Generator.MINUS»
			B«L101Generator.UP_DOWN»
		''')
	}

	/**
	 * optDoubleA()
	 */
	@Test
	def void testOptimization2() {
		'''
			SHORT x IN B;
			=A*2;
			*=2;
			=x*2;
			x<->x*2;
		'''.assertCompilesTo(
		'''
			A+
			A+
			B«L101Generator.DOWN»
			A+
			B«L101Generator.DOWN»
			A+
			B«L101Generator.UP_DOWN»
		''')
	}

	/**
	 * optNegX()
	 */
	@Test
	def void testOptimization3() {
		'''
			LONG x IN B;
			SHORT y IN F/;
			SHORT z IN C/;
			
			start ON V:
			
				5->x;
				7->y;
				0->z;
				
				x<->NEG(x);
				y<->NEG(y);
				z<->NEG(z);
		'''.assertCompilesTo(
		'''
			AV
			A/«L101Generator.UP»
			D/«L101Generator.MINUS»
			B«L101Generator.UP»
			A/«L101Generator.UP»
			D/«L101Generator.DIV»
			F/«L101Generator.UP»
			A/«L101Generator.UP»
			D/S
			C/«L101Generator.UP»
			A«L101Generator.MINUS»
			B«L101Generator.MINUS»
			B«L101Generator.UP_DOWN»
			A«L101Generator.MINUS»
			F/«L101Generator.MINUS»
			F/«L101Generator.UP_DOWN»
			A«L101Generator.MINUS»
			C/«L101Generator.MINUS»
			C/«L101Generator.UP_DOWN»
		''')
	}
	
	
	/**
	 * Tests that jumps placeholders are properly optimized.
	 */
	@Test
	def void testOptimizeJumps() {
		'''
			SHORT x IN B;
			
			start ON V:
			
			lbl001:
			lbl004:
			lbl002:
			lbl005:
			
			PRINT;
			 
			IF =A THEN GOTO lbl001; END
			IF =A THEN GOTO lbl002; END
			
			PRINT;
			
			UNTIL =A DO
				IF =A THEN GOTO lbl003; END
				GOTO lbl005;
			END
			lbl003:
			
			PRINT;
			
			lbl006:
			DO
				IF =A THEN GOTO lbl006; END
				GOTO lbl005;
			WHILE = A END
			
			PRINT;
			
			lbl007 ON W:
			lbl008:
			lbl009 ON Y:
			lbl010:
			
			GOTO lbl004;
			
			GOTO lbl008;
			GOTO lbl010;
		'''.assertCompilesTo(
		'''
			AV
			A/V
			/◇
			/V
			/◇
			/W
			V
			A/W
			/◇
			A/Y
			/Y
			V
			AW
			AY
			V
		''')
	}
	
}
