package org.jboss.ws.eventing.mgmt;

import org.jboss.ws.WSException;
import org.jboss.ws.eventing.EventingConstants;
import org.jboss.ws.eventing.deployment.EventingEndpointDI;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Heiko Braun, <heiko@openj.net>
 * @since 24-Jan-2006
 */
public class EventingBuilder {

   private EventingBuilder() {
   }

   public static EventingBuilder createEventingBuilder() {
      return new EventingBuilder();
   }

   public EventSource newEventSource(EventingEndpointDI desc) {
      URI eventSourceNS = newEventSourceURI(desc.getName());
      EventSource eventSource = new EventSource(desc.getName(), eventSourceNS, (String)desc.getSchema());
      eventSource.getSupportedFilterDialects().add(EventingConstants.getDefaultFilterDialect());
      return eventSource;
   }


   public URI newEventSourceURI(String name) {
      try
      {
         return new URI(name);
      }
      catch (URISyntaxException e)
      {
         throw new WSException("Failed to create eventsource URI: " + e.getMessage());
      }
   }


}