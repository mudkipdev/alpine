package alpine.json;

import com.google.gson.JsonParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class JsonBenchmark {
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

    @Benchmark
    public void alpine() throws ParsingException {
        Json.read(COMPLEX_JSON);
    }

    @Benchmark
    public void gson() {
        JsonParser.parseString(COMPLEX_JSON);
    }
}
