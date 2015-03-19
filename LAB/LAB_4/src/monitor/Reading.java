package monitor;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

public class Reading {
	/**
	 * The size required to store a reading.
	 */
	static final int SIZE = Long.BYTES * 2 
			+ Integer.BYTES + Float.BYTES;

    /*
     * Length of the message
     * It is required if the message isn't constant
     * but in this case the message size/length is
     * constant and it is unnecessary to add the
     * length field
     */

    static final int Length = SIZE + Integer.BYTES; //added by me

	/**
	 * ByteBuffer is a handy type for storing binary data.
	 */
	final ByteBuffer buf;
	
	/**
	 * Constructs a Reading from the given values. 
	 */
	public Reading(long id, long time, int pulse, float temp) {
		buf = ByteBuffer.allocate(Length); // added by me
		buf.putLong(id);
		buf.putLong(time);
		buf.putInt(pulse);
		buf.putFloat(temp);
        buf.putInt(Length); // added by me
	}
	
	/**
	 * Constructs a Reading from the given stream. 
	 */	
	public Reading(DataInputStream sin) throws IOException {
		//byte[] a = new byte[SIZE];
        byte[] a = new byte[Length]; // added by me
		sin.readFully(a);
		buf = ByteBuffer.wrap(a);
	}
	
	/**
	 * @return the underlying data array.
	 */
	byte[] data() {
		return buf.array();
	}
	
	/**
	 * Reading data rendered as a string. 
	 */
	@Override
	public String toString() {
		return "ID:" + buf.getLong()
			+ ", time:" + new Date(buf.getLong())
			+ ", pulse:" + buf.getInt()
			+ ", temp:" + buf.getFloat()
            + ", Length:" + buf.getInt(); // added by me
	}
	
	/**
	 * A quick unit test for the class. 
	 */
	public static void main(String[] args) throws IOException {
		Reading p1 = new Reading(12345l, 
				System.currentTimeMillis(),
				60,	37.5f);
		
		DataInputStream din = new DataInputStream( 
				new ByteArrayInputStream(p1.data()));
		
		Reading p2 = new Reading(din);
		
		assert Arrays.equals(p1.data(), p2.data());
	}
}
