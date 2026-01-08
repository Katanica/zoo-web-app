package zoo_web_app.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zoo_web_app.Entity.VrstaZivotinje;
import zoo_web_app.Service.VrstaZivotinjeService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VrstaZivotinjeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VrstaZivotinjeService vrstaService;

    @InjectMocks
    private VrstaZivotinjeController vrstaController;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private AutoCloseable closeable;

    private VrstaZivotinje testVrsta;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vrstaController).build();

        testVrsta = new VrstaZivotinje();
        testVrsta.setId(1L);
        testVrsta.setHrvatskiNaziv("Lav");
        testVrsta.setLatinskiNaziv("Panthera leo");
        testVrsta.setLink("Kralj životinja");
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void getAll_shouldReturnList() throws Exception {
        List<VrstaZivotinje> list = Arrays.asList(testVrsta);
        when(vrstaService.findAll()).thenReturn(list);

        mockMvc.perform(get("/api/vrste"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].hrvatskiNaziv").value("Lav"))
                .andExpect(jsonPath("$[0].latinskiNaziv").value("Panthera leo"))
                .andExpect(jsonPath("$[0].link").value("Kralj životinja"));
    }

    @Test
    public void findById_shouldReturnVrsta() throws Exception {
        when(vrstaService.findById(1L)).thenReturn(testVrsta);

        mockMvc.perform(get("/api/vrste/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hrvatskiNaziv").value("Lav"))
                .andExpect(jsonPath("$.latinskiNaziv").value("Panthera leo"))
                .andExpect(jsonPath("$.link").value("Kralj životinja"));
    }

    @Test
    public void create_shouldReturnCreatedVrsta() throws Exception {
        when(vrstaService.create(any(VrstaZivotinje.class))).thenReturn(testVrsta);

        mockMvc.perform(post("/api/vrste")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testVrsta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hrvatskiNaziv").value("Lav"))
                .andExpect(jsonPath("$.latinskiNaziv").value("Panthera leo"))
                .andExpect(jsonPath("$.link").value("Kralj životinja"));
    }

    @Test
    public void update_shouldReturnUpdatedVrsta() throws Exception {
        when(vrstaService.update(eq(1L), any(VrstaZivotinje.class))).thenReturn(testVrsta);

        mockMvc.perform(put("/api/vrste/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testVrsta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hrvatskiNaziv").value("Lav"))
                .andExpect(jsonPath("$.latinskiNaziv").value("Panthera leo"))
                .andExpect(jsonPath("$.link").value("Kralj životinja"));
    }

    @Test
    public void delete_shouldReturnNoContent() throws Exception {
        doNothing().when(vrstaService).delete(1L);

        mockMvc.perform(delete("/api/vrste/1"))
                .andExpect(status().isNoContent());

        verify(vrstaService, times(1)).delete(1L);
    }
}