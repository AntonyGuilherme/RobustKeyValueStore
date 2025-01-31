package org.telecom.slr.tests.actor;

import akka.actor.AbstractActor;
import org.telecom.slr.actor.Actor;
import org.telecom.slr.actor.messages.ReadIssued;
import org.telecom.slr.actor.messages.WriteIssued;

import java.util.ArrayList;
import java.util.List;

public class ActorListener extends Actor {
    public static final List<Object> messages = new ArrayList<Object>();
    private static Long start;

    public ActorListener() {
        run((message, context) -> messages.add(message));
        run(this::log).when(message -> message instanceof WriteIssued);
        run(this::log).when(message -> message instanceof ReadIssued);
    }

    public static void setStart(long start) {
        ActorListener.start = start;
    }

    private void log(Object message, AbstractActor.ActorContext context) {
        String from = context.sender().path().name();
        String to = context.self().path().name();

        System.out.printf("from %s to %s : %s%n", from, to, message);
    }
}
