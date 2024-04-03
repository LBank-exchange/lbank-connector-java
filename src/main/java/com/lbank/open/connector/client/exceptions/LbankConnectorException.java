package com.lbank.open.connector.client.exceptions;

public class LbankConnectorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LbankConnectorException(String fullErrMsg) {
        super(fullErrMsg);
    }

}
