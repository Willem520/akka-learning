package willem.weiyu.java.akka;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

/**
 * @Author weiyu
 * @Description
 * @Date 2019/3/4 10:49
 */
public class HelloAkka {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("hello-actor-java");
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class));
        helloActor.tell("Willem", ActorRef.noSender());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        system.terminate();
    }

    private static class HelloActor extends AbstractActor {

        @Override
        public Receive createReceive() {
            return ReceiveBuilder.create().match(String.class, s -> System.out.println("Hello " + s)).
                    build();
        }
    }
}
