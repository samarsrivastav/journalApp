package com.journalApp.journalApp.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<JournalEntry> getAll(){
        System.out.println(journalEntryService.getEntries());
        return journalEntryService.getEntries();
    }

    @PostMapping("/entry")
    public boolean createEntry(@RequestBody JournalEntry mEntry){
        journalEntryService.saveEntry(mEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.getById(myId).orElse(null);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry mEntry){
        JournalEntry old = journalEntryService.getById(myId).orElse(null);
        if (old!=null) {
            old.setTitle(mEntry.getTitle()!=null && mEntry.getTitle()!=""?mEntry.getTitle():old.getTitle());
            old.setContent(mEntry.getContent()!=null && mEntry.getContent()!=""?mEntry.getContent():old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }
}
