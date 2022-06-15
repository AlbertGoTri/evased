package edu.url.salle.albert.gt.evased.lab;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.url.salle.albert.gt.evased.entities.Event;

public class EventLab {

    private ArrayList<Event> events;

    public EventLab( ArrayList<User> users){
        events = new ArrayList<>();
        events.add(new Event("Justin fever Concert","get sick with your friends",
                new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime(), users.get(0)));
        events.add(new Event("El Intermedio show","a reventar negritos",
                new GregorianCalendar(2021, Calendar.JANUARY, 14).getTime(), users.get(0)));
        events.add(new Event("IT film","get sick with your friends",
                new GregorianCalendar(2023, Calendar.JULY, 1).getTime(), users.get(0)));
        events.add(new Event("Dolor y dinero film","get sick with your friends",
                new GregorianCalendar(2022, Calendar.DECEMBER, 23).getTime(), users.get(1)));
        events.add(new Event("Gallinas en Marte documentary","get sick with your friends",
                new GregorianCalendar(2024, Calendar.JUNE, 3).getTime(), users.get(2)));
        events.add(new Event("Who is my father? TV SHOW","get sick with your friends",
                new GregorianCalendar(2023, Calendar.MARCH, 26).getTime(), users.get(3)));
        events.add(new Event("DOWNTOWN the trilogy","get sick with your friends",
                new GregorianCalendar(2022, Calendar.APRIL, 21).getTime(), users.get(4)));
        events.add(new Event("Harry McGuire, the best Defendant Documentary","get sick with your friends",
                new GregorianCalendar(2025, Calendar.OCTOBER, 18).getTime(), users.get(5)));
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
