package edu.pdx.cs410J.mwk2.client;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements the <code>PhoneBillDumper</code> class.
 * It overrides the abstract method <code>dump()</code> which writes
 * the contents of a phone bill in a human readable format (pretty)
 * The output is either written to a file, or printed to standard out
 * The output represents a phone bill including the customer
 * and any phone calls contained in the phone bill. The phone calls
 * are sorted chronologically by start time. If 2 calls have the
 * same start time, they are sorted by the caller's phone number.
 *
 * @author Mark Kirby
 * @version %I%, %G%
 */
@SuppressWarnings("ALL")
public class PrettyPrinter implements PhoneBillDumper{
    /**
     * <code>PrintWriter</code> object
     * directs output to file or console
     */
    private PrintWriter pw;

    /**
     * Constructor for PrettyPrinter
     * @param pw <code>PrintWriter</code> object
     *           directs output to file or console
     */
    public PrettyPrinter(PrintWriter pw) {
        this.pw = pw;
    }

    /**
     * This method pretty prints the contents of a phone bill, including the <code>customer</code> and
     * any phone calls (<code>phoneCalls</code>) contained in the phone bill.
     *
     * @param bill a <code>PhoneBill</code> object
     */
    @Override
    public void dump(AbstractPhoneBill bill) throws IOException {
        long time;
        int min;

        pw.print("=============================================================================\n");
        pw.print("SLACKERPHONECO    Phone bill for " + bill.getCustomer() + '\n');
        pw.print("=============================================================================\n");

        pw.printf("%-16s%-16s%-20s%-18s%-6s%n", "Caller", "Callee", "Start Time", "End Time", "Minutes");
        pw.print("-----------------------------------------------------------------------------\n");

        Collection phoneCalls = bill.getPhoneCalls();
        for (PhoneCall aCall : (ArrayList<PhoneCall>) phoneCalls) {
            time = aCall.getEndDate().getTime() - aCall.getStartDate().getTime();
            min = (int) time/(60 * 1000);

            pw.printf("%-16s%-16s%-20s%-18s%7s%n", aCall.getCaller(), aCall.getCallee(),
                    aCall.getStartTimeString(), aCall.getEndTimeString(), min);
        }

        pw.print("=============================================================================\n");
        pw.print("Total ammount of $410.00 due by Wednesday, 6:00pm.\n\n");

        pw.flush();
        pw.close();

    }
}
