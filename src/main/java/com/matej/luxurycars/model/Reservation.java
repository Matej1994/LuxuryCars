package com.matej.luxurycars.model;

/**
 * Created by Mateusz on 2017-09-27.
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="car_id")
    private Car car;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "start_date", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "expiration_date", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date expirationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
