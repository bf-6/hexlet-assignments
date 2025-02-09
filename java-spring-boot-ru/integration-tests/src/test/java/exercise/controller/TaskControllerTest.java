package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

import java.util.HashMap;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN

    private Task getModel() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId)) // Исключаем поле `id`
                .supply(Select.field(Task::getTitle), () -> faker.book().title()) // Генерируем title с помощью Faker
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence()) // Генерируем description с помощью Faker
                .create();
    }

    @Test
    public void testShow() throws Exception {
        var model = getModel();
        taskRepository.save(model);

        var request = get("/tasks/" + model.getId());

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                value -> value.node("title").isEqualTo(model.getTitle()),
                value -> value.node("description").isEqualTo(model.getDescription())
        );

        taskRepository.delete(model);
    }

    @Test
    public void testCreate() throws Exception {
        var model = getModel();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(model));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var task = taskRepository.findByTitle(model.getTitle()).get();

        assertThat(task).isNotNull();
        assertThat(task.getTitle()).isEqualTo(model.getTitle());
        assertThat(task.getDescription()).isEqualTo(model.getDescription());

        taskRepository.delete(model);
    }

    @Test
    public void testUpdate() throws Exception {
        var model = getModel();
        taskRepository.save(model);

        var data = new HashMap<>();
        var newTitle = faker.book().title();
        data.put("title", newTitle);

        var request = put("/tasks/" + model.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        model = taskRepository.findById(model.getId()).get();
        assertThat(model.getTitle()).isEqualTo((newTitle));

        taskRepository.delete(model);
    }

    @Test
    public void testDelete() throws Exception {
        var model = getModel();
        taskRepository.save(model);

        var request = delete("/tasks/" + model.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk());

        model = taskRepository.findById(model.getId()).orElse(null);
        assertThat(model).isNull();
    }
    // END
}
