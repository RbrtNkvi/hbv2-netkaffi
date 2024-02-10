package is.hi.hbv501g.netkaffi.Controllers;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Booking;
import is.hi.hbv501g.netkaffi.Persistence.Entities.Product;
import is.hi.hbv501g.netkaffi.Persistence.Entities.User;
import is.hi.hbv501g.netkaffi.Services.BookingService;
import is.hi.hbv501g.netkaffi.Services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
public class BookedController {

    BookingService bookedService;
    ProductService productService;

    public BookedController(BookingService bookedService, ProductService productService){
        this.bookedService = bookedService;
        this.productService = productService;
    }

    /**
     *
     * @param user the user we want to find the bookings of
     * @return all bookings made by user/ all bookings if user is admin
     */
    @GetMapping(value="/booked")
    public List<Booking> bookedGet(@RequestBody User user){
        List<Booking> booked;
        if(user.getIsAdmin()){
            booked = bookedService.findAll();
        } else {
            booked = bookedService.findAllByUser(user);
        }
        return booked;
    }

    /**
     * Deletion of a specific booking
     *
     * @param booking the booking to be deleted
     * @return the booking if successful else null
     */
    @PostMapping(value="/booked",consumes = "application/json", produces = "application/json")
    public Booking bookedDelete(@RequestBody Booking booking){
        try{
            bookedService.delete(booking);
            return booking;
        } catch(Exception e){
            return null;
        }
    }
}
