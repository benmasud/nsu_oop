package r.masud.ccfit.nsu.ru.task_2_3_1_.controller.json;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;
import r.masud.ccfit.nsu.ru.task_2_3_1_.model.json.JsonData;

public class JsonParser {

    /**
     * Retrieves JSON data from a file.
     *
     * @param file The file containing the JSON data.
     * @return The parsed JSON data.
     */
    public JsonData getData(String file) {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream(file)))) {
            Gson gson = new Gson();
            return gson.fromJson(reader,
                    new TypeToken<JsonData>() {}.getType());
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
