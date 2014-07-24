package JNDISearch;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

/**
 * JNDI指定名称搜索
 * @author Administrator
 *
 */
public class NameSearch {
	public static void  main(String[] agrs){
		try{
			//得到上下文
			InitialDirContext ctx = new InitialDirContext();
			
			//创建查找属性对象
			BasicAttributes searchAttrs = new BasicAttributes();
			searchAttrs.put("sn", "Tippin");
			
			//从查找树顶端开始查找指定属性的结点
			NamingEnumeration objs = ctx.search("ldap://ldap/wutka.com/o=wutka Consulting, dc=wutka, dc=com",
					searchAttrs);
			
			SearchResult match;
			Attributes attrs;
			Attribute tempAttr;
			
			//循环查找返回结果
			while(objs.hasMoreElements()){
				//每个返回结果是一个SearchResult对象
				match = (SearchResult)objs.nextElement();
				
				//打印结点名称
				System.out.println("Found " + match.getName() + ":");
				
				//得到结点属性
				attrs = match.getAttributes();
				NamingEnumeration e = attrs.getAll();
				
				//循环属性内容
				while(e.hasMoreElements()){
					tempAttr = (Attribute)e.nextElement();
					
					//打印属性值
					System.out.println(tempAttr.getID() + "=");
					for(int i=0; i<tempAttr.size(); i++){
						System.out.println(tempAttr.get());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
