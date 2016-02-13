package edu.pdx.cs410J.mwk2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.System.out;

/**
 * A basic GWT class that makes sure that we can send an Phone Bill back from the server
 */
public class PhoneBillGwt implements EntryPoint {

    private DockPanel appPanel = new DockPanel();
    private TabPanel tabs = new TabPanel();

    private Grid addCallForm = new Grid(5, 3);
    private VerticalPanel addCallPanel = new VerticalPanel();
    private TextBox customerNameField;
    private TextBox callerField;
    private TextBox calleeField;
    private TextBox startTimeField;
    private TextBox endTimeField;

    private Grid displayBillForm = new Grid(1,3);
    private VerticalPanel displayBillPanel = new VerticalPanel();
    private TextBox displayBillNameField;

    private Grid searchCallForm = new Grid(3, 3);
    private VerticalPanel searchCallPanel = new VerticalPanel();
    private TextBox searchNameField;
    private TextBox searchStartTimeField;
    private TextBox searchEndTimeField;

    private VerticalPanel readMePanel = new VerticalPanel();
    private TextArea readmeTextOutput = new TextArea();

    private VerticalPanel textOutputPanel = new VerticalPanel();
    private TextArea textOutput = new TextArea();

    /**
     * Main function that runs on Phonebill app startup
     */
    public void onModuleLoad() {

        addCallPanel = setAddCallPanel();
        displayBillPanel = setDisplayBillPanel();
        searchCallPanel = setSearchCallPanel();

        tabs.setStyleName("gwt-TabPanel-tabs");
        textOutput.setStyleName("gwt-TextArea");
        textOutputPanel.add(textOutput);
        textOutputPanel.setStyleName("gwt-textOutputPanel");

        readmeTextOutput.setText(getREADMEtext());
        readmeTextOutput.setStyleName("gwt-TextArea");
        readMePanel.add(readmeTextOutput);

        String phoneIcon = "\u260e";
        Label appTitleLabel = new Label(phoneIcon + " SLACKERPHONECO Phonebill Application");
        appTitleLabel.setStyleName("gwt-appTitleLabel");

        RootPanel rootPanel = RootPanel.get();
        tabs.add(addCallPanel, "Add Call");
        tabs.add(displayBillPanel, "Display Bill");
        tabs.add(searchCallPanel, "Search Calls");
        tabs.add(readMePanel, "README");
        tabs.selectTab(0);
        appPanel.add((appTitleLabel), DockPanel.NORTH);
        appPanel.add(tabs, DockPanel.CENTER);
        appPanel.add(textOutputPanel, DockPanel.SOUTH);
        appPanel.setStyleName("gwt-appPanel");
        rootPanel.add(appPanel);
    }

    /**
     * Method that sets all text labels, text box input, and button
     * for the Add Call tab.
     * @return
     */
    private VerticalPanel setAddCallPanel() {
        customerNameField = setTextBox(30);
        Label customerNameLabel = new Label("Customer Name");

        callerField = setTextBox(12);
        Label callerLabel = new Label("Caller Phone Number");
        Label callTipLabel = new Label("Example: 503-777-7777");

        calleeField = setTextBox(12);
        Label calleeLabel = new Label("Callee Phone Number");

        startTimeField = new TextBox();
        Label startTimeLabel = new Label("Start Time");
        Label timeTipLabel = new Label("Example: 8/8/2015 9:00 AM");

        endTimeField = new TextBox();
        Label endTimeLabel = new Label("End Time");

        Button addCallButton = new Button("Add Call");
        addCallButton.addClickHandler(createNewPhoneBillOnServer());

        addCallForm.setCellPadding(5);
        addCallForm.setWidget(0, 0, customerNameLabel);
        addCallForm.setWidget(0, 1, customerNameField);
        addCallForm.setWidget(1, 0, callerLabel);
        addCallForm.setWidget(1, 1, callerField);
        addCallForm.setWidget(1, 2, callTipLabel);
        addCallForm.setWidget(2, 0, calleeLabel);
        addCallForm.setWidget(2, 1, calleeField);
        addCallForm.setWidget(3, 0, startTimeLabel);
        addCallForm.setWidget(3, 1, startTimeField);
        addCallForm.setWidget(3, 2, timeTipLabel);
        addCallForm.setWidget(4, 0, endTimeLabel);
        addCallForm.setWidget(4, 1, endTimeField);

        addCallPanel.setSpacing(10);
        addCallPanel.add(addCallForm);
        addCallPanel.add(addCallButton);

        return addCallPanel;
    }

    /**
     * Method that sets all text labels, text box input, and button
     * for the Display Bill tab.
     * @return
     */
    private VerticalPanel setDisplayBillPanel() {

        displayBillNameField = setTextBox(30);
        Label displayBillNameLabel = new Label("Customer Name");

        Button displayBillButton = new Button("Display Bill");
        displayBillButton.addClickHandler(fetchPhoneBillFromServer());

        displayBillForm.setCellPadding(5);
        displayBillForm.setWidget(0, 0, displayBillNameLabel);
        displayBillForm.setWidget(0, 1, displayBillNameField);

        displayBillPanel.setSpacing(10);
        displayBillPanel.add(displayBillForm);
        displayBillPanel.add(displayBillButton);

        return displayBillPanel;
    }

    /**
     * Method that sets all text labels, text box input, and button
     * for the Search Calls tab.
     * @return
     */
    private VerticalPanel setSearchCallPanel() {
        searchNameField = setTextBox(30);
        Label searchNameLabel = new Label("Customer Name");

        searchStartTimeField = new TextBox();
        Label searchStartTimeLabel = new Label("Start Time");
        Label searchTimeTipLabel = new Label("Example: 8/8/2015 9:00 AM");

        searchEndTimeField = new TextBox();
        Label searchEndTimeLabel = new Label("End Time");

        Button searchCallButton = new Button("Search Calls");
        searchCallButton.addClickHandler(searchForCallsOnServer());

        searchCallForm.setCellPadding(5);
        searchCallForm.setWidget(0, 0, searchNameLabel);
        searchCallForm.setWidget(0, 1, searchNameField);
        searchCallForm.setWidget(1, 0, searchStartTimeLabel);
        searchCallForm.setWidget(1, 1, searchStartTimeField);
        searchCallForm.setWidget(1, 2, searchTimeTipLabel);
        searchCallForm.setWidget(2, 0, searchEndTimeLabel);
        searchCallForm.setWidget(2, 1, searchEndTimeField);

        searchCallPanel.setSpacing(10);
        searchCallPanel.add(searchCallForm);
        searchCallPanel.add(searchCallForm);
        searchCallPanel.add(searchCallButton);

        return searchCallPanel;
    }

    /**
     * Creates a <code>DateBox</code> picker that formats the user
     * entered date to "m/d/yyyy" format
     *
     * @return <code>DateBox</code> object
     */
    private DateBox setDateBox() {
        DateBox aDateBox = new DateBox();
        DateTimeFormat dateFormat = DateTimeFormat.getFormat("M/d/yyyy");
        aDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        return aDateBox;
    }

    /**
     * Creates a <code>TextBox</code> for user input with
     * a defined maximum length
     *
     * @param maxLength maximum length of user input
     * @return <code>TextBox</code> object
     */
    private TextBox setTextBox(int maxLength) {
        TextBox aTextBox = new TextBox();
        aTextBox.setMaxLength(maxLength);
        return aTextBox;
    }

    /**
     * Method that returns text for README tab
     * @return
     */
    private String getREADMEtext() {
        StringBuilder sb = new StringBuilder();

        sb.append("Mark Kirby\n");
        sb.append("CS410 Advanced Java\n");
        sb.append("Project 5: A Rich Internet Phone Bill Application\n");
        sb.append("Version 1.0   August 12, 2015\n");

        sb.append("\nThis application was built using Google Web Toolkit. The application consisits of 3 tabs: \"Add Call\", \"Search Call\", and \"README\" which you are reading now.\n\nOn the \"Add Call\" tab, enter the Customer Name, the phone number for the Caller and the Callee, along with the dates/times for the start time, and end time of the phone call. Then click on the \"Add Call\" button. A message indicating that the call was saved successfully will be displayed unless there has been an error in the required input. In this case, the user will be alerted to the error.\n\nThe required format for entering a call is of the pattern \"nnn-nnn-nnnn\" where n represents a digit (0-9). The required format for the date and time is of the pattern \"M/d/yyyy H:m a\" where M represents the numeral month of the year (1-12), d represents the numeral day of the month (1-31 or the last day of the given month), H represents the hour of the day (1-12), m represents the minutes (00-59) and a represents the 12-hour period (AM or PM).\n\nOn the Search Call tab enter the customer name and the dates/times to search, both a start time and an end time. A custom phone bill will display which includes all phone calls for that customer between the dates/times entered. If no phone bill exists for the customer name entered a message will be displayed to indicate as much.");

        return sb.toString();
    }


    /**
     * <code>ClickHandler</code> method that creates a new phone bill
     * from the user input.
     *
     * @return
     */
    private ClickHandler createNewPhoneBillOnServer() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String customerName = customerNameField.getText();
                String callerNumber = callerField.getText();
                String calleeNumber = calleeField.getText();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();

                PhoneBillServiceAsync async = GWT.create(PhoneBillService.class);
                async.createPhoneBill(customerName, callerNumber, calleeNumber,
                        startTime, endTime, displayPhoneBill());
            }

            private AsyncCallback<AbstractPhoneBill> displayPhoneBill() {
                return new AsyncCallback<AbstractPhoneBill>() {

                    @Override
                    public void onFailure(Throwable ex) {
                        if (ex instanceof ValidatePhoneBillException) {
                            Window.alert("Invalid input: " + ex.getMessage());
                        } else {
                            Window.alert("onFailure: " + ex.toString());
                        }
                    }

                    @Override
                    public void onSuccess(AbstractPhoneBill phonebill) {
                        StringBuilder sb = new StringBuilder("Updated " + phonebill.toString() + ". \n");
                        Collection calls = phonebill.getPhoneCalls();
                        for (PhoneCall aCall : (ArrayList<PhoneCall>) calls) {
                            sb.append(aCall);
                            sb.append("\n");
                        }
                        textOutput.setText(sb.toString());
                    }
                };
            }
        };
    }

    /**
     * <code>ClickHandler</code> method that fetches a phone bill
     * mapped to a customer name from the server. If the customer
     * name is not present, an error message is returned. On success
     * the phone bill is returned in a formatted string.
     *
     * @return
     */
    private ClickHandler fetchPhoneBillFromServer() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String customerName = displayBillNameField.getText();

                PhoneBillServiceAsync async = GWT.create(PhoneBillService.class);
                async.fetchPhoneBill(customerName, displayFetchedPhoneBill());
            }

            private AsyncCallback<String> displayFetchedPhoneBill() {
                return new AsyncCallback<String>() {

                    @Override
                    public void onFailure(Throwable ex) {
                        if (ex instanceof ValidatePhoneBillException) {
                            Window.alert("Invalid input: " + ex.getMessage());
                        } else {
                            Window.alert("onFailure: " + ex.toString());
                        }
                    }

                    @Override
                    public void onSuccess(String result) {

                        textOutput.setText(result);
                    }
                };
            }
        };
    }

    /**
     * <code>ClickHandler</code> method that fetches a customized phone bill
     * mapped to a customer name from the server with only those phone calls.
     * that are present between the given start and end date/times.
     * If the customer name is not present, an error message is returned.
     * On success the customized phone bill is returned in a formatted string.
     *
     * @return
     */
    private ClickHandler searchForCallsOnServer() {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String customerName = searchNameField.getText();
                String startTime = searchStartTimeField.getText();
                String endTime = searchEndTimeField.getText();

                PhoneBillServiceAsync async = GWT.create(PhoneBillService.class);
                async.searchPhoneBill(customerName, startTime, endTime, displaySearchResult());
            }

            private AsyncCallback<String> displaySearchResult() {
                return new AsyncCallback<String>() {

                    @Override
                    public void onFailure(Throwable ex) {
                        if (ex instanceof ValidatePhoneBillException) {
                            Window.alert("Invalid input: " + ex.getMessage());
                        } else {
                            Window.alert("onFailure: " + ex.toString());
                        }
                    }

                    @Override
                    public void onSuccess(String result) {

                        textOutput.setText(result);
                        //Window.alert("This is where the correct search message will be.");

                    }
                };
            }
        };
    }
}