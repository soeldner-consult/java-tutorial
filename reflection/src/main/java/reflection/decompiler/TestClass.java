package reflection.decompiler;

import java.io.InputStream;
import java.io.Serializable;

public abstract class TestClass extends TestClassBase implements Serializable, Comparable<TestClass> {
	protected abstract int foo_protected_abstract();
	public final char foo_final() { return 0; }
	private static long foo_static() { return 0L; }
	private int foo_private() { return 0; }
	protected double foo_protected() { return 0; }
	float foo_package() { return 0.0F; }
	public String foo_public_String() { return ""; }
	public void foo_void() {}
	public void foo_one_parameter(int firstParameter) {}
	public void foo_two_parameters(int firstParameter, Exception secondParameter) {}
	public static void foo_public_voidClassMethod() {}
	@Override
	public int compareTo(TestClass o) {
		return 0;
	}
	public void foo(Integer i) {}
	
	private int intField;
	public float floatField;
	protected static double doubleClassField;
	InputStream fieldWithPackageVisibility;
	public static final long longField = 3;
	public TestClass(int intField) {
		this.intField = intField;
	}
	private TestClass() {
	}
	protected TestClass(int intField, float floatField, InputStream fieldWithPackageVisibility) {
		this.intField = intField;
		this.floatField = floatField;
		this.fieldWithPackageVisibility = fieldWithPackageVisibility;
	}
	TestClass(int intField, float floatField) {
		this.intField = intField;
		this.floatField = floatField;
	}
}