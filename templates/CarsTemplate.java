import java.util.ArrayList;
import java.util.List;

public class CarsTemplate {

    public static void main(String[] args) {

        List<Car> cars = generateCars();

        // REPLACE_WITH_QUESTIONS
    }

    private static List<Car> generateCars() {

        List<Car> cars = new ArrayList<>();

        Car car1 =
                new Car.CarBuilder()
                        .setId(1)
                        .setModel("McLaren Senna GTR")
                        .setBrand("McLaren Automotive")
                        .setPrice(1700000.00)
                        .setHorsepower(813)
                        .build();

        Car car2 =
                new Car.CarBuilder()
                        .setId(2)
                        .setModel("Czinger 21C")
                        .setBrand("Czinger")
                        .setPrice(1700000.00)
                        .setHorsepower(1250)
                        .build();

        Car car3 =
                new Car.CarBuilder()
                        .setId(3)
                        .setModel("Ferrari Monza")
                        .setBrand("Ferrari S.p.A.")
                        .setPrice(1700000.00)
                        .setHorsepower(809)
                        .build();

        Car car4 =
                new Car.CarBuilder()
                        .setId(4)
                        .setModel("Gordon Murray T.33")
                        .setBrand("Gordon Murray Automotive")
                        .setPrice(1700000.00)
                        .setHorsepower(607)
                        .build();

        Car car5 =
                new Car.CarBuilder()
                        .setId(5)
                        .setModel("Koenigsegg Gemera")
                        .setBrand("Koenigsegg")
                        .setPrice(1700000.00)
                        .setHorsepower(1700)
                        .build();

        Car car6 =
                new Car.CarBuilder()
                        .setId(6)
                        .setModel("Hennessey Venom F5")
                        .setBrand("Hennessey Performance Engineering")
                        .setPrice(1800000.00)
                        .setHorsepower(1817)
                        .build();

        Car car7 =
                new Car.CarBuilder()
                        .setId(7)
                        .setModel("Bentley Bacalar")
                        .setBrand("Bentley Motors")
                        .setPrice(1900000.00)
                        .setHorsepower(650)
                        .build();

        Car car8 =
                new Car.CarBuilder()
                        .setId(8)
                        .setModel("Hispano Suiza Carmen Boulogne")
                        .setBrand("Hispano Suiza")
                        .setPrice(1900000.00)
                        .setHorsepower(650)
                        .build();

        Car car9 =
                new Car.CarBuilder()
                        .setId(9)
                        .setModel("Bentley Mulliner Batur")
                        .setBrand("Bentley Motors")
                        .setPrice(2000000.00)
                        .setHorsepower(710)
                        .build();

        Car car10 =
                new Car.CarBuilder()
                        .setId(10)
                        .setModel("Rolls-Royce La Rose Noire Droptail")
                        .setBrand("Rolls-Royce")
                        .setPrice(30000000.00)
                        .setHorsepower(602)
                        .build();

        Car car11 =
                new Car.CarBuilder()
                        .setId(11)
                        .setModel("Rolls-Royce Boat Tail")
                        .setBrand("Rolls-Royce")
                        .setPrice(28000000.00)
                        .setHorsepower(563)
                        .build();

        Car car12 =
                new Car.CarBuilder()
                        .setId(11)
                        .setModel("Bugatti La Voiture Noire")
                        .setBrand("Bugatti Automobiles")
                        .setPrice(13400000.00)
                        .setHorsepower(1500)
                        .build();

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        cars.add(car6);
        cars.add(car7);
        cars.add(car8);
        cars.add(car9);
        cars.add(car10);
        cars.add(car11);
        cars.add(car12);

        return cars;
    }
}

class Car {

    private Integer id;
    private String model;
    private String brand;
    private Double price;
    private Integer horsepower;

    public Car(CarBuilder builder) {

        this.id = builder.id;
        this.model = builder.model;
        this.brand = builder.brand;
        this.price = builder.price;
        this.horsepower = builder.horsepower;
    }

    public Integer getId() {

        return this.id;
    }

    public String getModel() {

        return this.model;
    }

    public String getBrand() {

        return this.brand;
    }

    public Double getPrice() {

        return this.price;
    }

    public Integer getHorsepower() {

        return this.horsepower;
    }

    @Override
    public String toString() {

        return "{\"Car\": {\"id\" : "
                + id
                + ", \"model\" : "
                + model
                + ", \"brand\" : "
                + brand
                + ", \"price\" : "
                + price
                + ", \"horsepower\" : "
                + horsepower
                + "}}";
    }

    public static class CarBuilder {

        private Integer id;
        private String model;
        private String brand;
        private Double price;
        private Integer horsepower;

        public CarBuilder setId(Integer id) {

            this.id = id;
            return this;
        }

        public CarBuilder setModel(String model) {

            this.model = model;
            return this;
        }

        public CarBuilder setBrand(String brand) {

            this.brand = brand;
            return this;
        }

        public CarBuilder setPrice(Double price) {

            this.price = price;
            return this;
        }

        public CarBuilder setHorsepower(Integer horsepower) {

            this.horsepower = horsepower;
            return this;
        }

        public Car build() {

            return new Car(this);
        }
    }
}
