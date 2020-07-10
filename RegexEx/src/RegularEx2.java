import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularEx2 {

	public static void main(String[] args) {
		String[] data= {"bat", "baby", "bonus", "cA", "ca", "co", "c.",
				"c0", "c#", "car", "combat", "count", "date", "disc", "ck", "c\\n"};
		
		String[] pattern= {".*", "c[a-z]*", "c[a-z]", "c[a-zA-Z]",
				"c[a-zA-Z0-9]", "c.", "c.*", "c\\.", "c\\w", "c\\d",
				"c.*t", "[b|c].*", ".*a.*", ".*a.+", "[b|c].{2}" }; 
		
		for(int x=0;x<pattern.length;x++) {
			Pattern p=Pattern.compile(pattern[x]);
			System.out.print("Pattern : "+pattern[x] + "  결과: ");
			for(int i=0; i<data.length;i++) {
				Matcher m = p.matcher(data[i]);
				if(m.matches()) {
					System.out.print(data[i]+ ",");
				}
			}
			System.out.println();
		}
	}

}
/*	
	^:	문자열 시작
	$:	문자열 종료
	.:	임의의 문자(단''는 넣을 수 없음)
	*:	앞 문자가 0개 이상의 개수가 존재할 수 있다.
	+:	앞 문자가 1개 이상의 개수가 존재할 수 있다.
	?:	앞 문자가 없거나 하나 있을 수 있다.
	[]:	문자의 집합이나 범위를 표현한다. -기호를 통해 범위를 나타낼 수 있다. ^가 존재하면 not을 나타낸다.
	{}:	횟수 또는 범위를 나타낸다.
	(): 괄호 안의 문자를 하나의 문자로 인식한다.
	|:	패턴을 OR연산을 수행할 때 사용한다.
	
	
	\d:	숫자:	[0-9]
	\D: 숫자가 아닌 문자
	\s: 공백 문자:	[\t\n\x0B\f\r]
	\S: 공백이 아닌 문자:	[^\s]
	\w: 알파벳 단어 문자(word 문자): [a-zA-Z_0-9]
	\W: 알파벳 단어 문자가 아닌것: [^\w]
*/
