package service;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;
import service.impl.MasherServiceImpl;

import java.util.List;

/**
 * Created by Flo on 31/05/2016.
 */
@ProxyGen
@VertxGen
public interface MasherService {
    String ADDRESS = "mash.service";

    static MasherService create(Vertx vertx) {
        return new MasherServiceImpl(vertx);
    }

    static MasherService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(MasherService.class, vertx, address);
    }

    void getList(Handler<AsyncResult<List<JsonObject>>> resultHandler);

    void setList(JsonObject masherObject);
}
