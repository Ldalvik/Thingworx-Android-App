package thingworx;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Properties {
    private JSONObject prop;
    private String property;
    private String json;

    public Properties(String json, String property) {
        this.json = json;
        this.property = property;
        JSONParser parse = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parse.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject obj2 = (JSONObject) obj.get("dataShape");
        JSONObject obj3 = (JSONObject) obj2.get("fieldDefinitions");
        prop = (JSONObject) obj3.get(property);
    }

    public String getName(){
        return (String) prop.get("name");
    }

    public String getDescription(){
        return (String) prop.get("description");
    }

    public String getBaseType(){
        return (String) prop.get("baseType");
    }

    public Long getOrdinal(){
        return (Long) prop.get("ordinal");
    }

    public boolean isReadOnly(){
        JSONObject var = (JSONObject) prop.get("aspects");
        return (boolean) var.get("isReadOnly");
    }

    public boolean isPersistent(){
        JSONObject var = (JSONObject) prop.get("aspects");
        return (boolean) var.get("isPersistent");
    }

    public boolean isLogged(){
        JSONObject var = (JSONObject) prop.get("aspects");
        return (boolean) var.get("isLogged");
    }

    public String getDataChangeType(){
        JSONObject var = (JSONObject) prop.get("aspects");
        return (String) var.get("dataChangeType");
    }

    public String getCacheTime(){
        JSONObject var = (JSONObject) prop.get("aspects");
        return String.valueOf(var.get("cacheTime"));
    }

    public String getValue(){
        JSONParser parse = new JSONParser();
        JSONObject var = null;
        try {
            var = (JSONObject) parse.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray arr = (JSONArray) var.get("rows");
        JSONObject var2 = (JSONObject) arr.get(0);
        return String.valueOf(var2.get(property));
    }

    @Override
    public String toString(){
        return json;
    }
}
