package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.exception.MessageNotFoundException;
import kg.attractor.jobsearch_remake.model.Message;
import kg.attractor.jobsearch_remake.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getAll() {
        log.info("Получение всех сообщений");
        return messageRepository.findAll();
    }

    public Message getById(Long id) {
        log.info("Получение сообщения по id: {}", id);
        return messageRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Сообщение не найдено с id: {}", id);
                    return new MessageNotFoundException();
                });
    }
}