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
import zoo_web_app.Entity.Trosak;
import zoo_web_app.Service.TrosakService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TrosakControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TrosakService trosakService;

    @InjectMocks
    private TrosakController trosakController;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    private AutoCloseable closeable;

    private Trosak testTrosak;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trosakController).build();

        testTrosak = new Trosak();
        testTrosak.setId(1L);
        testTrosak.setTipTroska("Hrana");
        testTrosak.setIznos(new BigDecimal("150.00"));
        testTrosak.setOpis("Kupovina hrane za lavove");
        testTrosak.setDatum(LocalDate.now());
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void getAll_shouldReturnList() throws Exception {
        List<Trosak> list = Arrays.asList(testTrosak);
        when(trosakService.findAll()).thenReturn(list);

        mockMvc.perform(get("/api/troskovi"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tipTroska").value("Hrana"))
                .andExpect(jsonPath("$[0].iznos").value(150.00))
                .andExpect(jsonPath("$[0].opis").value("Kupovina hrane za lavove"));
    }

    @Test
    public void getById_shouldReturnTrosak() throws Exception {
        when(trosakService.findById(1L)).thenReturn(testTrosak);

        mockMvc.perform(get("/api/troskovi/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipTroska").value("Hrana"))
                .andExpect(jsonPath("$.iznos").value(150.00))
                .andExpect(jsonPath("$.opis").value("Kupovina hrane za lavove"));
    }

    @Test
    public void create_shouldReturnCreatedTrosak() throws Exception {
        when(trosakService.create(any(Trosak.class))).thenReturn(testTrosak);

        mockMvc.perform(post("/api/troskovi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTrosak)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipTroska").value("Hrana"))
                .andExpect(jsonPath("$.iznos").value(150.00));
    }

    @Test
    public void update_shouldReturnUpdatedTrosak() throws Exception {
        when(trosakService.update(eq(1L), any(Trosak.class))).thenReturn(testTrosak);

        mockMvc.perform(put("/api/troskovi/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTrosak)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipTroska").value("Hrana"))
                .andExpect(jsonPath("$.iznos").value(150.00));
    }

    @Test
    public void delete_shouldReturnNoContent() throws Exception {
        doNothing().when(trosakService).delete(1L);

        mockMvc.perform(delete("/api/troskovi/1"))
                .andExpect(status().isNoContent());

        verify(trosakService, times(1)).delete(1L);
    }
}