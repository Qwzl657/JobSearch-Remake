package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.model.Message;
import kg.attractor.jobsearch_remake.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getByRespondedApplicantId(Integer respondedApplicantId) {
        log.info("Fetching messages for responded applicant id: {}", respondedApplicantId);
        return messageRepository.findByRespondedApplicants(respondedApplicantId);
    }

    public Message getById(Integer id) {
        log.info("Fetching message by id: {}", id);
        return messageRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Message not found with id: {}", id);
                    return new NoSuchElementException("Message not found: " + id);
                });
    }

    public void send(Integer respondedApplicantId, String content) {
        log.info("Sending message for responded applicant id: {}", respondedApplicantId);
        Message message = Message.builder()
                .respondedApplicants(respondedApplicantId)
                .content(content)
                .timestamp(LocalDate.now())
                .build();
        messageRepository.save(message);
    }

    public void delete(Integer id) {
        log.warn("Deleting message id: {}", id);
        messageRepository.deleteById(id);
    }
}