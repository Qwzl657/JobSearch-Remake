package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.model.ContactType;
import kg.attractor.jobsearch_remake.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;

    public List<ContactType> getAll() {
        log.info("Fetching all contact types");
        return contactTypeRepository.findAll();
    }

    public ContactType getById(Integer id) {
        log.info("Fetching contact type by id: {}", id);
        return contactTypeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Contact type not found with id: {}", id);
                    return new NoSuchElementException("Contact type not found: " + id);
                });
    }
}