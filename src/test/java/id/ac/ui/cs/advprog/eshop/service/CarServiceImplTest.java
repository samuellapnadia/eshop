package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepositoryInterface carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("1");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(5);
    }

    @Test
    void testCreateCar() {
        when(carRepository.create(car)).thenReturn(car);
        Car createdCar = carService.create(car);
        assertEquals(car, createdCar);
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAll() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        Iterator<Car> carIterator = cars.iterator();

        when(carRepository.findAll()).thenReturn(carIterator);
        List<Car> result = carService.findAll();

        assertEquals(1, result.size());
        assertEquals(car, result.get(0));
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById("1")).thenReturn(car);
        Car foundCar = carService.findById("1");
        assertEquals(car, foundCar);
        verify(carRepository, times(1)).findById("1");
    }

    @Test
    void testUpdateCar() {
        doNothing().when(carRepository).update("1", car);
        carService.update("1", car);
        verify(carRepository, times(1)).update("1", car);
    }

    @Test
    void testDeleteCarById() {
        doNothing().when(carRepository).delete("1");
        carService.deleteCarById("1");
        verify(carRepository, times(1)).delete("1");
    }
}
