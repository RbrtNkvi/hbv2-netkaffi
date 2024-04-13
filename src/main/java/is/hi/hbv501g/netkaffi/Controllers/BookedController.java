package is.hi.hbv501g.netkaffi.Controllers;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Booking;
import is.hi.hbv501g.netkaffi.Persistence.Entities.BookingDTO;
import is.hi.hbv501g.netkaffi.Persistence.Entities.Product;
import is.hi.hbv501g.netkaffi.Persistence.Entities.User;
import is.hi.hbv501g.netkaffi.Services.BookingService;
import is.hi.hbv501g.netkaffi.Services.ProductService;
import is.hi.hbv501g.netkaffi.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookedController {

    BookingService bookedService;
    ProductService productService;
    UserService userService;

    public BookedController(BookingService bookedService, ProductService productService, UserService userService){
        this.bookedService = bookedService;
        this.productService = productService;
        this.userService = userService;
    }

    /**
     *
     * @param user the user we want to find the bookings of
     * @return all bookings made by user/ all bookings if user is admin
     */

    @GetMapping("/booked/{username}")
    public List<BookingDTO> bookedGet(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        List<Booking> bookings;
        if (user.getIsAdmin()) {
            bookings = bookedService.findAll();
        } else {
            bookings = bookedService.findAllByUser(user);
        }
        List<BookingDTO> bookingDTOs = bookings.stream()
                .map(BookingDTO::new) // Convert Booking entities to DTOs
                .collect(Collectors.toList());
        return bookingDTOs;
    }

    /**
     * Deletion of a specific booking
     *
     * @param booking the booking to be deleted
     * @return the booking if successful else null
     */
    @PostMapping(value="/booked/delete",consumes = "application/json", produces = "application/json")
    public Booking bookedDelete(@RequestBody Booking booking){
        try{
            bookedService.delete(booking);
            return booking;
        } catch(Exception e){
            return null;
        }
    }
}
