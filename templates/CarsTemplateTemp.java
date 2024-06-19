import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.Collection;
import java.util.Optional;

public class CarsTemplateTemp {

    public static void main(String[] args) {

        List<Car> cars = generateCars();

        // REPLACE_WITH_QUESTIONS
        // 1-1=Using stream and Collectors, create a List<String> of all models of cars
        List<String> models = cars.stream().map(Car::getModel).collect(Collectors.toList());
        // 1-2=Using stream and Collectors, create a List<Integer> of all ids of cars
        List<Integer> ids = cars.stream().map(Car::getId).collect(Collectors.toList());
        // 2-1=Using stream, filter, and Collectors, create a List<Integer> of all even horsepowers
        List<Integer> evenHorsepowers = cars.stream().map(Car::getHorsepower).filter(h -> h % 2 == 0).collect(Collectors.toList());
        // 2-2=Using stream, filter, and Collectors, create a List<Integer> of all odd horsepowers
        List<Integer> oddHorsepowers = cars.stream().map(Car::getHorsepower).filter(h -> h % 2 != 0).collect(Collectors.toList());
        // 3-1=Using stream, limit, distinct, and Collectors, create a List<String> of distinct brands of cars but limit the size of List into 3 
        List<String> brandsLimit3 = cars.stream().map(Car::getBrand).distinct().limit(3).collect(Collectors.toList());
        // 3-2=Using stream, limit, distinct, and Collectors, create a List<Double> of distinct prices of cars but limit the size of List into 4 
        List<Double> pricesLimit4 = cars.stream().map(Car::getPrice).distinct().limit(4).collect(Collectors.toList());
        // 4-1=Using stream, limit, distinct, sort, and Collectors, create a List<String> of distinct brands sorted alphabetically and limit the size into 5
        List<String> uniqueSortedBrandsLimit5 = cars.stream().map(Car::getBrand).distinct().sorted().limit(5).collect(Collectors.toList());
        // 4-2=Using stream, limit, distinct, sort, and Collectors, create a List<Integer> of distinct prices sorted from highest to lowest and limit the size into 3 
        List<Double> uniqueReversedPricesLimit3 = cars.stream().map(Car::getPrice).distinct().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        // 5-1=Using stream and allMatch, verify if all the cars' horsepower are above 500
        boolean areCarsAbove500hp = cars.stream().map(Car::getHorsepower).allMatch(h -> h > 500);
        // 5-2=Using stream and allMatch, verify if all the cars' prices are above 2000000.00 
        boolean areCarsAbove2000000price = cars.stream().map(Car::getPrice).allMatch(p -> p > 2000000);
        // 6-1=Using stream and anyMatch, verify if there is a car with a brand of Czinger
        boolean doesCzingerBrandExists = cars.stream().map(Car::getBrand).anyMatch(b -> b.equalsIgnoreCase("Czinger"));
        // 6-2=Using stream and anyMatch, verify if there is a Pagani Zonda car from the list 
        boolean doesPaganiZondaModelExists = cars.stream().map(Car::getModel).anyMatch(m -> m.equalsIgnoreCase("Pagani Zonda"));
        // 7-1=Using stream and noneMatch, verify if there are no 815 horspower from the car list 
        boolean does815hpNotExists = cars.stream().map(Car::getHorsepower).noneMatch(h -> h == 815);
        // 7-2=Using stream and noneMatch, verify if there are no cars with V8 engine
        boolean doesV8EngineNotExists = cars.stream().map(Car::getEngines).flatMap(Collection::stream).noneMatch(e -> e.equalsIgnoreCase("V8"));
        // 8-1=Using stream and skip, create a List<String> of models alphabetically but skip the first 3
        List<String> modelsSortedSkip3 = cars.stream().map(Car::getModel).sorted().skip(3).collect(Collectors.toList());
        // 8-2=Using stream and skip, create a List<Double> of prices from highest to lowest but skip the first 2 
        List<Double> pricesReversedSkip2 = cars.stream().map(Car::getPrice).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        // 9-1=Using stream, sort, and Comparator sort the list of Cars by horsepower from lowest to highest
        List<Car> sortedCarsByHoresepower = cars.stream().sorted(Comparator.comparing(Car::getHorsepower)).collect(Collectors.toList());
        // 9-2=Using stream, sort, and Comparator, sort the list of Cars by horspower from highest to lowest
        List<Car> sortedCarsByHorspowerReversed = cars.stream().sorted(Comparator.comparing(Car::getHorsepower, Comparator.reverseOrder())).collect(Collectors.toList());
        // 10-1=Using stream and findAny, return any Car with a Rolls-Royce brand
        Optional<Car> rollsRoyceBrandedCar = cars.stream().filter(c -> "Rolls-Royce".equalsIgnoreCase(c.getBrand())).findAny();
        // 10-2=Using stream and findAny, return any Car with a horsepower greater than 500 but less then 1000
        Optional<Car> carWith500to100Hp = cars.stream().filter(c -> 500 <= c.getHorsepower() && 1000 >= c.getHorsepower()).findAny();
        // 11-1=Using stream and map, create a List<Integer> where the values will be the number of characters of the car model
        List<Integer> modelNumberOfCharacters = cars.stream().map(Car::getModel).map(String::length).collect(Collectors.toList());
        // 11-2=Using stream and map, create a List<Integer> where the values will be the number of engies
        List<Integer> numberOfEngines = cars.stream().map(Car::getEngines).map(List::size).collect(Collectors.toList());
        // 12-1=Using stream and reduce, return the sum of all prices of all cars
        double sumOfPrices = cars.stream().map(Car::getPrice).reduce(0.0, (p1, p2) -> p1 + p2);
        // 12-2=Using stream, distinct, and reduce, create a string which has all the distinct car brands 
        String formattedCarBrands = cars.stream().map(Car::getBrand).distinct().reduce("", (m1, m2) -> m1 + m2);
        // 13-1=Using stream, reduce, and Double.max, print the highest price of cars
        cars.stream().map(Car::getPrice).reduce(Double::max).ifPresent(System.out::println);;
        // 13-2=Using stream, reduce, and Double.min, print the lowest horsepower of cars
        cars.stream().map(Car::getHorsepower).reduce(Integer::min).ifPresent(System.out::println);
        // 14-1=Using stream and sum, compute the sum of all horsepowers of cars
        System.out.println(cars.stream().mapToInt(Car::getHorsepower).sum());
        // 14-2=Using stream and min, print the minimum car price
        cars.stream().mapToDouble(Car::getPrice).min().ifPresent(System.out::println);;
        // 15-1=Using IntStream and range, print all the odd numbers from 1 to 10
        IntStream.range(1,10).filter(n -> n % 2 != 0).forEach(System.out::println);
        // 15-2=Using IntStream and range, pring all the even numbers from 1 to 10
        IntStream.range(1, 10).filter(n -> n %2 == 0).forEach(System.out::println);
        // 16-1=Using Stream.of, create a Stream<Integer> where values are 1, 2, 3, 4
        Stream<Integer> streamOfIntegert1to4 = Stream.of(1,2,3,4);
        // 16-2=Using Stream.of, create a Stream<Double> where values are 2.0, 4.0, 6.0, 8.0
        Stream<Double> streamOfDouble2to8 = Stream.of(2.0, 4.0, 6.0, 8.0);
        // 17-1=Using Stream.iterate, create a Stream<Integer> with multiples of 5
        Stream<Integer> multiplesOf5 = Stream.iterate(5, n -> n + 5);
        // 17-2=Using Stream.iterate, create a Stream<Double> with multiples of 2.5 
        Stream<Double> multiplesOf2p5 = Stream.iterate(2.5, n -> n + 2.5);
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
                        .setEngines(Arrays.asList("V8", "3994cc", "twin-turbo")) .build();

        Car car2 =
                new Car.CarBuilder()
                        .setId(2)
                        .setModel("Czinger 21C")
                        .setBrand("Czinger")
                        .setPrice(1700000.00)
                        .setHorsepower(1250)
                        .setEngines(Arrays.asList("V8", "twin-turbo"))
                        .build();

        Car car3 =
                new Car.CarBuilder()
                        .setId(3)
                        .setModel("Ferrari Monza")
                        .setBrand("Ferrari S.p.A.")
                        .setPrice(1700000.00)
                        .setHorsepower(809)
                        .setEngines(Arrays.asList("V12", "F140"))
                        .build();

        Car car4 =
                new Car.CarBuilder()
                        .setId(4)
                        .setModel("Gordon Murray T.33")
                        .setBrand("Gordon Murray Automotive")
                        .setPrice(1700000.00)
                        .setHorsepower(607)
                        .setEngines(Arrays.asList("V12"))
                        .build();

        Car car5 =
                new Car.CarBuilder()
                        .setId(5)
                        .setModel("Koenigsegg Gemera")
                        .setBrand("Koenigsegg")
                        .setPrice(1700000.00)
                        .setHorsepower(1700)
                        .setEngines(Arrays.asList("V8", "twin-turbo"))
                        .build();

        Car car6 =
                new Car.CarBuilder()
                        .setId(6)
                        .setModel("Hennessey Venom F5")
                        .setBrand("Hennessey Performance Engineering")
                        .setPrice(1800000.00)
                        .setHorsepower(1817)
                        .setEngines(Arrays.asList("V8", "twin-turbo"))
                        .build();

        Car car7 =
                new Car.CarBuilder()
                        .setId(7)
                        .setModel("Bentley Bacalar")
                        .setBrand("Bentley Motors")
                        .setPrice(1900000.00)
                        .setHorsepower(650)
                        .setEngines(Arrays.asList("W12", "twin-turbo"))
                        .build();

        Car car8 =
                new Car.CarBuilder()
                        .setId(8)
                        .setModel("Hispano Suiza Carmen Boulogne")
                        .setBrand("Hispano Suiza")
                        .setPrice(1900000.00)
                        .setHorsepower(650)
                        .setEngines(Arrays.asList("2 Electric Motors"))
                        .build();

        Car car9 =
                new Car.CarBuilder()
                        .setId(9)
                        .setModel("Bentley Mulliner Batur")
                        .setBrand("Bentley Motors")
                        .setPrice(2000000.00)
                        .setHorsepower(710)
                        .setEngines(Arrays.asList("V12", "twin-turbo"))
                        .build();

        Car car10 =
                new Car.CarBuilder()
                        .setId(10)
                        .setModel("Rolls-Royce La Rose Noire Droptail")
                        .setBrand("Rolls-Royce")
                        .setPrice(30000000.00)
                        .setHorsepower(602)
                        .setEngines(Arrays.asList("V12"))
                        .build();

        Car car11 =
                new Car.CarBuilder()
                        .setId(11)
                        .setModel("Rolls-Royce Boat Tail")
                        .setBrand("Rolls-Royce")
                        .setPrice(28000000.00)
                        .setHorsepower(563)
                        .setEngines(Arrays.asList("V12", "twin-turbo"))
                        .build();

        Car car12 =
                new Car.CarBuilder()
                        .setId(12)
                        .setModel("Bugatti La Voiture Noire")
                        .setBrand("Bugatti Automobiles")
                        .setPrice(13400000.00)
                        .setHorsepower(1500)
                        .setEngines(Arrays.asList("7993cc"))
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
    private List<String> engines;

    public Car(CarBuilder builder) {

        this.id = builder.id;
        this.model = builder.model;
        this.brand = builder.brand;
        this.price = builder.price;
        this.horsepower = builder.horsepower;
        this.engines = builder.engines;
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

    public List<String> getEngines(){

        return this.engines;
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
                + ", \"engine\" : "
                + engines
                + "}}";
    }

    public static class CarBuilder {

        private Integer id;
        private String model;
        private String brand;
        private Double price;
        private Integer horsepower;
        private List<String> engines;

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

        public CarBuilder setEngines(List<String> engines){

            this.engines = engines;
            return this;
        }

        public Car build() {

            return new Car(this);
        }
    }
}
