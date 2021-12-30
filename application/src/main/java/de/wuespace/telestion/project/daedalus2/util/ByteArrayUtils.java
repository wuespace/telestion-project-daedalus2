package de.wuespace.telestion.project.daedalus2.util;

public class ByteArrayUtils {

	/**
	 * Converts an unsigned byte array to an unsigned short array.
	 * @param array the unsigned byte array to convert
	 * @return an unsigned short array with the contents of the unsigned byte array
	 */
	public static short[] toUnsignedShort(byte[] array) {
		short[] conv = new short[array.length];
		for (int i = 0; i < array.length; i++) {
			conv[i] = (short) Byte.toUnsignedInt(array[i]);
		}
		return conv;
	}

	/**
	 * Concat two byte arrays to one.
	 * @param a the first byte array
	 * @param b the second byte array
	 * @return a new byte array containing the data from the first and second one in this sequence
	 */
	public static byte[] concatData(byte[] a, byte[] b) {
		var newData = new byte[a.length + b.length];

		System.arraycopy(a, 0, newData, 0, a.length);
		System.arraycopy(b, 0, newData, a.length, b.length);

		return newData;
	}

	/**
	 * Splits apart the given data into a chunk and a remainder.
	 *
	 * <pre>[data] -> [chunk:size] + [remainder]</pre>
	 *
	 * @param data the data to split up
	 * @param chunkSize the size of the chunk
	 * @return the chunk and the remainder
	 */
	public static SplitData splitData(byte[] data, int chunkSize) {
		if (data.length <= chunkSize) {
			// data fits in one chunk -> simply return data and an empty array as remainder
			return new SplitData(data, new byte[]{});
		}

		var chunk = new byte[chunkSize];
		var remainder = new byte[data.length - chunkSize];

		System.arraycopy(data, 0, chunk, 0, chunk.length);
		System.arraycopy(data, chunkSize, remainder, 0, remainder.length);

		return new SplitData(chunk, remainder);
	}

	private ByteArrayUtils() {
	}
}
