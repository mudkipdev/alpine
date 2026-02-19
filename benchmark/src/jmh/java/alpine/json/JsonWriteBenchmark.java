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

    private static final String[] INPUTS = {
        """
        {"name":"Mudkip","type":"Water","stats":{"hp":50,"attack":70,"defense":50,"speed":40,"abilities":["Torrent","Damp"]},"evolution":{"level":16,"next":"Marshtomp"},"moves":[{"name":"Water Gun","power":40},{"name":"Tackle","power":35},{"name":"Mud-Slap","power":20}],"metadata":{"origin":"Hoenn","isLegendary":false}}
        """,
        """
        {"name":"Charizard","type":"Fire","stats":{"hp":78,"attack":84,"defense":78,"speed":100,"abilities":["Blaze","Solar Power"]},"evolution":{"level":36,"next":null},"moves":[{"name":"Flamethrower","power":90},{"name":"Dragon Claw","power":80},{"name":"Air Slash","power":75},{"name":"Fire Blast","power":110}],"metadata":{"origin":"Kanto","isLegendary":false}}
        """,
        """
        {"name":"Pikachu","type":"Electric","stats":{"hp":35,"attack":55,"defense":40,"speed":90,"abilities":["Static","Lightning Rod"]},"evolution":{"level":null,"next":"Raichu"},"moves":[{"name":"Thunderbolt","power":90},{"name":"Quick Attack","power":40}],"metadata":{"origin":"Kanto","isLegendary":false}}
        """,
        """
        {"name":"Mewtwo","type":"Psychic","stats":{"hp":106,"attack":110,"defense":90,"speed":130,"abilities":["Pressure","Unnerve"]},"evolution":{"level":null,"next":null},"moves":[{"name":"Psystrike","power":100},{"name":"Shadow Ball","power":80},{"name":"Aura Sphere","power":80},{"name":"Ice Beam","power":90},{"name":"Recover","power":0}],"metadata":{"origin":"Kanto","isLegendary":true}}
        """,
        """
        {"name":"Bulbasaur","type":"Grass","stats":{"hp":45,"attack":49,"defense":49,"speed":45,"abilities":["Overgrow","Chlorophyll"]},"evolution":{"level":16,"next":"Ivysaur"},"moves":[{"name":"Vine Whip","power":45},{"name":"Razor Leaf","power":55},{"name":"Tackle","power":35}],"metadata":{"origin":"Kanto","isLegendary":false}}
        """,
        """
        {"name":"Snorlax","type":"Normal","stats":{"hp":160,"attack":110,"defense":65,"speed":30,"abilities":["Immunity","Thick Fat","Gluttony"]},"evolution":{"level":null,"next":null},"moves":[{"name":"Body Slam","power":85},{"name":"Crunch","power":80},{"name":"Rest","power":0},{"name":"Snore","power":50}],"metadata":{"origin":"Kanto","isLegendary":false}}
        """,
        """
        {"name":"Gengar","type":"Ghost","stats":{"hp":60,"attack":65,"defense":60,"speed":110,"abilities":["Cursed Body"]},"evolution":{"level":null,"next":"Mega Gengar"},"moves":[{"name":"Shadow Ball","power":80},{"name":"Sludge Bomb","power":90},{"name":"Dazzling Gleam","power":80},{"name":"Thunderbolt","power":90}],"metadata":{"origin":"Kanto","isLegendary":false}}
        """,
        """
        {"name":"Eevee","type":"Normal","stats":{"hp":55,"attack":55,"defense":50,"speed":55,"abilities":["Run Away","Adaptability","Anticipation"]},"evolution":{"level":null,"next":null},"moves":[{"name":"Swift","power":60},{"name":"Bite","power":60},{"name":"Covet","power":60}],"metadata":{"origin":"Kanto","isLegendary":false}}
        """
    };

    private Element[] alpineArray;
    private JsonElement[] gsonArray;
    private JsonNode[] jacksonArray;
    private JSONObject[] fastjsonArray;
    private int index;

    @Setup(Level.Trial)
    public void setup() throws Exception {
        this.alpineArray = new Element[INPUTS.length];
        this.gsonArray = new JsonElement[INPUTS.length];
        this.jacksonArray = new JsonNode[INPUTS.length];
        this.fastjsonArray = new JSONObject[INPUTS.length];

        for (var index = 0; index < INPUTS.length; index++) {
            this.alpineArray[index] = Json.read(INPUTS[index]);
            this.gsonArray[index] = JsonParser.parseString(INPUTS[index]);
            this.jacksonArray[index] = JACKSON.readTree(INPUTS[index]);
            this.fastjsonArray[index] = JSON.parseObject(INPUTS[index]);
        }
    }

    @Benchmark
    public void alpine(Blackhole blackhole) throws Exception {
        blackhole.consume(Json.write(this.alpineArray[this.index++ % INPUTS.length], Json.Formatting.COMPACT));
    }

    @Benchmark
    public void gson(Blackhole blackhole) throws Exception {
        blackhole.consume(GSON.toJson(this.gsonArray[this.index++ % INPUTS.length]));
    }

    @Benchmark
    public void jackson(Blackhole blackhole) throws Exception {
        blackhole.consume(JACKSON.writeValueAsString(this.jacksonArray[this.index++ % INPUTS.length]));
    }

    @Benchmark
    public void fastjson(Blackhole blackhole) throws Exception {
        blackhole.consume(JSON.toJSONString(this.fastjsonArray[this.index++ % INPUTS.length]));
    }
}
