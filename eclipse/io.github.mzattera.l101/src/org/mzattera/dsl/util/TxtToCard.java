/**
 * The below code has been de-compiled and adapted from the P101JavaPlus
 * emulator (http://www.museotecnologicamente.it/collezione/programma-101/).
 */

package org.mzattera.dsl.util;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Formatter;

public class TxtToCard {

	public static String call(final String code) {
		boolean fileOk = true;
		final char comment = '!';

		final BufferedReader br = new BufferedReader(new StringReader(code));

		try (Formatter formatInt = new Formatter()) {

			int pgLineNr = 0;
			int maxPgLine = 120;

			while (fileOk) {

				String pgLine = null;

				try {
					pgLine = br.readLine();
				} catch (Exception ex2) {
				}
				if (pgLine == null) {
					fileOk = false;
				} else {
					int stringLength = pgLine.length();
					if (stringLength >= 3) {
						stringLength = 3;
					}

					int intInstrCode = 0;

					final boolean commentFlag = false;
					boolean emptyFlag = false;
					boolean parityFlag = false;
					int nStringLength = 0;
					int i = 0;

					try {
						for (i = 1; i <= stringLength; ++i) {
							final String alfaS = pgLine.substring(i - 1, i);
							if (convertAddressCode(alfaS) == 255 & convertInstrCode(alfaS) == 255) {
								break;
							}
							nStringLength = i;
						}
					} catch (Exception e) {
						stringLength = i - 1;
					}
					stringLength = nStringLength;
					if (stringLength == 0) {
					} else if (pgLine.charAt(0) == comment) {
					} else {
						++pgLineNr;
						String alfaS = pgLine.substring(0, 1);
						String betaS = pgLine.substring(1, 2);
						final String deltaS = pgLine.substring(2, 3);

						if (convertAddressCode(betaS) == 128) {
							stringLength = 3;
						} else if (convertAddressCode(alfaS) != 255 & convertInstrCode(betaS) != 255) {
							stringLength = 2;
						} else if (convertInstrCode(alfaS) != 255) {
							stringLength = 1;
						} else {
							stringLength = 0;
						}
						if (stringLength != 0) {
							if (stringLength == 3) {
								intInstrCode = convertAddressCode(alfaS);
								if (convertAddressCode(betaS) != 255) {
									intInstrCode |= convertAddressCode(betaS);
								}
								if (convertInstrCode(deltaS) != 255) {
									intInstrCode |= convertInstrCode(deltaS);
								}
							} else if (stringLength == 2) {
								alfaS = pgLine.substring(0, 1);
								betaS = pgLine.substring(1, 2);
								intInstrCode = convertAddressCode(alfaS);
								intInstrCode |= convertInstrCode(betaS);
							} else if (stringLength == 1) {
								alfaS = pgLine.substring(0, 1);
								intInstrCode = convertInstrCode(alfaS);
							} else {
								emptyFlag = true;
								--pgLineNr;
							}
						}
						if (!(!commentFlag & !emptyFlag)) {
							continue;
						}
						if ((intInstrCode & 0x80) == 0x80) {
							parityFlag = true;
						}
						if ((intInstrCode & 0x40) == 0x40) {
							parityFlag = parityFlagToggle(parityFlag);
						}
						if ((intInstrCode & 0x20) == 0x20) {
							parityFlag = parityFlagToggle(parityFlag);
						}
						if ((intInstrCode & 0x10) == 0x10) {
							parityFlag = parityFlagToggle(parityFlag);
						}
						if ((intInstrCode & 0x8) == 0x8) {
							parityFlag = parityFlagToggle(parityFlag);
						}
						if ((intInstrCode & 0x4) == 0x4) {
							parityFlag = parityFlagToggle(parityFlag);
						}
						if ((intInstrCode & 0x2) == 0x2) {
							parityFlag = parityFlagToggle(parityFlag);
						}
						if ((intInstrCode & 0x1) == 0x1) {
							parityFlag = parityFlagToggle(parityFlag);
						}
						int parityDigit;
						if (parityFlag) {
							parityDigit = 1;
						} else {
							parityDigit = 0;
						}
						formatInt.format("%02X%X", intInstrCode, parityDigit);
						if (pgLineNr >= maxPgLine) {
							continue;
						}
						formatInt.format("%n", new Object[0]);
					}
				}
			}
			for (int i = pgLineNr; i < maxPgLine; ++i) {
				if (i <= maxPgLine - 1) {
					formatInt.format("%03X%n", 0);
				} else if (i == maxPgLine) {
					formatInt.format("%03X", 0);
				}
			}

			return formatInt.toString();
		} // try(formatInt)
	}

	private static boolean parityFlagToggle(boolean pFlag) {
		pFlag = !pFlag;
		return pFlag;
	}

	private static int convertInstrCode(final String uCode) {
		int hCode = 0;
		if (uCode.equals("S")) {
			hCode = 0;
		} else if (uCode.equals("d")) {
			hCode = 1;
		} else if (uCode.equals("\u2193")) {
			hCode = 1;
		} else if (uCode.equals("u")) {
			hCode = 2;
		} else if (uCode.equals("\u2191")) {
			hCode = 2;
		} else if (uCode.equals("e")) {
			hCode = 3;
		} else if (uCode.equals("\u2195")) {
			hCode = 3;
		} else if (uCode.equals("+")) {
			hCode = 4;
		} else if (uCode.equals("-")) {
			hCode = 5;
		} else if (uCode.equals("X")) {
			hCode = 6;
		} else if (uCode.equals(":")) {
			hCode = 7;
		} else if (uCode.equals("p")) {
			hCode = 8;
		} else if (uCode.equals("\u25ca")) {
			hCode = 8;
		} else if (uCode.equals("*")) {
			hCode = 9;
		} else if (uCode.equals("r")) {
			hCode = 11;
		} else if (uCode.equals("\u221a")) {
			hCode = 11;
		} else if (uCode.equals("V")) {
			hCode = 12;
		} else if (uCode.equals("W")) {
			hCode = 13;
		} else if (uCode.equals("Y")) {
			hCode = 14;
		} else if (uCode.equals("Z")) {
			hCode = 15;
		} else {
			hCode = 255;
		}
		return hCode;
	}

	private static int convertAddressCode(final String uCode) {
		int hCode = 0;
		if (uCode.equals("B")) {
			hCode = 80;
		} else if (uCode.equals("b")) {
			hCode = 208;
		} else if (uCode.equals("C")) {
			hCode = 16;
		} else if (uCode.equals("c")) {
			hCode = 144;
		} else if (uCode.equals("D")) {
			hCode = 48;
		} else if (uCode.equals("d")) {
			hCode = 176;
		} else if (uCode.equals("E")) {
			hCode = 112;
		} else if (uCode.equals("e")) {
			hCode = 240;
		} else if (uCode.equals("F")) {
			hCode = 96;
		} else if (uCode.equals("f")) {
			hCode = 224;
		} else if (uCode.equals("A")) {
			hCode = 64;
		} else if (uCode.equals("a")) {
			hCode = 192;
		} else if (uCode.equals("R")) {
			hCode = 32;
		} else if (uCode.equals("r")) {
			hCode = 160;
		} else if (uCode.equals("/")) {
			hCode = 128;
		} else if (uCode.equals("M")) {
			hCode = 0;
		} else if (uCode.equals("m")) {
			hCode = 0;
		} else if (uCode.equals(" ")) {
			hCode = 0;
		} else {
			hCode = 255;
		}
		return hCode;
	}
}
