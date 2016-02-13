package edu.pdx.cs410J.mwk2.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.mwk2.client.*;

import java.io.*;
import java.lang.Override;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The server-side implementation of the Phone Bill service
 */
public class PhoneBillServiceImpl extends RemoteServiceServlet implements PhoneBillService {

    private final Map<String, PhoneBill> data = new HashMap<>();

    /**
     * This method creates a phone call and phone bill from the passed in parameters. It first
     * checks for blank entries, throwing an error message if any fields are null. Then the
     * method validates each string parameter for its format. If all is OK, it proceeds to call
     * the constructors for the new call. If the customer already is in the system, it adds the
     * new phone call to the exisiting bill, otherwise it creates a new bill.
     *
     * @param customerName
     * @param callerNumber
     * @param calleeNumber
     * @param startTime
     * @param endTime
     * @return
     * @throws ValidatePhoneBillException
     */
    @Override
    public AbstractPhoneBill createPhoneBill(String customerName, String callerNumber, String calleeNumber,
                                             String startTime, String endTime) throws ValidatePhoneBillException {

        PhoneBill aBill;
        checkParametersForNull(customerName, callerNumber, calleeNumber, startTime, endTime);

        checkPhoneNumberFormat(callerNumber);
        checkPhoneNumberFormat(calleeNumber);

        validateDateTime(startTime);
        validateDateTime(endTime);

        Date startDate = parseStringToDate(startTime);
        Date endDate = parseStringToDate(endTime);

        PhoneCall aCall = new PhoneCall(callerNumber, calleeNumber,startTime, endTime, startDate, endDate);

        if (data.containsKey(customerName)) {
            data.get(customerName).addPhoneCall(aCall);
            aBill = data.get(customerName);
        } else {
            aBill = new PhoneBill(customerName, aCall);
            data.put(customerName, aBill);
        }
        return aBill;
    }

    /**
     * This method searches the server for a phone bill associated with the passed in customer name. If
     * a bill exists,the method searches for any phone calls stored in the bill that occured between the
     * passed in start and end date/times. Any phonecalls that fit the range are added to a customized
     * phone bill which is then returned as a string. If no phone calls are found in the given range an
     * appropriate message is returned.
     *
     * @param customerName
     * @param startTime
     * @param endTime
     * @return
     * @throws ValidatePhoneBillException
     */
    @Override
    public String searchPhoneBill(String customerName, String startTime, String endTime) throws ValidatePhoneBillException {

        PhoneBill aBill;
        PhoneBill searchBillResult = null;

        checkSearchParametersForNull(customerName, startTime, endTime);

        validateDateTime(startTime);
        validateDateTime(endTime);

        Date startDate = parseStringToDate(startTime);
        Date endDate = parseStringToDate(endTime);

        if (data.containsKey(customerName)) {
            data.get(customerName);
            aBill = data.get(customerName);
        } else {
            throw new ValidatePhoneBillException("Customer is not in system.");
        }

        ArrayList<PhoneCall> phoneCalls = (ArrayList<PhoneCall>) aBill.getPhoneCalls();
        for (PhoneCall aCall: phoneCalls) {
            if (aCall.getStartDate().getTime() >= startDate.getTime()
                    && aCall.getStartDate().getTime() <= endDate.getTime()) {
                if (searchBillResult == null) {
                    searchBillResult = new PhoneBill(customerName, aCall);
                } else {
                    searchBillResult.addPhoneCall(aCall);
                }
            }
        }

        StringWriter stringwriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringwriter);

        if (searchBillResult != null) {
            pw.println("=============================================================================");
            pw.println("Search results for " + customerName + "'s calls \nbetween " + startTime + " and " + endTime);
            PrettyPrinter pp = new PrettyPrinter(pw);
            try {
                pp.dump(searchBillResult);
            } catch (IOException e) {
                throw new ValidatePhoneBillException("IOException: " + e.getMessage());
            }
            pw.flush();

        } else {
            pw.println("There are no calls for " + customerName + " between " + startTime + " and " + endTime);
            pw.flush();
        }

        return stringwriter.toString();
    }

    /**
     * This method returns the phone bill representation for a given customer. If the customer name
     * is not found in the system, an appropriate message is sent to that effect.
     */
    @Override
    public String fetchPhoneBill(String customerName) throws ValidatePhoneBillException {

        PhoneBill aBill;

        if (data.containsKey(customerName)) {
            data.get(customerName);
            aBill = data.get(customerName);
        } else {
            throw new ValidatePhoneBillException("Customer is not in system.");
        }

        StringWriter stringwriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringwriter);

        if (aBill != null) {
            PrettyPrinter pp = new PrettyPrinter(pw);
            try {
                pp.dump(aBill);
            } catch (IOException e) {
                throw new ValidatePhoneBillException("IOException: " + e.getMessage());
            }
            pw.flush();

        } else {
            pw.println("There are no calls for " + customerName);
            pw.flush();
        }

        return stringwriter.toString();
    }

    /**
     * This method checks for null or empty string parameters for creating a new phone bill/phonecall.
     *
     * @param customerName
     * @param callerNumber
     * @param calleeNumber
     * @param startTime
     * @param endTime
     */
    private void checkParametersForNull(String customerName, String callerNumber, String calleeNumber, String startTime, String endTime) {
        if (customerName == null || "".equals(customerName)) {
            throw new ValidatePhoneBillException("Customer name cannot be blank");
        } else if (callerNumber == null || "".equals(callerNumber)) {
            throw new ValidatePhoneBillException("Caller number cannot be blank");
        } else if (calleeNumber == null || "".equals(calleeNumber)) {
            throw new ValidatePhoneBillException("Callee number cannot be blank");
        } else if (startTime == null || "".equals(startTime)) {
            throw new ValidatePhoneBillException("Start time cannot be blank");
        } else if (endTime == null || "".equals(endTime)) {
            throw new ValidatePhoneBillException("End time cannot be blank");
        }
    }

    /**
     * This method checks for null or empty string parameters for searching calls in a phone bill.
     *
     * @param customerName
     * @param startTime
     * @param endTime
     */
    private void checkSearchParametersForNull(String customerName, String startTime, String endTime) {
        if (customerName == null || "".equals(customerName)) {
            throw new ValidatePhoneBillException("Customer name cannot be blank");
        } else if (startTime == null || "".equals(startTime)) {
            throw new ValidatePhoneBillException("Start time cannot be blank");
        } else if (endTime == null || "".equals(endTime)) {
            throw new ValidatePhoneBillException("End time cannot be blank");
        }
    }

    /**
     * This method parses phone number arguments to check for valid phone number formats.
     * It uses the @see <a href = http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html>Pattern class</a>
     * which includes methods to define a pattern, in this case a phone number sequence "nnn-nnn-nnnn", and then
     * check a number parameter against that pattern to validate that it matches. If it doesn't match, the
     * appropriate error message is printed.
     *
     * @param number <code>String</code> representing the phone number whose format is validated
     */
    private static void checkPhoneNumberFormat(String number) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matchCallerNumber = pattern.matcher(number);

        if (!matchCallerNumber.matches())
            throw new ValidatePhoneBillException("Invalid phone number format: " + number);
    }

    /**
     * This method validates the passed in string representing the date/time instance.
     * This string is split into its date/time and AM or PM portions which are then
     * checked for valid formats accordingly. If the parsing and validation return
     * an error, an appropriate error message is thrown.
     *
     * @param aTime
     */
    private void validateDateTime(String aTime) {
        String[] parsedTime = new String[100];
        parsedTime = aTime.split(" ");
        if (parsedTime.length == 3) {
            String aTimeDate = parsedTime[0] + " " + parsedTime[1];
            String aTimeAMorPM = parsedTime[2];
            checkDateTimeFormat(aTimeDate);
            checkAMorPMFormat(aTimeAMorPM);
        } else {
            throw new ValidatePhoneBillException("Invalid date/time format: " + aTime);
        }
    }

    /**
     * This method checks for valid AM or PM format as part of the Date/Time arguments
     * @param amOrPm <code>String</code> representing AM or PM
     */
    private static void checkAMorPMFormat(String amOrPm) {
        Pattern pattern = Pattern.compile("(am.*|AM.*|pm.*|PM.*)");
        Matcher matchAMorPM = pattern.matcher(amOrPm);

        if (!matchAMorPM.matches())
            throw new ValidatePhoneBillException("Invalid AM or PM format: " + amOrPm);
    }

    /**
     * This method parses date/time arguments to check for valid date/time format.
     * The code is referenced from the Java tutorial page for the JDK 1.8 Date/Time class:
     * @see <a href = https://docs.oracle.com/javase/tutorial/datetime/iso/format.html>Date/Time class</a>.
     * The given format is defined by a DateTimeFormatter object, which is compared to
     * the passed in argument string. If the strings don't match a DateTimeParseException
     * is thrown with an appropriate message.
     *
     * @param aTime
     */
    private static void checkDateTimeFormat(String aTime) {
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("M/d/yyyy h:m");
            LocalDate.parse(aTime, formatter);
        }
        catch (DateTimeParseException exc) {
            throw new ValidatePhoneBillException("Invalid date/time format: " + aTime);
        }
    }

    /**
     * Returns a <code>DateFormat</code> object
     * formatted in the SHORT format
     * @return <code>DateFormat</code> date/time object formatted in the SHORT format
     */
    private DateFormat getDateFormatShort() {
        int f;
        DateFormat df;
        f = DateFormat.SHORT;
        df = DateFormat.getDateTimeInstance(f, f);
        return df;
    }

    /**
     * Parses a <code>String</code> representing a date/time
     * and returns a <code>Date</code> object
     * @param aTime string to be parsed
     * @return <code>Date</code> date
     */
    private Date parseStringToDate(String aTime) {
        Date date = null;
        DateFormat df = getDateFormatShort();
        try {
            date = df.parse(aTime);
        } catch(ParseException ex) {
            System.err.println("Bad date: " + aTime);
            System.exit(1);
        }
        return date;
    }

    /**
     * Log unhandled exceptions to standard error
     *
     * @param unhandled
     *        The exception that wasn't handled
     */
    @Override
    protected void doUnexpectedFailure(Throwable unhandled) {
        unhandled.printStackTrace(System.err);
        super.doUnexpectedFailure(unhandled);
    }
}
