package alpine.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class JsonWriteBenchmark {
    private static final ObjectMapper JACKSON = new ObjectMapper();
    private static final Gson GSON = new Gson();

    private static final String COMPLEX_JSON = """
    {
        "name": "Mudkip",
        "type": "Water",
        "stats": {
            "hp": 50,
            "attack": 70,
            "defense": 50,
            "speed": 40,
            "abilities": ["Torrent", "Damp"]
        },
        "evolution": {
            "level": 16,
            "next": "Marshtomp"
        },
        "moves": [
            {"name": "Water Gun", "power": 40},
            {"name": "Tackle", "power": 35},
            {"name": "Mud-Slap", "power": 20}
        ],
        "metadata": {
            "origin": "Hoenn",
            "isLegendary": false
        }
    }
    """;

    private Element alpineElement;
    private JsonElement gsonElement;
    private JsonNode jacksonNode;
    private JSONObject fastjsonObject;

    @Setup(Level.Trial)
    public void setup() throws Exception {
        this.alpineElement = Json.read(COMPLEX_JSON);
        this.gsonElement = JsonParser.parseString(COMPLEX_JSON);
        this.jacksonNode = JACKSON.readTree(COMPLEX_JSON);
        this.fastjsonObject = JSON.parseObject(COMPLEX_JSON);
    }

    @Benchmark
    public void alpine(Blackhole blackhole) throws Exception {
        blackhole.consume(Json.write(this.alpineElement, Json.Formatting.COMPACT));
    }

    @Benchmark
    public void gson(Blackhole blackhole) throws Exception {
        blackhole.consume(GSON.toJson(this.gsonElement));
    }

    @Benchmark
    public void jackson(Blackhole blackhole) throws Exception {
        blackhole.consume(JACKSON.writeValueAsString(this.jacksonNode));
    }

    @Benchmark
    public void fastjson(Blackhole blackhole) throws Exception {
        blackhole.consume(JSON.toJSONString(this.fastjsonObject));
    }
}
