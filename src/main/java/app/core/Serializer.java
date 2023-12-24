package app.core;

import app.model.composite.AbstractClassyNode;
import app.model.implementation.DiagramNode;
import app.model.implementation.ProjectNode;

import java.io.File;

public interface Serializer {
    ProjectNode loadProject(File file);
    DiagramNode loadDiagram(File file);
    void saveProject(ProjectNode node);
    void saveDiagram(DiagramNode node, String path);
}
