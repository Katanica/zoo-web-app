package zoo_web_app.Service;

import zoo_web_app.Entity.VrstaZivotinje;

import java.util.List;

public interface VrstaZivotinjeService {
    List<VrstaZivotinje> findAll();

    VrstaZivotinje findById(Long id);

    VrstaZivotinje create(VrstaZivotinje vrsta);

    VrstaZivotinje update(Long id, VrstaZivotinje vrsta);

    void delete(Long id);
}
