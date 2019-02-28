package Beans.IocFactory;

import java.util.List;
import java.util.Map;

public interface ApplicationContext {

	Object getBean(String name);
	List<String> getAops();
}
