package web.model;



public class Car {

    private String model;
    private String engine;
    private int yearOfManufacture;

    public Car() {
    }

    public Car(String model, String engine, int yearOfManufacture) {
        this.model = model;
        this.engine = engine;
        this.yearOfManufacture = yearOfManufacture;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                '}';
    }
}
