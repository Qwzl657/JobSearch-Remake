package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.exception.ContactTypeNotFoundException;
import kg.attractor.jobsearch_remake.model.ContactType;
import kg.attractor.jobsearch_remake.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;

    public List<ContactType> getAll() {
        log.info("Получение всех типов контактов");
        return contactTypeRepository.findAll();
    }

    public ContactType getById(Integer id) {
        log.info("Получение типа контакта по id: {}", id);
        return contactTypeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Тип контакта не найден с id: {}", id);
                    return new ContactTypeNotFoundException();
                });
    }
}