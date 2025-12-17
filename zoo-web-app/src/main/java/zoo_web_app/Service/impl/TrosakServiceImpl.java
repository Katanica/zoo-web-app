package zoo_web_app.Service.impl;

import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Trosak;
import zoo_web_app.Repository.TrosakRepository;
import zoo_web_app.Service.TrosakService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.util.List;

@Service
public class TrosakServiceImpl implements TrosakService {

    private final TrosakRepository trosakRepository;

    public TrosakServiceImpl(TrosakRepository trosakRepository) {
        this.trosakRepository = trosakRepository;
    }

    @Override
    public List<Trosak> findAll() {
        return trosakRepository.findAll();
    }

    @Override
    public Trosak findById(Long id) {
        return trosakRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trosak nije pronađen: " + id));
    }

    @Override
    public Trosak create(Trosak trosak) {
        return trosakRepository.save(trosak);
    }

    @Override
    public Trosak update(Long id, Trosak updated) {
        Trosak t = findById(id);

        t.setJedinka(updated.getJedinka());
        t.setSkupina(updated.getSkupina());
        t.setTipTroska(updated.getTipTroska());
        t.setIznos(updated.getIznos());
        t.setOpis(updated.getOpis());
        t.setDatum(updated.getDatum());

        return trosakRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        trosakRepository.delete(findById(id));
    }
}
