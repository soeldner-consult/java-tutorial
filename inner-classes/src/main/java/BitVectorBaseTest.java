import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// This whole BitVector concept does not really pertain to this course, it might be from Datenstrukturen und Algorithmen
// thus we don't need to understand the workings of the BitVector in detail, it complicates matters a bit..

// in the first steps a BitVectorBase and an extending BitVectorXX-class is implemented.
// then a diagram is given with interfaces to implement, the BitVectorXX class is supposed to implement the Iterable
// interface, which has a method iterator returning an Iterator
// the question is now, how do we return an implementing class of Iterator? And it is only here where the fun with
// inner classes begins. In the end we have to find a way to implement the

// @Override
// public Iterator<Integer> iterator() { return SomeIterator; } method 

// here we have 4 options that would fulfill the "new" statement in this method using a class inside the class:
// - an inner static class
// - an inner class
// - a local inner class
// - an anonymous class (where we don't write "class" anywhere!)

// of course we might have a separate class for the Iterator..  using inner classes always suggests a tight connection 
// between the outer class and the inner class .. the inner class acts as a helper class for the outer class in such
// a case.

// with that said, we should know these 4 options regarding inner classes in the exam.
// Prof. Volz presumably had this rather complicated example as exercise in the script as 
// a combination of an outer class implementing Iterable and an inner class implementing iterator is 
// a) commonly accepted design and b) the example is probably from another course taught at HS Ansbach.

// additional info about Iterable/Iterator:
// The Iterable/Iterator combination in Java provides a standardized way to traverse elements  
// in a collection or custom data structure.

// Iterable Key Method:
// iterator(): Returns an Iterator object.
// Iterator: Encapsulates the logic for traversing elements in a collection or data structure.
// hasNext(): Checks if there are more elements to traverse.
// next(): Retrieves the next element.
// remove(): (Optional) Removes the current element from the underlying collection.

class BitVector01 extends BitVectorBase implements Iterable<Integer> {

    // Constructor for default initialization
    public BitVector01() {
        super();
    }

    // Constructor to initialize with a long value
    public BitVector01(long value) {
        super(value);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BitVectorIterator(getValue());
    }

    // Static inner class implementing Iterator<Integer>
    private static class BitVectorIterator implements Iterator<Integer> {
        private final long value;  // The long value to iterate over
        private int currentBit = 0; // The current bit position (0-63)

        public BitVectorIterator(long value) {
            this.value = value;
        }

        @Override
        public boolean hasNext() {
            // Check if there are more bits to iterate over
        	// The expression if ((value & (1L << currentBit)) != 0) checks whether the bit at position currentBit 
        	// in the variable value is set (1) or not set (0). If it is set, the condition evaluates to true.
        	
			//      	Breakdown of the Expression
			//        	1L << currentBit:
			//
			//        	This creates a number where only the bit at position currentBit is set to 1, and all other bits are 0.
			//        	Example: If currentBit = 3, the result of 1L << 3 is 00001000 in binary (decimal 8).
			//        	value & (1L << currentBit):
			//
			//        	This performs a bitwise AND operation between value and the result of 1L << currentBit.
			//        	The AND operation will:
			//        	Keep the bit at position currentBit from value if it is set to 1.
			//        	Set all other bits to 0.
			//        	Example: If value = 13 (00001101 in binary) and currentBit = 3, then:
			//        	bash
			//        	Code kopieren
			//        	value         = 00001101
			//        	1L << 3       = 00001000
			//        	value & (1L << 3) = 00001000 (non-zero, so the bit is set)
			//        	!= 0:
			//
			//        	This checks if the result of the bitwise AND operation is non-zero.
			//        	A non-zero result means the bit at currentBit in value is set to 1.
			//        	A zero result means the bit at currentBit in value is 0.
		    while (currentBit < Long.SIZE) {
            	if ((value & (1L << currentBit)) != 0) { // Check if the bit is set
                    return true;
                }
                currentBit++;
            }
            return false;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more set bits in the BitVector.");
            }
            // Return the position of the current set bit and move to the next bit
            return currentBit++;
        }
    }
}


class BitVector02 extends BitVectorBase implements Iterable {

	// Constructor for default initialization
    public BitVector02() {
        super();
    }

    // Constructor to initialize with a long value
    public BitVector02(long value) {
        super(value);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BitVectorIterator(getValue());
    }

	
    // Static inner class implementing Iterator<Integer>
    private class BitVectorIterator implements Iterator<Integer> {
        private final long value;  // The long value to iterate over
        private int currentBit = 0; // The current bit position (0-63)

        public BitVectorIterator(long value) {
            this.value = value;
        }

        @Override
        public boolean hasNext() {
            // Check if there are more bits to iterate over
            while (currentBit < Long.SIZE) {
                if ((value & (1L << currentBit)) != 0) { // Check if the bit is set
                    return true;
                }
                currentBit++;
            }
            return false;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more set bits in the BitVector.");
            }
            // Return the position of the current set bit and move to the next bit
            return currentBit++;
        }
    }
}

class BitVector03 extends BitVectorBase implements Iterable {

	 // Constructor for default initialization
    public BitVector03() {
        super();
    }

    // Constructor to initialize with a long value
    public BitVector03(long value) {
        super(value);
    }

    @Override
    public Iterator<Integer> iterator() {
        
    	// permitted modifiers for a local class
        class BitVectorIterator implements Iterator<Integer> {
            private final long value;  // The long value to iterate over
            private int currentBit = 0; // The current bit position (0-63)

            public BitVectorIterator(long value) {
                this.value = value;
            }

            @Override
            public boolean hasNext() {
                // Check if there are more bits to iterate over
                while (currentBit < Long.SIZE) {
                    if ((value & (1L << currentBit)) != 0) { // Check if the bit is set
                        return true;
                    }
                    currentBit++;
                }
                return false;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more set bits in the BitVector.");
                }
                // Return the position of the current set bit and move to the next bit
                return currentBit++;
            }
        }
    	
    	
    	return new BitVectorIterator(getValue());
    }
    
}

class BitVector04 extends BitVectorBase implements Iterable {

	 // Constructor for default initialization
    public BitVector04() {
        super();
    }

    // Constructor to initialize with a long value
    public BitVector04(long value) {
        super(value);
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() { // Anonymous class implementing Iterator<Integer>
            private final long value = getValue(); // Capture the current value
            private int currentBit = 0; // Start from the least significant bit (LSB)

            @Override
            public boolean hasNext() {
                while (currentBit < Long.SIZE) {
                    if ((value & (1L << currentBit)) != 0) { // Check if the bit is set
                        return true;
                    }
                    currentBit++;
                }
                return false;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more set bits in the BitVector.");
                }
                return currentBit++; // Return the position of the current set bit
            }
        };
    }
}

class BitVectorBase {
	long value;
	
	BitVectorBase() {};
	BitVectorBase(long value) {
		this.value = value;
	}
	boolean isSet(int bit) {
		if (bit<0 || bit>63) {
			throw new IllegalArgumentException("Argument bit is out of range; valid range: [0..63], was " + bit);
		}
		
		return false;
	}
	
	void setBit(int bit, boolean set) {
		if (bit<0 || bit>63) {
			throw new IllegalArgumentException("Argument bit is out of range; valid range: [0..63], was " + bit);
		}
		
		if (set) {
            // Set the bit to 1 using bitwise OR
            value |= (1L << bit);
        } else {
            // Clear the bit to 0 using bitwise AND with NOT
            value &= ~(1L << bit);
        }
	}
	
	public long getValue() {
        return value;
    }
	
	// https://www.baeldung.com/java-equals-hashcode-contracts
	@Override
	public boolean equals(Object o) {
	    // Check for reference equality
	    if (this == o) {
	        return true;
	    }
	    
	    // Check for null and type mismatch
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }
	    
	    // Cast and compare the `value` field
	    BitVectorBase that = (BitVectorBase) o;
	    return value == that.value;
	}

	@Override
	public final int hashCode() {
	    // Generate hash code using the `value` field
	    return Long.hashCode(value);
	}
}

// here we test everything
public class BitVectorBaseTest {

	public static void main(String[] args) {
		BitVectorBase manipulator = new BitVectorBase(0b1010); // Initial value: 10
        System.out.println("Initial value: " + Long.toBinaryString(manipulator.getValue()));

        manipulator.setBit(1, true); // Set bit 1
        System.out.println("After setting bit 1: " + Long.toBinaryString(manipulator.getValue()));

        manipulator.setBit(2, false); // Clear bit 2
        System.out.println("After clearing bit 2: " + Long.toBinaryString(manipulator.getValue()));

        manipulator.setBit(63, true); // Set the highest bit
        System.out.println("After setting bit 63: " + Long.toBinaryString(manipulator.getValue()));
        
        BitVectorBase bv1 = new BitVectorBase(12345L);
        BitVectorBase bv2 = new BitVectorBase(12345L);
        BitVectorBase bv3 = new BitVectorBase(54321L);

        System.out.println("bv1 equals bv2: " + bv1.equals(bv2)); // true
        System.out.println("bv1 equals bv3: " + bv1.equals(bv3)); // false

        System.out.println("bv1 hashCode: " + bv1.hashCode()); // hash code of 12345L
        System.out.println("bv2 hashCode: " + bv2.hashCode()); // hash code of 12345L
        System.out.println("bv3 hashCode: " + bv3.hashCode()); // hash code of 54321L
        
        System.out.println("BitVector01");
        BitVector01 bitVector01 = new BitVector01(); // Create the BitVector01 instance

        // Set bits in the BitVector01 instance
        bitVector01.setBit(1, true);
        bitVector01.setBit(3, true);
        bitVector01.setBit(5, true);

        System.out.println("Bits set in the BitVector:");
        // Iterate over the bits set in the BitVector01
        for (int bit : bitVector01) {
            System.out.println("Bit position: " + bit);
        }
        
        int number = 259; // Example number

        // Convert the number to its binary representation
        String binaryString = Integer.toBinaryString(number);

        // Add a leading '1' to denote a positive number
        String leadingOneBinary = "1" + String.format("%63s", binaryString).replace(' ', '0');

        // Group the binary string into bytes (8 bits) and separate with spaces
        String formattedBinary = IntStream.range(0, leadingOneBinary.length())
                .filter(i -> i % 8 == 0)
                .mapToObj(i -> leadingOneBinary.substring(i, Math.min(i + 8, leadingOneBinary.length())))
                .collect(Collectors.joining(" "));

        // Print the result
        System.out.println(number + " = (" + formattedBinary + ") as binary number");
        
	}
	
	
}
