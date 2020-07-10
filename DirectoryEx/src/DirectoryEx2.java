import java.nio.file.*;

public class DirectoryEx2 {

	public static void main(String[] args) {
		
		Path a=Paths.get("C:\\apple\\banana\\mango\\SSS.txt");
		Path b=Paths.get("C:\\apple\\melon\\kiwi");
		
		System.out.println("파일 또는 디렉토리 명: "+a.getFileName());           //파일 또는 디렉토리 명: SSS.txt
		System.out.println("부모 디렉토리 명: "+a.getParent().getFileName());  //부모 디렉토리 명: mango
		System.out.println("중첩 경로 수: "+a.getNameCount());                //중첩 경로 수: 4
		System.out.println(a.getRoot());                                 //C:\
		System.out.println(Paths.get("광현이방\\SSS.txt").toAbsolutePath());//C:\Users\skdsk\eclipse-workspace2\DirectoryEx\광현이방\SSS.txt
		System.out.println(a.subpath(0, 3));                             //apple\banana\mango   <-마지막 인덱스는 포함X
		
		for(int i=0;i<a.getNameCount();i++) {
			System.out.println(a.getName(i));//apple, banana, mango, SSS.txt
		}
		
		System.out.println("======================================");
		
		System.out.println(a.resolve(b));//C:\apple\melon\kiwi
		System.out.println(b.resolve(a));//C:\apple\banana\mango\SSS.txt
		System.out.println(a.relativize(b));//..\..\..\melon\kiwi
		System.out.println(b.relativize(a));//..\..\banana\mango\SSS.txt
		System.out.println(a.resolve(a.relativize(b)));//C:\apple\banana\mango\SSS.txt\..\..\..\melon\kiwi
		System.out.println(b.resolve(b.relativize(a)));//C:\apple\melon\kiwi\..\..\banana\mango\SSS.txt
		
	}

}
