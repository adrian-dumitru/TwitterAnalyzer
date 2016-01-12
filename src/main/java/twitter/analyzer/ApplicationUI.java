package twitter.analyzer;

import com.vaadin.ui.*;

/**
 * Created by adumitru on 1/7/2016.
 */
public class ApplicationUI extends Window {

    // Layouts
    private VerticalLayout masterLayout = new VerticalLayout();
    private HorizontalLayout titleLayout = new HorizontalLayout();
    private HorizontalLayout findTweetsLayout = new HorizontalLayout();
    private VerticalLayout showTweetsLayout = new VerticalLayout();

    //Components
    private Label title = new Label("Twitter Analyzer");
    private Button findButton = new Button("Find tweets");
    private TextField findField = new TextField();

    public Button getFindButton() {
        return findButton;
    }

    public TextField getFindField() {
        return findField;
    }

    ApplicationUI(){
        this.addComponent(masterLayout);
        masterLayout.addComponent(titleLayout);
        masterLayout.addComponent(findTweetsLayout);
        masterLayout.addComponent(showTweetsLayout);

        titleLayout.addComponent(title);


        findTweetsLayout.addComponent(findField);
        findTweetsLayout.addComponent(findButton);
    }

}
