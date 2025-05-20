import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.jakubjanor.model.*;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {

    @Test
    void testParseCommands() {
        String json = """
            {
              "commands": [
                {
                  "type": "addVehicle",
                  "vehicleId": "vehicle1",
                  "startRoad": "south",
                  "endRoad": "north"
                },
                {
                  "type": "step"
                },
                {
                  "type": "addVehicle",
                  "vehicleId": "vehicle2",
                  "startRoad": "east",
                  "endRoad": "west"
                }
              ]
            }
            """;

        Queue<Command> commands = CommandParser.parseCommands(json);
        assertEquals(3, commands.size());

        Command first = commands.poll();
        assertInstanceOf(AddVehicleCommand.class, first);
        AddVehicleCommand av1 = (AddVehicleCommand) first;
        assertEquals("vehicle1", av1.vehicleId());
        assertEquals("south", av1.startRoad());
        assertEquals("north", av1.endRoad());

        Command second = commands.poll();
        assertInstanceOf(StepCommand.class, second);

        Command third = commands.poll();
        assertInstanceOf(AddVehicleCommand.class, third);
        AddVehicleCommand av2 = (AddVehicleCommand) third;
        assertEquals("vehicle2", av2.vehicleId());
        assertEquals("east", av2.startRoad());
        assertEquals("west", av2.endRoad());
    }

    @Test
    void testWriteNotesToJSON() {
        Vehicle v1 = new Vehicle("v1", Direction.NORTH, Direction.SOUTH);
        Vehicle v2 = new Vehicle("v2", Direction.SOUTH, Direction.NORTH);

        Note note1 = new Note(List.of(v1, v2));
        Note note2 = new Note(List.of());

        String jsonOutput = CommandParser.writeNotesToJSON(List.of(note1, note2));
        JSONObject parsed = new JSONObject(jsonOutput);

        assertTrue(parsed.has("stepStatuses"));
        var steps = parsed.getJSONArray("stepStatuses");
        assertEquals(2, steps.length());

        var step0 = steps.getJSONObject(0);
        var left0 = step0.getJSONArray("leftVehicles");
        assertEquals(2, left0.length());
        assertEquals("v1", left0.get(0));
        assertEquals("v2", left0.get(1));

        var step1 = steps.getJSONObject(1);
        var left1 = step1.getJSONArray("leftVehicles");
        assertEquals(0, left1.length());
    }
}
