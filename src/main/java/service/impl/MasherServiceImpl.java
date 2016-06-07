package service.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import service.MasherService;
import java.util.List;

/**
 * Created by Flo on 31/05/2016.
 */
public class MasherServiceImpl implements MasherService {
    private MongoClient client;
    private static final String COLLECTION = "Mashers";

    public MasherServiceImpl(Vertx vertx) {
        super();
        JsonObject config = new JsonObject()
                .put("host", "127.0.0.1")
                .put("port", 27017)
                .put("db_name", "test");
        client = MongoClient.createShared(vertx, config);
    }

    public void getList(Handler<AsyncResult<List<JsonObject>>> resultHandler) {
        client.find(COLLECTION, new JsonObject(), resultHandler);
    }

    public void setList(JsonObject masherObject){
        client.save(COLLECTION, masherObject, res -> {
            if (res.succeeded()) {
                System.out.println("DataBase Saved");
            } else {
                res.cause().printStackTrace();
            }
        });
    }
}