/**
 * Created by Flo on 31/05/2016.
 */
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;
import service.MasherService;
import service.impl.MasherServiceImpl;

public class Main {
    public static void main(String... args) {
        Vertx vertx = Vertx.vertx();
        ProxyHelper.registerService(MasherService.class, vertx, new MasherServiceImpl(vertx), MasherService.ADDRESS);
        vertx.deployVerticle(Server.class.getName(), res -> {
            if (res.succeeded()) {
                System.out.println("Started");
            } else {
                System.out.println("failed");
            }
        });
    }
}