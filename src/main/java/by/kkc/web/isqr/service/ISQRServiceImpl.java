package by.kkc.web.isqr.service;

import java.util.Collection;

import by.kkc.web.isqr.model.*;
import by.kkc.web.isqr.repository.CommentRepository;
import by.kkc.web.isqr.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import by.kkc.web.isqr.model.Response;
import by.kkc.web.isqr.repository.ResponseRepository;
import by.kkc.web.isqr.repository.StatisticRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all IS-QR controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 */
@org.springframework.stereotype.Service
public class ISQRServiceImpl implements ISQRService {

    private ResponseRepository responseRepository;
    private CommentRepository commentRepository;
    private ServiceRepository serviceRepository;
    private StatisticRepository statisticRepository;

    @Autowired
    public ISQRServiceImpl(ResponseRepository responseRepository, CommentRepository commentRepository, ServiceRepository serviceRepository, StatisticRepository statisticRepository) {
        this.responseRepository = responseRepository;
        this.commentRepository = commentRepository;
        this.serviceRepository = serviceRepository;
        this.statisticRepository = statisticRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PetType> findPetTypes() throws DataAccessException {
        return responseRepository.findPetTypes();
    }

    @Override
    @Transactional(readOnly = true)
    public Service findOwnerById(int id) throws DataAccessException {
        return serviceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Service> findOwnerByLastName(String lastName) throws DataAccessException {
        return serviceRepository.findByLastName(lastName);
    }

    @Override
    @Transactional
    public void saveOwner(Service service) throws DataAccessException {
        serviceRepository.save(service);
    }


    @Override
    @Transactional
    public void saveVisit(Statistic statistic) throws DataAccessException {
        statisticRepository.save(statistic);
    }


    @Override
    @Transactional(readOnly = true)
    public Response findPetById(int id) throws DataAccessException {
        return responseRepository.findById(id);
    }

    @Override
    @Transactional
    public void savePet(Response response) throws DataAccessException {
        responseRepository.save(response);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "vets")
    public Collection<Comment> findVets() throws DataAccessException {
        return commentRepository.findAll();
    }


}
