/**
 * Created by Robert on 10/03/17.
 */
import javax.json.*;
import java.io.StringReader;

public class CountriesJSONParser {
    public static void setCountries(Countries countries, String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonStructure jsonst = jsonReader.read();
        JsonObject object = (JsonObject) jsonst;
        JsonObject response = object.getJsonObject("RestResponse");
        JsonArray result = response.getJsonArray("result");

        for(int x=0; x<result.size(); x++){
            JsonObject country = result.getJsonObject(x);
            String name = country.getString("name");
            String iso2code = country.getString("alpha2_code");
            countries.addCountry(name, iso2code);
        }
    }
}