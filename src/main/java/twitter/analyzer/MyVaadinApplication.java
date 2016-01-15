package twitter.analyzer;


import com.vaadin.Application;
import com.vaadin.ui.Button;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application {



	@Override
	public void init() {
		final ApplicationUI window = new ApplicationUI();

		window.getFindButton().addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					TwitterFinder.getTweets(window.getFindField().getValue().toString());
					window.addTweets(TwitterFinder.tweets);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		window.getClearButton().addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
					window.clearTweets();
			}
		});

		setMainWindow(window);
	}

}
