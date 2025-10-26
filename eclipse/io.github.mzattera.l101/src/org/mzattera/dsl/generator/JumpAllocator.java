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
package org.mzattera.dsl.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mzattera.dsl.l101.Statement;

/**
 * This class is used to allocate jump instructions used by program statements.
 * 
 * It keeps track of allocated jump instructions and provides methods to
 * allocate destination instructions to statements.
 * 
 * See "Implementation of Jumps for details".
 * 
 * @author Massimiliano_Zattera
 *
 */
public final class JumpAllocator {

	// This lists all destination instructions (except those assigned to keys) for
	// un-conditional jumps
	private static final String[] UNCONDITIONAL = { "BV", "BW", "BY", "BZ", "EV", "EW", "EY", "EZ", "FV", "FW", "FY",
			"FZ" };

	// This lists all destination instructions for conditional jumps
	private static final String[] CONDITIONAL = { "A/V", "A/W", "A/Y", "A/Z", "B/V", "B/W", "B/Y", "B/Z", "E/V", "E/W",
			"E/Y", "E/Z", "F/V", "F/W", "F/Y", "F/Z" };

	/**
	 * Types of jump instructions.
	 * 
	 * @author Massimiliano_Zattera
	 */
	public enum JumpType {
		CONDITIONAL, UNCONDITIONAL, NONE
	}

	/**
	 * Describes an allocation for a jump. An allocation contains both the statement
	 * for which the jump has been allocated, but also an "origin" which is used to
	 * differentiate between different jumps for the same statement (e.g. a CONTINUE
	 * statement might allocate a jump for its containing WHILE loop, while the
	 * WHILE loop itself allocates its own jumps).
	 * 
	 * @author Massimiliano_Zattera
	 *
	 */
	public final static class AllocationKey {

		private final Statement stmt;

		private final String origin;

		private final JumpType type;

		public static AllocationKey of(final Statement stmt, final String origin, final JumpType type) {
			return new AllocationKey(stmt, origin, type);
		}

		private AllocationKey(final Statement stmt, final String origin, JumpType type) {
			if ((stmt == null) || (origin == null))
				throw new IllegalArgumentException();

			this.stmt = stmt;
			this.origin = origin;
			this.type = type;
		}

		public Statement getStatement() {
			return stmt;
		}

		public String getOrigin() {
			return origin;
		}

		public JumpType getType() {
			return type;
		}

		@Override
		public boolean equals(Object other) {
			if (other == null)
				return false;
			if (this == other)
				return true;
			if (this.getClass().equals(other.getClass())) {
				AllocationKey otherAlloc = (AllocationKey) other;
				return this.stmt.equals(otherAlloc.stmt) && this.origin.equals(otherAlloc.origin)
						&& (this.type == otherAlloc.type);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return stmt.hashCode() + 23 * origin.hashCode() + 17 * type.hashCode();
		}
	}

	// index of next un-allocated destination instruction
	private int next = 0;

	// contains the jump destinations placeholders already allocated.
	private final Map<AllocationKey, String> destinationPlaceholders = new HashMap<AllocationKey, String>();

	// contains the jump destinations placeholders already allocated to key presses
	// (VWYX): destination placeholder -> key (e.g. "V")
	private Map<String, String> keys = new HashMap<String, String>();

	/**
	 * @return List of all placeholders (destinations) allocated so far.
	 */
	public List<String> getPlaceholders() {
		return new ArrayList<String>(destinationPlaceholders.values());
	}

	/**
	 * @return true if given string is a jump destination placeholder.
	 */
	public static boolean isJumpDestinationPlaceholder(String l) {
		return l.startsWith("_D_" + jumpType(l).toString() + ":");
	}

	/**
	 * @return true if given string is a jump source placeholder.
	 */
	public static boolean isJumpSourcePlaceholder(String l) {
		return l.startsWith("_S_" + jumpType(l).toString() + ":");
	}

	/**
	 * @return the type of jump to which the given placeholder refers (or NONE if
	 *         the string is not a valid destination or source identifier).
	 */
	public static JumpType jumpType(String placeholder) {
		if (placeholder.contains("_" + JumpType.CONDITIONAL.toString()))
			return JumpType.CONDITIONAL;
		if (placeholder.contains("_" + JumpType.UNCONDITIONAL.toString()))
			return JumpType.UNCONDITIONAL;
		return JumpType.NONE;
	}

	/**
	 * @return true if the given destination placeholder is associated to a key.
	 */
	public boolean isKey(String placeholder) {
		return keys.containsKey(placeholder);
	}

	/**
	 * Allocate a jump placeholder. Notice that if for the same combination of
	 * parameters a jump is already allocated, it will not be re-allocated
	 * (basically the class avoid duplicated jumps allocated for same thing).
	 * 
	 * @param stmt   the Statement for which we are allocating a jump.
	 * @param origin why are we allocating a jump (used in compilation).
	 * @param type   conditional or unconditional
	 * 
	 * @return a string placeholder that uniquely identifies the jump destination.
	 */
	public String allocateJumpPlaceholder(Statement stmt, String origin, JumpType type) {
		if (type == JumpType.NONE)
			throw new IllegalArgumentException();

		AllocationKey key = AllocationKey.of(stmt, origin, type);
		String s = destinationPlaceholders.get(key);
		if (s == null) {
			s = "_D_" + type.toString() + ":" + String.format("%05d", next++);
			destinationPlaceholders.put(key, s);
		}
		return s;
	}

	/**
	 * Allocate an unconditional jump placeholder associated with a key.
	 * 
	 * @param stmt     the Statement for which we are allocating a jump.
	 * @param origin   why are we allocating a jump (used in compilation).
	 * @param keyPress the key that, when pressed, will cause execution to jump
	 *                 here.
	 * 
	 * @return a string placeholder that uniquely identifies the jump destination.
	 */
	public String allocateJumpPlaceholder(Statement stmt, String origin, String keyPress) {
		String placeholder = allocateJumpPlaceholder(stmt, origin, JumpType.UNCONDITIONAL);
		keys.put(placeholder, keyPress);
		return placeholder;
	}

	/**
	 * @return String placeholder for the destination corresponding to given source
	 *         placeholder.
	 */
	public static String getDestinationPlaceholder(String source) {
		if (!JumpAllocator.isJumpSourcePlaceholder(source))
			throw new IllegalArgumentException("Invalid source: " + source);
		return source.replaceAll("_S_", "_D_");
	}

	/**
	 * @return String placeholder that uniquely identifies a jump destination
	 *         corresponding to the jump that was allocated for given combination of
	 *         parameters. Returns null if no jump was allocated.
	 * 
	 * @param stmt   the Statement for which we are allocating a jump.
	 * @param origin why are we allocating a jump (used in compilation).
	 * @param type   conditional or unconditional
	 */
	public String getDestinationPlaceholder(Statement stmt, String origin, JumpType type) {
		return destinationPlaceholders.get(AllocationKey.of(stmt, origin, type));
	}

	/**
	 * @return String placeholder for the source corresponding to given destination
	 *         placeholder.
	 */
	public static String getSourcePlaceholder(String destination) {
		if (!JumpAllocator.isJumpDestinationPlaceholder(destination))
			throw new IllegalArgumentException("Invalid destination: " + destination);
		return destination.replaceAll("_D_", "_S_");
	}

	/**
	 * @return String placeholder that uniquely identifies a source corresponding to
	 *         the jump that was allocated for given combination of parameters.
	 *         Returns null if no jump was allocated.
	 * 
	 * @param stmt   the Statement for which we are allocating a jump.
	 * @param origin why are we allocating a jump (used in compilation).
	 * @param type   conditional or unconditional
	 */
	public String getSourcePlaceholder(Statement stmt, String origin, JumpType type) {
		return getSourcePlaceholder(getDestinationPlaceholder(stmt, origin, type));
	}

	// index of next un-allocated destination instruction
	private int nextCond = 0, nextUncond = 0;

	// Maps a destination placeholder into corresponding jump destination
	// instruction.
	private final Map<String, String> destinationInstructions = new HashMap<String, String>();

	/**
	 * 
	 * @param placeholder
	 * @return The instruction corresponding to given placeholder. Instructions are
	 *         allocated as required and an exception is thrown if there are too
	 *         many jump locations and no more instructions can be allocated.
	 */
	public String getInstruction(String placeholder) {
		String result = null;

		if (JumpAllocator.isJumpDestinationPlaceholder(placeholder)) {

			// If corresponding instruction has been already allocated for the placeholder,
			// return it
			result = destinationInstructions.get(placeholder);
			if (result != null)
				return result;

			JumpType type = JumpAllocator.jumpType(placeholder);
			if (type == JumpType.CONDITIONAL) { // CONDITIONAL jump
				if (nextCond >= CONDITIONAL.length)
					throw new RuntimeException("Too many conditional jumps required.");

				result = CONDITIONAL[nextCond++];
				destinationInstructions.put(placeholder, result);
				return result;
			} else { // UNCONDITIONAL jump
				if (keys.containsKey(placeholder)) {
					// instructions associated to keys are done differently
					return "A" + keys.get(placeholder);
				} else {
					if (nextUncond >= UNCONDITIONAL.length)
						throw new RuntimeException("Too many unconditional jumps required.");

					result = UNCONDITIONAL[nextUncond++];
					destinationInstructions.put(placeholder, result);
					return result;
				}
			}
		} else if (JumpAllocator.isJumpSourcePlaceholder(placeholder)) {
			// Instruction for corresponding destination label
			String dest = getInstruction(JumpAllocator.getDestinationPlaceholder(placeholder));

			// Translate it
			if (dest.length() == 2) { // Un-conditional
				switch (dest.charAt(0)) {
				case 'A':
					return Character.toString(dest.charAt(1));
				case 'B':
					return "C" + dest.charAt(1);
				case 'E':
					return "D" + dest.charAt(1);
				case 'F':
					return "R" + dest.charAt(1);
				}
			} else { // Conditional
				switch (dest.charAt(0)) {
				case 'A':
					return "/" + dest.charAt(2);
				case 'B':
					return "C/" + dest.charAt(2);
				case 'E':
					return "D/" + dest.charAt(2);
				case 'F':
					return "R/" + dest.charAt(2);
				}
			}

			throw new IllegalArgumentException("Unrecognized instruction: " + dest);

		} else
			throw new IllegalArgumentException("Invalid placeholder: " + placeholder);
	}
}
