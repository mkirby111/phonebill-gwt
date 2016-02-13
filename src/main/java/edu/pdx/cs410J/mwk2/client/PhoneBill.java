package edu.pdx.cs410J.mwk2.client;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.lang.Override;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This class represents a customer's phone bill that
 * consists of multiple phone calls. It extends the class
 * AbstractPhoneBill, written by David Whitlock
 *
 * @author Mark Kirby
 * @version %I%, %G%
 *
 */
public class PhoneBill extends AbstractPhoneBill {
  /**
   * Person whose phone bill we’re modeling
   */
  private String customer;

  /**
   * List of phone calls included in this bill
   */
  private ArrayList<PhoneCall> phoneCalls;

  /**
   * Creates a new <code>PhoneBill</code> with the customer name stored in the field
   * <code>customer</code> and a <code>PhoneCall</code> object stored in the
   * ArrayList <code>phonecalls</code>.
   *
   * @param customer the person whose phone bill we’re modeling
   * @param aCall the <code>PhoneCall</code> object representing a phone call
   */
  public PhoneBill(String customer, PhoneCall aCall) {
    this.customer = customer;
    this.phoneCalls = new ArrayList<PhoneCall>();
    phoneCalls.add(aCall);
  }

  public PhoneBill() {
  }

  /**
   * Returns the name of the customer whose phone bill this is
   */
  @Override
  public String getCustomer() {
    return customer;
  }

  /**
   * Adds a phone call to this phone bill
   *
   * @param call phone call to add
   */
  @Override
  public void addPhoneCall(AbstractPhoneCall call) {
    phoneCalls.add((PhoneCall) call);
    Collections.sort(phoneCalls);
  }

  /**
   * Returns all of the phone calls, sorted, (as instances of {@link
   * AbstractPhoneCall}) in this phone bill.
   */
  @Override
  public Collection getPhoneCalls() {
    return phoneCalls;
  }

  /**
   * This method prints the phone calls included in this phone bill.
   * It is called when the "-print" option is present on the command line.
   */
  public void printBill() {
    System.out.println(this.getPhoneCalls().toString());
  }

  /**
   * Sets phoneCalls field
   * @param phoneCalls List of phone calls included in this bill
   */
  public void setPhoneCalls(Collection phoneCalls) {
    this.phoneCalls = (ArrayList<PhoneCall>) phoneCalls;
  }
}
