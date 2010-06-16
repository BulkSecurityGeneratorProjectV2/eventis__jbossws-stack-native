package org.jboss.test.ws.xop.doclit;

import java.rmi.RemoteException;

/**
 * @author Heiko Braun, <heiko@openj.net>
 * @since 11-Apr-2006
 */
public class TestServiceImpl implements TestService_PortType {
    public PingMsgResponse ping(PingMsg pingMsg) throws RemoteException {
        return new PingMsgResponse(pingMsg.getXopContent());
    }
}