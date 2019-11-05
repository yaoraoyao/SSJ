package cn.itsource.ssj.util;


import cn.itsource.ssj.query.BaseQuery;

import java.lang.reflect.Field;




public class ConditionUtils {
	private ConditionUtils(){}

	public static String getCondition(BaseQuery baseQuery){
		StringBuilder sb = new StringBuilder();
		//1.获取字节码对象
		Class<? extends BaseQuery> clz = baseQuery.getClass();
		//2.获取所有字段数组
		Field[] fields = clz.getDeclaredFields();
		Condition condition = null;
		String andOr = null, columnName = null, operation = null;
		Object value = null;//字段的值
		//3.循环遍历
		try {
			for (Field f : fields) {
				//获取当前字段上面的@Condition注解对象
				condition = f.getAnnotation(Condition.class);
				//判断condition是否为null
				if(null != condition){
					andOr = condition.andOr();
					columnName = condition.columnName();
					operation = condition.operation();
					f.setAccessible(true);
					value = f.get(baseQuery);
					if(null != value){
						sb.append(" ").append(andOr).append(" ").append(columnName).append(" ").append(operation).append(" ");
						if("like".equalsIgnoreCase(operation)){
							sb.append("'%"+value+"%'");
						}else{
							sb.append(value);
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.toString();	
	}
	

	
}
