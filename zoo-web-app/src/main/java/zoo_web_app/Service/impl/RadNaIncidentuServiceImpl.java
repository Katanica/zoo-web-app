package zoo_web_app.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zoo_web_app.Entity.Incident;
import zoo_web_app.Entity.RadNaIncidentu;
import zoo_web_app.Repository.IncidentRepository;
import zoo_web_app.Repository.RadNaIncidentuRepository;
import zoo_web_app.Service.RadNaIncidentuService;
import zoo_web_app.Exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RadNaIncidentuServiceImpl implements RadNaIncidentuService {

    private final RadNaIncidentuRepository radRepo;
    private final IncidentRepository incidentRepository;

    @Override
    public RadNaIncidentu create(RadNaIncidentu rad) {

        rad.setDatumRada(LocalDate.now());

        double ukupno = rad.getTrajanjeSati() * rad.getCijenaSata();
        rad.setUkupniTrosak(ukupno);

        Incident incident = incidentRepository.findById(rad.getIncident().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Incident ne postoji!"));

        rad.setIncident(incident);

        return radRepo.save(rad);
    }

    @Override
    public RadNaIncidentu update(Long id, RadNaIncidentu updated) {
        RadNaIncidentu rad = findById(id);

        rad.setRadnik(updated.getRadnik());
        rad.setIncident(updated.getIncident());
        rad.setDatumRada(updated.getDatumRada());
        rad.setTrajanjeSati(updated.getTrajanjeSati());
        rad.setCijenaSata(updated.getCijenaSata());

        double ukupno = rad.getTrajanjeSati() * rad.getCijenaSata();
        rad.setUkupniTrosak(ukupno);

        return radRepo.save(rad);
    }

    @Override
    public void delete(Long id) {
        radRepo.delete(findById(id));
    }

    @Override
    public RadNaIncidentu findById(Long id) {
        return radRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rad na incidentu ne postoji: " + id));
    }

    @Override
    public List<RadNaIncidentu> findAll() {
        return radRepo.findAll();
    }

    @Override
    public List<RadNaIncidentu> findByIncident(Long incidentId) {
        return radRepo.findByIncidentId(incidentId);
    }
}
