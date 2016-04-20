package com.augmatics.battleship;

import com.google.common.collect.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class BattleshipTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BattleshipTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BattleshipTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    
        ImmutableList<Integer> integerList = ContiguousSet.create(Range.closed(0, 10), DiscreteDomain.integers()).asList();
        
        System.out.println(integerList);
        
        assertTrue( true );
    }
}
