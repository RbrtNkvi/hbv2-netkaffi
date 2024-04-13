package is.hi.hbv501g.netkaffi.Persistence.Entities;

public class BookingDTO {
    private String userName;
    private String productName;
    private long startTime;

    public BookingDTO(String userName, String productName, long startTime) {
        this.userName = userName;
        this.productName = productName;
        this.startTime = startTime;
    }

    public BookingDTO(Booking booking) {
        this.userName = booking.getUser().getUsername(); // Assuming there's a method to get the user's name
        this.productName = booking.getProduct().getName(); // Assuming there's a method to get the product's name
        this.startTime = booking.getStarttime();
    }


    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}

