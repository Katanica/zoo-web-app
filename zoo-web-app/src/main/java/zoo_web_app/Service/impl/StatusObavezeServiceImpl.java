package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.StatusObaveze;
import zoo_web_app.Repository.StatusObavezeRepository;
import zoo_web_app.Service.StatusObavezeService;

import java.util.List;

@Service
public class StatusObavezeServiceImpl implements StatusObavezeService {
    private final StatusObavezeRepository repository;

    public StatusObavezeServiceImpl(StatusObavezeRepository repository){
        this.repository = repository;
    }
    @Override
    public List<StatusObaveze> findAll(){
        return repository.findAll();
    }
    @Override
    public StatusObaveze findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("StatusObaveze nije pronaden: " + id));
    }
    @Override
    public StatusObaveze create(StatusObaveze statusObaveze){
        return repository.save(statusObaveze);
    }
    @Override
    public StatusObaveze update(Long id, StatusObaveze statusObaveze){
        StatusObaveze so = findById(id);

        so.setNazivStatusa(statusObaveze.getNazivStatusa());
        so.setObaveze(statusObaveze.getObaveze());

        return repository.save(so);
    }
    @Override
    public void delete(Long id){
        repository.delete(findById(id));
    }
}
