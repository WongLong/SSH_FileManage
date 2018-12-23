import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.Folder;
import com.entity.User;
import com.service.FolderService;
import com.service.UserService;

public class test {
	static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args) {
		UserService service = (UserService) applicationContext.getBean("userService");
		User u = service.query(1);
		Folder folder = new Folder("word文档", "专门放置word文件的文档", null, u.getId());
		FolderService folderService = (FolderService) applicationContext.getBean("folderService");
		folderService.add(folder);
		
//		UserService service = (UserService) applicationContext.getBean("userService");
//		User u1 = new User("admin", "admin");
//		User u2 = new User("廖卧龙", "11603080317");
//		User u3 = new User("张三", "zhangsan");
//		User u4 = new User("李四", "lisi");
//		service.add(u1);
//		service.add(u2);
//		service.add(u3);
//		service.add(u4);
	}
}
