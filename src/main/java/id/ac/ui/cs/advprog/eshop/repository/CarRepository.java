package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository extends GenericRepository<Car> {
    @Override
    protected String getId(Car car) {
        return car.getCarId();
    }

    @Override
    protected void setId(Car car, String id) {
        car.setCarId(id);
    }
}
