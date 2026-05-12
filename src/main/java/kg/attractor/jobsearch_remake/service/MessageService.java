package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAll();
    Message getById(Long id);
}