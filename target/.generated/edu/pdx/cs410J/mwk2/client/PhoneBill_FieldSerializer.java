package edu.pdx.cs410J.mwk2.client;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class PhoneBill_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getCustomer(edu.pdx.cs410J.mwk2.client.PhoneBill instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneBill::customer;
  }-*/;
  
  private static native void setCustomer(edu.pdx.cs410J.mwk2.client.PhoneBill instance, java.lang.String value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneBill::customer = value;
  }-*/;
  
  private static native java.util.ArrayList getPhoneCalls(edu.pdx.cs410J.mwk2.client.PhoneBill instance) /*-{
    return instance.@edu.pdx.cs410J.mwk2.client.PhoneBill::phoneCalls;
  }-*/;
  
  private static native void setPhoneCalls(edu.pdx.cs410J.mwk2.client.PhoneBill instance, java.util.ArrayList value) 
  /*-{
    instance.@edu.pdx.cs410J.mwk2.client.PhoneBill::phoneCalls = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, edu.pdx.cs410J.mwk2.client.PhoneBill instance) throws SerializationException {
    setCustomer(instance, streamReader.readString());
    setPhoneCalls(instance, (java.util.ArrayList) streamReader.readObject());
    
    edu.pdx.cs410J.AbstractPhoneBill_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static edu.pdx.cs410J.mwk2.client.PhoneBill instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new edu.pdx.cs410J.mwk2.client.PhoneBill();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.pdx.cs410J.mwk2.client.PhoneBill instance) throws SerializationException {
    streamWriter.writeString(getCustomer(instance));
    streamWriter.writeObject(getPhoneCalls(instance));
    
    edu.pdx.cs410J.AbstractPhoneBill_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return edu.pdx.cs410J.mwk2.client.PhoneBill_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    edu.pdx.cs410J.mwk2.client.PhoneBill_FieldSerializer.deserialize(reader, (edu.pdx.cs410J.mwk2.client.PhoneBill)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    edu.pdx.cs410J.mwk2.client.PhoneBill_FieldSerializer.serialize(writer, (edu.pdx.cs410J.mwk2.client.PhoneBill)object);
  }
  
}
