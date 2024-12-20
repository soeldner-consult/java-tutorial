package builder;

public class House {
    private String foundation;
    private String structure;
    private String roof;
    private int windows;
    private int doors;

    // Private Constructor to prevent direct instantiation, the inner builder class calls it
    private House() { }

    // Getters
    public String getFoundation() { return foundation; }
    public String getStructure() { return structure; }
    public String getRoof() { return roof; }
    public int getWindows() { return windows; }
    public int getDoors() { return doors; }

    // Setters - private, only accessible to the builder
    private void setFoundation(String foundation) { this.foundation = foundation; }
    private void setStructure(String structure) { this.structure = structure; }
    private void setRoof(String roof) { this.roof = roof; }
    private void setWindows(int windows) { this.windows = windows; }
    private void setDoors(int doors) { this.doors = doors; }

    @Override
    public String toString() {
        return "House{" +
                "foundation='" + foundation + '\'' +
                ", structure='" + structure + '\'' +
                ", roof='" + roof + '\'' +
                ", windows=" + windows +
                ", doors=" + doors +
                '}';
    }
    
    // https://softwareengineering.stackexchange.com/questions/225207/why-should-a-builder-be-an-inner-class-instead-of-in-its-own-class-file
    // Static Builder Class
    public static class HouseBuilder {
        private final House house;

        public HouseBuilder() {
        	// the inner static class can use the private constructor!
            this.house = new House();
        }

        public HouseBuilder foundation(String foundation) {
            // written in the script but house.setFoundation works as well
        	this.house.setFoundation(foundation);
            // return this is however essential for the builder pattern
            return this;
        }

        public HouseBuilder structure(String structure) {
            this.house.setStructure(structure);
            return this;
        }

        public HouseBuilder roof(String roof) {
            this.house.setRoof(roof);
            return this;
        }

        public HouseBuilder windows(int windows) {
            this.house.setWindows(windows);
            return this;
        }

        public HouseBuilder doors(int doors) {
            this.house.setDoors(doors);
            return this;
        }

        public House build() {
            return this.house;
        }
    }
}