/**
 * Created by Flo on 31/05/2016.
 */
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import service.MasherService;

public class Server  extends AbstractVerticle {
    MasherService masherService;
    JsonArray masherList = new JsonArray();

    @Override
    public void start() throws Exception {
        masherService = MasherService.createProxy(vertx, MasherService.ADDRESS);
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/api/*").handler(routingContext -> {
            routingContext.response()
                    .putHeader("content-type", "application/json")
                    .setChunked(true);
            routingContext.next();
        });
        router.get("/mash").handler(this::masherGetList);
        router.get("/test").handler(this::masherSetList);
        router.route("/*").handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(router::accept).listen(9090);
    }

    private void masherGetList(RoutingContext routingContext) {
        masherService.getList(asyncResult -> {
            masherList.clear();
            asyncResult.result().forEach(masherList::add);
            routingContext.response().end(masherList.encodePrettily());
            /*for (int i = 0; i < masherList.size(); i++) {
                Masher masher = new Masher();
                masher.setDescription(masherList.getJsonObject(i).getString("description"));
                masher.setId(masherList.getJsonObject(i).getString("id"));
                masher.setImg(masherList.getJsonObject(i).getString("img"));
                masher.setName(masherList.getJsonObject(i).getString("name"));
                masher.setElo(Integer.parseInt(masherList.getJsonObject(i).getString("elo")));
                masher.setParties(Integer.parseInt(masherList.getJsonObject(i).getString("parties")));
            }*/
        });
    }

    private void masherSetList(RoutingContext routingContext){
        masherList.getJsonObject(0).put("description", "C'est vert et blanc mais on s'en fiche pas mal hein.");
        masherService.setList(masherList.getJsonObject(0));
    }
}