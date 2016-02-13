package edu.pdx.cs410J.mwk2.client;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class PhoneCall_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getCalleeNumber(edu.pdx.cs410J.mwk2.client.PhoneCall instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::calleeNumber;
  }-*/;
  
  private static native void setCalleeNumber(edu.pdx.cs410J.mwk2.client.PhoneCall instance, java.lang.String value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::calleeNumber = value;
  }-*/;
  
  private static native java.lang.String getCallerNumber(edu.pdx.cs410J.mwk2.client.PhoneCall instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::callerNumber;
  }-*/;
  
  private static native void setCallerNumber(edu.pdx.cs410J.mwk2.client.PhoneCall instance, java.lang.String value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::callerNumber = value;
  }-*/;
  
  private static native java.util.Date getEndDate(edu.pdx.cs410J.mwk2.client.PhoneCall instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::endDate;
  }-*/;
  
  private static native void setEndDate(edu.pdx.cs410J.mwk2.client.PhoneCall instance, java.util.Date value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::endDate = value;
  }-*/;
  
  private static native java.lang.String getEndTime(edu.pdx.cs410J.mwk2.client.PhoneCall instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::endTime;
  }-*/;
  
  private static native void setEndTime(edu.pdx.cs410J.mwk2.client.PhoneCall instance, java.lang.String value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::endTime = value;
  }-*/;
  
  private static native java.util.Date getStartDate(edu.pdx.cs410J.mwk2.client.PhoneCall instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::startDate;
  }-*/;
  
  private static native void setStartDate(edu.pdx.cs410J.mwk2.client.PhoneCall instance, java.util.Date value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::startDate = value;
  }-*/;
  
  private static native java.lang.String getStartTime(edu.pdx.cs410J.mwk2.client.PhoneCall instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::startTime;
  }-*/;
  
  private static native void setStartTime(edu.pdx.cs410J.mwk2.client.PhoneCall instance, java.lang.String value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneCall::startTime = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, edu.pdx.cs410J.mwk2.client.PhoneCall instance) throws SerializationException {
    setCalleeNumber(instance, streamReader.readString());
    setCallerNumber(instance, streamReader.readString());
    setEndDate(instance, (java.util.Date) streamReader.readObject());
    setEndTime(instance, streamReader.readString());
    setStartDate(instance, (java.util.Date) streamReader.readObject());
    setStartTime(instance, streamReader.readString());
    
    edu.pdx.cs410J.AbstractPhoneCall_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static edu.pdx.cs410J.mwk2.client.PhoneCall instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new edu.pdx.cs410J.mwk2.client.PhoneCall();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.pdx.cs410J.mwk2.client.PhoneCall instance) throws SerializationException {
    streamWriter.writeString(getCalleeNumber(instance));
    streamWriter.writeString(getCallerNumber(instance));
    streamWriter.writeObject(getEndDate(instance));
    streamWriter.writeString(getEndTime(instance));
    streamWriter.writeObject(getStartDate(instance));
    streamWriter.writeString(getStartTime(instance));
    
    edu.pdx.cs410J.AbstractPhoneCall_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return edu.pdx.cs410J.mwk2.client.PhoneCall_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    edu.pdx.cs410J.mwk2.client.PhoneCall_FieldSerializer.deserialize(reader, (edu.pdx.cs410J.mwk2.client.PhoneCall)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    edu.pdx.cs410J.mwk2.client.PhoneCall_FieldSerializer.serialize(writer, (edu.pdx.cs410J.mwk2.client.PhoneCall)object);
  }
  
}
