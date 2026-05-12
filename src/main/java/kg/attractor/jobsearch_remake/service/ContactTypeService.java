package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.model.ContactType;

import java.util.List;

public interface ContactTypeService {
    List<ContactType> getAll();
    ContactType getById(Integer id);
}