package twitter.analyzer;

import com.vaadin.ui.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ApplicationUI extends Window {

    // Layouts
    private VerticalLayout masterLayout = new VerticalLayout();
    private HorizontalLayout titleLayout = new HorizontalLayout();
    private HorizontalLayout findTweetsLayout = new HorizontalLayout();
    private VerticalLayout showTweetsLayout = new VerticalLayout();

    //Components
    private Label title = new Label("Twitter Analyzer");
    private Button findButton = new Button("Find tweets");
    private Button clearButton = new Button("Clear tweets");
    private TextField findField = new TextField();

    private Label emotionVeryHappy = new Label("Very Happy");
    private Label emotionHappy = new Label("Happy");
    private Label emotionNeutral = new Label("Neutral");
    private Label emotionSad = new Label("Sad");
    private Label emotionVerySad = new Label("Very Sad");

    public Button getFindButton() {
        return findButton;
    }

    public Button getClearButton() {
        return clearButton;
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
        findTweetsLayout.addComponent(clearButton);

    }

    public void clearTweets() {
        TwitterFinder.tweets = new ArrayList<String>();
        TwitterFinder.emotions = new int[5];
        showTweetsLayout.removeAllComponents();
    }

    public void addTweets(ArrayList<String> messages){


        double verySadPercent = (double)TwitterFinder.emotions[0] * 100 / (messages.size());
        double sadPercent = (double)TwitterFinder.emotions[1] * 100 / (messages.size());
        double neutralPercent = (double)TwitterFinder.emotions[2] * 100 / (messages.size());
        double happyPercent = (double)TwitterFinder.emotions[3] * 100 / (messages.size());
        double veryHappyPercent = (double)TwitterFinder.emotions[4] * 100 / (messages.size());



        emotionVerySad = new Label("Very Sad" + " = " + verySadPercent + "%");
        emotionSad = new Label("Sad" + " = " + sadPercent+ "%");
        emotionNeutral = new Label("Neutral" + " = " + neutralPercent + "%");
        emotionHappy = new Label("Happy" + " = " + happyPercent + "%");
        emotionVeryHappy = new Label("Very happy" + " = " + veryHappyPercent + "%");

        showTweetsLayout.addComponent(emotionVerySad);
        showTweetsLayout.addComponent(emotionSad);
        showTweetsLayout.addComponent(emotionNeutral);
        showTweetsLayout.addComponent(emotionHappy);
        showTweetsLayout.addComponent(emotionVeryHappy);

        for(int i = 0; i < messages.size(); i++){
            showTweetsLayout.addComponent(new Label(i + ". " + messages.get(i)));
        }
    }
    private static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

}
