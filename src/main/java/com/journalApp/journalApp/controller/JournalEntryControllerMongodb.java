package com.journalApp.journalApp.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.journalApp.entity.JournalEntry;
import com.journalApp.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMongodb {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/entry")
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> entries = journalEntryService.getEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PostMapping("/entry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry mEntry) {
        try {
            journalEntryService.saveEntry(mEntry);
            return new ResponseEntity<>(mEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry mEntry) {
        Optional<JournalEntry> oldEntryOpt = journalEntryService.getById(myId);
        if (oldEntryOpt.isPresent()) {
            JournalEntry oldEntry = oldEntryOpt.get();
            oldEntry.setTitle(mEntry.getTitle() != null && !mEntry.getTitle().isEmpty() ? mEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(mEntry.getContent() != null && !mEntry.getContent().isEmpty() ? mEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<HttpStatus> deleteEntry(@PathVariable ObjectId myId) {
        try {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
