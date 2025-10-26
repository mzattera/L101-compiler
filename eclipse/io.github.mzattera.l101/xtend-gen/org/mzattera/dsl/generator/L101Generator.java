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
package org.mzattera.dsl.generator;

import com.google.common.collect.Iterators;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.XbaseGenerated;
import org.mzattera.dsl.l101.AdditiveOperator;
import org.mzattera.dsl.l101.AssignmentStatement;
import org.mzattera.dsl.l101.BreakStatement;
import org.mzattera.dsl.l101.Constant;
import org.mzattera.dsl.l101.ContinueStatement;
import org.mzattera.dsl.l101.Current;
import org.mzattera.dsl.l101.Expression;
import org.mzattera.dsl.l101.ForStatement;
import org.mzattera.dsl.l101.GotoStatement;
import org.mzattera.dsl.l101.IfStatement;
import org.mzattera.dsl.l101.Input;
import org.mzattera.dsl.l101.Label;
import org.mzattera.dsl.l101.LoopStatement;
import org.mzattera.dsl.l101.MultiplicativeOperator;
import org.mzattera.dsl.l101.NewLine;
import org.mzattera.dsl.l101.PrintParameter;
import org.mzattera.dsl.l101.PrintStatement;
import org.mzattera.dsl.l101.Program;
import org.mzattera.dsl.l101.Statement;
import org.mzattera.dsl.l101.Statements;
import org.mzattera.dsl.l101.StoreStatement;
import org.mzattera.dsl.l101.UnaryOperator;
import org.mzattera.dsl.l101.UntilStatement;
import org.mzattera.dsl.l101.Variable;
import org.mzattera.dsl.l101.VariableAccess;
import org.mzattera.dsl.l101.VariableDeclaration;
import org.mzattera.dsl.l101.WhileStatement;
import org.mzattera.dsl.util.Extensions;
import org.mzattera.dsl.util.FileUtil;
import org.mzattera.dsl.util.P101EmulUtil;
import org.mzattera.dsl.util.P101JavaPlusUtil;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class L101Generator extends AbstractGenerator {
  public static final String DIAMOND = "◇";

  public static final String UP = "↑";

  public static final String DOWN = "↓";

  public static final String UP_DOWN = "↕";

  public static final String PLUS = "+";

  public static final String MINUS = "-";

  public static final String MUL = "x";

  public static final String DIV = "÷";

  public static final String SQRT = "√";

  public static final String STAR = "*";

  private static final String EXTENSION = "P101";

  private static final String P101_EXTENSION = "666";

  private static final String P101PLUS_EXTENSION = "pmc";

  private static String[] DIGITS = { "S", L101Generator.DOWN, L101Generator.UP, L101Generator.UP_DOWN, L101Generator.PLUS, L101Generator.MINUS, L101Generator.MUL, L101Generator.DIV, L101Generator.DIAMOND, L101Generator.STAR };

  private JumpAllocator jumps = null;

  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    try {
      final Program prg = Iterators.<Program>filter(resource.getAllContents(), Program.class).next();
      JumpAllocator _jumpAllocator = new JumpAllocator();
      this.jumps = _jumpAllocator;
      L101Generator.assignJumps(prg.getBody(), this.jumps);
      String code = this.compile(prg).toString();
      final CodeOptimizer opt = new CodeOptimizer(code, this.jumps);
      code = opt.optimize();
      final int codeLen = Extensions.getCodeLength(code);
      if ((codeLen > 120)) {
        throw new RuntimeException(
          (("Code too long (" + Integer.valueOf(codeLen)) + "); maximum program size (120 instruction) exceeded."));
      }
      final Variable v = L101Generator.findOvrelappingVariable(prg, codeLen);
      if ((v != null)) {
        String _name = v.getName();
        String _plus = ((("Code too long (" + Integer.valueOf(codeLen)) + "); code overflows over variable ") + _name);
        String _plus_1 = (_plus + ".");
        throw new RuntimeException(_plus_1);
      }
      boolean _isPlatform = resource.getURI().isPlatform();
      if (_isPlatform) {
        String _compiledFileName = FileUtil.getCompiledFileName(resource, L101Generator.EXTENSION);
        byte[] _bytes = code.getBytes("UTF-8");
        ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(_bytes);
        fsa.generateFile(_compiledFileName, _byteArrayInputStream);
        OutputStream out = null;
        try {
          out = FileUtil.getCompiledOutputStream(resource, L101Generator.P101_EXTENSION);
          P101EmulUtil.encode(code, out);
        } finally {
          if ((out != null)) {
            out.close();
          }
        }
        out = null;
        try {
          out = FileUtil.getCompiledOutputStream(resource, L101Generator.P101PLUS_EXTENSION);
          P101JavaPlusUtil.encode(code, out);
        } finally {
          if ((out != null)) {
            out.close();
          }
        }
      } else {
        fsa.generateFile(FileUtil.getCompiledFileName(resource, L101Generator.EXTENSION), code);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected CharSequence _compile(final Program program) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _compile = this.compile(program.getBody());
    _builder.append(_compile);
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected CharSequence _compile(final Statements stmts) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Statement> _statements = stmts.getStatements();
      for(final Statement stmt : _statements) {
        CharSequence _compile = this.compile(stmt);
        _builder.append(_compile);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  protected CharSequence _compile(final Label lbl) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _destinationPlaceholder = this.jumps.getDestinationPlaceholder(lbl, "GOTO", JumpAllocator.JumpType.UNCONDITIONAL);
      boolean _tripleNotEquals = (_destinationPlaceholder != null);
      if (_tripleNotEquals) {
        String _destinationPlaceholder_1 = this.jumps.getDestinationPlaceholder(lbl, "GOTO", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder.append(_destinationPlaceholder_1);
        _builder.newLineIfNotEmpty();
      }
    }
    {
      String _destinationPlaceholder_2 = this.jumps.getDestinationPlaceholder(lbl, "GOTO", JumpAllocator.JumpType.CONDITIONAL);
      boolean _tripleNotEquals_1 = (_destinationPlaceholder_2 != null);
      if (_tripleNotEquals_1) {
        String _destinationPlaceholder_3 = this.jumps.getDestinationPlaceholder(lbl, "GOTO", JumpAllocator.JumpType.CONDITIONAL);
        _builder.append(_destinationPlaceholder_3);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  protected CharSequence _compile(final AssignmentStatement exp) {
    CharSequence _switchResult = null;
    String _op = exp.getOp();
    if (_op != null) {
      switch (_op) {
        case "=":
          _switchResult = this.loadA(exp.getOperand());
          break;
        case "<->":
          StringConcatenation _builder = new StringConcatenation();
          CharSequence _loadA = this.loadA(exp.getOperand());
          _builder.append(_loadA);
          _builder.newLineIfNotEmpty();
          Expression _store = exp.getStore();
          String _register = Extensions.toRegister(((VariableAccess) _store));
          String _plus = (_register + L101Generator.UP_DOWN);
          _builder.append(_plus);
          _builder.newLineIfNotEmpty();
          _switchResult = _builder;
          break;
        case "%":
          StringConcatenation _builder_1 = new StringConcatenation();
          CharSequence _loadM = this.loadM(exp.getOperand());
          _builder_1.append(_loadM);
          _builder_1.append(L101Generator.DIV);
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("R");
          _builder_1.append(L101Generator.DOWN);
          _builder_1.newLineIfNotEmpty();
          _switchResult = _builder_1;
          break;
        default:
          CharSequence _loadM_1 = this.loadM(exp.getOperand());
          String _op_1 = Extensions.toOp(exp.getOp());
          _switchResult = (_loadM_1 + _op_1);
          break;
      }
    } else {
      CharSequence _loadM_1 = this.loadM(exp.getOperand());
      String _op_1 = Extensions.toOp(exp.getOp());
      _switchResult = (_loadM_1 + _op_1);
    }
    return _switchResult;
  }

  protected CharSequence _compile(final AdditiveOperator exp) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _loadA = this.loadA(exp.getLeft());
    _builder.append(_loadA);
    _builder.newLineIfNotEmpty();
    CharSequence _loadM = this.loadM(exp.getRight());
    _builder.append(_loadM);
    String _op = Extensions.toOp(exp.getOp());
    _builder.append(_op);
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected CharSequence _compile(final MultiplicativeOperator exp) {
    CharSequence _xifexpression = null;
    String _op = exp.getOp();
    boolean _equals = Objects.equals(_op, "%");
    if (_equals) {
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _loadA = this.loadA(exp.getLeft());
      _builder.append(_loadA);
      _builder.newLineIfNotEmpty();
      CharSequence _loadM = this.loadM(exp.getRight());
      _builder.append(_loadM);
      String _op_1 = Extensions.toOp("/");
      _builder.append(_op_1);
      _builder.newLineIfNotEmpty();
      _builder.append("R");
      _builder.append(L101Generator.DOWN);
      _builder.newLineIfNotEmpty();
      _xifexpression = _builder;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      CharSequence _loadA_1 = this.loadA(exp.getLeft());
      _builder_1.append(_loadA_1);
      _builder_1.newLineIfNotEmpty();
      CharSequence _loadM_1 = this.loadM(exp.getRight());
      _builder_1.append(_loadM_1);
      String _op_2 = Extensions.toOp(exp.getOp());
      _builder_1.append(_op_2);
      _builder_1.newLineIfNotEmpty();
      _xifexpression = _builder_1;
    }
    return _xifexpression;
  }

  protected CharSequence _compile(final UnaryOperator exp) {
    CharSequence _xifexpression = null;
    String _op = exp.getOp();
    boolean _equals = Objects.equals(_op, "SQRT");
    if (_equals) {
      CharSequence _xifexpression_1 = null;
      if (((exp.getOperand() instanceof Constant) || (exp.getOperand() instanceof Input))) {
        StringConcatenation _builder = new StringConcatenation();
        CharSequence _loadM = this.loadM(exp.getOperand());
        _builder.append(_loadM);
        _builder.newLineIfNotEmpty();
        String _op_1 = Extensions.toOp(exp.getOp());
        _builder.append(_op_1);
        _builder.newLineIfNotEmpty();
        _xifexpression_1 = _builder;
      } else {
        StringConcatenation _builder_1 = new StringConcatenation();
        CharSequence _loadA = this.loadA(exp.getOperand());
        _builder_1.append(_loadA);
        _builder_1.newLineIfNotEmpty();
        _builder_1.append("A");
        String _op_2 = Extensions.toOp(exp.getOp());
        _builder_1.append(_op_2);
        _builder_1.newLineIfNotEmpty();
        _xifexpression_1 = _builder_1;
      }
      _xifexpression = _xifexpression_1;
    } else {
      StringConcatenation _builder_2 = new StringConcatenation();
      CharSequence _loadA_1 = this.loadA(exp.getOperand());
      _builder_2.append(_loadA_1);
      _builder_2.newLineIfNotEmpty();
      String _op_3 = Extensions.toOp(exp.getOp());
      _builder_2.append(_op_3);
      _builder_2.newLineIfNotEmpty();
      _xifexpression = _builder_2;
    }
    return _xifexpression;
  }

  protected CharSequence _compile(final StoreStatement stmt) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _loadM = this.loadM(stmt.getParam());
    _builder.append(_loadM);
    _builder.newLineIfNotEmpty();
    Expression _store = stmt.getStore();
    String _register = Extensions.toRegister(((VariableAccess) _store));
    _builder.append(_register);
    _builder.append(L101Generator.UP);
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected CharSequence _compile(final PrintStatement stmt) {
    try {
      int _size = stmt.getParams().size();
      boolean _equals = (_size == 0);
      if (_equals) {
        return ("/" + L101Generator.DIAMOND);
      } else {
        String result = "";
        for (int i = 0; (i < stmt.getParams().size()); i++) {
          {
            PrintParameter p = stmt.getParams().get(i);
            if ((i > 0)) {
              String _result = result;
              result = (_result + "\n");
            }
            String _result_1 = result;
            String _switchResult = null;
            boolean _matched = false;
            if (p instanceof NewLine) {
              _matched=true;
              _switchResult = ("/" + L101Generator.DIAMOND);
            }
            if (!_matched) {
              if (p instanceof Current) {
                _matched=true;
                _switchResult = ("A" + L101Generator.DIAMOND);
              }
            }
            if (!_matched) {
              if (p instanceof Constant) {
                _matched=true;
                CharSequence _loadM = this.loadM(((Expression)p));
                _switchResult = (_loadM + L101Generator.DIAMOND);
              }
            }
            if (!_matched) {
              if (p instanceof VariableAccess) {
                _matched=true;
                String _register = Extensions.toRegister(((VariableAccess)p));
                _switchResult = (_register + L101Generator.DIAMOND);
              }
            }
            if (!_matched) {
              throw new Exception("Unsupported PRINT parameter");
            }
            result = (_result_1 + _switchResult);
          }
        }
        return result;
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected CharSequence _compile(final GotoStatement stmt) {
    String _xifexpression = null;
    boolean _isOptimizable = Extensions.isOptimizable(stmt);
    if (_isOptimizable) {
      _xifexpression = this.jumps.getSourcePlaceholder(stmt.getLabel(), "GOTO", JumpAllocator.JumpType.CONDITIONAL);
    } else {
      _xifexpression = this.jumps.getSourcePlaceholder(stmt.getLabel(), "GOTO", JumpAllocator.JumpType.UNCONDITIONAL);
    }
    return _xifexpression;
  }

  /**
   * Please look at "Implementation of Jumps.txt" file under "Documentation" project for an explanation of
   * how control statements have been implemented.
   * Optimizing the compiled code as much as possible, by reducing the number of allocated
   * P101 jump instruction used has unfortunately made the code more difficult to understand.
   */
  protected CharSequence _compile(final UntilStatement stmt) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _destinationPlaceholder = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
      boolean _tripleNotEquals = (_destinationPlaceholder != null);
      if (_tripleNotEquals) {
        String _destinationPlaceholder_1 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
        _builder.append(_destinationPlaceholder_1);
        _builder.newLineIfNotEmpty();
      }
    }
    String _destinationPlaceholder_2 = this.jumps.getDestinationPlaceholder(stmt, "UNTIL", JumpAllocator.JumpType.UNCONDITIONAL);
    _builder.append(_destinationPlaceholder_2);
    _builder.newLineIfNotEmpty();
    CharSequence _compile = this.compile(stmt.getCondition());
    _builder.append(_compile);
    _builder.newLineIfNotEmpty();
    String _sourcePlaceholder = this.jumps.getSourcePlaceholder(stmt, "UNTIL", JumpAllocator.JumpType.CONDITIONAL);
    _builder.append(_sourcePlaceholder);
    _builder.newLineIfNotEmpty();
    CharSequence _compile_1 = this.compile(stmt.getBody());
    _builder.append(_compile_1);
    _builder.newLineIfNotEmpty();
    String _sourcePlaceholder_1 = this.jumps.getSourcePlaceholder(stmt, "UNTIL", JumpAllocator.JumpType.UNCONDITIONAL);
    _builder.append(_sourcePlaceholder_1);
    _builder.newLineIfNotEmpty();
    String _destinationPlaceholder_3 = this.jumps.getDestinationPlaceholder(stmt, "UNTIL", JumpAllocator.JumpType.CONDITIONAL);
    _builder.append(_destinationPlaceholder_3);
    _builder.newLineIfNotEmpty();
    {
      String _destinationPlaceholder_4 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
      boolean _tripleNotEquals_1 = (_destinationPlaceholder_4 != null);
      if (_tripleNotEquals_1) {
        String _destinationPlaceholder_5 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder.append(_destinationPlaceholder_5);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  protected CharSequence _compile(final ForStatement stmt) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Statement _init = stmt.getInit();
      boolean _tripleNotEquals = (_init != null);
      if (_tripleNotEquals) {
        CharSequence _compile = this.compile(stmt.getInit());
        _builder.append(_compile);
        _builder.newLineIfNotEmpty();
      }
    }
    String _destinationPlaceholder = this.jumps.getDestinationPlaceholder(stmt, "FOR", JumpAllocator.JumpType.UNCONDITIONAL);
    _builder.append(_destinationPlaceholder);
    _builder.newLineIfNotEmpty();
    CharSequence _compile_1 = this.compile(stmt.getCondition());
    _builder.append(_compile_1);
    _builder.newLineIfNotEmpty();
    String _sourcePlaceholder = this.jumps.getSourcePlaceholder(stmt, "FOR", JumpAllocator.JumpType.CONDITIONAL);
    _builder.append(_sourcePlaceholder);
    _builder.newLineIfNotEmpty();
    CharSequence _compile_2 = this.compile(stmt.getBody());
    _builder.append(_compile_2);
    _builder.newLineIfNotEmpty();
    {
      String _destinationPlaceholder_1 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
      boolean _tripleNotEquals_1 = (_destinationPlaceholder_1 != null);
      if (_tripleNotEquals_1) {
        String _destinationPlaceholder_2 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
        _builder.append(_destinationPlaceholder_2);
        _builder.newLineIfNotEmpty();
      }
    }
    {
      String _destinationPlaceholder_3 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
      boolean _tripleNotEquals_2 = (_destinationPlaceholder_3 != null);
      if (_tripleNotEquals_2) {
        String _destinationPlaceholder_4 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder.append(_destinationPlaceholder_4);
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Statement _end = stmt.getEnd();
      boolean _tripleNotEquals_3 = (_end != null);
      if (_tripleNotEquals_3) {
        CharSequence _compile_3 = this.compile(stmt.getEnd());
        _builder.append(_compile_3);
        _builder.newLineIfNotEmpty();
      }
    }
    String _sourcePlaceholder_1 = this.jumps.getSourcePlaceholder(stmt, "FOR", JumpAllocator.JumpType.UNCONDITIONAL);
    _builder.append(_sourcePlaceholder_1);
    _builder.newLineIfNotEmpty();
    String _destinationPlaceholder_5 = this.jumps.getDestinationPlaceholder(stmt, "FOR", JumpAllocator.JumpType.CONDITIONAL);
    _builder.append(_destinationPlaceholder_5);
    _builder.newLineIfNotEmpty();
    {
      String _destinationPlaceholder_6 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
      boolean _tripleNotEquals_4 = (_destinationPlaceholder_6 != null);
      if (_tripleNotEquals_4) {
        String _destinationPlaceholder_7 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder.append(_destinationPlaceholder_7);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  protected CharSequence _compile(final WhileStatement stmt) {
    StringConcatenation _builder = new StringConcatenation();
    String _destinationPlaceholder = this.jumps.getDestinationPlaceholder(stmt, "WHILE", JumpAllocator.JumpType.CONDITIONAL);
    _builder.append(_destinationPlaceholder);
    _builder.newLineIfNotEmpty();
    CharSequence _compile = this.compile(stmt.getBody());
    _builder.append(_compile);
    _builder.newLineIfNotEmpty();
    {
      String _destinationPlaceholder_1 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
      boolean _tripleNotEquals = (_destinationPlaceholder_1 != null);
      if (_tripleNotEquals) {
        String _destinationPlaceholder_2 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
        _builder.append(_destinationPlaceholder_2);
        _builder.newLineIfNotEmpty();
      }
    }
    {
      String _destinationPlaceholder_3 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
      boolean _tripleNotEquals_1 = (_destinationPlaceholder_3 != null);
      if (_tripleNotEquals_1) {
        String _destinationPlaceholder_4 = this.jumps.getDestinationPlaceholder(stmt, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder.append(_destinationPlaceholder_4);
        _builder.newLineIfNotEmpty();
      }
    }
    CharSequence _compile_1 = this.compile(stmt.getCondition());
    _builder.append(_compile_1);
    _builder.newLineIfNotEmpty();
    String _sourcePlaceholder = this.jumps.getSourcePlaceholder(stmt, "WHILE", JumpAllocator.JumpType.CONDITIONAL);
    _builder.append(_sourcePlaceholder);
    _builder.newLineIfNotEmpty();
    {
      String _destinationPlaceholder_5 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.CONDITIONAL);
      boolean _tripleNotEquals_2 = (_destinationPlaceholder_5 != null);
      if (_tripleNotEquals_2) {
        String _destinationPlaceholder_6 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.CONDITIONAL);
        _builder.append(_destinationPlaceholder_6);
        _builder.newLineIfNotEmpty();
      }
    }
    {
      String _destinationPlaceholder_7 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
      boolean _tripleNotEquals_3 = (_destinationPlaceholder_7 != null);
      if (_tripleNotEquals_3) {
        String _destinationPlaceholder_8 = this.jumps.getDestinationPlaceholder(stmt, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder.append(_destinationPlaceholder_8);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }

  protected CharSequence _compile(final ContinueStatement stmt) {
    CharSequence _xblockexpression = null;
    {
      final LoopStatement loop = Extensions.getContainingLoop(stmt);
      CharSequence _xifexpression = null;
      boolean _isOptimizable = Extensions.isOptimizable(stmt);
      if (_isOptimizable) {
        StringConcatenation _builder = new StringConcatenation();
        String _sourcePlaceholder = this.jumps.getSourcePlaceholder(loop, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
        _builder.append(_sourcePlaceholder);
        _builder.newLineIfNotEmpty();
        _xifexpression = _builder;
      } else {
        CharSequence _switchResult = null;
        boolean _matched = false;
        if (loop instanceof UntilStatement) {
          _matched=true;
          StringConcatenation _builder_1 = new StringConcatenation();
          String _sourcePlaceholder_1 = this.jumps.getSourcePlaceholder(loop, "UNTIL", JumpAllocator.JumpType.UNCONDITIONAL);
          _builder_1.append(_sourcePlaceholder_1);
          _builder_1.newLineIfNotEmpty();
          _switchResult = _builder_1;
        }
        if (!_matched) {
          if (loop instanceof ForStatement) {
            _matched=true;
            CharSequence _xifexpression_1 = null;
            Statement _end = ((ForStatement)loop).getEnd();
            boolean _tripleNotEquals = (_end != null);
            if (_tripleNotEquals) {
              StringConcatenation _builder_1 = new StringConcatenation();
              String _sourcePlaceholder_1 = this.jumps.getSourcePlaceholder(loop, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
              _builder_1.append(_sourcePlaceholder_1);
              _builder_1.newLineIfNotEmpty();
              _xifexpression_1 = _builder_1;
            } else {
              StringConcatenation _builder_2 = new StringConcatenation();
              String _sourcePlaceholder_2 = this.jumps.getSourcePlaceholder(loop, "FOR", JumpAllocator.JumpType.UNCONDITIONAL);
              _builder_2.append(_sourcePlaceholder_2);
              _builder_2.newLineIfNotEmpty();
              _xifexpression_1 = _builder_2;
            }
            _switchResult = _xifexpression_1;
          }
        }
        if (!_matched) {
          if (loop instanceof WhileStatement) {
            _matched=true;
            StringConcatenation _builder_1 = new StringConcatenation();
            String _sourcePlaceholder_1 = this.jumps.getSourcePlaceholder(loop, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
            _builder_1.append(_sourcePlaceholder_1);
            _builder_1.newLineIfNotEmpty();
            _switchResult = _builder_1;
          }
        }
        if (!_matched) {
          throw new IllegalArgumentException();
        }
        _xifexpression = _switchResult;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }

  protected CharSequence _compile(final BreakStatement stmt) {
    CharSequence _xblockexpression = null;
    {
      final LoopStatement loop = Extensions.getContainingLoop(stmt);
      CharSequence _xifexpression = null;
      boolean _isOptimizable = Extensions.isOptimizable(stmt);
      if (_isOptimizable) {
        CharSequence _switchResult = null;
        boolean _matched = false;
        if (loop instanceof UntilStatement) {
          _matched=true;
          StringConcatenation _builder = new StringConcatenation();
          String _sourcePlaceholder = this.jumps.getSourcePlaceholder(loop, "UNTIL", JumpAllocator.JumpType.CONDITIONAL);
          _builder.append(_sourcePlaceholder);
          _builder.newLineIfNotEmpty();
          _switchResult = _builder;
        }
        if (!_matched) {
          if (loop instanceof ForStatement) {
            _matched=true;
            StringConcatenation _builder = new StringConcatenation();
            String _sourcePlaceholder = this.jumps.getSourcePlaceholder(loop, "FOR", JumpAllocator.JumpType.CONDITIONAL);
            _builder.append(_sourcePlaceholder);
            _builder.newLineIfNotEmpty();
            _switchResult = _builder;
          }
        }
        if (!_matched) {
          if (loop instanceof WhileStatement) {
            _matched=true;
            StringConcatenation _builder = new StringConcatenation();
            String _sourcePlaceholder = this.jumps.getSourcePlaceholder(loop, "BREAK", JumpAllocator.JumpType.CONDITIONAL);
            _builder.append(_sourcePlaceholder);
            _builder.newLineIfNotEmpty();
            _switchResult = _builder;
          }
        }
        if (!_matched) {
          throw new IllegalArgumentException();
        }
        _xifexpression = _switchResult;
      } else {
        StringConcatenation _builder = new StringConcatenation();
        String _sourcePlaceholder = this.jumps.getSourcePlaceholder(loop, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder.append(_sourcePlaceholder);
        _builder.newLineIfNotEmpty();
        _xifexpression = _builder;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }

  protected CharSequence _compile(final IfStatement stmt) {
    CharSequence _xifexpression = null;
    boolean _isOptimizable = Extensions.isOptimizable(stmt);
    if (_isOptimizable) {
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _compile = this.compile(stmt.getCondition());
      _builder.append(_compile);
      _builder.newLineIfNotEmpty();
      CharSequence _compile_1 = this.compile(Extensions.getFirstThenStatement(stmt));
      _builder.append(_compile_1);
      _builder.newLineIfNotEmpty();
      {
        Statements _elseClause = stmt.getElseClause();
        boolean _tripleNotEquals = (_elseClause != null);
        if (_tripleNotEquals) {
          CharSequence _compile_2 = this.compile(stmt.getElseClause());
          _builder.append(_compile_2);
          _builder.newLineIfNotEmpty();
        }
      }
      _xifexpression = _builder;
    } else {
      CharSequence _xifexpression_1 = null;
      boolean _isElseEmpty = Extensions.isElseEmpty(stmt);
      if (_isElseEmpty) {
        StringConcatenation _builder_1 = new StringConcatenation();
        CharSequence _compile_3 = this.compile(stmt.getCondition());
        _builder_1.append(_compile_3);
        _builder_1.newLineIfNotEmpty();
        String _sourcePlaceholder = this.jumps.getSourcePlaceholder(stmt, "IF", JumpAllocator.JumpType.CONDITIONAL);
        _builder_1.append(_sourcePlaceholder);
        _builder_1.newLineIfNotEmpty();
        String _sourcePlaceholder_1 = this.jumps.getSourcePlaceholder(stmt, "IF", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder_1.append(_sourcePlaceholder_1);
        _builder_1.newLineIfNotEmpty();
        String _destinationPlaceholder = this.jumps.getDestinationPlaceholder(stmt, "IF", JumpAllocator.JumpType.CONDITIONAL);
        _builder_1.append(_destinationPlaceholder);
        _builder_1.newLineIfNotEmpty();
        CharSequence _compile_4 = this.compile(stmt.getThenClause());
        _builder_1.append(_compile_4);
        _builder_1.newLineIfNotEmpty();
        String _destinationPlaceholder_1 = this.jumps.getDestinationPlaceholder(stmt, "IF", JumpAllocator.JumpType.UNCONDITIONAL);
        _builder_1.append(_destinationPlaceholder_1);
        _builder_1.newLineIfNotEmpty();
        _xifexpression_1 = _builder_1;
      } else {
        CharSequence _xifexpression_2 = null;
        boolean _isThenEmpty = Extensions.isThenEmpty(stmt);
        if (_isThenEmpty) {
          StringConcatenation _builder_2 = new StringConcatenation();
          CharSequence _compile_5 = this.compile(stmt.getCondition());
          _builder_2.append(_compile_5);
          _builder_2.newLineIfNotEmpty();
          String _sourcePlaceholder_2 = this.jumps.getSourcePlaceholder(stmt, "IF", JumpAllocator.JumpType.CONDITIONAL);
          _builder_2.append(_sourcePlaceholder_2);
          _builder_2.newLineIfNotEmpty();
          CharSequence _compile_6 = this.compile(stmt.getElseClause());
          _builder_2.append(_compile_6);
          _builder_2.newLineIfNotEmpty();
          String _destinationPlaceholder_2 = this.jumps.getDestinationPlaceholder(stmt, "IF", JumpAllocator.JumpType.CONDITIONAL);
          _builder_2.append(_destinationPlaceholder_2);
          _builder_2.newLineIfNotEmpty();
          _xifexpression_2 = _builder_2;
        } else {
          StringConcatenation _builder_3 = new StringConcatenation();
          CharSequence _compile_7 = this.compile(stmt.getCondition());
          _builder_3.append(_compile_7);
          _builder_3.newLineIfNotEmpty();
          String _sourcePlaceholder_3 = this.jumps.getSourcePlaceholder(stmt, "IF", JumpAllocator.JumpType.CONDITIONAL);
          _builder_3.append(_sourcePlaceholder_3);
          _builder_3.newLineIfNotEmpty();
          CharSequence _compile_8 = this.compile(stmt.getElseClause());
          _builder_3.append(_compile_8);
          _builder_3.newLineIfNotEmpty();
          String _sourcePlaceholder_4 = this.jumps.getSourcePlaceholder(stmt, "IF", JumpAllocator.JumpType.UNCONDITIONAL);
          _builder_3.append(_sourcePlaceholder_4);
          _builder_3.newLineIfNotEmpty();
          String _destinationPlaceholder_3 = this.jumps.getDestinationPlaceholder(stmt, "IF", JumpAllocator.JumpType.CONDITIONAL);
          _builder_3.append(_destinationPlaceholder_3);
          _builder_3.newLineIfNotEmpty();
          CharSequence _compile_9 = this.compile(stmt.getThenClause());
          _builder_3.append(_compile_9);
          _builder_3.newLineIfNotEmpty();
          String _destinationPlaceholder_4 = this.jumps.getDestinationPlaceholder(stmt, "IF", JumpAllocator.JumpType.UNCONDITIONAL);
          _builder_3.append(_destinationPlaceholder_4);
          _builder_3.newLineIfNotEmpty();
          _xifexpression_2 = _builder_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }

  /**
   * The below should cover all and only possible ways to load A register from language before performing an operation.
   */
  protected CharSequence _loadA(final Constant c) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _loadM = this.loadM(c);
    _builder.append(_loadM);
    _builder.newLineIfNotEmpty();
    _builder.append(L101Generator.DOWN);
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected CharSequence _loadA(final Input in) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("S");
    _builder.newLine();
    _builder.append(L101Generator.DOWN);
    _builder.newLineIfNotEmpty();
    return _builder;
  }

  protected CharSequence _loadA(final VariableAccess v) {
    String _register = Extensions.toRegister(v);
    return (_register + L101Generator.DOWN);
  }

  protected CharSequence _loadA(final Current c) {
    return "";
  }

  protected CharSequence _loadA(final Expression exp) {
    return this.compile(exp);
  }

  /**
   * The below should cover all and only possible ways to load M register from language before performing an operation.
   */
  protected CharSequence _loadM(final Constant c) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("A/");
    _builder.append(L101Generator.UP);
    _builder.newLineIfNotEmpty();
    String result = _builder.toString();
    String v = c.getValue();
    int _indexOf = v.indexOf(".");
    boolean _tripleEquals = (_indexOf == (-1));
    if (_tripleEquals) {
      String _v = v;
      v = (_v + ".");
    }
    String dot = null;
    String digit = null;
    String marker = null;
    for (int i = (v.length() - 1); (i >= 0); i--) {
      {
        char _charAt = v.charAt(i);
        char _charAt_1 = ".".charAt(0);
        boolean _equals = (_charAt == _charAt_1);
        if (_equals) {
          dot = "/";
          i--;
        } else {
          dot = "";
        }
        String _xifexpression = null;
        boolean _isNegative = c.isNegative();
        if (_isNegative) {
          String _xifexpression_1 = null;
          if ((i == 0)) {
            _xifexpression_1 = "E";
          } else {
            _xifexpression_1 = "F";
          }
          _xifexpression = _xifexpression_1;
        } else {
          String _xifexpression_2 = null;
          if ((i == 0)) {
            _xifexpression_2 = "D";
          } else {
            _xifexpression_2 = "R";
          }
          _xifexpression = _xifexpression_2;
        }
        marker = _xifexpression;
        char _charAt_2 = v.charAt(i);
        int _minus = (_charAt_2 - '0');
        String _get = L101Generator.DIGITS[_minus];
        String _plus = (_get + "\n");
        digit = _plus;
        String _result = result;
        result = (_result + ((marker + dot) + digit));
      }
    }
    return result;
  }

  protected CharSequence _loadM(final Input in) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("S");
    _builder.newLine();
    return _builder;
  }

  protected CharSequence _loadM(final Current c) {
    return "A";
  }

  protected CharSequence _loadM(final VariableAccess v) {
    return Extensions.toRegister(v.getStore());
  }

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
  private static void assignJumps(final Statements stmts, final JumpAllocator jumps) {
    EList<Statement> _statements = stmts.getStatements();
    for (final Statement stmt : _statements) {
      boolean _matched = false;
      if (stmt instanceof Label) {
        _matched=true;
        String _target = ((Label)stmt).getTarget();
        boolean _tripleNotEquals = (_target != null);
        if (_tripleNotEquals) {
          jumps.allocateJumpPlaceholder(stmt, "GOTO", ((Label)stmt).getTarget());
        }
      }
      if (!_matched) {
        if (stmt instanceof GotoStatement) {
          _matched=true;
          boolean _isOptimizable = Extensions.isOptimizable(stmt);
          if (_isOptimizable) {
            jumps.allocateJumpPlaceholder(((GotoStatement)stmt).getLabel(), "GOTO", JumpAllocator.JumpType.CONDITIONAL);
          } else {
            String _target = ((GotoStatement)stmt).getLabel().getTarget();
            boolean _tripleEquals = (_target == null);
            if (_tripleEquals) {
              jumps.allocateJumpPlaceholder(((GotoStatement)stmt).getLabel(), "GOTO", JumpAllocator.JumpType.UNCONDITIONAL);
            }
          }
        }
      }
      if (!_matched) {
        if (stmt instanceof UntilStatement) {
          _matched=true;
          jumps.allocateJumpPlaceholder(stmt, "UNTIL", JumpAllocator.JumpType.UNCONDITIONAL);
          jumps.allocateJumpPlaceholder(stmt, "UNTIL", JumpAllocator.JumpType.CONDITIONAL);
          L101Generator.assignJumps(((UntilStatement)stmt).getBody(), jumps);
        }
      }
      if (!_matched) {
        if (stmt instanceof ForStatement) {
          _matched=true;
          jumps.allocateJumpPlaceholder(stmt, "FOR", JumpAllocator.JumpType.UNCONDITIONAL);
          jumps.allocateJumpPlaceholder(stmt, "FOR", JumpAllocator.JumpType.CONDITIONAL);
          L101Generator.assignJumps(((ForStatement)stmt).getBody(), jumps);
        }
      }
      if (!_matched) {
        if (stmt instanceof WhileStatement) {
          _matched=true;
          jumps.allocateJumpPlaceholder(stmt, "WHILE", JumpAllocator.JumpType.CONDITIONAL);
          L101Generator.assignJumps(((WhileStatement)stmt).getBody(), jumps);
        }
      }
      if (!_matched) {
        if (stmt instanceof ContinueStatement) {
          _matched=true;
          final LoopStatement loop = Extensions.getContainingLoop(stmt);
          boolean _isOptimizable = Extensions.isOptimizable(stmt);
          if (_isOptimizable) {
            jumps.allocateJumpPlaceholder(loop, "CONTINUE", JumpAllocator.JumpType.CONDITIONAL);
          } else {
            boolean _matched_1 = false;
            if (loop instanceof ForStatement) {
              _matched_1=true;
              Statement _end = ((ForStatement)loop).getEnd();
              boolean _tripleNotEquals = (_end != null);
              if (_tripleNotEquals) {
                jumps.allocateJumpPlaceholder(loop, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
              }
            }
            if (!_matched_1) {
              if (loop instanceof WhileStatement) {
                _matched_1=true;
                jumps.allocateJumpPlaceholder(loop, "CONTINUE", JumpAllocator.JumpType.UNCONDITIONAL);
              }
            }
          }
        }
      }
      if (!_matched) {
        if (stmt instanceof BreakStatement) {
          _matched=true;
          final LoopStatement loop = Extensions.getContainingLoop(stmt);
          boolean _isOptimizable = Extensions.isOptimizable(stmt);
          if (_isOptimizable) {
            if ((loop instanceof WhileStatement)) {
              jumps.allocateJumpPlaceholder(loop, "BREAK", JumpAllocator.JumpType.CONDITIONAL);
            }
          } else {
            jumps.allocateJumpPlaceholder(loop, "BREAK", JumpAllocator.JumpType.UNCONDITIONAL);
          }
        }
      }
      if (!_matched) {
        if (stmt instanceof IfStatement) {
          _matched=true;
          boolean _isOptimizable = Extensions.isOptimizable(stmt);
          boolean _not = (!_isOptimizable);
          if (_not) {
            jumps.allocateJumpPlaceholder(stmt, "IF", JumpAllocator.JumpType.CONDITIONAL);
            boolean _isThenEmpty = Extensions.isThenEmpty(((IfStatement)stmt));
            boolean _not_1 = (!_isThenEmpty);
            if (_not_1) {
              jumps.allocateJumpPlaceholder(stmt, "IF", JumpAllocator.JumpType.UNCONDITIONAL);
            }
          }
          Statements _thenClause = ((IfStatement)stmt).getThenClause();
          boolean _tripleNotEquals = (_thenClause != null);
          if (_tripleNotEquals) {
            L101Generator.assignJumps(((IfStatement)stmt).getThenClause(), jumps);
          }
          Statements _elseClause = ((IfStatement)stmt).getElseClause();
          boolean _tripleNotEquals_1 = (_elseClause != null);
          if (_tripleNotEquals_1) {
            L101Generator.assignJumps(((IfStatement)stmt).getElseClause(), jumps);
          }
        }
      }
    }
  }

  /**
   * Check variables being allocated and return the one that overlaps with the code
   * 
   * @param prg Source for the program being checked.
   * @paran codeLen Length of the compiled code for the program.
   * @return a reference to the variable that determines the smallest code size, or null if no variable is overlapping with the code.
   */
  public static Variable findOvrelappingVariable(final Program prg, final int codeLen) {
    if ((codeLen <= 48)) {
      return null;
    }
    Variable result = null;
    int maxSpace = 120;
    EList<VariableDeclaration> _varDeclarations = prg.getVarDeclarations();
    for (final VariableDeclaration vd : _varDeclarations) {
      EList<Variable> _vars = vd.getVars();
      for (final Variable v : _vars) {
        {
          int _switchResult = (int) 0;
          String _reg = v.getReg();
          if (_reg != null) {
            switch (_reg) {
              case "F":
                _switchResult = 48;
                break;
              case "F/":
                _switchResult = 59;
                break;
              case "E":
                _switchResult = 72;
                break;
              case "E/":
                _switchResult = 83;
                break;
              case "D":
                _switchResult = 96;
                break;
              case "D/":
                _switchResult = 107;
                break;
              default:
                _switchResult = 120;
                break;
            }
          } else {
            _switchResult = 120;
          }
          final int allowedLen = _switchResult;
          if ((allowedLen < maxSpace)) {
            maxSpace = allowedLen;
            result = v;
          }
        }
      }
    }
    if ((maxSpace >= codeLen)) {
      return null;
    }
    return result;
  }

  @XbaseGenerated
  public CharSequence compile(final EObject stmt) {
    if (stmt instanceof ForStatement) {
      return _compile((ForStatement)stmt);
    } else if (stmt instanceof UntilStatement) {
      return _compile((UntilStatement)stmt);
    } else if (stmt instanceof WhileStatement) {
      return _compile((WhileStatement)stmt);
    } else if (stmt instanceof AdditiveOperator) {
      return _compile((AdditiveOperator)stmt);
    } else if (stmt instanceof BreakStatement) {
      return _compile((BreakStatement)stmt);
    } else if (stmt instanceof ContinueStatement) {
      return _compile((ContinueStatement)stmt);
    } else if (stmt instanceof GotoStatement) {
      return _compile((GotoStatement)stmt);
    } else if (stmt instanceof IfStatement) {
      return _compile((IfStatement)stmt);
    } else if (stmt instanceof MultiplicativeOperator) {
      return _compile((MultiplicativeOperator)stmt);
    } else if (stmt instanceof PrintStatement) {
      return _compile((PrintStatement)stmt);
    } else if (stmt instanceof StoreStatement) {
      return _compile((StoreStatement)stmt);
    } else if (stmt instanceof UnaryOperator) {
      return _compile((UnaryOperator)stmt);
    } else if (stmt instanceof AssignmentStatement) {
      return _compile((AssignmentStatement)stmt);
    } else if (stmt instanceof Label) {
      return _compile((Label)stmt);
    } else if (stmt instanceof Program) {
      return _compile((Program)stmt);
    } else if (stmt instanceof Statements) {
      return _compile((Statements)stmt);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(stmt).toString());
    }
  }

  @XbaseGenerated
  public CharSequence loadA(final Expression c) {
    if (c instanceof Constant) {
      return _loadA((Constant)c);
    } else if (c instanceof Current) {
      return _loadA((Current)c);
    } else if (c instanceof Input) {
      return _loadA((Input)c);
    } else if (c instanceof VariableAccess) {
      return _loadA((VariableAccess)c);
    } else if (c != null) {
      return _loadA(c);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(c).toString());
    }
  }

  @XbaseGenerated
  public CharSequence loadM(final Expression c) {
    if (c instanceof Constant) {
      return _loadM((Constant)c);
    } else if (c instanceof Current) {
      return _loadM((Current)c);
    } else if (c instanceof Input) {
      return _loadM((Input)c);
    } else if (c instanceof VariableAccess) {
      return _loadM((VariableAccess)c);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(c).toString());
    }
  }
}
