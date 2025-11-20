package zoo_web_app.Service;

import zoo_web_app.Entity.NastambaKarakteristika;

import java.util.List;

public interface NastambaKarakteristikaService {

    List<NastambaKarakteristika> findAll();

    NastambaKarakteristika findById(Long id);

    NastambaKarakteristika create(NastambaKarakteristika nastambaKarakteristika);

    NastambaKarakteristika update(Long id, NastambaKarakteristika nastambaKarakteristika);

    void delete(Long id);
}
