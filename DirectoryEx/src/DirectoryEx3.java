import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Iterator;
//Files, Path
public class DirectoryEx3 {

	public static void main(String[] args) {
		Path path1=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123\\apple\\kiwi");
		Path path2=Paths.get("C:","Users","skdsk","OneDrive","바탕 화면", "Ex123","banana","mango");
		try {
			Files.createDirectories(path1);//디렉터리 생성
			Files.createDirectories(path2);//디렉터리 생성
			
		} catch (IOException e) { 
			System.out.println("Error");
		}
		
		System.out.println("==========================================");
		
		Path path4=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\PExample\\hello.txt");
		
		try {
			System.out.println("디렉토리 여부: "+Files.isDirectory(path4));
			System.out.println("파일 여부: "+Files.isRegularFile(path4));
			System.out.println("마지막 수정 시간: "+Files.getLastModifiedTime(path4));
			System.out.println("파일 크기: "+Files.size(path4));//디렉토리는 크키 측정 불가
			System.out.println("소유자: "+Files.getOwner(path4));
			System.out.println("숨김 파일 여부: "+Files.isHidden(path4));
			System.out.println("읽기 가능 여부: "+Files.isReadable(path4));
			System.out.println("쓰기 가능 여부: "+Files.isWritable(path4));
		} catch (IOException e) { }
		
		System.out.println("==========================================");
		
		Path path5=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123\\apple\\kiwi\\TTT.txt");
		
		try {
			Files.createFile(path5);
		} catch (IOException e) { 
			System.out.println("Already Exist!");
		}
		
		System.out.println("==========================================");
		
		Path path6=Paths.get("C:\\Users\\skdsk\\OneDrive\\사진");
		try {
			DirectoryStream<Path> directoryStream=Files.newDirectoryStream(path6);
			for(Path path: directoryStream) {
				if(Files.isDirectory(path)) {
					System.out.println("디렉토리: "+path.getFileName());
				}else {
					System.out.println("파일: "+path.getFileName());
				}
			}
		} catch (IOException e) { }
		
		System.out.println("==========================================");
		
		Path sourcePath1=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123\\apple\\kiwi\\TTT.txt");
		Path destinationPath1=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123\\banana\\mango\\SSS.txt");
		
		try {
			Files.move(sourcePath1, destinationPath1, StandardCopyOption.REPLACE_EXISTING);//TTT.txt파일이 SSS.txt로 mango 디렉토리 밑으로 옮겨짐
		} catch (IOException e) { }
		
		
		
		
		
//		Path path7=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123\\banana\\mango");
//		//SSS.txt가 지워짐, 만약 디렉터리를 지우고 싶다면 그 디렉토리 안에는 아무것도 없어야 한다
//		try {
//			Files.delete(path7);
//		} catch (IOException e) { }
		
		
		
		Path sourcePath2=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123\\apple\\kiwi\\Copy.txt");
		Path destinationPath2=Paths.get("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123\\banana\\mango\\CopyResult.txt");
		
		try {
			Files.createFile(sourcePath2);//Copy.txt파일을 만든다
			Files.copy(sourcePath2, destinationPath2, StandardCopyOption.REPLACE_EXISTING);//이미 존재하면 덮어쓰기 옵션
		} catch (FileAlreadyExistsException e1) {
			System.out.println("파일이 이미 존재합니다");
		} catch(IOException e2) { 
			System.out.println("디렉토리가 존재하지 않습니다.");
		}
		
		System.out.println("==========================================");
		
		
//		FileVisitor 인터페이스
//		public interface FileVisitor<T> {
//			
//			FileVisitResult postVisitDirectory(T dir, IOException exc) 
//			//Invoked for a directory after entries in the directory, and all of theirdescendants, have been visited.
//			 
//			FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) 
//			//Invoked for a directory before entries in the directory are visited.
//			 
//			FileVisitResult visitFile(T file, BasicFileAttributes attrs) 
//			//Invoked for a file in a directory.
//			 
//			FileVisitResult visitFileFailed(T file, IOException exc) 
//			//Invoked for a file that could not be visited. 
//
//		}
//		
//		직접 FileVisitor 인터페이스를 구현해야 하고, walkFileTree() 메소드에 구현한 객체를 전달해야 한다
//		FileVisitor 구현의 각 메소드들은 디렉토리를 순회하는 동안 서로 다른 시간에 호출될 것이다.
//		만약 이 메소드들 모두를 거칠 필요가 없다면, FileVisitor 인터페이스의 모든 메소드들의 기본 구현을 포함한
//		SimpleFileVisitor 클래스를 확장할 수 있다.
		
//		preVisitDirectory() 메소드는 어떤 디렉토리를 방문하기 직전에 호출된다.
//		postVisitDirectory() 메소드는 디렉토리를 방문 뒤에 바로 호출된다.
//		VisitFile() 메소드는 방문한 모든 파일(디렉토리 아님)에 돌아다니는 동안 호출된다.
//		visitFileFailed() 메소드는 파일 방문이 실패했을 경우 호출된다.(예를 들어 올바른 권한을 갖고있지 않은 경우)
//		
//		위 4개의 메소드들은 FileVisitResult의 enum 객체를 반환한다.
//		1. CONTINUE      = 파일 방문을 계속 할 것을 의미
//		2. TERMINATE     = 파일 방문을 지금 종료해야 할 것을 의미
//		3. SKIP_SIBLINGS = 파일 방문을 계속 하지만, 이 파일이나 디렉토리의 어떠한 형제에도 방문하지 않음을 의미
//		4. SKIP_SUBTREE  = 파일 방문을 계속 하지만, 이 디렉토리의 전체에는 방문하지 않음을 의미한다.
//					              이 값은 preVisitDirectory()로부터 반환된 경우에만 기능을 갖고 있다.
//					              만약 나머지 다른 메소들로부터 반환되었다면 이는 CONTINUE로 해석될 것이다.
		
		
		
//		Methods:
//		public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor) throws IOException
//		public static Path walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor) throws IOException
		
//		Parameters:
//			start- the starting file
//			options- options to configure the traversal
//			maxDepth- the maximum number of directory levels to visit
//			visitor- the file visitor to invoke for each file 
//		Returns:
//			the starting file
		
		
		//사용 방법
//		public static void main(String... args) throws IOException {
		
//	        String pathString = "C:\\temp";
//	        Files.walkFileTree(Paths.get(pathString), new HashSet<>(), 2, new FileVisitor<Path>() {
//	            @Override
//	            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
//	                    throws IOException {
//	                System.out.println("preVisitDirectory: " + dir);
//	                return FileVisitResult.CONTINUE;
//	            }
//
//	            @Override
//	            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
//	                    throws IOException {
//	                System.out.println("visitFile: " + file);
//	                return FileVisitResult.CONTINUE;
//	            }
//
//	            @Override
//	            public FileVisitResult visitFileFailed(Path file, IOException exc)
//	                    throws IOException {
//	                System.out.println("visitFileFailed: " + file);
//	                return FileVisitResult.CONTINUE;
//	            }
//
//	            @Override
//	            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
//	                    throws IOException {
//	                System.out.println("postVisitDirectory: " + dir);
//	                return FileVisitResult.CONTINUE;
//	            }
//	        });
//	    }
		
		
		Path path8=Paths.get("C:\\Users\\skdsk\\OneDrive\\사진\\유튜브");
		try {
			Files.walkFileTree(path8, new HashSet<>(), 3, new FileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					System.out.println("preVisitDirectory: " + dir);
	                return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					System.out.println("visitFile: " + file);
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
				
			});
		} catch (IOException e) { 
			System.out.println(e.getMessage());
		}
	}

}
