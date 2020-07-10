import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryEx4 {

	public static void main(String[] args) {
		
		Path root=Paths.get("C:\\Users\\skdsk\\OneDrive\\사진");
		MyVisitor v=new MyVisitor();
		try {
			Files.walkFileTree(root, v);
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
		System.out.println("전체 크기 ="+v.size+"(Byte)");
	}

}

class MyVisitor implements FileVisitor<Path>{
	
	public static long size=0;
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		System.out.println("preVisitDirectory: " + dir);
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println("visitFile: " + file);
		size+=Files.size(file);
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("visitFileFailed: " + file);
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		System.out.println("postVisitDirectory: " + dir);
        return FileVisitResult.CONTINUE;
	}
	
}
//FileVisitor 인터페이스
//public interface FileVisitor<T> {
//	
//	FileVisitResult postVisitDirectory(T dir, IOException exc) 
//	//Invoked for a directory after entries in the directory, and all of theirdescendants, have been visited.
//	 
//	FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) 
//	//Invoked for a directory before entries in the directory are visited.
//	 
//	FileVisitResult visitFile(T file, BasicFileAttributes attrs) 
//	//Invoked for a file in a directory.
//	 
//	FileVisitResult visitFileFailed(T file, IOException exc) 
//	//Invoked for a file that could not be visited. 
//
//}


//직접 FileVisitor 인터페이스를 구현해야 하고, walkFileTree() 메소드에 구현한 객체를 전달해야 한다
//FileVisitor 구현의 각 메소드들은 디렉토리를 순회하는 동안 서로 다른 시간에 호출될 것이다.
//만약 이 메소드들 모두를 거칠 필요가 없다면, FileVisitor 인터페이스의 모든 메소드들의 기본 구현을 포함한
//SimpleFileVisitor 클래스를 확장할 수 있다.

//preVisitDirectory() 메소드는 어떤 디렉토리를 방문하기 직전에 호출된다.
//postVisitDirectory() 메소드는 디렉토리를 방문 뒤에 바로 호출된다.
//VisitFile() 메소드는 방문한 모든 파일(디렉토리 아님)에 돌아다니는 동안 호출된다.
//visitFileFailed() 메소드는 파일 방문이 실패했을 경우 호출된다.(예를 들어 올바른 권한을 갖고있지 않은 경우)
//
//위 4개의 메소드들은 FileVisitResult의 enum 객체를 반환한다.
//1. CONTINUE      = 파일 방문을 계속 할 것을 의미
//2. TERMINATE     = 파일 방문을 지금 종료해야 할 것을 의미
//3. SKIP_SIBLINGS = 파일 방문을 계속 하지만, 이 파일이나 디렉토리의 어떠한 형제에도 방문하지 않음을 의미
//4. SKIP_SUBTREE  = 파일 방문을 계속 하지만, 이 디렉토리의 전체에는 방문하지 않음을 의미한다.
//			              이 값은 preVisitDirectory()로부터 반환된 경우에만 기능을 갖고 있다.
//			              만약 나머지 다른 메소드들로 부터 반환되었다면 이는 CONTINUE로 해석될 것이다.



//Methods:
//public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor) throws IOException
//public static Path walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor) throws IOException

//Parameters:
//	start- the starting file
//	options- options to configure the traversal
//	maxDepth- the maximum number of directory levels to visit
//	visitor- the file visitor to invoke for each file 
//Returns:
//	the starting file


//사용 방법
//public static void main(String... args) throws IOException {

//    String pathString = "C:\\temp";
//    Files.walkFileTree(Paths.get(pathString), new HashSet<>(), 2, new FileVisitor<Path>() {
//        @Override
//        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
//                throws IOException {
//            System.out.println("preVisitDirectory: " + dir);
//            return FileVisitResult.CONTINUE;
//        }
//
//        @Override
//        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
//                throws IOException {
//            System.out.println("visitFile: " + file);
//            return FileVisitResult.CONTINUE;
//        }
//
//        @Override
//        public FileVisitResult visitFileFailed(Path file, IOException exc)
//                throws IOException {
//            System.out.println("visitFileFailed: " + file);
//            return FileVisitResult.CONTINUE;
//        }
//
//        @Override
//        public FileVisitResult postVisitDirectory(Path dir, IOException exc)
//                throws IOException {
//            System.out.println("postVisitDirectory: " + dir);
//            return FileVisitResult.CONTINUE;
//        }
//    });
//}