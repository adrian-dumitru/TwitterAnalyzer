package twitter.analyzer;


import com.vaadin.Application;
import com.vaadin.ui.Button;

import java.io.IOException;

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
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		setMainWindow(window);
	}

}
