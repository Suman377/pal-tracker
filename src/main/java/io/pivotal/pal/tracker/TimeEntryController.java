package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping(produces = "application/json",consumes="application/json")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);

        if(null != timeEntry){
            return new ResponseEntity(timeEntry,HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList =   timeEntryRepository.list();
        return new ResponseEntity(timeEntryList,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntryResponse = timeEntryRepository.update(id,timeEntry);
        if(null != timeEntryResponse) {
            return new ResponseEntity(timeEntryResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {

        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
