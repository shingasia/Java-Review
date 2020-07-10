import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryEx5 {

	public static void main(String[] args) {
		
		//경로에 '.', '..' 도 포함 가능
		String a=new String("C:\\Users\\skdsk\\OneDrive\\바탕 화면\\Ex123");//복사할 파일 또는 폴더(디렉토리)의 절대경로
		String b=new String("C:\\Destination");//대상이 되는 폴더(디렉토리)의 절대경로
		
		try {
			copyTree(a, b);//a의 내용을 디렉터리 b로 복사
		} catch (Exception e) { System.out.println(e.getMessage()); }
		
	}
	
	//src로 지정된 폴더(디렉토리) 또는 파일을 dest 디렉토리 밑으로 복사
	public static void copyTree(String src, String dest) throws IOException {
		MyVisitor2 v=new MyVisitor2(src, dest);
		Files.walkFileTree(Paths.get(src), v);
	}
	
}
class MyVisitor2 implements FileVisitor<Path> {
	
	Path temp;
	String source;
	String destination;
	Path src, dest;
	
	public MyVisitor2(String source, String destination) {
		this.source = source;
		this.destination = destination;
		this.src=Paths.get(source);
		this.dest=Paths.get(destination);
		temp=null;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		
		if(!src.equals(dir)) { //현재 방문중인 디렉토리 경로(dir)와 복사하려는 src가 다른 경우
			Path dirPath=dir.subpath(src.getNameCount()-1, dir.getNameCount());//복사하려는 최상위 디렉터리 ~ 지금 현재 방문하려는 디렉터리의 경로
			
			String dirName=dir.getFileName().toString();//복사할 디렉터리 이름 저장
			temp=dir.resolve(dir.relativize(dest)).resolve(dirPath);
			Files.createDirectory(temp);
		}else {
			temp=dest.resolve(src.getFileName());
			Files.createDirectory(temp);
		}
		
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		
		if(!src.equals(file)) { //현재 방문중인 파일 경로(file)와 src가 다른 경우
			Path filePath=file.subpath(src.getNameCount()-1, file.getNameCount());//복사하려는 최상위 디렉터리 , 지금 현재 방문하는 중인 파일
			temp=file.resolve(file.relativize(dest).resolve(filePath));
			
			Files.copy(file, temp, StandardCopyOption.REPLACE_EXISTING);//파일이 이미 존재하면 덮어쓰기 옵션
		}else if(Files.isRegularFile(src)) { //디렉터리가 아니라 파일을 복사하는 경우(file==src 인 경우)
			String fileName=file.getFileName().toString();//복사할 파일의 이름 저장
			temp=src.resolve(src.relativize(dest).resolve(file.getFileName()));
			
			Files.copy(src, temp);
		}
		
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		
        return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		
        return FileVisitResult.CONTINUE;
	}
	
}