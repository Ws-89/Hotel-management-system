package pl.siuda.hotel.hotel;

import org.springframework.data.relational.core.mapping.Column;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OpeningTimes {
    @Column("day_of_week")
    private DayOfWeek dayOfWeek;
    private LocalTime from_data;
    private LocalTime to_data;

    public OpeningTimes(DayOfWeek dayOfWeek, LocalTime from_data, LocalTime to_data) {
        this.dayOfWeek = dayOfWeek;
        this.from_data = from_data;
        this.to_data = to_data;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getFrom_data() {
        return from_data;
    }

    public void setFrom_data(LocalTime from_data) {
        this.from_data = from_data;
    }

    public LocalTime getTo_data() {
        return to_data;
    }

    public void setTo_data(LocalTime to_data) {
        this.to_data = to_data;
    }
}
