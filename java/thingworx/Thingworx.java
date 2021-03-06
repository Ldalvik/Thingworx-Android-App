package thingworx;

import android.content.Context;
import android.widget.TextView;

import org.json.simple.JSONObject;

import java.util.concurrent.ExecutionException;

public class Thingworx {
    private String thing;
    private Context ctx;
    private TextView tv;
    private String ENDPOINT = "http://pp-1804271345f2.portal.ptc.io:8080/Thingworx/Things/";

    public Thingworx(String thing, Context ctx, TextView tv) {
        this.thing = thing;
        this.tv = tv;
        this.ctx = ctx;
    }

    public void createThing(String name, String description, String thingTemplateName) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("description", description);
        obj.put("thingTemplateName", thingTemplateName);
        PostRequest p = new PostRequest(ctx,
                ENDPOINT + "/Thingworx/Resources/EntityServices/Services/CreateThing",
                obj.toJSONString()
        );
        p.execute();
    }

    public void getProperties(String property) throws ExecutionException, InterruptedException {
        GetRequest g = new GetRequest(ctx, ENDPOINT + thing + "/Properties/" + property, property, tv);
        g.execute();
    }

    public void addProperty(String name, String type) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("type", type);
        PostRequest p = new PostRequest(ctx,
                ENDPOINT + thing + "/Services/AddPropertyDefinition",
                obj.toJSONString()
        );
        p.execute();
    }

    /*public void sendText(String number, String message){
        JSONObject obj = new JSONObject();
        obj.put("message", message);
        obj.put("number", number);
        Utils.postRequest(ENDPOINT + thing + "/Services/sendText", obj.toJSONString());
    }*/

    public void updateProperty(String property, String value) {
        JSONObject obj = new JSONObject();
        obj.put(property, value);
        PutRequest p = new PutRequest(ctx,
                ENDPOINT + thing + "/Properties/*",
                obj.toJSONString()
        );
        p.execute();
    }

    public void updateProperty(String property, int value) {
        JSONObject obj = new JSONObject();
        obj.put(property, value);
        PutRequest p = new PutRequest(ctx,
                ENDPOINT + thing + "/Properties/*",
                obj.toJSONString()
        );
        p.execute();
    }

    /*public void restart(){
        Utils.postRequest(ENDPOINT + thing + "/Services/RestartThing");
    }

    public void enable(){
        Utils.postRequest(ENDPOINT + thing + "/Services/EnableThing");
    }*/                                             
}