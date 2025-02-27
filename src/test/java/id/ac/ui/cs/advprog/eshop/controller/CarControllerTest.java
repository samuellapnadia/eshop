package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

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
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        assertEquals("CreateCar", viewName); 
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        String viewName = carController.createCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carService, times(1)).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        when(carService.findAll()).thenReturn(cars);

        String viewName = carController.carListPage(model);
        assertEquals("CarList", viewName);
        verify(model, times(1)).addAttribute("cars", cars);
    }

    @Test
    void testEditCarPage() {
        when(carService.findById("1")).thenReturn(car);

        String viewName = carController.editCarPage("1", model);
        assertEquals("EditCar", viewName);
        verify(model, times(1)).addAttribute("car", car);
    }

    @Test
    void testEditCarPost() {
        String viewName = carController.editCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carService, times(1)).update(car.getCarId(), car);
    }

    @Test
    void testDeleteCar() {
        String viewName = carController.deleteCar("1");
        assertEquals("redirect:listCar", viewName);
        verify(carService, times(1)).deleteCarById("1");
    }
}
