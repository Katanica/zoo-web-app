package zoo_web_app.Service;

import zoo_web_app.Entity.Nastamba;
import zoo_web_app.Entity.NastambaInfrastruktura;

import java.util.List;

public interface NastambaInfrastrukturaService {

    List<NastambaInfrastruktura> findAll();

    NastambaInfrastruktura findById(Long id);

    NastambaInfrastruktura create(NastambaInfrastruktura nastambaInfrastruktura);

    NastambaInfrastruktura update(Long id, NastambaInfrastruktura nastambaInfrastruktura);

    void delete(Long id);
}
