package zoo_web_app.Service;

import zoo_web_app.Entity.NacinNabave;

import java.util.List;

public interface NacinNabaveService {
    List<NacinNabave> findAll();
    NacinNabave findById(Long id);
    NacinNabave update(Long id, NacinNabave nacinNabave);
    NacinNabave create(NacinNabave nacinNabave);
    void delete(Long id);
}
