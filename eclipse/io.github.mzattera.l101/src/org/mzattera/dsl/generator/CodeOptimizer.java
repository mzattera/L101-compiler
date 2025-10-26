/**
 * 
 */
package org.mzattera.dsl.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.mzattera.dsl.generator.JumpAllocator.JumpType;

/**
 * Applies some optimization to generated code.
 * 
 * @author Massimiliano_Zattera
 *
 */
public final class CodeOptimizer {

	// To load 1/2 in A, program a square root followed by : (div) -> CANNOT be done
	// since A must be non zero.

	// TODO 2 - Check in manuals other possible optimizations

	// TODO 2 - implement optimizations described in
	// "P101GeneralReferenceManual.pdf" at page 44ss.

	// TODO 2 - implement optimizations described in "P101ManualeProgrammazione.pdf"
	// at page 41ss.

	// the JumpAllocator used to generate the code being optimized.
	private final JumpAllocator allocator;

	// the code to optimize.
	private String code;

	public CodeOptimizer(String code, JumpAllocator allocator) {
		this.allocator = allocator;
		this.code = code;
	}

	/**
	 * Applies all known optimizations.
	 * 
	 * @return code after optimization.
	 */
	public final String optimize() {

		// This is best done at the very beginning, as removing code might impact other
		// optimizations; in addition, it is easier to optimize if jump placeholders are
		// still in place
		optimizeUnreachanbleCode();

		optNegX();
		optLoadA0();
		optDoubleA();

		optimizeJumps();

		return code;
	}

	/**
	 * Remove unreachable code. Unreachable code is that follows an unconditional
	 * jump (until a jump destination is found again).
	 */
	private void optimizeUnreachanbleCode() {
		String[] lines = code.split("\\r\\n");
		StringBuilder result = new StringBuilder();
		boolean lastGo = false; // true if last instruction we kept was

		for (String l : lines) {
			if (lastGo) {
				if (JumpAllocator.isJumpDestinationPlaceholder(l)) {
					result.append(l).append("\r\n");
					lastGo = false;
				}
			} else {
				result.append(l).append("\r\n");
				if (JumpAllocator.isJumpSourcePlaceholder(l) && (JumpAllocator.jumpType(l) == JumpType.UNCONDITIONAL))
					lastGo = true;
			}
		}

		code = result.toString();
		lines = code.split("\\r\\n");
		result = new StringBuilder();

		// By removing unreachable code, jump source and destination might now
		// be consecutive one another, in this case they can both be removed (if source
		// comes first).
		for (int i = 0; i < lines.length; ++i) {
			if (i == (lines.length - 1)) {
				// Always add last instruction
				result.append(lines[i]).append("\r\n");
			} else if (JumpAllocator.isJumpSourcePlaceholder(lines[i])
					&& JumpAllocator.getDestinationPlaceholder(lines[i]).equals(lines[i + 1])) {
				++i; // skip this and next instruction
			} else {
				result.append(lines[i]).append("\r\n");
			}
		}

		code = result.toString();
		lines = code.split("\\r\\n");
		result = new StringBuilder();

		// By removing unreachable code, some destination labels might now not be used
		// (corresponding source instruction was unreachable). Remove them as well (do
		// NOT remove labels for keys).
		for (int i = 0; i < lines.length; ++i) {
			if (JumpAllocator.isJumpDestinationPlaceholder(lines[i]) && !allocator.isKey(lines[i])
					&& !code.contains(JumpAllocator.getSourcePlaceholder(lines[i])))
				continue;
			result.append(lines[i]).append("\r\n");
		}

		code = result.toString();
	}

	private final static String ptrnLoadA0 = Pattern
			.quote("A/" + L101Generator.UP + "\r\n" + "D/S\r\n" + L101Generator.DOWN + "\r\n");

	/**
	 * Optimizes "=0" "A-". Notice this also optimizes x<->0 Notice 0->x cannot be
	 * optimized this way, as it will change value in A.
	 * 
	 * @param code
	 * @return
	 */
	private final void optLoadA0() {
		code = code.replaceAll(ptrnLoadA0, "A" + L101Generator.MINUS + "\r\n");
	}

	private final static String ptrnDoubleA = Pattern
			.quote("A/" + L101Generator.UP + "\r\n" + "D/" + L101Generator.UP + "\r\n" + "x\r\n");

	/**
	 * Optimizes "=A*2" and "*=2" as "A+". Note this also optimizes =x*2 or x<->x*2.
	 * 
	 * @param code
	 * @return
	 */
	private final void optDoubleA() {
		code = code.replaceAll(ptrnDoubleA, "A" + L101Generator.PLUS + "\r\n");
	}

	private final static String ptrnNegX = "([BCDEF]/?)"
			+ Pattern.quote(L101Generator.DOWN + "\r\n" + "A-\r\n" + "-" + "\r\n");

	/**
	 * Optimizes "=NEG(x)" as "A- x-". Otherwise it will be x↓ A- -
	 * 
	 * @param code
	 * @return
	 */
	private final void optNegX() {
		code = code.replaceAll(ptrnNegX, "A" + L101Generator.MINUS + "\r\n" + "$1" + L101Generator.MINUS + "\r\n");
	}

	/**
	 * 
	 * @return
	 */
	private final void optimizeJumps() {

		/// Phase 1: Identify which destination labels can be replaced (by merging them
		/// with neighbouring ones).
		/// ////////////////////////////

		// Last conditional and unconditional label found (if we are in a sequence of
		// destination labels)
		String lastCond = null, lastUCond = null;

		// Maps a jump destination identifier into a list of identifiers that can be
		// replaced by the key one.
		Map<String, List<String>> replaceWith = new HashMap<String, List<String>>();

		for (String l : code.split("\\r\\n")) {
			if (!JumpAllocator.isJumpDestinationPlaceholder(l)) {
				lastCond = null;
				lastUCond = null;
				continue;
			}

			switch (JumpAllocator.jumpType(l)) {

			case CONDITIONAL:
				if (lastCond == null) {
					// this jump is not in a sequence
					lastCond = l;
				} else {
					// this can be replaced by the first jump in the sequence
					List<String> lst = replaceWith.get(lastCond);
					if (lst == null) {
						lst = new ArrayList<String>();
						replaceWith.put(lastCond, lst);
					}
					lst.add(l);
				}
				break;

			case UNCONDITIONAL:
				if (lastUCond == null) {
					// this jump is not in a sequence
					lastUCond = l;
				} else {
					if (allocator.isKey(l)) {
						// this is also a key, use this label instead of first one
						List<String> lst = replaceWith.remove(lastUCond);
						if (lst != null)
							replaceWith.put(l, lst);
						if (!allocator.isKey(lastUCond)) {
							// replace this as well
							if (lst == null) {
								lst = new ArrayList<String>();
								replaceWith.put(l, lst);
							}
							lst.add(lastUCond);
						}
						lastUCond = l;
					} else {
						// this can be replaced by the first jump in the sequence
						List<String> lst = replaceWith.get(lastUCond);
						if (lst == null) {
							lst = new ArrayList<String>();
							replaceWith.put(lastUCond, lst);
						}
						lst.add(l);
					}
				}
				break;

			default:
				throw new IllegalArgumentException();
			}
		}

		/// Phase 2: Do th eactual replacing
		/// ////////////////////////////

		for (String key : replaceWith.keySet()) {
			for (String value : replaceWith.get(key)) {
				// remove unneeded jump destinations
				code = code.replaceAll(Pattern.quote(value) + "\\r\\n", "");
				// change corresponding jump sources accordingly
				code = code.replaceAll(Pattern.quote(JumpAllocator.getSourcePlaceholder(value)),
						JumpAllocator.getSourcePlaceholder(key));
			}
		}

		// As result of merging destination placeholders, you might now have two
		// identical conditional jumps in sequence. Only the first one
		// should be kept.

		String[] lines = code.split("\\r\\n");
		StringBuilder result = new StringBuilder();

		if (lines.length > 0)
			result.append(lines[0]).append("\r\n");
		for (int i = 1; i < lines.length; ++i) {
			if (JumpAllocator.isJumpSourcePlaceholder(lines[i]) && lines[i].equals(lines[i - 1]))
				continue; // skip this instruction
			result.append(lines[i]).append("\r\n");
		}
		code = result.toString();

		/// Phase 3: replace remaining placehlders with actual instructions
		/// ////////////////////////////

		for (String l : code.split("\\r\\n")) {
			if (JumpAllocator.isJumpDestinationPlaceholder(l) || JumpAllocator.isJumpSourcePlaceholder(l)) {
				code = code.replaceAll(Pattern.quote(l), allocator.getInstruction(l));
			}
		}
	}
}
