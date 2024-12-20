package builder;

public class BuilderApp {

	public static void main(String[] args) {
		// Create a House using the inner static HouseBuilder class, chaining the methods
		// this design does not allow direct instantiation..
		// https://medium.com/@mehar.chand.cloud/builder-design-pattern-use-case-build-http-response-5b109100c7ef
		House house = new House.HouseBuilder()
                .foundation("Concrete")
                .structure("Brick")
                .roof("Tiles")
                .windows(8)
                .doors(3)
                .build();

        System.out.println(house);
        
        Car car = new CarBuilder().wheels("Continental").autobody("Audi").build();
        System.out.println(car.getAutobody());
        
        Car myCar = new Car();
        myCar.setAutobody("VW");
        myCar.setWheels("Bridgestone");
        
        System.out.println(myCar.getAutobody());
        
        try {
            // Throwing a CustomException using the Builder
            throw new CustomException.CustomExceptionBuilder("Something went wrong")
                    .errorCode(404)
                    .details("Resource not found: /api/data")
                    .build();
        } catch (CustomException e) {
            // Handling the exception
            System.out.println("Caught CustomException:");
            System.out.println("Message: " + e.getMessage());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Details: " + e.getDetails());
        }

        try {
            // Throwing another CustomException with minimal info
            throw new CustomException.CustomExceptionBuilder("Critical Error").build();
        } catch (CustomException e) {
            System.out.println("\nCaught Minimal CustomException:");
            System.out.println(e);
        }
	}
}