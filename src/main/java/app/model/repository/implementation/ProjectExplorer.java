package app.model.repository.implementation;

import app.model.repository.composite.AbstractClassyNode;
import app.model.repository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite<ProjectNode> {

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(AbstractClassyNode child) {

        if (child != null && child instanceof ProjectNode) {
            ProjectNode project = (ProjectNode) child;
            child.setParent(this);
            if (!this.getChildren().contains(project)) {
                this.getChildren().add(project);
            }
        }

    }

    @Override
    public void removeChildren() {

    }
}
