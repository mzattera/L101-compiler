package org.mzattera.dsl.util

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.mzattera.dsl.generator.L101Generator
import org.mzattera.dsl.l101.BreakStatement
import org.mzattera.dsl.l101.ContinueStatement
import org.mzattera.dsl.l101.GotoStatement
import org.mzattera.dsl.l101.IfStatement
import org.mzattera.dsl.l101.LoopStatement
import org.mzattera.dsl.l101.Program
import org.mzattera.dsl.l101.Statement
import org.mzattera.dsl.l101.Statements
import org.mzattera.dsl.l101.Variable
import org.mzattera.dsl.l101.VariableAccess

/**
 * Extension methods for variables.
 */
final class Extensions {

	/**
	 * @return P101 operator for given "Java-like" operator.
	 */
	def static String toOp(String s) {

		switch s {
			case "+",
			case "+=":
				L101Generator.PLUS
			case "-",
			case "-=":
				L101Generator.MINUS
			case "*",
			case "*=":
				L101Generator.MUL
			case "/",
			case "/=":
				L101Generator.DIV
			case "%",
			case "%=":
				"%" // operator % has special handling
			case "SQ": '''
				Ax
			'''
			case "SQRT":
				L101Generator.SQRT
			case "INT": // Integral part (everything left of comma)
			'''
				/«L101Generator.UP_DOWN»
				-
			'''
			case "DEC": // Decimal part (everything right of comma)
			'''
				/«L101Generator.UP_DOWN»
				«L101Generator.DOWN»
			'''
			case "NEG": // Negate number in A
			'''
				A«L101Generator.MINUS»
				«L101Generator.MINUS»
			'''
			case "INV": // 1 / A
			'''
				A«L101Generator.DIV»
				«L101Generator.DIV»
			'''
			default: // guard
				throw new IllegalArgumentException("Unrecognized operator: " + s)
		}
	}

	/**
	 * Given a variable, returns a string that can be used to address the location where the variable is stored.
	 * 
	 * @return P101 register name for given variable.
	 */
	def static String toRegister(Variable v) {
		v.reg;
	}

	/**
	 * Given a variable, returns a string that can be used to address the location where the variable is stored.
	 * 
	 * @return P101 register name for given variable.
	 */
	def static String toRegister(VariableAccess v) {
		v.store.toRegister();
	}

	/**
	 * @return the innermost loop (LoopStatement) that contains given statement, or null if it does not exist.
	 * */
	def static LoopStatement getContainingLoop(Statement stmt) {
		var parent = stmt.eContainer
		while (parent !== null) {
			if(parent instanceof LoopStatement) return parent
			parent = parent.eContainer
		}

		return null
	}

	/**
	 * @return the innermost IF statement that contains given statement, or null if it does not exist.
	 * */
	def static IfStatement getContainingIf(Statement stmt) {
		var parent = stmt.eContainer
		while (parent !== null) {
			if(parent instanceof IfStatement) return parent
			parent = parent.eContainer
		}

		return null
	}

	/**
	 * @return the Program containing given object.
	 * */
	def static Program getProgram(EObject obj) {
		EcoreUtil2.getRootContainer(obj) as Program
	}

	/**
	 * @return true if given list of statements is null or empty.
	 */
	def static boolean isEmpty(Statements stmt) {
		(stmt === null) || (stmt.statements.size == 0)
	}

	/**
	 * @return true if IF statement has no THEN or THEN has no statements.
	 */
	def static boolean isThenEmpty(IfStatement stmt) {
		isEmpty(stmt.thenClause)
	}

	/**
	 * @return true if IF statement has no ELSE or ELSE has no statements.
	 */
	def static boolean isElseEmpty(IfStatement stmt) {
		isEmpty(stmt.elseClause)
	}

	/**
	 * @return first instruction in the THEN body for given IF statement, or null if it is empty.
	 */
	def static Statement getFirstThenStatement(IfStatement stmt) {
		val s = stmt?.thenClause?.statements
		if ((s === null) || (s.size == 0))
			null
		else
			stmt.thenClause.statements.get(0)
	}

	/**
	 * @return true if the statement can be optimized accordingly what is described in 
	 * "Documentation\Implementation of Jumps.txt".
	 */
	def static boolean isOptimizable(Statement stmt) {
		switch stmt {
			GotoStatement|ContinueStatement|BreakStatement: {
				val ifStmt = stmt.containingIf;
				(ifStmt !== null) && (ifStmt.firstThenStatement === stmt)
			}
			IfStatement:
				(stmt.firstThenStatement !== null) && (stmt.firstThenStatement.isOptimizable)
			default:
				false
		}
	}

	/**
	 * @return length of a P101 program (number of instructions).
	 */
	def static int getCodeLength(String code) {
		return code.trim().split('\n').length
	}
}
