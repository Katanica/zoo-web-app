package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Karakteristika;
import zoo_web_app.Repository.KarakteristikaRepository;
import zoo_web_app.Service.KarakteristikaService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class KarakteristikaServiceImpl implements KarakteristikaService {
    private final KarakteristikaRepository repository;

    public KarakteristikaServiceImpl(KarakteristikaRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Karakteristika> findAll(){
        return repository.findAll();
    }

    @Override
    public Karakteristika findById(long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Karakteristika nije pronađena: " + id));
    }

    @Override
    public Karakteristika create(Karakteristika karakteristika){
        return repository.save(karakteristika);
    }

    @Override
    public Karakteristika update(Long id, Karakteristika karakteristika){
        Karakteristika k = findById(id);

        k.setNaziv(karakteristika.getNaziv());
        k.setNastambe(karakteristika.getNastambe());

        return repository.save(k);
    }

    @Override
    public void delete(Long id){
        repository.delete(findById(id));
    }
}
