package test.com.vi8e.um.wunderlist.util;

import com.vi8e.um.wunderlist.utils.DateTimeHelper;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Calendar;
import java.util.Date;


/**
 * DateTimeHelper Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>09/29/2015</pre>
 */
public
class DateTimeHelperTest extends TestCase {
public
DateTimeHelperTest ( String name ) {
	super ( name );
}

protected double fValue1;
protected double fValue2;

public
void setUp () throws Exception {
	super.setUp ();
	fValue1 = 2.0;
	fValue2 = 3.0;

}

public
void testAdd () {
	double result = fValue1 + fValue2;
	assertTrue ( result == 5.0 );
}


public
void tearDown () throws Exception {
	super.tearDown ();
}

/**
 * Method: getTimeHHmm(Date date)
 */
public
void testGetTimeHHmm () throws Exception {
//TODO: Test goes here... 
}

/**
 * Method: getFormatedDate(Date date)
 */
public
void testGetFormatedDate () throws Exception {
//TODO: Test goes here... 
}

/**
 * Method: getYesterdayOrTodayOrTmr(Date dateTime)
 */
public
void testGetYesterdayOrTodayOrTmr () throws Exception {

	Date date = new Date ();
	Calendar calendar=Calendar.getInstance ();

	Assert.assertEquals ( DateTimeHelper.getYesterdayOrTodayOrTmr (calendar.getTime ()),"Today " );
	calendar.add ( Calendar.DAY_OF_YEAR, 1 );
	Assert.assertEquals ( DateTimeHelper.getYesterdayOrTodayOrTmr (calendar.getTime ()), "Tomorrow " );
	calendar = Calendar.getInstance ();
	calendar.add ( Calendar.DAY_OF_YEAR,-1 );
	Assert.assertEquals ( DateTimeHelper.getYesterdayOrTodayOrTmr (calendar.getTime ()),"Yesterday " );

//TODO: Test goes here... 
}


public static
Test suite () {
	return new TestSuite ( DateTimeHelperTest.class );
}
} 
