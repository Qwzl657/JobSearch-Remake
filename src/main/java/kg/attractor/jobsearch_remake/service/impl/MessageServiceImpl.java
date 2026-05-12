package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.exception.MessageNotFoundException;
import kg.attractor.jobsearch_remake.model.Message;
import kg.attractor.jobsearch_remake.repository.MessageRepository;
import kg.attractor.jobsearch_remake.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message getById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Сообщение не найдено с id: {}", id);
                    return new MessageNotFoundException();
                });
    }
}