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

    // non-static, non-final to prevent the JVM from constant-folding the input
    String complexJson = """
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

    @Benchmark
    public void alpine(Blackhole blackhole) throws Exception {
        blackhole.consume(Json.read(this.complexJson));
    }

    @Benchmark
    public void gson(Blackhole blackhole) throws Exception {
        blackhole.consume(JsonParser.parseString(this.complexJson));
    }

    @Benchmark
    public void jackson(Blackhole blackhole) throws Exception {
        blackhole.consume(JACKSON.readTree(this.complexJson));
    }

    @Benchmark
    public void fastjson(Blackhole blackhole) throws Exception {
        blackhole.consume(JSON.parseObject(this.complexJson));
    }
}
