package edu.pdx.cs410J.mwk2.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.Date;

/**
 * The client-side interface to the ping service
 */
public interface PhoneBillServiceAsync {

    /**
     * Returns the a dummy Phone Bill
     */
    void createPhoneBill(String customerName, String callerNumber, String calleeNumber,
                         String startTime, String endTime, AsyncCallback<AbstractPhoneBill> async);

    void searchPhoneBill(String customerName, String startTime, String endTime, AsyncCallback<String> async);

    void fetchPhoneBill(String customerName, AsyncCallback<String> stringAsyncCallback);
}
