/**
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
package org.mzattera.dsl.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.mzattera.dsl.generator.L101Generator;
import org.mzattera.dsl.l101.BreakStatement;
import org.mzattera.dsl.l101.ContinueStatement;
import org.mzattera.dsl.l101.GotoStatement;
import org.mzattera.dsl.l101.IfStatement;
import org.mzattera.dsl.l101.LoopStatement;
import org.mzattera.dsl.l101.Program;
import org.mzattera.dsl.l101.Statement;
import org.mzattera.dsl.l101.Statements;
import org.mzattera.dsl.l101.Variable;
import org.mzattera.dsl.l101.VariableAccess;

/**
 * Extension methods for variables.
 */
@SuppressWarnings("all")
public final class Extensions {
  /**
   * @return P101 operator for given "Java-like" operator.
   */
  public static String toOp(final String s) {
    String _switchResult = null;
    if (s != null) {
      switch (s) {
        case "+":
        case "+=":
          _switchResult = L101Generator.PLUS;
          break;
        case "-":
        case "-=":
          _switchResult = L101Generator.MINUS;
          break;
        case "*":
        case "*=":
          _switchResult = L101Generator.MUL;
          break;
        case "/":
        case "/=":
          _switchResult = L101Generator.DIV;
          break;
        case "%":
        case "%=":
          _switchResult = "%";
          break;
        case "SQ":
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("Ax");
          _builder.newLine();
          _switchResult = _builder.toString();
          break;
        case "SQRT":
          _switchResult = L101Generator.SQRT;
          break;
        case "INT":
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("/");
          _builder_1.append(L101Generator.UP_DOWN);
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("-");
          _builder_1.newLine();
          _switchResult = _builder_1.toString();
          break;
        case "DEC":
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("/");
          _builder_2.append(L101Generator.UP_DOWN);
          _builder_2.newLineIfNotEmpty();
          _builder_2.append(L101Generator.DOWN);
          _builder_2.newLineIfNotEmpty();
          _switchResult = _builder_2.toString();
          break;
        case "NEG":
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("A");
          _builder_3.append(L101Generator.MINUS);
          _builder_3.newLineIfNotEmpty();
          _builder_3.append(L101Generator.MINUS);
          _builder_3.newLineIfNotEmpty();
          _switchResult = _builder_3.toString();
          break;
        case "INV":
          StringConcatenation _builder_4 = new StringConcatenation();
          _builder_4.append("A");
          _builder_4.append(L101Generator.DIV);
          _builder_4.newLineIfNotEmpty();
          _builder_4.append(L101Generator.DIV);
          _builder_4.newLineIfNotEmpty();
          _switchResult = _builder_4.toString();
          break;
        default:
          throw new IllegalArgumentException(("Unrecognized operator: " + s));
      }
    } else {
      throw new IllegalArgumentException(("Unrecognized operator: " + s));
    }
    return _switchResult;
  }

  /**
   * Given a variable, returns a string that can be used to address the location where the variable is stored.
   * 
   * @return P101 register name for given variable.
   */
  public static String toRegister(final Variable v) {
    return v.getReg();
  }

  /**
   * Given a variable, returns a string that can be used to address the location where the variable is stored.
   * 
   * @return P101 register name for given variable.
   */
  public static String toRegister(final VariableAccess v) {
    return Extensions.toRegister(v.getStore());
  }

  /**
   * @return the innermost loop (LoopStatement) that contains given statement, or null if it does not exist.
   */
  public static LoopStatement getContainingLoop(final Statement stmt) {
    EObject parent = stmt.eContainer();
    while ((parent != null)) {
      {
        if ((parent instanceof LoopStatement)) {
          return ((LoopStatement)parent);
        }
        parent = parent.eContainer();
      }
    }
    return null;
  }

  /**
   * @return the innermost IF statement that contains given statement, or null if it does not exist.
   */
  public static IfStatement getContainingIf(final Statement stmt) {
    EObject parent = stmt.eContainer();
    while ((parent != null)) {
      {
        if ((parent instanceof IfStatement)) {
          return ((IfStatement)parent);
        }
        parent = parent.eContainer();
      }
    }
    return null;
  }

  /**
   * @return the Program containing given object.
   */
  public static Program getProgram(final EObject obj) {
    EObject _rootContainer = EcoreUtil2.getRootContainer(obj);
    return ((Program) _rootContainer);
  }

  /**
   * @return true if given list of statements is null or empty.
   */
  public static boolean isEmpty(final Statements stmt) {
    return ((stmt == null) || (stmt.getStatements().size() == 0));
  }

  /**
   * @return true if IF statement has no THEN or THEN has no statements.
   */
  public static boolean isThenEmpty(final IfStatement stmt) {
    return Extensions.isEmpty(stmt.getThenClause());
  }

  /**
   * @return true if IF statement has no ELSE or ELSE has no statements.
   */
  public static boolean isElseEmpty(final IfStatement stmt) {
    return Extensions.isEmpty(stmt.getElseClause());
  }

  /**
   * @return first instruction in the THEN body for given IF statement, or null if it is empty.
   */
  public static Statement getFirstThenStatement(final IfStatement stmt) {
    Statement _xblockexpression = null;
    {
      Statements _thenClause = null;
      if (stmt!=null) {
        _thenClause=stmt.getThenClause();
      }
      EList<Statement> _statements = null;
      if (_thenClause!=null) {
        _statements=_thenClause.getStatements();
      }
      final EList<Statement> s = _statements;
      Statement _xifexpression = null;
      if (((s == null) || (s.size() == 0))) {
        _xifexpression = null;
      } else {
        _xifexpression = stmt.getThenClause().getStatements().get(0);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }

  /**
   * @return true if the statement can be optimized accordingly what is described in
   * "Documentation\Implementation of Jumps.txt".
   */
  public static boolean isOptimizable(final Statement stmt) {
    boolean _switchResult = false;
    boolean _matched = false;
    if (stmt instanceof GotoStatement || stmt instanceof ContinueStatement || stmt instanceof BreakStatement) {
      _matched=true;
      boolean _xblockexpression = false;
      {
        final IfStatement ifStmt = Extensions.getContainingIf(stmt);
        _xblockexpression = ((ifStmt != null) && (Extensions.getFirstThenStatement(ifStmt) == stmt));
      }
      _switchResult = _xblockexpression;
    }
    if (!_matched) {
      if (stmt instanceof IfStatement) {
        _matched=true;
        _switchResult = ((Extensions.getFirstThenStatement(((IfStatement)stmt)) != null) && Extensions.isOptimizable(Extensions.getFirstThenStatement(((IfStatement)stmt))));
      }
    }
    if (!_matched) {
      _switchResult = false;
    }
    return _switchResult;
  }

  /**
   * @return length of a P101 program (number of instructions).
   */
  public static int getCodeLength(final String code) {
    return code.trim().split("\n").length;
  }
}
