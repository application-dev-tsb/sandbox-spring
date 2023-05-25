package dev.codefactory.sandbox.core.usecase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

public class CarRental {
    public static Boolean canScheduleAll(Collection<RentalTime> rentalTimes) {
        var sorted = rentalTimes.stream().sorted(Comparator.comparing(RentalTime::getStart)).toList();

        RentalTime current = null;
        for (RentalTime rentalTime : sorted) {
            if (current==null) current = rentalTime;
            if (current.conflictsWith(rentalTime)) return false;
            current = rentalTime;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/y H:m");

        ArrayList<RentalTime> rentalTimes = new ArrayList<RentalTime>();
        rentalTimes.add(new RentalTime(sdf.parse("03/05/2020 19:00"), sdf.parse("03/05/2020 20:30")));
        rentalTimes.add(new RentalTime(sdf.parse("03/05/2020 22:10"), sdf.parse("03/05/2020 22:30")));
        rentalTimes.add(new RentalTime(sdf.parse("03/05/2020 20:30"), sdf.parse("03/05/2020 22:00")));

        System.out.println(CarRental.canScheduleAll(rentalTimes));
    }
}

class RentalTime {
    private Date start, end;

    public RentalTime(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    public boolean conflictsWith(RentalTime other) {
        if (other.start.after(start) && other.start.before(end)) {
            return true;
        }

        if (other.end.after(start) && other.end.before(end)) {
            return true;
        }

        return false;
    }
}
