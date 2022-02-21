package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    private static List<Car> carList = new ArrayList<>();

    static {
        carList.add(new Car("Ford Fusion", "1.5", 2016));
        carList.add(new Car("Peugeot 406", "2.0", 2002));
        carList.add(new Car("BMW e46", "2.0", 2004));
        carList.add(new Car("WV Passat", "1.8", 2011));
        carList.add(new Car("Skoda Octavia", "1.4", 2015));
    }


    public static List<Car> getSomeCars(int count) {
        if (count > -1 && count < carList.size()) {
            return carList.stream().limit(count).collect(Collectors.toList());
        } else {
            return carList;
        }
    }
}
