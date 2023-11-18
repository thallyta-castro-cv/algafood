package br.com.thallyta.algafood.services;

import br.com.thallyta.algafood.models.Request;
import br.com.thallyta.algafood.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ChangeStatusRequestService {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestRepository requestRepository;

    @Transactional
    public void confirmRequest(String code){
        Request request = requestService.findOrFail(code);
        request.confirmRequest();
        requestRepository.save(request);
    }

    @Transactional
    public void cancelRequest(String code) {
        Request request = requestService.findOrFail(code);
        request.cancelRequest();
        requestRepository.save(request);
    }

    @Transactional
    public void deliverRequest(String code) {
        Request request = requestService.findOrFail(code);
        request.deliverRequest();
    }
}
