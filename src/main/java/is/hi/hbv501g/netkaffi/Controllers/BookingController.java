package is.hi.hbv501g.netkaffi.Controllers;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Booking;
import is.hi.hbv501g.netkaffi.Persistence.Entities.Product;
import is.hi.hbv501g.netkaffi.Persistence.Entities.User;
import is.hi.hbv501g.netkaffi.Services.BookingService;
import is.hi.hbv501g.netkaffi.Services.ProductService;
import is.hi.hbv501g.netkaffi.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class BookingController {
    BookingService bookingService;
    ProductService productService;
    UserService userService;

    public BookingController(BookingService bookingService, ProductService productService, UserService userService){
        this.bookingService = bookingService;
        this.productService = productService;
        this.userService = userService;
    }

    /**
     * Fetches booking information for a specific product,
     * fetches session attributes of user
     *
     * @param product which is being booked
     * @return direction to booking.html with information for product
     */
    @GetMapping("/book/{product}")
    public Product productGet(@PathVariable String product){
        return productService.findByName(product);
    }

    /**
     * Creates a new booking and inserts it into the PSQL database
     *
     * @param booking Booking object from json post request
     * @return json of the booking if successful
     */
    @PostMapping(value = "/book", consumes = "application/json", produces = "application/json")
    public Booking bookingPost(@RequestBody Booking booking){
        Product prod = productService.findByName(booking.getProduct().getName());
        User user = userService.findByUsername(booking.getUser().getUsername());
        Booking realBooking = new Booking(user,prod,booking.getStarttime());
        /*
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();
        
        if( booking.getStarttime() < today || booking.getProduct().isDeleted() ){
            return null;
        }
        if (bookingService.findByProductAndStarttime(booking.getProduct(),booking.getStarttime()) != null) {
            return null;
        }
        */
        return bookingService.save(realBooking);


    }
}
