package zoo_web_app.Service.impl;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zoo_web_app.DTO.RadnikCreate;
import zoo_web_app.Entity.Obrazovanje;
import zoo_web_app.Entity.Radnik;
import zoo_web_app.Entity.RadnikObrazovanje;
import zoo_web_app.Repository.ObrazovanjeRepository;
import zoo_web_app.Repository.RadnikObrazovanjeRepository;
import zoo_web_app.Repository.RadnikRepository;
import zoo_web_app.Service.RadnikService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.util.List;


@Service
public class RadnikServiceImpl implements RadnikService {
    private final RadnikRepository radnikRepository;
    private final ObrazovanjeRepository obrazovanjeRepository;
    private final RadnikObrazovanjeRepository radnikObrazovanjeRepository;

    public RadnikServiceImpl(RadnikRepository radnikRepository,  ObrazovanjeRepository obrazovanjeRepository, RadnikObrazovanjeRepository radnikObrazovanjeRepository) {
        this.radnikRepository = radnikRepository;
        this.obrazovanjeRepository = obrazovanjeRepository;
        this.radnikObrazovanjeRepository = radnikObrazovanjeRepository;
    }

    @Override
    public long brojRadnika(){
        return radnikRepository.count();
    }

    @Override
    public List<Radnik> findAll() {
        return radnikRepository.findAll();
    }

    @Override
    public Radnik findById(Long id) {
        return radnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Radnik nije pronađen: " + id));
    }


    @Override
    public Radnik create(Radnik radnik) {
        return radnikRepository.save(radnik);
    }

    @Override
    public Radnik update(Long id, Radnik updated) {
        Radnik r = findById(id);

        r.setIme(updated.getIme());
        r.setPrezime(updated.getPrezime());
        r.setTelefon(updated.getTelefon());
        r.setEmail(updated.getEmail());
        r.setStatus(updated.getStatus());

        return radnikRepository.save(r);
    }

    @Override
    public void delete(Long id) {
        Radnik r = findById(id);
        radnikRepository.delete(r);
    }

    public List<Radnik> findAllWithObrazovanja() {
        return radnikRepository.findAllWithObrazovanja();
    }

    @Override
    @Transactional
    public Radnik createSaObrazovanjima(RadnikCreate dto) {
        Radnik r = new Radnik();
        r.setIme(dto.ime);
        r.setPrezime(dto.prezime);
        r.setTelefon(dto.telefon);
        r.setEmail(dto.email);
        r.setStatus(dto.status);

        Radnik saved = radnikRepository.save(r);

        if (dto.obrazovanja != null) {
            for (String naziv : dto.obrazovanja) {
                if (naziv == null || naziv.trim().isEmpty()) continue;

                Obrazovanje o = obrazovanjeRepository.findByNaziv(naziv.trim())
                        .orElseGet(() -> obrazovanjeRepository.save(new Obrazovanje(null, naziv.trim(), null)));

                RadnikObrazovanje ro = new RadnikObrazovanje();
                ro.setRadnik(saved);
                ro.setObrazovanje(o);
                radnikObrazovanjeRepository.save(ro);
            }
        }

        return saved;
    }


}









