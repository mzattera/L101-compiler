/**
 * 
 */
package org.mzattera.dsl.util;

import java.io.IOException;
import java.io.OutputStream;

import org.mzattera.dsl.generator.L101Generator;

/**
 * Utility method to convert a compiled program into a virtual magnetic card
 * compatible with P101Emul.
 * 
 * @author Massimiliano_Zattera
 *
 */
public final class P101EmulUtil {

	private P101EmulUtil() {
	}

	// Needed at end of file.
	private final static String EOF = "'\n#0\n0\n0\n0\n0\n0\n0\n";

	/**
	 * Encodes given P101 program in a format compatible with the P101Emul emulator
	 * (http://www.claudiolarini.altervista.org/emul2.htm).
	 * 
	 * @param code Compiled P101 program.
	 */
	public static void encode(String code, OutputStream stream) throws IOException {
		code = code + EOF;

		for (int i = 0; i < code.length(); ++i) {

			int c = code.charAt(i);

			// I found no better way of doing encoding automatically.
			if (c == L101Generator.DIAMOND.charAt(0))
				stream.write(0x04);
			else if (c == L101Generator.UP.charAt(0))
				stream.write(0x18);
			else if (c == L101Generator.DOWN.charAt(0))
				stream.write(0x19);
			else if (c == L101Generator.UP_DOWN.charAt(0))
				stream.write(0x17);
			else if (c == L101Generator.PLUS.charAt(0))
				stream.write(0x2B);
			else if (c == L101Generator.MINUS.charAt(0))
				stream.write(0x2D);
			else if (c == L101Generator.MUL.charAt(0))
				stream.write(0x78);
			else if (c == L101Generator.DIV.charAt(0))
				stream.write(0xF6);
			else if (c == L101Generator.SQRT.charAt(0))
				stream.write(0xFB);
			else
				stream.write(c);
		} // for each char
	}
}
