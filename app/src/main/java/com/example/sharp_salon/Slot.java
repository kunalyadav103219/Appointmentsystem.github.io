package com.example.sharp_salon;


import java.util.Date;

public class Slot {
    private Date date;
    private String time;
    private String venue;
    private boolean booked;

    public void TimeSlot() {
        // Required empty constructor for Firestore
    }

    public void TimeSlot(Date date, String time, String venue) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.booked = false;
    }

    // Getters and setters
}
