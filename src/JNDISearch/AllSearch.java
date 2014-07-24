package JNDISearch;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * ʹ��JNDI����ȫ����
 * @author Administrator
 *
 */
public class AllSearch {
	public static void main(String[] agrs){
		try{
			//���������
			InitialDirContext ctx = new InitialDirContext();
			
			//�����ѯ��Χ
			SearchControls searchControls = new SearchControls();
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			
			//�Ӳ��������˿�ʼ����ָ�����ԵĽ��
			NamingEnumeration objs = ctx.search("ldap://ldap.wutka.com/o=wutka Consulting, dc=wutka, dc=com", "(objectClass=*)", searchControls);
			
			SearchResult match;
			Attributes attrs;
			Attribute tempAttr;
			
			//ѭ�����ҷ��ؽ��
			while(objs.hasMoreElements()){
				//ÿ�����ؽ����һ��SearchResult����
				match = (SearchResult)objs.nextElement();
				
				//��ӡ�������
				System.out.println("Found " + match.getName() + ":");
				
				//�õ��������
				attrs = match.getAttributes();
				NamingEnumeration e = attrs.getAll();
				
				//ѭ����������
				while(e.hasMoreElements()){
					tempAttr = (Attribute)e.nextElement();
					
					//��ӡ����ֵ
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
