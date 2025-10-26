# L101-compiler

This is a BASIC-like language compiler for the Olivetti Programma P101 (P101).

I created this a couple of years ago, mostly to learn Xtext.

I have decided to release this in the public now, in occasion of the 60th anniversary of Programma 101.


## Coding on the  P101

If you want to understand how the P101 works, you can find programmer's handbooks [here](http://www.claudiolarini.altervista.org/emul2.htm) and [here](http://www.microatena.it/scheda_libro.php?id=55#menu_tabs).


## Installation

Unfortunately, at the moment, you can only launch the project from within Eclipse (if anybody manages to export it as an Eclipse Product, please let me know).

You need Xtext 24.0 Eclipse plugin and Java 21 installed to run the project.

Launch the application as an "Eclipse Application", create a Java project in the application workbench (you will be prompted to turn it into an Xtext project, say yes) and create a .l101 file; it will be automatically compiled when you save it or when you clean the project (this is a good way to recompile all examples).

The runtime-workbench contains an example workbench with many examples.


## Output

Currently, this application generates:

  1. .P101 files: these are UTF-8 files containing the listing of the compiled P101 program.
                
  2. .666  files: these are files for the [P101Emul emulator](http://www.claudiolarini.altervista.org/emul2.htm).
    **At the moment, this is the best emulator I found under Windows.** It can run from a command prompt by launching ``P101EMUL64.EXE`` (for 64 bit Windows).
	The emulator needs the card to be renamed as "P101CARD.nnn" where "nnn" is a 3-digit number 000-999 and put in the folder "SCHEDE".
	Then under "Controllo" menu, click "Usa scheda magn." and enter the card number in the pop-up keypad to load it.
	Use "GEN RESET" button when you start the emulator and any time the machine get jammed (red indicator) to reset the emulated P101.

    Unfortunately, this emulator was developed for older versions of DOS/Windows and I experience GUI issues running it under
    Windows 10 (sometimes one single keystroke on the emulated P101 keyboard generates multiple strokes in the emulator,
    which might cause issues when running a program); beside this, programs appear to run fine in this emulator.

  3. .pmc  files: these are card files for the [P101JavaPlus emulator](http://www.museotecnologicamente.it/collezione/programma-101/).
    The files seem to load correctly in the emulator using "Card Choice" button but I encountered issues running them.
    I think these issues come from the emulator, not from the compiled code that appears correct.
    This is probably due to the fact that constants in program are not supported.				 
				 
## The L101 language

Due to P101 limitations, L101 is a very simple language, between BASIC and assembler.

All language keywords are case-sensitive. Notice some keywords are single-letters (e.g. ``E``, ``Y``, etc).

Any L101 program starts with variable declarations followed by program statements.

### Comments

```
// This is an in-line Comments

/*
This is a multi-line
comment.
*/
```

### Variables

P101 has 5 numeric registries (B, C, D, E, F) that can be used to store data, each one can be used either as a 22 digits register (including sign and comma separator) or treated as two 11 digits registers.

L101 allows you to declare variables that will be placed in these registers using ``LONG`` (for using the full register) and ``SHORT`` (for using half register) keywords.

Notice how each line ends with a ``;``.

```
// Allocates a 22 digits variable e and places it in the E register.
LONG e IN E;

// Allocates variables x and y and put them in the F register.
// These are 11 digit variables, y will be in "left" part of the register.
SHORT x IN F, y IN F/;
```

Registers F, E, and D in this order are also used to store code, allowing for longer programs, if they are not used for variables; when split, right part is used for code earlier then left part.
Therefore, it is advised you allocate B and C first, then "left" part of F, then its "right" part and so on for E and D.

The "accumulator" register A can be accessed with keyword 'A'.


### Expressions

The most basic expressions are:

  * ``A`` which indicates current value of the "accumulator" (P101 A register).
  
  * ``INPUT`` which instructs P101 to stop and ask for an input value, which will be used as the expression value.
  
  * Numeric constants (numbers with no leading zeros, with an optional decimal separator and minus sign).
    Notice storing constants takes up a lot of code space, so typically P101 code requires user to manually enter constants.  
	
  * A variable name.
  
More complex expressions can be created by using brackets and the below operators.

**Notice operators are described in decreasing order of precedence (with unary operators being the one with highest priority).**


#### Unary Operators (functions)

Unary operators operate on a single expression.

  * ``SQRT`` extract square root of given expression (e.g. ``SQRT 4`` returns 2).    
  * ``SQ`` squares expression (e.g. ``SQ(x)`` returns x times x);
  * ``ABS`` takes absolute value for the expression.
  * ``NEG`` negates one expression (= -expression).
  * ``INT`` takes integer part of an expression (everything left of the decimal separator).
  * ``DEC`` takes decimal part of an expression (everything right of the decimal separator).
    
	**Note:** I could not find in any P101 manual a clear description of how this works for negative numbers. 
	In the P203 manual the same instruction is supposed to preserve sign (so DEC(-1.2)=-0.2).
	However, P101Emul emulator always makes this positive.
	
  * ``INV`` calculate the inverse (reciprocal) of one expression (= 1/expression).

#### Multiplicative Operators

  * ``*`` multiplication.
  * ``/`` division.
  * ``%`` modulo (reminder); it executes x/y then the "exact reminder" (content of R register) is moved to A.

#### Additive Operators

  * ``+`` sum.
  * ``-`` subtraction.
    
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

(``+=`` | ``-=`` | ``/=`` | ``*=`` | ``%=``) (_variable_ | ``INPUT`` | `A` | _constant_) ``;``

Similarly to other programming languages, ``+= 5`` performs A=A+5 (which is equivalent to L101 code ``=A+5``).
Notice that the argument cannot be any expression; it might be a variable, constant, the accumulator value or ``INPUT`` meaning the machine will stop and wait for user input.

_variable_ ``<->`` _expression_ ``;``

will calculate the value of _expression_ then will store it into _variable_. In addition, current value of _variable_ will be transferred into A register.

Finally you can store a constant value or the user input into a variable like this:

(_constant_ | ``INPUT``) ``->`` _variable_ ``;``

Below some examples:

```
SHORT x IN B/;

// A=3
=3;

// A=A+5
+=5;

// A=A times the number user enters
*= INPUT;

// Put 42 in x
42 -> x;

// Calculates x^2+5 and stores result in x.
// Previous value of x (42) is moved to A.
x <-> SQ(x) * 5;  
```





