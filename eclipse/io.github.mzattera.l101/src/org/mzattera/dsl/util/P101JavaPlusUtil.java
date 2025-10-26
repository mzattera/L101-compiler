/**
 * 
 */
package org.mzattera.dsl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.mzattera.dsl.generator.L101Generator;


/**
 * Utility method to convert a compiled program into a virtual magnetic card
 * compatible with P101JavaPlus.
 * 
 * @author Massimiliano_Zattera
 *
 */
public final class P101JavaPlusUtil {

	private P101JavaPlusUtil() {
	}

	/**
	 * Encodes given P101 program in a format compatible with the TXT cards for the P101JavaPlus
	 * emulator (http://www.museotecnologicamente.it/collezione/programma-101/).
	 * 
	 * @param code
	 *            Compiled P101 program.
	 */
	public static void encode(String code, OutputStream out) throws IOException {
		
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			for (int i = 0; i < code.length(); ++i) {

				int c = code.charAt(i);

				// I found no better way of doing encoding automatically.
				if (c == L101Generator.DIAMOND.charAt(0))
					stream.write('p');
				else if (c == L101Generator.UP.charAt(0))
					stream.write('u');
				else if (c == L101Generator.DOWN.charAt(0))
					stream.write('d');
				else if (c == L101Generator.UP_DOWN.charAt(0))
					stream.write('e');
				else if (c == L101Generator.PLUS.charAt(0))
					stream.write('+');
				else if (c == L101Generator.MINUS.charAt(0))
					stream.write('-');
				else if (c == L101Generator.MUL.charAt(0))
					stream.write('X');
				else if (c == L101Generator.DIV.charAt(0))
					stream.write(':');
				else if (c == L101Generator.SQRT.charAt(0))
					stream.write('r');
				else if (c == L101Generator.STAR.charAt(0))
					stream.write('*');
				else if (c == '\r')
					;
				else if (c == '\n') {
					stream.write(0x20);
					stream.write(0x20);
					stream.write(0x20);
					stream.write(0x0a);
				} else
					stream.write(c);
			} // for each char

			String pmc = TxtToCard.call(new String(stream.toByteArray(), "ASCII"));
			out.write(pmc.getBytes("ASCII"));
		}		
	}
}
