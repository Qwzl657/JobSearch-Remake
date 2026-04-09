package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ResumeDao;
import kg.attractor.jobsearch_remake.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeDao resumeDao;

    public List<Resume> getAll() {
        return resumeDao.findAll();
    }

    public List<Resume> getByCategory(String category) {
        return resumeDao.findByCategory(category);
    }
}