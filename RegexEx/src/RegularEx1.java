import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegularEx1 {

	public static void main(String[] args) {
		String[] data= {"bat", "baby", "bonus", "cA", "ca", "co", "c.",
				"c0", "cat", "combat", "count", "date", "disc"};
		Pattern p=Pattern.compile("c[a-z]*");//c로 시작하는 소문자 영단어
		
		for(int i=0;i<data.length;i++) {
			Matcher m=p.matcher(data[i]);
			if(m.matches())
				System.out.print(data[i]+", ");
		}
	}

}
/*
	===============Matcher 클래스의 메서드===============
	boolean matches() 			정규표현식이 패턴에 일치하는지 여부를 테스트
	boolean find()				패턴에 일치하는 다음 표현식을 찾는다.
	boolean find(int start)		주어진 시작 숫자부터 패턴에 일치하는 다음 표현식을 찾는다.
	
	
	
	===============Pattern 클래스의 메서드===============
	static Pattern compile(String regex)
	주어진 정규표현식을 컴파일하고, 인터페이스 패턴을 반환한다.
	
	Matcher matcher(CharSequence input)
	주어진 입력, 패턴과 일치하는 matcher를 만든다
	
	static boolean matches(String regex, CharSequence input)
	컴파일과 matcher 메소드의 결합으로 동작한다. 정규표현식을 컴파일하고, 주어진 입력 패턴을 일치하게 한다.
	
	String[] split(CharSequence input)
	주어진 패턴에 맞는 주어진 입력 문자열을 나눈다.
	
	String pattern()
	정규표현식 패턴을 반환한다.
*/