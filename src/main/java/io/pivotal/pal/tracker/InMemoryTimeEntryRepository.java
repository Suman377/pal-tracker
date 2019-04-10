package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> inMemoryTimeEntryDB = new HashMap<>();

    int i;

    public TimeEntry create(TimeEntry timeEntry) {
        if (0L == timeEntry.getId()) {
            timeEntry.setId(new Long(++i));
        }
        inMemoryTimeEntryDB.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }


    public TimeEntry find(long id) {
        return inMemoryTimeEntryDB.get(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        Set<Long> keySet = inMemoryTimeEntryDB.keySet();
        for (Long key : keySet) {
            list.add(inMemoryTimeEntryDB.get(key));
        }
        return list;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        TimeEntry temp = inMemoryTimeEntryDB.get(id);
        if (temp == null) {
            return null;
        } else {
            if (0L == id) {
                timeEntry.setId(new Long(++i));
            } else {
                timeEntry.setId(id);
            }
            inMemoryTimeEntryDB.put(id, timeEntry);
        }

        return timeEntry;
    }

    public void delete(long id) {

        inMemoryTimeEntryDB.remove(id);
    }

}
