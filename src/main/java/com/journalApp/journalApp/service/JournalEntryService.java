package com.journalApp.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journalApp.journalApp.entity.JournalEntry;
import com.journalApp.journalApp.entity.User;
import com.journalApp.journalApp.repository.JournalEntryRepository;

@Service
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry,String username){
        User user=userService.findByUsername(username);
        JournalEntry saved= journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.createUser(user);
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id,String username){
         User user= userService.findByUsername(username);
         user.getJournalEntries().removeIf(x->x.getId().equals(id));
         userService.createUser(user);
         journalEntryRepository.deleteById(id);
    }
}
