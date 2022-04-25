// This file was automatically generated. Do not modify.
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.Set;
import java.util.Collection;

import de.renew.engine.simulator.SimulationThreadPool;
import de.renew.net.NetInstance;
import de.renew.net.Place;
import de.renew.net.PlaceInstance;
import de.renew.engine.searchqueue.SearchQueue;
public class MqttMessageReceiver
  extends de.renew.net.NetInstanceImpl
  implements pt.nfriacowboy.presib.hermes.utils.IMessageProcessor
{

  private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
                                                        .getLogger(MqttMessageReceiver.class);
  private final NetInstance _instance = this;

  public void messageReceived(final org.eclipse.paho.mqttv5.common.MqttMessage pparg0)
  {
      final Object vvarg0=pparg0;
      SimulationThreadPool.getCurrent().execute(new Runnable() {
        public void run() {
            de.renew.unify.Tuple inTuple;
            de.renew.unify.Tuple outTuple;
            inTuple=new de.renew.unify.Tuple(1);
            try {
              de.renew.unify.Unify.unify(inTuple.getComponent(0),vvarg0,null);
            } catch (de.renew.unify.Impossible e) {
              throw new RuntimeException("Unification failed unexpectedly.", e);
            }
            outTuple=de.renew.call.SynchronisationRequest.synchronize(
            _instance,"incomingMessage",inTuple);
//**only to avoid unused warnings. !BAD! style**
            outTuple.hashCode();
        }
      });
  }
  public MqttMessageReceiver()
  {
    super();
    Future<Object> future = SimulationThreadPool.getCurrent()
                                 .submitAndWait(new Callable<Object>() {
      public Object call() throws RuntimeException {
        try {
          de.renew.net.Net net = de.renew.net.Net.forName("mqtt_message_receiver");
          net.setEarlyTokens(true);
          initNet(net, true);
          createConfirmation(de.renew.application.SimulatorPlugin.getCurrent().getCurrentEnvironment().getSimulator().currentStepIdentifier());
        } catch (de.renew.net.NetNotFoundException e) {
          throw new RuntimeException(e.toString(), e);
        } catch (de.renew.unify.Impossible e) {
          throw new RuntimeException(e.toString(), e);
        }
        return null;
      }
    });
    try {
        future.get();
    } catch (InterruptedException e) {
        logger.error("Timeout while waiting for simulation thread to finish", e);
    } catch (ExecutionException e) {
        logger.error("Simulation thread threw an exception", e);
    }
  }
}
