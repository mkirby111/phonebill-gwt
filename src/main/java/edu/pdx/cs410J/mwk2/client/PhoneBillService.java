package edu.pdx.cs410J.mwk2.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import edu.pdx.cs410J.AbstractPhoneBill;

/**
 * A GWT remote service that returns a dummy Phone Bill
 */
@RemoteServiceRelativePath("ping")
public interface PhoneBillService extends RemoteService {

    /**
     * Returns the a dummy Phone Bill
     */
    public AbstractPhoneBill createPhoneBill(String customerName, String callerNumber, String calleeNumber,
                                             String startTime, String endTime) throws ValidatePhoneBillException;

    public String searchPhoneBill(String customerName, String startTime, String endTime) throws ValidatePhoneBillException;

    String fetchPhoneBill(String customerName) throws ValidatePhoneBillException;
}
