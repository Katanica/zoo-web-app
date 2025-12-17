package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.NacinNabave;
import zoo_web_app.Repository.NacinNabaveRepository;
import zoo_web_app.Service.NacinNabaveService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class NacinNabaveServiceImpl implements NacinNabaveService {

    private final NacinNabaveRepository repository;

    public NacinNabaveServiceImpl(NacinNabaveRepository repository){
        this.repository = repository;
    }

    @Override
    public List<NacinNabave> findAll(){
        return repository.findAll();
    }

    @Override
    public NacinNabave findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NacinNabave nije pronađen: " + id));
    }

    @Override
    public NacinNabave create(NacinNabave nacinNabave){
        return repository.save(nacinNabave);
    }

    @Override
    public NacinNabave update(Long id, NacinNabave nacinNabave){
        NacinNabave nn = findById(id);

        nn.setOpisNabave(nacinNabave.getOpisNabave());

        return repository.save(nn);
    }

    @Override
    public void delete(Long id){
        repository.delete(findById(id));
    }
}
