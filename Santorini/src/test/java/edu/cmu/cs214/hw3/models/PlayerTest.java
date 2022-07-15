package edu.cmu.cs214.hw3.models;

import edu.cmu.cs214.hw3.models.gods.NoGod;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;


/**
 * The test is mainly focused on testing {@link Player}
 */
public class PlayerTest {

    Worker worker; // selectedWorker
    // worker that is not selected but belong to current player
    Worker notSelectedWorker;
    Player player;
    Worker fakeWorker; // fake worker doesn't belong to the current player
    Grid dest;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        player = new Player(new NoGod());
        worker = new Worker(player);
        notSelectedWorker = new Worker(player); /* a worker that is not selected */
        dest = new Grid(3, 4);
        fakeWorker = new Worker(new Player()); /* worker that doesn't belong to this player */

    }


    @Test
    public void test_getters() {
        Worker worker2 = new Worker(player);
        assertEquals(player.getWorkerList().size(), 3);


        player.setSelectedWorker(worker);
        assertEquals(player.getSelectedWorker(), worker);
        assertNotEquals(player.getSelectedWorker(), worker2);

    }

    @Test
    public void test_workersAllInit() {
        assertEquals(player.getWorkerList().size(), 2);
        assertFalse(player.workersAllInit());
        worker.setPosition(dest);

        assertFalse(player.workersAllInit());

        notSelectedWorker.setPosition(new Grid(2,3));
        assertTrue(player.workersAllInit());

    }

}
