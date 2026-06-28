package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class JsonUtil {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Date.class, new DatumAdapter())
            .create();

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static void upisiUFajl(Object obj, File fajl) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(fajl), StandardCharsets.UTF_8)) {
            writer.write(toJson(obj));
        }
    }

    public static <T> T citajIzFajla(File fajl, Class<T> clazz) throws IOException {
        try (Reader reader = new InputStreamReader(new FileInputStream(fajl), StandardCharsets.UTF_8)) {
            return GSON.fromJson(reader, clazz);
        }
    }

    private static final class DatumAdapter extends TypeAdapter<Date> {

        private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(sdf.format(value));
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                return sdf.parse(in.nextString());
            } catch (ParseException e) {
                throw new IOException("Neispravan format datuma, očekivan je " + DATE_FORMAT, e);
            }
        }
    }
}
