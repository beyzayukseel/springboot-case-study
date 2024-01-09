package com.beyzanur.simpracasestudy.entity;

import com.beyzanur.simpracasestudy.model.ReservationResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String confirmationNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @OneToOne(fetch = FetchType.EAGER)
    private RateCode rateCode;
    @OneToOne(fetch = FetchType.EAGER)
    private RoomCode roomCode;
    private LocalDateTime checkinDate;
    private LocalDateTime checkoutDate;
    private String status;
    private String created;
    private String updated;
    private int totalPax;

    public ReservationResponse convertToReservationResponse() {
        return new ReservationResponse(
                this.getCustomer().getFirstName(),
                this.getCustomer().getLastName(),
                this.getCustomer().getEmail(),
                this.getCustomer().getPhone(),
                this.getId().toString(),
                this.getStatus(),
                this.getCheckinDate().toString(),
                this.getCheckoutDate().toString(),
                this.getRoomCode().getCode(),
                this.getRateCode().getCode(),
                this.getTotalPax()
        );
    }
}
