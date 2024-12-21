package com.journalApp.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.journalApp.entity.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    /*
     * This hash map acting as database
     */
    public Map<Long,JournalEntry>journalEntries= new HashMap<>(); 

    @GetMapping("/entry") // or can send @GetMapping without endpoint if request mapping given
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("/entry")
    public boolean createEntry(@RequestBody JournalEntry mEntry){
        journalEntries.put(mEntry.getId(), mEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @PutMapping("id/{myId}")
    public boolean updateEntry(@PathVariable Long myId, @RequestBody JournalEntry mEntry){
        journalEntries.put(myId, mEntry);
        return true;
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntry(@PathVariable Long myId){
        journalEntries.remove(myId);
        return true;
    }
}
