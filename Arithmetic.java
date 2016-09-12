import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Author: Nigel Smith
// Date:   09/07/16

public class Arithmetic {
	
	// =================================================================
	// inc(A) returns an array of bits representing A+1.
	// =================================================================

	public static byte[] inc(byte[] A) {
		byte[] aByte = reverseArray(A);
		int lengtha = A.length - 1;
		byte[] result = null;

		for (int i = lengtha; i >= 0; i--) {
			// inputs a 1 and returns when the for loop arrives at zero
			if (aByte[i] == 0) {
				aByte[i] = 1;
				aByte = reverseArray(aByte);
				return aByte;
			} else {
				// carries the 1 to the beginning of the given array and returns
				// result
				aByte[i] = 0;
				if (i == 0) {
					result = new byte[A.length + 1];
					result[i] = 1;
					result = reverseArray(result);
					return result;
				}
			}
		}
		result = reverseArray(result);
		return result;
	}

	// =================================================================
	// sum(A,B) returns an array of bits representing A+B.
	// =================================================================

	public static byte[] sum(byte[] A, byte[] B) {
		byte[] aArray = reverseArray(A);
		byte[] bArray = reverseArray(B);
		int lengtha = A.length - 1;
		int lengthb = B.length - 1;
		byte[] result = null;

		// checks if A > B then calls the appropriate function to calculate sum
		if (lengtha >= lengthb) {
			result = new byte[A.length];
			result = aGreaterEqual(lengtha, aArray, bArray);
			result = reverseArray(result);
			return result;
		} else {
			// checks if B > A then calls the appropriate function to calculate
			// sum
			result = new byte[B.length];
			result = bGreater(lengthb, aArray, bArray);
			result = reverseArray(result);
			return result;
		}
	}

	// =============================================================
	// bGreater() returns the result of A+B when B is greater than A
	// =============================================================
	public static byte[] bGreater(int lengthb, byte[] A, byte[] B) {
		byte[] carry = new byte[lengthb + 1];
		byte[] match = new byte[B.length];
		int dif = (B.length - A.length);
		byte[] sumbyte = new byte[B.length];
		byte[] result = new byte[B.length];

		// loops to match the sizes of A & B
		for (int n = dif; n <= lengthb; n++) {
			match[n] = A[n - dif];
		}
		// loops through to add A+B+C(the carry bits) and adds them accordingly
		// to position "i"
		for (int i = lengthb; i >= 0; i--) {
			if (B[i] + match[i] + carry[i] == 1) {
				sumbyte[i] = 1;

				if (i == 0) {
					return sumbyte;
				}
			} else if (B[i] + match[i] + carry[i] == 2) {
				sumbyte[i] = 0;
				if (i == 0) {
					carryprint(sumbyte, result, lengthb);
					return result;
				}
				carry[i - 1] = 1;
			} else if (B[i] + match[i] + carry[i] == 0) {
				sumbyte[i] = 0;
			} else {
				sumbyte[i] = 1;
				if (i == 0) {
					if (B[i] + match[i] + carry[i] != 2) {
						carryprint(sumbyte, result, lengthb);
						return result;
					}
				}
				carry[i - 1] = 1;
			}
		}
		return result;
	}

	// =========================================================================
	// aGreaterEqual() returns the result of A+B when A is greater or equal to B
	// =========================================================================

	public static byte[] aGreaterEqual(int lengtha, byte[] A, byte[] B) {
		byte[] carry = new byte[lengtha + 1];
		byte[] match = new byte[A.length];
		int dif = (A.length - B.length);
		byte[] sumbyte = new byte[A.length];
		byte[] result = new byte[A.length];

		// matches the sizes of A and B
		for (int n = dif; n <= lengtha; n++) {
			match[n] = B[n - dif];
		}
		// loops through to add A+B+C(the carry bits) and adds them accordingly
		// to position "i"
		for (int i = lengtha; i >= 0; i--) {
			if (A[i] + match[i] + carry[i] == 1) {
				sumbyte[i] = 1;
				if (i == 0) {
					return sumbyte;
				}
			} else if (A[i] + match[i] + carry[i] == 2) {
				sumbyte[i] = 0;

				if (i == 0) {
					result = carryprint(sumbyte, result, lengtha);
					return result;
				}

				carry[i - 1] = 1;
			} else if (A[i] + match[i] + carry[i] == 0) {
				sumbyte[i] = 0;
			} else {
				sumbyte[i] = 1;

				if (i == 0) {
					if (A[i] + match[i] + carry[i] != 2) {
						carryprint(sumbyte, result, lengtha);
						return result;
					}
				}
				carry[i - 1] = 1;
			}
		}
		return sumbyte;
	}

	// ===========================================================================================================
	// carryprint(sumbyte, result, lengtha) returns an array of bits
	// representing 1(the carry bit) added to the
	// beginning of A+B.
	// ===========================================================================================================

	public static byte[] carryprint(byte[] sumbyte, byte[] result, int length) {
		int bytelength = length;
		byte[] answer = new byte[length + 2];
		answer[0] = 1;

		for (int n = 1; n <= bytelength + 1; n++) {
			answer[n] = sumbyte[n - 1];
		}

		return answer;
	}

	// =================================================================
	// product(A,B) returns an array of bits representing A*B.
	// =================================================================

	public static byte[] product(byte[] A, byte[] B) {
		byte[] total = new byte[(A.length + B.length)];
		int shiftAbyte = total.length - A.length;
		int shiftBbyte = total.length - B.length;
		byte[] result;

		if (A.length >= B.length) {
			result = new byte[A.length + B.length];
			--shiftAbyte;

			// Shifts through B for zeroes to compare to A and call the sum
			// function at arrival of a 1
			for (int i = B.length - 1; i >= 0; --i) {
				if (B[i] == 1) {
					byte[] aShift = addZero(A, shiftAbyte, total.length);
					result = sum(result, aShift);
					--shiftAbyte;
				}
				// If there is no 1 in B then shift to the next position in B
				else {
					--shiftAbyte;
				}
			}
		} else {
			result = new byte[A.length + B.length];
			--shiftBbyte;

			// Shifts through A for zeroes to compare to B and call the sum
			// function at arrival of a 1
			for (int i = A.length - 1; i >= 0; --i) {
				if (A[i] == 1) {
					byte[] bShift = addZero(B, shiftBbyte, total.length);
					result = sum(result, bShift);
					--shiftBbyte;
				} else {
					--shiftBbyte;
				}
			}
		}
		return result;
	}

	// ==============================================================================================
	// addZero(A,B) returns an array of bits representing "in" inside of "size"
	// at position "shift"
	// ==============================================================================================

	public static byte[] addZero(byte[] in, int shift, int size) {
		byte[] result = new byte[size];

		for (int i = 0; i < in.length; i++) {
			result[i + shift] = in[i];
		}
		return result;
	}

	// ==============================================================================================
	// removeZero(A) returns an array of bits representing an array in reverse
	// order
	// ==============================================================================================

	public static byte[] reverseArray(byte[] array) {
		byte[] result = new byte[array.length];
		int length = array.length - 1;

		for (int i = 0; i < array.length; i++) {
			result[length] = array[i];
			--length;
		}
		return result;
	}
}
