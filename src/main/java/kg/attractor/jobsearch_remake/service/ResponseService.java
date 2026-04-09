package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ResponseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseDao responseDao;

    public void respond(Long userId, Long vacancyId) {
        responseDao.respond(userId, vacancyId);
    }
}