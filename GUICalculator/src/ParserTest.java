
class ParserTest {
	public static boolean flag=false;
    public static void main(String[] arg) throws ParseException {
		//title,width,height
		CalcView v = new CalcView("電卓",600,700);
		v.setLocationRelativeTo(null);

		/* =を押したらパーサ起動、StringにあるものをInputStreamに変換し、double型で返す。 String.valueOf(double)でresultLabelに表示 */
/*
			final Reader r = new InputStreamReader(System.in);
			final Parser parser = new Parser(r);
			final double tree = parser.start();
*/

    }
}