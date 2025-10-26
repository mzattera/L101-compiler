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
package org.mzattera.dsl.generator

import java.io.ByteArrayInputStream
import java.io.OutputStream
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractGenerator
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext
import org.mzattera.dsl.generator.JumpAllocator.JumpType
import org.mzattera.dsl.l101.AdditiveOperator
import org.mzattera.dsl.l101.AssignmentStatement
import org.mzattera.dsl.l101.BreakStatement
import org.mzattera.dsl.l101.Constant
import org.mzattera.dsl.l101.ContinueStatement
import org.mzattera.dsl.l101.Current
import org.mzattera.dsl.l101.Expression
import org.mzattera.dsl.l101.ForStatement
import org.mzattera.dsl.l101.GotoStatement
import org.mzattera.dsl.l101.IfStatement
import org.mzattera.dsl.l101.Input
import org.mzattera.dsl.l101.Label
import org.mzattera.dsl.l101.MultiplicativeOperator
import org.mzattera.dsl.l101.NewLine
import org.mzattera.dsl.l101.PrintStatement
import org.mzattera.dsl.l101.Program
import org.mzattera.dsl.l101.Statement
import org.mzattera.dsl.l101.Statements
import org.mzattera.dsl.l101.StoreStatement
import org.mzattera.dsl.l101.UnaryOperator
import org.mzattera.dsl.l101.UntilStatement
import org.mzattera.dsl.l101.Variable
import org.mzattera.dsl.l101.VariableAccess
import org.mzattera.dsl.l101.VariableDeclaration
import org.mzattera.dsl.l101.WhileStatement
import org.mzattera.dsl.util.FileUtil
import org.mzattera.dsl.util.P101EmulUtil
import org.mzattera.dsl.util.P101JavaPlusUtil

import static extension org.mzattera.dsl.util.Extensions.*

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class L101Generator extends AbstractGenerator {

// Encoding of P101 symbols used in code
	public final static String DIAMOND = "◇"
	public final static String UP = "↑"
	public final static String DOWN = "↓"
	public final static String UP_DOWN = "↕"
	public final static String PLUS = "+"
	public final static String MINUS = "-"
	public final static String MUL = "x"
	public final static String DIV = "÷"
	public final static String SQRT = "√"
	public final static String STAR = "*"

// Extension to use for output files
	static final String EXTENSION = "P101"
	static final String P101_EXTENSION = "666"
	static final String P101PLUS_EXTENSION = "pmc"

// Characters used to encode digits
	static String[] DIGITS = #["S", DOWN, UP, UP_DOWN, PLUS, MINUS, MUL, DIV, DIAMOND, STAR]

	// This class is responsible to keep track of jumps allocated to the various program statements
	var JumpAllocator jumps = null;

	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {

		val Program prg = resource.allContents.filter(Program).next()

		// Allocate jumps
		jumps = new JumpAllocator()
		assignJumps(prg.body, jumps)

		// Generates compiled code
		var code = prg.compile().toString();

		// TODO 3 - if possible, use a nicer way to signal the error in the IDE, both when allocating jumps and checking code length
		// Optimizes code
		val opt = new CodeOptimizer(code, jumps)
		code = opt.optimize()

		// TODO 3 - write test code for code length checks
		// Checks for code length
		val codeLen = code.codeLength
		if (codeLen > 120)
			throw new RuntimeException(
				"Code too long (" + codeLen + "); maximum program size (120 instruction) exceeded.")
		val v = findOvrelappingVariable(prg, codeLen)
		if (v !== null)
			throw new RuntimeException("Code too long (" + codeLen + "); code overflows over variable " + v.name + ".")

		if (resource.URI.isPlatform) { // I am running as an application
			fsa.generateFile(FileUtil.getCompiledFileName(resource, EXTENSION),
				new ByteArrayInputStream(code.getBytes("UTF-8")))

			var OutputStream out = null

			// Generates a P101EMU compatible card file
			try {
				out = FileUtil.getCompiledOutputStream(resource, P101_EXTENSION)
				P101EmulUtil.encode(code, out)
			} finally {
				if(out !== null) out.close()
			}

			// Generates a P101JavaPlus compatible card file
			out = null
			try {
				out = FileUtil.getCompiledOutputStream(resource, P101PLUS_EXTENSION)
				P101JavaPlusUtil.encode(code, out)
			} finally {
				if(out !== null) out.close()
			}
		} else {
			// I am doing unit tests, must use this method
			fsa.generateFile(FileUtil.getCompiledFileName(resource, EXTENSION), code)
		}
	}

	def dispatch CharSequence compile(Program program) {
		'''
			«compile(program.body)»
		'''
	}

	def dispatch CharSequence compile(Statements stmts) {
		'''
			«FOR Statement stmt : stmts.statements»
				«compile(stmt)»
			«ENDFOR»
		'''
	}

/// Labels ////////////////////////////////////////////////////////////////////
	def dispatch CharSequence compile(Label lbl) {
		// Notice that with GOTO optimization a label can be target of a conditional or unconditional jump (or both).	
		'''
			«IF jumps.getDestinationPlaceholder(lbl, "GOTO", JumpType.UNCONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(lbl, "GOTO", JumpType.UNCONDITIONAL)»
			«ENDIF»		
			«IF jumps.getDestinationPlaceholder(lbl, "GOTO", JumpType.CONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(lbl, "GOTO", JumpType.CONDITIONAL)»
			«ENDIF»		
		'''
	}

/// Expressions ////////////////////////////////////////////////////////////////////
	def dispatch CharSequence compile(AssignmentStatement exp) {
		switch (exp.op) {
			case "=":
				loadA(exp.operand)
			case "<->": '''
				«loadA(exp.operand)»
				«(exp.store as VariableAccess).toRegister() + UP_DOWN»
			'''
			case "%": '''
				«loadM(exp.operand)»«DIV»
				R«DOWN»
			'''
			default:
				loadM(exp.operand) + exp.op.toOp()
		}
	}

	def dispatch CharSequence compile(AdditiveOperator exp) {
		'''
			«loadA(exp.left)»
			«loadM(exp.right)»«exp.op.toOp()»
		'''
	}

	def dispatch CharSequence compile(MultiplicativeOperator exp) {
		if (exp.op == "%") {
			// operator % has special handling
			'''
				«loadA(exp.left)»
				«loadM(exp.right)»«"/".toOp()»
				R«DOWN»
			'''
		} else {
			'''
				«loadA(exp.left)»
				«loadM(exp.right)»«exp.op.toOp()»
			'''
		}
	}

	def dispatch CharSequence compile(UnaryOperator exp) {
		if (exp.op == "SQRT") {
			// Square root is calculated from M and result stored in A		
			if ((exp.operand instanceof Constant) || (exp.operand instanceof Input)) {
				// in this case we can load directly in M and perform calculation	
				'''
					«loadM(exp.operand)»
					«exp.op.toOp()»
				'''
			} else {
				// Otherwise we load A and move in M before calculation
				'''
					«loadA(exp.operand)»
					A«exp.op.toOp()»
				'''
			}
		} else {
			// all other operators expect operand in A
			'''
				«loadA(exp.operand)»
				«exp.op.toOp()»
			'''
		}
	}

/// Statements ///////////////////////////////////////////////////

	def dispatch CharSequence compile(StoreStatement stmt) {
		'''
			«loadM(stmt.param)»
			«(stmt.store as VariableAccess).toRegister()»«UP»
		'''
	}

	def dispatch CharSequence compile(PrintStatement stmt) {

		if (stmt.params.size == 0) {
			// no parameters, print a newline
			return "/" + DIAMOND
		} else {
			var result = ""

			for (var i = 0; i < stmt.params.size; i++) {
				var p = stmt.params.get(i)
				if(i > 0) result += "\n"
				result += switch (p) {
					NewLine:
						("/" + DIAMOND)
					Current:
						("A" + DIAMOND)
					Constant:
						(loadM(p) + DIAMOND)
					VariableAccess:
						(p.toRegister() + DIAMOND)
					default:
						throw new Exception("Unsupported PRINT parameter")
				}
			}

			return result;
		}
	}

	def dispatch CharSequence compile(GotoStatement stmt) {
		if (stmt.isOptimizable) // use conditional jumps
			jumps.getSourcePlaceholder(stmt.label, "GOTO", JumpType.CONDITIONAL)
		else
			jumps.getSourcePlaceholder(stmt.label, "GOTO", JumpType.UNCONDITIONAL)
	}

	/*
	 * Please look at "Implementation of Jumps.txt" file under "Documentation" project for an explanation of
	 * how control statements have been implemented.
	 * Optimizing the compiled code as much as possible, by reducing the number of allocated
	 * P101 jump instruction used has unfortunately made the code more difficult to understand.
	 */
	def dispatch CharSequence compile(UntilStatement stmt) {
		'''
			«IF jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.CONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.CONDITIONAL)»
			«ENDIF»
			«jumps.getDestinationPlaceholder(stmt, "UNTIL", JumpType.UNCONDITIONAL)»
			«compile(stmt.condition)»
			«jumps.getSourcePlaceholder(stmt, "UNTIL", JumpType.CONDITIONAL)»
			«compile(stmt.body)»
			«jumps.getSourcePlaceholder(stmt, "UNTIL", JumpType.UNCONDITIONAL)»
			«jumps.getDestinationPlaceholder(stmt, "UNTIL", JumpType.CONDITIONAL)»
			«IF jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.UNCONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.UNCONDITIONAL)»
			«ENDIF»
		'''
	}

	def dispatch CharSequence compile(ForStatement stmt) {
		// TODO 2 - Extra optimization for IF THEN CONTINUE
		// Actually, there is one extra optimization for IF THEN CONTINUE
		// when stmt.end == null, but it will not save any jump, only speed up execution a bit,
		// since it is potentially a mess to implement (conditional destinations might appear in 2 
		// different places), it is currently not implemented.
		'''
			«IF (stmt.init !== null)»
				«compile(stmt.init)»
			«ENDIF»
			«jumps.getDestinationPlaceholder(stmt, "FOR", JumpType.UNCONDITIONAL)»
			«compile(stmt.condition)»
			«jumps.getSourcePlaceholder(stmt, "FOR", JumpType.CONDITIONAL)»
			«compile(stmt.body)»
			«IF jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.CONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.CONDITIONAL)»
			«ENDIF»
			«IF jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.UNCONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.UNCONDITIONAL)»
			«ENDIF»
			«IF (stmt.end !== null)»
				«compile(stmt.end)»
			«ENDIF»
			«jumps.getSourcePlaceholder(stmt, "FOR", JumpType.UNCONDITIONAL)»
			«jumps.getDestinationPlaceholder(stmt, "FOR", JumpType.CONDITIONAL)»
			«IF jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.UNCONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.UNCONDITIONAL)»
			«ENDIF»
		'''
	}

	def dispatch CharSequence compile(WhileStatement stmt) {
		'''
			«jumps.getDestinationPlaceholder(stmt, "WHILE", JumpType.CONDITIONAL)»
			«compile(stmt.body)»
			«IF jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.CONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.CONDITIONAL)»
			«ENDIF»
			«IF jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.UNCONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpType.UNCONDITIONAL)»
			«ENDIF»
			«compile(stmt.condition)»
			«jumps.getSourcePlaceholder(stmt, "WHILE", JumpType.CONDITIONAL)»
			«IF jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.CONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.CONDITIONAL)»
			«ENDIF»
			«IF jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.UNCONDITIONAL) !== null»
				«jumps.getDestinationPlaceholder(stmt, "BREAK", JumpType.UNCONDITIONAL)»
			«ENDIF»
		'''
	}

	def dispatch CharSequence compile(ContinueStatement stmt) {
		val loop = stmt.getContainingLoop
		if (stmt.isOptimizable) {
			'''
				«jumps.getSourcePlaceholder(loop, "CONTINUE", JumpType.CONDITIONAL)»
			'''
		} else {
			switch loop {
				UntilStatement: '''
					«jumps.getSourcePlaceholder(loop, "UNTIL", JumpType.UNCONDITIONAL)»
				'''
				ForStatement:
					if (loop.end !== null) {
						'''
							«jumps.getSourcePlaceholder(loop, "CONTINUE", JumpType.UNCONDITIONAL)»
						'''
					} else {
						// if there is no .end in the loop, the continue can reuse the main loop jump
						'''
							«jumps.getSourcePlaceholder(loop, "FOR", JumpType.UNCONDITIONAL)»
						'''
					}
				WhileStatement: '''
					«jumps.getSourcePlaceholder(loop, "CONTINUE", JumpType.UNCONDITIONAL)»
				'''
				default: // guard
					throw new IllegalArgumentException()
			}
		}
	}

	def dispatch CharSequence compile(BreakStatement stmt) {
		val loop = stmt.getContainingLoop
		if (stmt.isOptimizable) {
			switch loop {
				UntilStatement: '''
					«jumps.getSourcePlaceholder(loop, "UNTIL", JumpType.CONDITIONAL)»
				'''
				ForStatement: '''
					«jumps.getSourcePlaceholder(loop, "FOR", JumpType.CONDITIONAL)»
				'''
				WhileStatement: '''
					«jumps.getSourcePlaceholder(loop, "BREAK", JumpType.CONDITIONAL)»
				'''
				default: // guard
					throw new IllegalArgumentException()
			}
		} else {
			'''
				«jumps.getSourcePlaceholder(loop, "BREAK", JumpType.UNCONDITIONAL)»
			'''
		}
	}

	def dispatch CharSequence compile(IfStatement stmt) {
		if (stmt.isOptimizable) {
			'''
				«compile(stmt.condition)»
				«compile(stmt.firstThenStatement)»
				«IF (stmt.elseClause !== null)»
					«compile(stmt.elseClause)»
				«ENDIF»
			'''
		} else { // No optimization
			if (stmt.isElseEmpty) {
				// Only THEN
				'''
					«compile(stmt.condition)»
					«jumps.getSourcePlaceholder(stmt, "IF", JumpType.CONDITIONAL)»
					«jumps.getSourcePlaceholder(stmt, "IF", JumpType.UNCONDITIONAL)»
					«jumps.getDestinationPlaceholder(stmt, "IF", JumpType.CONDITIONAL)»
					«compile(stmt.thenClause)»
					«jumps.getDestinationPlaceholder(stmt, "IF", JumpType.UNCONDITIONAL)»
				'''
			} else if (stmt.isThenEmpty) {
				// only ELSE
				'''
					«compile(stmt.condition)»
					«jumps.getSourcePlaceholder(stmt, "IF", JumpType.CONDITIONAL)»
					«compile(stmt.elseClause)»
					«jumps.getDestinationPlaceholder(stmt, "IF", JumpType.CONDITIONAL)»
				'''
			} else {
				// both present 
				'''
					«compile(stmt.condition)»
					«jumps.getSourcePlaceholder(stmt, "IF", JumpType.CONDITIONAL)»
					«compile(stmt.elseClause)»
					«jumps.getSourcePlaceholder(stmt, "IF", JumpType.UNCONDITIONAL)»
					«jumps.getDestinationPlaceholder(stmt, "IF", JumpType.CONDITIONAL)»
					«compile(stmt.thenClause)»
					«jumps.getDestinationPlaceholder(stmt, "IF", JumpType.UNCONDITIONAL)»
				'''
			}
		}
	}

	/*
	 * The below should cover all and only possible ways to load A register from language before performing an operation.
	 * 
	 * **************************************************************************************************************
	 */
	def dispatch CharSequence loadA(Constant c) {
		'''
			«loadM(c)»
			«DOWN»
		'''
	}

	def dispatch CharSequence loadA(Input in) {
		'''
			S
			«DOWN»
		'''
	}

	def dispatch CharSequence loadA(VariableAccess v) {
		v.toRegister() + DOWN
	}

	def dispatch CharSequence loadA(Current c) {
		"" // current value of a, nothing to do
	}

	def dispatch CharSequence loadA(Expression exp) {
		// We are trying to calculate an expression and store it in A, but this is exactly what compiled code does
		compile(exp)
	}

	/*
	 * The below should cover all and only possible ways to load M register from language before performing an operation.
	 * 
	 * **************************************************************************************************************
	 */
	def dispatch CharSequence loadM(Constant c) {
		var result = '''
			A/«UP»
		''';

		// Calculate encoding for the number to load.
		var v = c.value
		if (v.indexOf(".") === -1)
			v += "."

		var String dot
		var String digit
		var String marker

		for (var i = v.length - 1; i >= 0; i--) {
			if (v.charAt(i) == ".".charAt(0)) { // encode decimal point
				dot = "/"
				i--
			} else {
				dot = ""
			}
			marker = if (c.isNegative) { // different encoding for positive and negative numbers
				if (i === 0) { // most significant digit marker
					"E"
				} else {
					"F"
				}
			} else {
				if (i === 0) { // most significant digit marker
					"D"
				} else {
					"R"
				}
			}
			digit = DIGITS.get(v.charAt(i) - '0') + "\n"

			result += (marker + dot + digit)
		}

		return result
	}

	def dispatch CharSequence loadM(Input in) {
		'''
			S
		'''
	}

	def dispatch CharSequence loadM(Current c) {
		"A"
	}

	def dispatch CharSequence loadM(VariableAccess v) {
		v.store.toRegister()
	}

	/*
	 * Below all functions used to handle Labels
	 * 
	 * *****************************************************************************************************************
	 */
	/**
	 * Assigns jumps needed for all given statements.
	 * 
	 * @params jumps this class is used to keep track of allocated jump instructions.
	 * @param JumpType.CONDITIONAL at the end of the run, this will map a Statement into the conditional jump destination it needs.
	 * @param JumpType.UNCONDITIONAL at the end of the run, this will map a Statement into the un-conditional jump destination it needs.
	 * 
	 * @return null if execution was successful and all required jumps could be assigned
	 * Otherwise, it returns the statement for wich a label was required but we ran out of labels.
	 */
	def private static void assignJumps(Statements stmts, JumpAllocator jumps) {

		for (Statement stmt : stmts.statements) {

			switch (stmt) {
				Label: {
					if (stmt.target !== null) {
						// this is assigned to a key, therefore a jump needs to be allocated always
						jumps.allocateJumpPlaceholder(stmt, "GOTO", stmt.target)
					}
				}
				GotoStatement: {
					if (stmt.isOptimizable) {
						// Can be optimized with a single conditional jump within an IF
						jumps.allocateJumpPlaceholder(stmt.label, "GOTO", JumpType.CONDITIONAL)
					} else {
						// no optimization, use unconditional jumps
						if (stmt.label.target === null) {
							// notice that if label is assigned to a key, the jump is allocated by the Label: itself
							jumps.allocateJumpPlaceholder(stmt.label, "GOTO", JumpType.UNCONDITIONAL)
						}
					}
				}
				UntilStatement: { // UNTIL needs 2 labels
					jumps.allocateJumpPlaceholder(stmt, "UNTIL", JumpType.UNCONDITIONAL)
					jumps.allocateJumpPlaceholder(stmt, "UNTIL", JumpType.CONDITIONAL)

					// Allocate labels for instructions in the body
					assignJumps(stmt.body, jumps)
				}
				ForStatement: { // For needs 2 labels
					jumps.allocateJumpPlaceholder(stmt, "FOR", JumpType.UNCONDITIONAL)
					jumps.allocateJumpPlaceholder(stmt, "FOR", JumpType.CONDITIONAL)
					assignJumps(stmt.body, jumps)
				}
				WhileStatement: { // While needs 1 label
					jumps.allocateJumpPlaceholder(stmt, "WHILE", JumpType.CONDITIONAL)
					assignJumps(stmt.body, jumps)
				}
				ContinueStatement: {
					val loop = stmt.getContainingLoop
					if (stmt.isOptimizable) {
						jumps.allocateJumpPlaceholder(loop, "CONTINUE", JumpType.CONDITIONAL)
					} else {
						switch loop {
							ForStatement:
								if (loop.end !== null) {
									jumps.allocateJumpPlaceholder(loop, "CONTINUE", JumpType.UNCONDITIONAL)
								}
							WhileStatement:
								jumps.allocateJumpPlaceholder(loop, "CONTINUE", JumpType.UNCONDITIONAL)
						}
					}
				}
				BreakStatement: {
					val loop = stmt.getContainingLoop
					if (stmt.isOptimizable) {
						if (loop instanceof WhileStatement)
							jumps.allocateJumpPlaceholder(loop, "BREAK", JumpType.CONDITIONAL)
					} else {
						jumps.allocateJumpPlaceholder(loop, "BREAK", JumpType.UNCONDITIONAL)
					}
				}
				IfStatement: {
					if (!stmt.isOptimizable) {
						// If needs 2 labels, if it is optimized, required labels are allocated by inner statements
						jumps.allocateJumpPlaceholder(stmt, "IF", JumpType.CONDITIONAL)

						if (!stmt.isThenEmpty) {
							jumps.allocateJumpPlaceholder(stmt, "IF", JumpType.UNCONDITIONAL)
						}
					}

					// "recursively" assign jumps to contained statements
					if (stmt.thenClause !== null) {
						assignJumps(stmt.thenClause, jumps)
					}
					if (stmt.elseClause !== null) {
						assignJumps(stmt.elseClause, jumps)
					}
				}
			} // switch
		} // for each statement
	}

	/**
	 * Check variables being allocated and return the one that overlaps with the code
	 * 
	 * @param prg Source for the program being checked.
	 * @paran codeLen Length of the compiled code for the program.
	 * @return a reference to the variable that determines the smallest code size, or null if no variable is overlapping with the code. 
	 */
	def static findOvrelappingVariable(Program prg, int codeLen) {
		if(codeLen <= 48) return null

		var Variable result = null
		var maxSpace = 120 // max allowed length for the program
		for (VariableDeclaration vd : prg.varDeclarations) {
			for (Variable v : vd.vars) {

				val allowedLen = switch (v.reg) {
					case "F": 48
					case "F/": 59
					case "E": 72
					case "E/": 83
					case "D": 96
					case "D/": 107
					default: 120
				}

				if (allowedLen < maxSpace) {
					maxSpace = allowedLen
					result = v
				}
			} // for each variable			
		} // for each variable declaration
		if(maxSpace >= codeLen) return null
		return result
	}
}
