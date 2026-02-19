package alpine.json;

import com.alibaba.fastjson2.JSON;
import com.google.gson.JsonParser;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class JsonReadBenchmark {
    private static final ObjectMapper JACKSON = new ObjectMapper();

    // Pool of 8 varied inputs (power of 2 for cheap index masking).
    // Different string lengths, number values, array sizes and nesting depth
    // prevent the branch predictor from memorizing a single character sequence.
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

    private int index = 0;

    @Benchmark
    public void alpine(Blackhole blackhole) throws Exception {
        blackhole.consume(Json.read(INPUTS[this.index++ % INPUTS.length]));
    }

    @Benchmark
    public void gson(Blackhole blackhole) throws Exception {
        blackhole.consume(JsonParser.parseString(INPUTS[this.index++ % INPUTS.length]));
    }

    @Benchmark
    public void jackson(Blackhole blackhole) throws Exception {
        blackhole.consume(JACKSON.readTree(INPUTS[this.index++ % INPUTS.length]));
    }

    @Benchmark
    public void fastjson(Blackhole blackhole) throws Exception {
        blackhole.consume(JSON.parseObject(INPUTS[this.index++ % INPUTS.length]));
    }
}
