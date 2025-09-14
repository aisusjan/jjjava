// Engine interface
interface Engine {
    String getType();
}

// Concrete engine classes
class SportEngine implements Engine {
    @Override
    public String getType() {
        return "Sport Engine";
    }
}

class DieselEngine implements Engine {
    @Override
    public String getType() {
        return "Diesel Engine";
    }
}

// Car class with Builder pattern
public class Car {

    // Private final fields – immutability
    private final int seats;
    private final Engine engine;
    private final boolean hasGPS;
    private final boolean hasTripComputer;

    // Private constructor – only Builder can create Car
    private Car(Builder builder) {
        this.seats = builder.seats;
        this.engine = builder.engine;
        this.hasGPS = builder.hasGPS;
        this.hasTripComputer = builder.hasTripComputer;
    }

    // Getters – no setters to maintain immutability
    public int getSeats() { return seats; }
    public Engine getEngine() { return engine; }
    public boolean hasGPS() { return hasGPS; }
    public boolean hasTripComputer() { return hasTripComputer; }

    @Override
    public String toString() {
        return "Car{" +
                "seats=" + seats +
                ", engine=" + engine.getType() +
                ", hasGPS=" + hasGPS +
                ", hasTripComputer=" + hasTripComputer +
                '}';
    }

    // Static Builder class
    public static class Builder {
        private int seats;
        private Engine engine;
        private boolean hasGPS;
        private boolean hasTripComputer;

        // Builder methods with fluent API
        public Builder setSeats(int seats) {
            this.seats = seats;
            return this;
        }

        public Builder setEngine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public Builder setGPS(boolean hasGPS) {
            this.hasGPS = hasGPS;
            return this;
        }

        public Builder setTripComputer(boolean hasTripComputer) {
            this.hasTripComputer = hasTripComputer;
            return this;
        }

        // Build method – validate inputs if needed
        public Car build() {
            if (engine == null) {
                throw new IllegalStateException("Engine must be set!");
            }
            if (seats <= 0) {
                throw new IllegalStateException("Seats must be greater than 0!");
            }
            return new Car(this);
        }
    }

    // Demo main method
    public static void main(String[] args) {
        Car sportsCar = new Car.Builder()
                .setSeats(2)
                .setEngine(new SportEngine())
                .setGPS(true)
                .setTripComputer(true)
                .build();

        Car familyCar = new Car.Builder()
                .setSeats(5)
                .setEngine(new DieselEngine())
                .setGPS(false)
                .setTripComputer(false)
                .build();

        System.out.println(sportsCar);
        System.out.println(familyCar);
    }
}
