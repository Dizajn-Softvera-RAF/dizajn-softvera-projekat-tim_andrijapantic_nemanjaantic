package app.model.implementation;

import app.model.classcontent.Attribute;
import app.model.classcontent.ClassContent;
import app.model.classcontent.Method;
import app.model.composite.AbstractClassyNode;
import app.model.composite.ClassyNodeComposite;
import app.model.diagcomposite.DiagramElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import app.model.diagcomposite.Interclass;
import app.model.diagcomposite.Visibility;
import app.model.diagimplementation.connection.Aggregation;
import app.model.diagimplementation.connection.Composition;
import app.model.diagimplementation.connection.Dependency;
import app.model.diagimplementation.connection.Generalization;
import app.model.diagimplementation.interclass.EnumComp;
import app.model.diagimplementation.interclass.Interface;
import app.model.diagimplementation.interclass.Klasa;
import app.model.event.IPublisher;
import app.model.event.ISubscriber;
import app.model.event.Notification;
import app.model.event.NotificationType;
import app.model.tree.MyNodeMutable;
import app.view.mainframe.MainFrame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DiagramElement.class, name = "DiagramElement"),
})
@JsonTypeName("DiagramNode")
public class DiagramNode extends ClassyNodeComposite<DiagramElement> implements IPublisher {
    @JsonIgnore
    private List<ISubscriber> subscribers;
    @JsonIgnore
    private MyNodeMutable myNodeMutable;

    public DiagramNode() {
        this.subscribers = new ArrayList<>();
    }
    public DiagramNode(String name, AbstractClassyNode parent) {
        super(name, parent);
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(AbstractClassyNode child) {
        if (child instanceof Klasa) {
            Klasa node = (Klasa) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Interface) {
            Interface node = (Interface) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof EnumComp) {
            EnumComp node = (EnumComp) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Aggregation) {
            Aggregation node = (Aggregation) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Composition) {
            Composition node = (Composition) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Generalization) {
            Generalization node = (Generalization) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        } else if (child instanceof Dependency) {
            Dependency node = (Dependency) child;
            if (!this.getChildren().contains(node)) {
                this.getChildren().add(node);
                notifySubscribers(new Notification(NotificationType.PAINTER_ADDED));
            }
        }

    }

    @Override
    public void removeChildren() {
        this.getChildren().clear();
        MainFrame.getInstance().getClassyTree().getTreeView().notifySubscribers(new Notification(NotificationType.DELETE_DIAGRAM, this.getId()));
    }

    public void removeChildren(ArrayList<DiagramElement> lista) {
        for (DiagramElement element: lista) {
            if (this.getChildren().contains(element)) {
                this.getChildren().remove(element);
                notifySubscribers(new Notification(NotificationType.PAINTER_REMOVED));
            }
        }
    }

    public void tabOpened() {
        notifySubscribers(new Notification(NotificationType.EXISTING_TAB_OPENED));
    }

    public void removeChild(DiagramElement child) {
        if (this.getChildren().contains(child)) {
            this.getChildren().remove(child);
            notifySubscribers(new Notification(NotificationType.PAINTER_REMOVED));
        }
    }

    public void generateFile(String path) {
        File file = new File(path, getName() + ".txt");
        try {
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            StringBuilder content = new StringBuilder();
            content.append(generatePackage());
            for (DiagramElement child : getChildren()) {
                content.append(generateBody(child));
            }
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
        }

    }

    public String generatePackage() {
        ArrayList<String> packageNames = new ArrayList<>();
        StringBuilder packageBuilder = new StringBuilder();
        AbstractClassyNode currentNode = this;
        packageBuilder.append("package ");
        while (!(currentNode instanceof ProjectNode)) {
            AbstractClassyNode temp;
            temp = currentNode.getParent();
            packageNames.add(temp.getName());
            currentNode = temp;
        }
        packageNames.remove(packageNames.size()-1);
        for (int i=packageNames.size()-1; i>=0; i--) {
            if (i==packageNames.size()-1)
                packageBuilder.append(packageNames.get(i).toLowerCase());
            else
            packageBuilder.append("."+packageNames.get(i).toLowerCase());
        }
        packageBuilder.append(";\n\n");
        return packageBuilder.toString();
    }

    public String generateBody(DiagramElement element) {
        StringBuilder content = new StringBuilder();
        if (element instanceof Interclass) {
            String interklasa = "";
            if (element.getClass().getSimpleName().equalsIgnoreCase("klasa"))
                interklasa = "class";
            else if (element.getClass().getSimpleName().equalsIgnoreCase("interface"))
                interklasa = "interface";
            else if (element.getClass().getSimpleName().equalsIgnoreCase("enumcomp"))
                interklasa = "enum";
            String extensions = generateExtensions((Interclass) element);
            content.append("public " + interklasa + " " + element.getName() + extensions + " {\n\n");
            content.append(generateContent((Interclass)element));
            content.append("}\n\n");
        }
        return content.toString();
    }

    private String generateExtensions(Interclass element) {
        StringBuilder finalBuilder = new StringBuilder();
        StringBuilder extensionsBuilder = new StringBuilder();
        StringBuilder implementationsBuilder = new StringBuilder();
        boolean anyExtentionFlag = false;
        boolean anyImplementationFlag = false;
        for (DiagramElement child: getChildren()) {
            if (child instanceof Generalization && ((Generalization) child).getFromInterclass().equals(element)) {
              if (((Generalization) child).getExtentionType().equals("extend") && extensionsBuilder.length()==0) {
                  extensionsBuilder.append(" extends ");


              } else  if (((Generalization) child).getExtentionType().equals("implement") && implementationsBuilder.length()==0) {
                  implementationsBuilder.append(" implements ");
              }
            }
        }
        for (DiagramElement child: getChildren()) {
            if (child instanceof Generalization && ((Generalization) child).getFromInterclass().equals(element)) {
                if (element instanceof Klasa) {
                    if (((Generalization) child).getExtentionType().equals("implement")) {
                        if (!anyImplementationFlag) {
                            implementationsBuilder.append(((Generalization) child).getToInterclass().getName());
                            anyImplementationFlag = true;
                        } else
                            implementationsBuilder.append("," + ((Generalization) child).getToInterclass().getName());
                    }
                    if (((Generalization) child).getExtentionType().equals("extend")) {
                        if (!anyExtentionFlag ) {
                            extensionsBuilder.append(((Generalization) child).getToInterclass().getName());
                            anyExtentionFlag = true;
                        }
                    }
                } else if (element instanceof Interface) {
                    if (((Generalization) child).getExtentionType().equals("extend")) {
                        if (!anyExtentionFlag ) {
                            extensionsBuilder.append(((Generalization) child).getToInterclass().getName());
                            anyExtentionFlag  = true;
                        } else
                            extensionsBuilder.append("," + ((Generalization) child).getToInterclass().getName());
                    }
                }


            }
        }
        if (anyExtentionFlag)
            finalBuilder.append(extensionsBuilder + " ");
        if (anyImplementationFlag)
            finalBuilder.append(implementationsBuilder);
        return finalBuilder.toString();
    }

    public String generateContent(Interclass element) {
        StringBuilder contentBuilder = new StringBuilder();
        if (element instanceof Klasa) {
            String visibility = null;
            for (ClassContent content:  element.getContent()) {
                if (content instanceof Attribute) {
                    if (content.getVisibility().equals(Visibility.PUBLIC))
                        visibility = "public";
                    else if (content.getVisibility().equals(Visibility.PRIVATE))
                        visibility = "private";
                    else if (content.getVisibility().equals(Visibility.PROTECTED))
                        visibility = "protected";
                    contentBuilder.append("\t"+visibility+" "+content.getType() + " " + content.getName() + ";\n");

                }
            }
            contentBuilder.append("\n\n");
            contentBuilder.append("\tpublic " + element.getName() + "() {\n\t}");
            contentBuilder.append("\n\n");
            for (ClassContent content:  element.getContent()) {
                if (content instanceof Method) {
                    if (content.getVisibility().equals(Visibility.PUBLIC))
                        visibility = "public";
                    else if (content.getVisibility().equals(Visibility.PRIVATE))
                        visibility = "private";
                    else if (content.getVisibility().equals(Visibility.PROTECTED))
                        visibility = "protected";
                    if (!content.getType().equalsIgnoreCase("void"))
                        contentBuilder.append("\t"+visibility+" "+content.getType() + " " + content.getName() + "() {\n\t\treturn " + content.getType() +";\n\t}\n");
                    else
                        contentBuilder.append("\t"+visibility+" "+content.getType() + " " + content.getName() + "() {\n\t\treturn;\n\t}\n");
                }
            }
            for (DiagramElement child: getChildren()) {
                if (child instanceof Generalization && ((Generalization)child).getFromInterclass().getPosition().equals(element.getPosition())) {
                    for (ClassContent content:  ((Generalization)child).getToInterclass().getContent()) {
                        if (content instanceof Method) {
                            if (content.getVisibility().equals(Visibility.PUBLIC))
                                visibility = "public";
                            else if (content.getVisibility().equals(Visibility.PRIVATE))
                                visibility = "private";
                            else if (content.getVisibility().equals(Visibility.PROTECTED))
                                visibility = "protected";
                            if (!sameMethodExists(element, (Method)content)) {
                                if (!content.getType().equalsIgnoreCase("void"))
                                    contentBuilder.append("\t"+visibility+" "+content.getType() + " " + content.getName() + "() {\n\t\treturn " + content.getType() +";\n\t}\n");
                                else
                                    contentBuilder.append("\t"+visibility+" "+content.getType() + " " + content.getName() + "() {\n\t\treturn;\n\t}\n");
                            }
                        }
                    }

                }
            }
        }
        else if (element instanceof Interface) {
            for (ClassContent content:  element.getContent()) {
                contentBuilder.append("\t"+content.getType() + " " + content.getName() + "();\n");
            }
            for (DiagramElement child: getChildren()) {

                if (child instanceof Generalization && ((Generalization)child).getToInterclass() instanceof Interface
                        && ((Generalization)child).getFromInterclass().getPosition().equals(element.getPosition())) {
                    for (ClassContent content:  ((Generalization)child).getToInterclass().getContent()) {
                        if (content instanceof Method) {
                            if (!sameMethodExists(element, (Method)content)) {
                                contentBuilder.append("\t"+content.getType() + " " + content.getName() + "();\n");
                            }
                        }
                    }

                }
            }
        } else if (element instanceof EnumComp) {
            for (ClassContent content:  element.getContent()) {
                if (!content.equals(element.getContent().get(element.getContent().size()-1)))
                    contentBuilder.append("\t" + content.getName() + ",\n");
                else
                    contentBuilder.append("\t" + content.getName() + "\n");
            }
        }
        contentBuilder.append("\n\n");
        return contentBuilder.toString();
    }

    private boolean sameMethodExists(Interclass element, Method method) {
        for (ClassContent content: element.getContent()) {
            if (content instanceof Method && content.getName().equals(method.getName())
                    && content.getType().equals(method.getType()) && content.getVisibility().equals(method.getVisibility()))
                return true;
        }
        return false;
    }


    @Override
    public void addSubscriber(ISubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for (ISubscriber subscriber : subscribers) {

            subscriber.update(notification);
        }
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public MyNodeMutable getMyNodeMutable() {
        return myNodeMutable;
    }

    public void setMyNodeMutable(MyNodeMutable myNodeMutable) {
        this.myNodeMutable = myNodeMutable;
    }

}
