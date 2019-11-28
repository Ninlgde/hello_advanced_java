import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HelloWorld
{
	public static void main(String[] args)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str1 = "2011-12-01";
		String str2 = "2012-01-2";
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		try
		{
			bef.setTime(sdf.parse(str1));
			aft.setTime(sdf.parse(str2));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR))*12;
		System.out.println(month+result );
	}
}
