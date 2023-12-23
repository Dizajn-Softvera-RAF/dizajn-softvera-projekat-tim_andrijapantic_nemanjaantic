package app.serializer;

import app.core.Serializer;
import app.model.implementation.DiagramNode;
import app.model.implementation.ProjectNode;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JacksonSerializer implements Serializer {

    private ObjectMapper objectMapper;

    public JacksonSerializer() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public ProjectNode loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return objectMapper.readValue(fileReader, ProjectNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DiagramNode loadDiagram(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return objectMapper.readValue(fileReader, DiagramNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(ProjectNode node) {
        try {
            objectMapper.writeValue(new File(node.getPath()), node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDiagram(DiagramNode node) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(node.getPath()), node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
