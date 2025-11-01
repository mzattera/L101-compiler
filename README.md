# L101-compiler

This is a BASIC-like language compiler for the [Olivetti Programma P101](https://en.wikipedia.org/wiki/Programma_101) (P101), the first real personal computer.

I created this a couple of years ago, mostly to learn Xtext.

I have decided to release this in the public now, in occasion of the 60th anniversary of Programma 101.

Using this compiler I have created two *new games* for the P101:

  * **Wump101** ([P101 code](https://github.com/mzattera/L101-compiler/blob/master/runtime-workbench/examples/src-gen/New%20Apps/Wump101.P101))
    is a remake of [Mugwump](https://en.wikipedia.org/wiki/Mugwump_(video_game)).
	Check the [L101 source](https://github.com/mzattera/L101-compiler/blob/master/runtime-workbench/examples/src/New%20Apps/Wump101.l101) for instructions how to play the game.
	
  * **Lunar Landing Game** ([P101 code](https://github.com/mzattera/L101-compiler/blob/master/runtime-workbench/examples/src-gen/New%20Apps/Lunar%20Landing%20Game.P101))
    is a remake of a game originally written in the FOCAL programming language by Jim Storer in 1969 for PDP-8. This is completed but still being tested (necessary approximations might impact gameplay).
	Check the [L101 source](https://github.com/mzattera/L101-compiler/blob/master/runtime-workbench/examples/src/New%20Apps/Wump101.l101) for details.

## Coding on the  P101

If you want to understand how the P101 works, you can find programmer's handbooks [here](http://www.claudiolarini.altervista.org/emul2.htm) and [here](http://www.microatena.it/scheda_libro.php?id=55#menu_tabs).

In addition, the [examples](https://github.com/mzattera/L101-compiler/tree/master/runtime-workbench/examples) folders contains a lot of code examples and tests.


## Installation

Unfortunately, currently you can only launch the project from within Eclipse (if anybody manages to export it as an Eclipse Product, please let me know).

You need Xtext 24.0 Eclipse plugin and Java 21 installed to run the project.

The ``eclipse`` folder is an Eclipse workbench with the entire project. Launch the application as an "Eclipse Application", create a Java project in the application workbench (you will be prompted to turn it into an Xtext project, say yes) and create a ``.l101`` file; it will be automatically compiled when you save it or when you clean the project (this is also a good way to recompile all examples).

The ``runtime-workbench`` folder contains an example workbench with many examples.


## Output

Currently, this application generates:

  1. ``.P101`` files: these are UTF-8 files containing the listing of the compiled P101 program.
                
  2. ``.666``  files: these are files for the [P101Emul emulator](http://www.claudiolarini.altervista.org/emul2.htm).
  
	  **At the moment, this is the best emulator I found under Windows.** It can run from a command prompt by launching ``P101EMUL64.EXE`` (for 64 bit Windows).
	  The emulator needs the card to be renamed as "P101CARD.nnn" where "nnn" is a 3-digit number 000-999 and put in the folder "SCHEDE".
	  Then under "Controllo" menu, click "Usa scheda magn." and enter the card number in the pop-up keypad to load it.
	  Use "GEN RESET" button when you start the emulator and any time the machine get jammed (red indicator) to reset the emulated P101.

      Unfortunately, this emulator was developed for older versions of DOS/Windows and I experience GUI issues running it under
      Windows 10 (sometimes one single keystroke on the emulated P101 keyboard generates multiple strokes in the emulator,
      which might cause issues when running a program); beside this, programs appear to run fine in this emulator.

  3. ``.pmc``  files: these are card files for the [P101JavaPlus emulator](http://www.museotecnologicamente.it/collezione/programma-101/).
    The files seem to load correctly in the emulator using "Card Choice" button but I encountered issues running them.
    I think these issues come from the emulator, not from the compiled code that appears correct.
    This is probably due to the fact that constants in program are not supported.				 
				 
## The L101 language

Due to P101 limitations, L101 is a very simple language, between BASIC and assembler.

All language keywords are case-sensitive and uppercase. Notice some keywords are single-letters (e.g. ``E``, ``Y``, etc).
All identifiers (variable names and labels) must start with a lowercase letter, followed by zero or more upper- or lower-case letters, numbers or underscore.


### Comments

```
// This is an single-line / in-line comment

/*
This is a multi-line
comment.
*/
```

### Variables

All L101 programs start with variable declarations, followed by program statements.

P101 has 5 numeric registries (B, C, D, E, F) that can be used to store data. Each one can be used either as a 22 digits register (including sign and comma separator) or treated as two 11 digits registers.

L101 allows you to declare variables that will be placed in these registers using ``LONG..IN`` (for using the full register) and ``SHORT..IN`` (for using half register) keywords.

Notice how each line ends with a ``;``.

```
// Allocates a 22 digits variable e and places it in the E register.
LONG e IN E;

// Allocates variables x and y and puts them in the F register.
// These are 11 digit variables, y will be in "left" part of the register.
SHORT x IN F, y IN F/;
```

The P101 "accumulator" register A can be accessed with keyword ``A``.


### Expressions

The most basic expressions are:

  * ``A`` which indicates current value of the "accumulator" (P101 A register).
  
  * ``INPUT`` which instructs P101 to stop and ask for an input value, which will be used as the expression value.
  
  * Numeric constants (numbers with no leading zeros, with an optional decimal separator and minus sign, e.g. 1, 2.0, -3.14).
    	
  * A variable name.
  
More complex expressions can be created by using brackets and the below operators.
**Operators are described in decreasing order of precedence** (with unary operators being the one with highest priority).


#### Unary Operators (functions)

Unary operators operate on a single expression.

  * ``SQRT`` extract square root of given expression (e.g. ``SQRT 4`` returns 2).    
  * ``SQ`` squares expression (e.g. ``SQ(x)`` returns x times x);
  * ``ABS`` takes absolute value for the expression.
  * ``NEG`` negates one expression (= -expression).
  * ``INT`` takes integer part of an expression (everything left of the decimal separator).
    This uses the undocumented P101 instruction ``/↑``.
	
  * ``DEC`` takes decimal part of an expression (everything right of the decimal separator).
    
	**Note:** This uses the P101 instruction ``/↕``;
	I could not find in any P101 manual a clear description of how this works for negative numbers. 
	In the P203 manual the same instruction is supposed to preserve sign (so DEC(-1.2)=-0.2).
	However, P101Emul emulator always makes this positive.
	
  * ``INV`` calculate the inverse (reciprocal) of one expression (= 1/expression).

#### Binary Operators

Binary operators work on two arguments.

Multiplicative operators have the highest priority and are left associative.

  * ``*`` multiplication.
  * ``/`` division.
  * ``%`` modulo (reminder); it executes x/y then the "exact reminder" (content of R register) is moved to A.

Additive operators have less priority and are also left associative.

  * ``+`` sum.
  * ``-`` subtraction.
    
Because of the P101 hardware limitations, there are also limitations on the syntax of the binary operators.
In particular, the right operand can be only a constant, a variable, or ``INPUT``. This effects best way to write expressions.

``a + b * c`` is interpreted as ``a + (b * c)`` due to operator precedence and will cause a compilation error since ``(b*c)``
is not a simple variable. However, the expression ``b * c + a`` will compile fine since it will be interpreted as
``(b * c) + a`` where each right operand is a simple variable.

More complex expressions like ``a*b + b*c`` must therefore broken down in simpler expressions using temporary storage, something like:

```
temp = a*b
result = b*c + temp
```

The compiler leaves the developer the decision about what is the most efficient way of reorganizing expressions.

### Program statements

After variables have been declared, the program code is provided as a list of statements.
  
#### Assignments

There is no point in calculating an expression if you cannot store its result somewhere.

``=`` _expression_ ``;``

Calculates the value of _expression_ and stores it into the A register.

```
=1+2;
```

will put the value 3 in A.

L101 also has compound assignment operators, they always target the A register:

(``+=`` | ``-=`` | ``/=`` | ``*=`` | ``%=``) (_variable_ | _constant_ | ``A`` | ``INPUT``) ``;``

Similarly to other programming languages, ``+=5`` performs A=A+5 (which is equivalent to L101 code ``=A+5``).
Notice that the argument cannot be any expression; it must be a variable, a constant, the accumulator value or ``INPUT`` indicating the machine must stop and wait for user input.

_variable_ ``<->`` _expression_ ``;``

will calculate the value of _expression_ and store it into _variable_. In addition, current value of _variable_ will be transferred into A register.


#### Store

Finally, you can store a constant value or the user input into a variable like this:

(_constant_ | ``INPUT``) ``->`` _variable_ ``;``

Below some examples:

```
SHORT x IN B/;

// Put 42 in x
42 -> x;

// A=3
= 3;

// A=A+x
+= x;

// A=A times the number user enters
*= INPUT;

// Calculates x = x^2+5 and stores it in x.
// Previous value of x (42) is stored in A.
x <-> SQ(x) * 5;  
```

#### PRINT

``PRINT`` (_constant_ | _variable_ | ``A`` | ``NL``)* ``;``

will print a given constant value, the value of a given variable, the content of the A register or an empty line respectively.
Multiple values to print can be separated with commas. ``PRINT`` alone just prints an empty line.

Below, valid examples of ``PRINT`` usage.

```
PRINT 3, x;
PRINT A, 4;
PRINT;
```

#### Labels and GOTO

L101 allows unconditional jumps by using the ``GOTO`` statement:

``GOTO`` _label_ ``;``

will cause execution to jump to the position in the code indicated by _label_. To define a label you simple enter its name followed by column:

_label_ ``:``

The P101 has 4 keys labeled "V"-"Z" that, when pressed, will cause the P101 to execute code from a given position (this is the standard way to start a program stored in memory).
Labels can be associated with these key like this:

_label_  ``ON``  (``V`` | ``W`` | ``Y`` | ``Z``) ``:``

The below code runs when user presses "V" and keeps asking for numbers to square, until the user presses "Z";

```
SHORT x IN B/;
 
start ON V: 	// When user presses "V" key, execution starts from this point
	
	INPUT ->x; 	// Reads user input and stores it in x
	=SQ(x);		// A = x squared
	PRINT A;	// Prints result
	
	GOTO start;	// Next number
	
stop ON Z:		// If user presses "Z", execution jumps here and program ends
```

#### IF...THEN...ELSE

In L101, the IF statement has the following structure:

``IF`` _assigment_ ``THEN`` _then_statements_ ``ELSE`` _else_statements_ ``END``

_assignment_ is a simple (``=``), compound (e.g. ``+=``) or swap (``<->``) assignment that is executed first;
if after the assignment A>0 (=true), then _then_statements_ are executed, otherwise (if A<=0) _else_statements_ are executed instead.

Either ``THEN`` or ``ELSE`` can be omitted.

Below code print numbers 4..3..2..1..42

```
start ON V:

	=5;				// A = 5;
	
loop:	
	IF -=1 THEN		// A = A-1; if A>0 print it and loop
		PRINT A;
		GOTO loop;
	ELSE
		PRINT 42;	// else print 42 and quit
	END
```

#### Loops

``UNTIL`` _assignment_ ``DO`` _statements_ ``END``

Similarly to other languages, the ``UNITL`` loop executes the _assigment_ then, if A<=0 (=false) executes _statements_ repeating the loop until the _assignment_ puts a value >0 in A.

```
// Prints numbers -3..-2..0

start ON V:

	= -3;			// A = -3	
	UNTIL =A DO		// If A <= 0 then do...
		PRINT A;
		+= 1;		// A = A+1
	END
```

``DO`` _statements_ ``WHILE`` _assignment_ ``END``

``DO..WHILE`` statement executes _statements_, then executes the _assigment_ and loops if A>0 (=true).

```
// Prints numbers 3..2..1

start ON V:

	=3;			// A = 3	
	DO		
		PRINT A;
	WHILE -= 1 END	// A = A-1, then if A > 0 loop
```	

``FOR`` _initialisation_ ``;`` _assignment_ ``;`` _loop_end_ ``DO`` _statements_ ``END``

``FOR`` loop is a concise way of writing:

```
<initialisation>
UNTIL <assignment>
	<statements>
	<loop_end>
END
```

_initialisation_ and _loop_end_ can be an assignment or a simple statement like ``GOTO`` or ``PRINT``.

**Note** that the loop is executed if _assigment_ resulted in A<=0 (=false); this is the **opposite** of how FOR loops behave for example in C or Java.

```
// Prints squares of numbers from 1 to 5.

SHORT i IN B/;

start ON V:
	FOR 1->i; =i-5; i<->i+1 DO // i=1, if i-5<=0 (-> i<=5) do the loop...  
		=SQ(i);
		PRINT A;				// print x squared, i<->i+1 is executed before END
	END 
```	

Like in many other languages, ``BREAK`` can be used to jump out of current loop instruction, while ``CONTINUE`` will go to the end of the loop.


## Tips and Tricks

Because of P101 limitations, you need to write L101 code in the most efficient way, this means you need to be aware of some details about how L101 works "under the hood".


### Constants and Variables

Storing constants in code takes up a lot of code space, so typically P101 code requires user to manually enter them instead.
Also, if you use a constant value over and over (e.g. in different formulas), it is better to have it stored in a variable, instead of writing it in code each time. 

P101 registers F, E, and D (in this order) are also used to store code if they are not used for variables; when split, right part of each register is used for code earlier then left part.
Therefore, it is advised you allocate variables in B and C first, then allocate "left" part of F followed by its "right" part, and so on for E and D.

Finally, keep in mind operations and checks for true/false conditions all use the A register. You should therefore try to keep intermediate results in the A register as much as you can.

For example:

```
// Prints numbers 3..2..1

SHORT x IN B/;

start ON V:

	3 ->x;			// x = 3	
	DO		
		PRINT x;
		x<->x-1;	// x = x-1
	WHILE =x END	// if x > 0 loop
```	

is much better written as:

```
// Prints numbers 3..2..1

start ON V:

	=3;				// A = 3	
	DO		
		PRINT A;
	WHILE -= 1 END	// A = A-1, then if A > 0 loop
```	
 
### Expressions

If A is not 0, the instruction ``=1;`` which would be compiled as 3 P101 instructions:
	
```			
A/↑
D/↓
↓
```

this is better written as `/=A;` (A = A/A) which compiles as a single P101 instruction.

```
A÷
```

Similarly, to have the value 2 stored in A, if A is not 0, use below L101 code:

```
/=A;
+=A;
```
	
Because the compiler cannot be sure about the value of A, these optimizations are not done automatically.



### Labels, Jumps, and Loops

P101 has a fixed number of jump locations it can use for unconditional (e.g. ``GOTO``) or conditional (e.g. ``IF..THEN..ELSE``) jumps.

The L101 compiler tries to optimize the usage of jump locations, but there are some approaches you can use to help the compiler.

When  _statements_ is **not** a single ``GOTO``, ``BREAK`` or ``CONTINUE`` instruction, the format:

```
IF _assignment_ THEN
	_statements_
END
```

allocates one jump more, needs one instruction more, and is slower than writing:

```
IF _NOT_assignment_ ELSE
	_statements_
END
```

Below an example of how two statements are compiled:

```
IF =x THEN PRINT A; END

	D↓
	/V	   if D>0 goto A/V 	-> Notice nothing is printed if D=0
	CV     else goto BV
	A/V
	A◇     print A
	BV

IF =x ELSE PRINT A; END

	D↓
	/V     if D>0 goto A/V 	-> Notice prints 0 if D=0
	A◇     print A
	A/V
```	
	
### Automatic Optimization

L101 compiler performs some optimizations automatically.

L101 code ``=0``  is automatically optimized as P101 code:

```
A- 
```

instead of:
	
```	
A/↑
D/S
↓
```
	
Similarly, ``NEG(y)``, where y is a single variable, is optimized as P101 code:

```
A-
y-
```

Finally, L101 code ``=A*2`` or ``*=2`` is automatically optimized as P101 code:

```
A+ 
```

instead of:

```
A/↑
D/↑
x
```