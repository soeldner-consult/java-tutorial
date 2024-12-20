package builder;

public class CarBuilder {

	private final Car car;
	
	public CarBuilder() {
		this.car = new Car();
	}
	
	public CarBuilder wheels(String wheels) {
		this.car.setWheels(wheels);
		return this;
	}
	
	public CarBuilder autobody(String autobody) {
		this.car.setAutobody(autobody);
		return this;
	}
	
	public Car build() {
		return this.car;
	}
}
