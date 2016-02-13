package edu.pdx.cs410J.mwk2.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.lang.Override;
import java.util.Date;


/**
 * This class represents a phone call between a caller (the
 * phone number of the person who originates the call) and callee (the
 * phone number of the person whose receives the phone call).  Phone
 * calls begin and end at given times. It extends the class
 * AbstractPhoneCall, written by David Whitlock
 *
 * @author Mark Kirby
 * @version %I%, %G%
 *
 */
public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {
  /**
   * The phone number of caller
   */
  private String callerNumber;

  /**
   * The phone number of person who was called
   */
  private String calleeNumber;

  /**
   * The date and time the call began
   */
  private String startTime;

  /**
   * The date and time the call began (Date format)
   */
  private Date startDate;

  /**
   * The date and time the call ended
   */
  private String endTime;

  /**
   * The date and time the call ended (Date format)
   */
  private Date endDate;

  /**
   * Creates a new <code>PhoneCall</code>
   * @param aCallerNumber The phone number of caller
   * @param aCalleeNumber The phone number of person who was called
   * @param aStartTime  The date and time the call began
   * @param aEndTime  The date and time the call ended
   *
   */
  public PhoneCall(String aCallerNumber, String aCalleeNumber, String aStartTime, String aEndTime, Date startDate, Date endDate) {
    this.callerNumber = aCallerNumber;
    this.calleeNumber = aCalleeNumber;
    this.startTime = aStartTime;
    this.endTime = aEndTime;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public PhoneCall() {
  }

  /**
   * Returns the phone number of the person who originated this phone
   * call.
   * @return callerNumber The phone number of caller
   *
   */
  @Override
  public String getCaller() {
    return callerNumber;
  }

  /**
   * Returns the phone number of the person who received this phone
   * call.
   * @return calleeNumber The phone number of person who was called
   *
   */
  @Override
  public String getCallee() {
    return calleeNumber;
  }

  /**
   * Returns a textual representation of the time that this phone call
   * was originated.
   * @return startTime The date and time the call began
   *
   */
  @Override
  public String getStartTimeString() {
    return startTime;
  }

  /**
   * Returns a textual representation of the time that this phone call
   * was completed.
   * @return endTime The date and time the call ended
   */
  @Override
  public String getEndTimeString() {
    return endTime;
  }

  /**
   * Returns startDate (Date format)
   * @return <code>Date</code> start date/time of phone call
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * Returns endDate (Date format)
   * @return <code>Date</code> end date/time of phone call
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * Compares this <code>PhoneCall</code> with the specified <code>PhoneCall</code>
   * object for order. The phone calls are ordered according to their start time.
   * If the start times are equal, they are then sorted according to their stored
   * caller number. If both the start time and caller number are equal, the phone
   * calls are considered to be equal.
   * <p>
   * Returns a negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   * <p>
   * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
   * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
   * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
   * <tt>y.compareTo(x)</tt> throws an exception.)
   * <p>
   * <p>The implementor must also ensure that the relation is transitive:
   * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
   * <tt>x.compareTo(z)&gt;0</tt>.
   * <p>
   * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
   * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
   * all <tt>z</tt>.
   * <p>
   * <p>It is strongly recommended, but <i>not</i> strictly required that
   * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
   * class that implements the <tt>Comparable</tt> interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   * <p>
   * <p>In the foregoing description, the notation
   * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
   * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
   * <tt>0</tt>, or <tt>1</tt> according to whether the value of
   * <i>expression</i> is negative, zero or positive.
   *
   * @param thatCall the <code>PhoneCall</code> object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   */
  @Override
  public int compareTo(PhoneCall thatCall) {
    int result = 0;
    Date thisStartTime = this.startDate;
    Date thatStartTime = thatCall.startDate;
    String thisCaller = this.callerNumber;
    String thatCaller = thatCall.callerNumber;

    if (thisStartTime.compareTo(thatStartTime) == 0) {
      result = thisCaller.compareTo(thatCaller);
    } else {
      result = thisStartTime.compareTo(thatStartTime);
    }
    return result;
  }
}
