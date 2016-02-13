package edu.pdx.cs410J.mwk2.client;

import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.impl.RpcStatsContext;

public class PhoneBillService_Proxy extends RemoteServiceProxy implements edu.pdx.cs410J.mwk2.client.PhoneBillServiceAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "edu.pdx.cs410J.mwk2.client.PhoneBillService";
  private static final String SERIALIZATION_POLICY ="89F52BD10DDDDA98249EC6FD38B94F3B";
  private static final edu.pdx.cs410J.mwk2.client.PhoneBillService_TypeSerializer SERIALIZER = new edu.pdx.cs410J.mwk2.client.PhoneBillService_TypeSerializer();
  
  public PhoneBillService_Proxy() {
    super(GWT.getModuleBaseURL(),
      "ping", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void createPhoneBill(java.lang.String customerName, java.lang.String callerNumber, java.lang.String calleeNumber, java.lang.String startTime, java.lang.String endTime, com.google.gwt.user.client.rpc.AsyncCallback async) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("PhoneBillService_Proxy", "createPhoneBill");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 5);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(customerName);
      streamWriter.writeString(callerNumber);
      streamWriter.writeString(calleeNumber);
      streamWriter.writeString(startTime);
      streamWriter.writeString(endTime);
      helper.finish(async, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      async.onFailure(ex);
    }
  }
  
  public void fetchPhoneBill(java.lang.String customerName, com.google.gwt.user.client.rpc.AsyncCallback stringAsyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("PhoneBillService_Proxy", "fetchPhoneBill");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(customerName);
      helper.finish(stringAsyncCallback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      stringAsyncCallback.onFailure(ex);
    }
  }
  
  public void searchPhoneBill(java.lang.String customerName, java.lang.String startTime, java.lang.String endTime, com.google.gwt.user.client.rpc.AsyncCallback async) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("PhoneBillService_Proxy", "searchPhoneBill");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 3);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(customerName);
      streamWriter.writeString(startTime);
      streamWriter.writeString(endTime);
      helper.finish(async, ResponseReader.STRING);
    } catch (SerializationException ex) {
      async.onFailure(ex);
    }
  }
  @Override
  public SerializationStreamWriter createStreamWriter() {
    ClientSerializationStreamWriter toReturn =
      (ClientSerializationStreamWriter) super.createStreamWriter();
    if (getRpcToken() != null) {
      toReturn.addFlags(ClientSerializationStreamWriter.FLAG_RPC_TOKEN_INCLUDED);
    }
    return toReturn;
  }
  @Override
  protected void checkRpcTokenType(RpcToken token) {
    if (!(token instanceof com.google.gwt.user.client.rpc.XsrfToken)) {
      throw new RpcTokenException("Invalid RpcToken type: expected 'com.google.gwt.user.client.rpc.XsrfToken' but got '" + token.getClass() + "'");
    }
  }
}
